import { useEffect, useState } from "react";
import axios from "axios";
import "./Alumini.css";

const CAREERS = [
  "Software & IT", "Engineering", "Science & Research", "Healthcare",
  "Management", "Business & Finance", "Arts & Design", "Education", "Social Services"
];

function Alumni() {
  const [selected, setSelected] = useState("Software & IT");
  const [talks, setTalks]       = useState([]);
  const [loading, setLoading]   = useState(false);

  useEffect(() => {
    setLoading(true);
    const token = localStorage.getItem("token");
    axios
      .get(`http://localhost:8080/recommend/alumni?career=${selected}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((r) => setTalks(r.data))
      .catch(() => setTalks([]))
      .finally(() => setLoading(false));
  }, [selected]);

  return (
    <div className="alumni-page">
      <h1 className="alumni-title">Alumni Talks</h1>
      <p className="alumni-sub">Learn from professionals who've been there.</p>

      <div className="career-tabs">
        {CAREERS.map((c) => (
          <button
            key={c}
            className={`tab-btn ${selected === c ? "active" : ""}`}
            onClick={() => setSelected(c)}
          >
            {c}
          </button>
        ))}
      </div>

      {loading ? (
        <p className="loading-text">Loading...</p>
      ) : (
        <div className="talks-grid">
          {talks.map((t, i) => (
            <a key={i} href={t.videoUrl} target="_blank" rel="noreferrer" className="talk-card">
              <div className="talk-avatar">{t.name[0]}</div>
              <div className="talk-info">
                <h3>{t.name}</h3>
                <p className="talk-role">{t.role} @ {t.company}</p>
                <p className="talk-topic">"{t.topic}"</p>
                <span className="watch-btn">Watch Talk →</span>
              </div>
            </a>
          ))}
          {talks.length === 0 && <p className="empty">No talks available for this career yet.</p>}
        </div>
      )}
    </div>
  );
}

export default Alumni;
