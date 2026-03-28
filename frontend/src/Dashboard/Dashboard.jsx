import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import {
  BookOpen, Award, Briefcase, Calendar, Users,
  ExternalLink, TrendingUp, CheckCircle
} from "lucide-react";
import "./Dashboard.css";

const API = "http://localhost:8080/recommend";

const TABS = [
  { key: "courses",        label: "Courses",        icon: BookOpen  },
  { key: "certifications", label: "Certifications", icon: Award     },
  { key: "jobs",           label: "Jobs",           icon: Briefcase },
  { key: "events",         label: "Events",         icon: Calendar  },
  { key: "alumni",         label: "Alumni Talks",   icon: Users     },
];

const CAREER_DESCRIPTIONS = {
  "Software & IT":      "Design, build and maintain software systems, apps and digital infrastructure. Roles include developer, engineer, analyst and architect.",
  "Engineering":        "Apply science and math to design machines, structures and systems. Covers mechanical, electrical, civil, chemical and aerospace fields.",
  "Science & Research": "Conduct experiments and analysis to expand knowledge. Includes data science, biology, chemistry, physics and environmental science.",
  "Healthcare":         "Diagnose, treat and prevent illness to improve patient wellbeing. Roles include doctor, nurse, pharmacist and surgeon.",
  "Management":         "Plan, organise and lead teams to achieve business goals. Covers project management, operations, HR and executive leadership.",
  "Business & Finance": "Manage money, investments and business strategy. Roles include accountant, financial analyst, banker and consultant.",
  "Arts & Design":      "Create visual, digital and multimedia content. Covers graphic design, UI/UX, animation, photography and fashion.",
  "Education":          "Teach, train and mentor students of all ages. Roles include teacher, lecturer, curriculum designer and academic counselor.",
  "Social Services":    "Support individuals and communities facing challenges. Covers counseling, social work, psychology and community development.",
  "Manufacturing":      "Oversee production of goods in factories and plants. Roles include production engineer, quality manager and supply chain analyst.",
  "Construction":       "Build and maintain physical structures like buildings, roads and bridges. Covers civil, structural and project management roles.",
  "Transportation":     "Move people and goods efficiently. Covers logistics, supply chain, fleet management and operations.",
  "Mechanical & Repair":"Maintain and fix mechanical and electrical systems. Covers automotive, HVAC, industrial machinery and electronics.",
  "Administration":     "Keep organisations running smoothly through coordination and management. Covers office admin, executive support and operations.",
  "Personal Services":  "Provide direct services to individuals. Covers fitness training, event planning, beauty, hospitality and lifestyle coaching.",
  "Public Safety":      "Protect communities and maintain security. Covers law enforcement, cybersecurity, fire safety and emergency management.",
  "Healthcare Support": "Assist medical professionals in patient care. Roles include nursing assistant, medical coder, lab technician and care coordinator.",
  "Sales":              "Drive revenue by connecting customers with products and services. Covers retail, B2B sales, marketing and business development.",
};

const SkeletonCard = () => (
  <div className="skeleton-card">
    <div className="skeleton-line wide" />
    <div className="skeleton-line medium" />
    <div className="skeleton-line narrow" />
  </div>
);

const LinkCard = ({ title, sub, badge, url }) => (
  <a className="rec-card" href={url} target="_blank" rel="noreferrer">
    <div className="rec-card-body">
      <h3>{title}</h3>
      <p>{sub}</p>
    </div>
    <div className="rec-card-footer">
      {badge && <span className="rec-badge">{badge}</span>}
      <ExternalLink size={14} className="ext-icon" />
    </div>
  </a>
);

