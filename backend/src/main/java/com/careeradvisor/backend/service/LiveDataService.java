package com.careeradvisor.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class LiveDataService {

    private static final Logger log = LoggerFactory.getLogger(LiveDataService.class);

    @Value("${adzuna.app.id}")
    private String adzunaAppId;

    @Value("${adzuna.app.key}")
    private String adzunaAppKey;

    @Value("${youtube.api.key}")
    private String youtubeApiKey;

    @Value("${google.search.api.key}")
    private String googleSearchKey;

    @Value("${google.search.cx}")
    private String googleSearchCx;

    private final RestTemplate restTemplate = new RestTemplate();

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    // ── JOBS via Adzuna ───────────────────────────────────────────────────────
    public List<Map<String, String>> fetchJobs(String career) {
        try {
            String url = "https://api.adzuna.com/v1/api/jobs/in/search/1"
                + "?app_id=" + adzunaAppId
                + "&app_key=" + adzunaAppKey
                + "&what=" + encode(career)
                + "&where=India"
                + "&results_per_page=6";

            Map response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

            List<Map<String, String>> jobs = new ArrayList<>();
            for (Map<String, Object> r : results) {
                Map<String, String> job = new HashMap<>();
                job.put("title",    (String) r.get("title"));
                job.put("company",  (String) ((Map<?, ?>) r.get("company")).get("display_name"));
                job.put("location", (String) ((Map<?, ?>) r.get("location")).get("display_name"));
                job.put("url",      (String) r.get("redirect_url"));
                jobs.add(job);
            }
            return jobs;
        } catch (Exception e) {
            log.error("Adzuna jobs failed: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // ── COURSES via YouTube ───────────────────────────────────────────────────
    public List<Map<String, String>> fetchCourses(String career) {
        return fetchYouTubeVideos(career + " course tutorial for beginners", 6);
    }

    // ── CERTIFICATIONS via Google Custom Search ───────────────────────────────
    public List<Map<String, String>> fetchCertifications(String career) {
        try {
            String url = "https://www.googleapis.com/customsearch/v1"
                + "?key=" + googleSearchKey
                + "&cx=" + googleSearchCx
                + "&num=6"
                + "&q=" + encode(career + " professional certification");

            Map response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");

            List<Map<String, String>> certs = new ArrayList<>();
            for (Map<String, Object> item : items) {
                Map<String, String> cert = new HashMap<>();
                cert.put("title",  (String) item.get("title"));
                cert.put("issuer", (String) item.get("displayLink"));
                cert.put("url",    (String) item.get("link"));
                certs.add(cert);
            }
            return certs;
        } catch (Exception e) {
            log.error("Google Search certs failed: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // ── ALUMNI TALKS via YouTube ──────────────────────────────────────────────
    public List<Map<String, String>> fetchAlumniTalks(String career) {
        List<Map<String, String>> videos = fetchYouTubeVideos(career + " career talk alumni success story India", 5);
        List<Map<String, String>> alumni = new ArrayList<>();
        for (Map<String, String> v : videos) {
            Map<String, String> a = new HashMap<>();
            a.put("name",     v.get("platform").replace("YouTube — ", ""));
            a.put("role",     career + " Professional");
            a.put("company",  "YouTube");
            a.put("topic",    v.get("title"));
            a.put("videoUrl", v.get("url"));
            alumni.add(a);
        }
        return alumni;
    }

    // ── HACKATHONS via Devpost ────────────────────────────────────────────────
    public List<Map<String, String>> fetchHackathons(String career) {
        try {
            String url = "https://devpost.com/api/hackathons"
                + "?challenge_filter=open"
                + "&order_by=deadline"
                + "&search=" + encode(career);

            Map response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> hackathons = (List<Map<String, Object>>) response.get("hackathons");

            List<Map<String, String>> events = new ArrayList<>();
            for (Map<String, Object> h : hackathons.subList(0, Math.min(5, hackathons.size()))) {
                Map<String, String> event = new HashMap<>();
                event.put("title", (String) h.get("title"));
                event.put("type",  "Hackathon");
                event.put("date",  String.valueOf(h.get("submission_period_dates")));
                event.put("url",   (String) h.get("url"));
                events.add(event);
            }
            return events;
        } catch (Exception e) {
            log.error("Devpost hackathons failed: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // ── SHARED: YouTube search ────────────────────────────────────────────────
    private List<Map<String, String>> fetchYouTubeVideos(String query, int maxResults) {
        try {
            String url = "https://www.googleapis.com/youtube/v3/search"
                + "?part=snippet"
                + "&type=video"
                + "&relevanceLanguage=en"
                + "&maxResults=" + maxResults
                + "&key=" + youtubeApiKey
                + "&q=" + encode(query);

            Map response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");

            List<Map<String, String>> videos = new ArrayList<>();
            for (Map<String, Object> item : items) {
                Map<String, Object> snippet = (Map<String, Object>) item.get("snippet");
                Map<String, Object> idMap   = (Map<String, Object>) item.get("id");
                String videoId = (String) idMap.get("videoId");
                if (videoId == null) continue;

                Map<String, String> video = new HashMap<>();
                video.put("title",    (String) snippet.get("title"));
                video.put("platform", "YouTube — " + snippet.get("channelTitle"));
                video.put("url",      "https://www.youtube.com/watch?v=" + videoId);
                video.put("level",    "Free");
                videos.add(video);
            }
            return videos;
        } catch (Exception e) {
            log.error("YouTube search failed: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}