const Dashboard = () => {
  const navigate    = useNavigate();
  const isCompleted = localStorage.getItem("assessmentCompleted");
  const data        = JSON.parse(localStorage.getItem("result"));

  const recommendations = data?.prediction?.recommendations || [];

  const [selectedCareer, setSelectedCareer] = useState(null);
  const [activeTab, setActiveTab]           = useState("courses");
  const [loading, setLoading]               = useState({});
  const [tabData, setTabData]               = useState({});

  // Set default selected career once data is available
  useEffect(() => {
    if (recommendations.length > 0 && !selectedCareer) {
      setSelectedCareer(recommendations[0].career);
    }
  }, [recommendations]);

  // Fetch tab data whenever selected career or active tab changes
  useEffect(() => {
    if (!selectedCareer) return;
    const cacheKey = `${selectedCareer}__${activeTab}`;
    if (tabData[cacheKey]) return; // already fetched

    const token   = localStorage.getItem("token");
    const headers = { Authorization: `Bearer ${token}` };

    setLoading(prev => ({ ...prev, [activeTab]: true }));
    axios
      .get(`${API}/${activeTab}?career=${encodeURIComponent(selectedCareer)}`, { headers })
      .then(r => setTabData(prev => ({ ...prev, [cacheKey]: r.data })))
      .catch(err => console.error(`Failed to fetch ${activeTab}:`, err))
      .finally(() => setLoading(prev => ({ ...prev, [activeTab]: false })));
  }, [selectedCareer, activeTab]);

  if (!isCompleted || !data) {
    return (
      <div className="dashboard center">
        <div className="assessment-card">
          <TrendingUp size={48} className="empty-icon" />
          <h2>You haven't taken the assessment yet</h2>
          <p>Answer 35 questions to unlock your personalized career roadmap — courses, jobs, certifications and more.</p>
          <button onClick={() => navigate("/assessment")} className="btn-primary">
            Take Assessment Now
          </button>
        </div>
      </div>
    );
  }

  const score    = data.score;
  const selected = recommendations.find(r => r.career === selectedCareer) || recommendations[0];

  const skills = [
    { name: "Logical",         value: score.logicalScore,        max: 5 },
    { name: "Numerical",       value: score.numericalScore,      max: 5 },
    { name: "Verbal",          value: score.verbalScore,         max: 5 },
    { name: "Analytical",      value: score.analyticalScore,     max: 5 },
    { name: "Problem Solving", value: score.problemSolvingScore, max: 5 },
  ];

  const cacheKey = `${selectedCareer}__${activeTab}`;
  const items    = tabData[cacheKey] || [];

  const renderCard = (item, i) => {
    switch (activeTab) {
      case "courses":        return <LinkCard key={i} title={item.title} sub={item.platform} badge={item.level} url={item.url} />;
      case "certifications": return <LinkCard key={i} title={item.title} sub={item.issuer} url={item.url} />;
      case "jobs":           return <LinkCard key={i} title={item.title} sub={`${item.company} · ${item.location}`} url={item.url} />;
      case "events":         return <LinkCard key={i} title={item.title} sub={item.date} badge={item.type} url={item.url} />;
      case "alumni":         return <LinkCard key={i} title={item.name || item.title} sub={item.role ? `${item.role} @ ${item.company}` : item.platform} url={item.videoUrl || item.url} />;
      default:               return null;
    }
  };

  return (
    <div className="dashboard">

      {/* CAREER SELECTOR */}
      <div className="career-selector">
        <p className="selector-label">Your AI-matched careers — choose one to explore</p>
        <div className="career-cards-row">
          {recommendations.map((rec, i) => (
            <button
              key={i}
              className={`career-choice-card ${selectedCareer === rec.career ? "chosen" : ""}`}
              onClick={() => { setSelectedCareer(rec.career); setActiveTab("courses"); }}
            >
              <div className="choice-top">
                <span className="choice-rank">#{i + 1} Match</span>
                {selectedCareer === rec.career && <CheckCircle size={16} className="chosen-icon" />}
              </div>
              <h3 className="choice-title">{rec.career}</h3>
              <p className="choice-desc">{CAREER_DESCRIPTIONS[rec.career]?.split(".")[0]}.</p>
              <div className="choice-score">{(rec.score * 100).toFixed(0)}% match</div>
            </button>
          ))}
        </div>
      </div>

      {/* SELECTED CAREER HERO */}
      <div className="hero-card">
        <p className="hero-label">Exploring Career Path</p>
        <h1 className="hero-career">{selected.career}</h1>
        <div className="hero-match">{(selected.score * 100).toFixed(0)}% Match</div>
        {CAREER_DESCRIPTIONS[selected.career] && (
          <p className="hero-desc">{CAREER_DESCRIPTIONS[selected.career]}</p>
        )}
      </div>

      {/* SKILL PROFILE */}
      <div className="skills-section">
        <h2 className="section-title">
          <TrendingUp size={18} /> Skill Profile
        </h2>
        <div className="skill-bars">
          {skills.map((s, i) => {
            const pct = Math.round((s.value / s.max) * 100);
            return (
              <div className="skill-row" key={i}>
                <div className="skill-meta">
                  <span>{s.name}</span>
                  <span className="skill-score">{s.value}/{s.max}</span>
                </div>
                <div className="skill-track">
                  <div className="skill-fill" style={{ width: `${pct}%` }} />
                </div>
              </div>
            );
          })}
        </div>
      </div>

      {/* TABS */}
      <div className="tabs-section">
        <div className="tab-bar">
          {TABS.map(({ key, label, icon: Icon }) => (
            <button
              key={key}
              className={`tab-btn ${activeTab === key ? "active" : ""}`}
              onClick={() => setActiveTab(key)}
            >
              <Icon size={15} />
              {label}
            </button>
          ))}
        </div>

        <div className="tab-content">
          {loading[activeTab] ? (
            <div className="card-grid">
              {[...Array(6)].map((_, i) => <SkeletonCard key={i} />)}
            </div>
          ) : items.length === 0 ? (
            <div className="empty-tab">
              <p>No {activeTab} found for {selected.career}. Try again later.</p>
            </div>
          ) : (
            <div className="card-grid">
              {items.map((item, i) => renderCard(item, i))}
            </div>
          )}
        </div>
      </div>

    </div>
  );
};

export default Dashboard;
