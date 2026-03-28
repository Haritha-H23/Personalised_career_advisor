import "./Home.css";
import { useNavigate } from "react-router-dom";
// Import classic icons
import { BrainCircuit, Target, Zap, TrendingUp, Award, Users, Compass } from "lucide-react";

function Home() {
  const navigate  = useNavigate();
  const token     = localStorage.getItem("token");
  const isLoggedIn = !!token;

  return (
    <div className="home-container">
      {/* HEADER: Sticky Style */}
      <header className="main-header">
        <div className="header-inner">
          <div className="logo" onClick={() => navigate("/")}>
            <Compass className="logo-icon" /> Career<span className="accent">Advisor</span>
          </div>
          <nav className="nav-menu">
            <a href="#features" className="nav-link">Home</a>
            {isLoggedIn ? (
              <>
                <a href="/dashboard" className="nav-link">Dashboard</a>
                <a href="/assessment" className="nav-link">Assessment</a>
              </>
            ) : (
              <button className="login-pill" onClick={() => navigate("/login")}>
                Sign In
              </button>
            )}
          </nav>
        </div>
      </header>

      {/* HERO SECTION */}
      <section className="hero">
        <div className="hero-content">
          <div className="hero-badge">AI-Powered Career Guidance Platform</div>
          <h1>
            Discover Your <br />
            <span className="gradient-text">Perfect Career</span>
          </h1>
          <p className="hero-subtitle">
            Take a structured assessment, let AI analyze your strengths, and unlock 
            a personalized career roadmap with certifications, courses, and live 
            opportunities.
          </p>
          <div className="hero-buttons">
            {isLoggedIn ? (
              <button className="btn-primary large" onClick={() => navigate("/assessment")}>
                Take Assessment
              </button>
            ) : (
              <>
                <button className="btn-primary large" onClick={() => navigate("/register")}>
                  Start Free Assessment
                </button>
                <button className="btn-outline" onClick={() => navigate("/login")}>
                  Sign In
                </button>
              </>
            )}
          </div>
        </div>
      </section>

      {/* FEATURES SECTION */}
      <section id="features" className="features">
        <div className="section-header">
          <h2>Your Career Ecosystem</h2>
          <p>More than a survey — a complete AI-powered platform for career discovery.</p>
        </div>

        <div className="feature-grid">
          {[
            { icon: BrainCircuit, title: "Aptitude Assessment", desc: "25 objective MCQs across 5 cognitive domains" },
            { icon: Target, title: "Interest Profiling", desc: "10 scenario-based questions mapping your passions" },
            { icon: Zap, title: "AI Career Match", desc: "Smart recommendations based on your unique profile" },
            { icon: TrendingUp, title: "Growth Roadmap", desc: "Certifications, courses, and skill gap analysis" },
            { icon: Award, title: "Live Opportunities", desc: "Jobs, hackathons, and events tailored for you" },
            { icon: Users, title: "Alumni Network", desc: "Connect with professionals in your matched field" },
          ].map((feature, index) => (
            <div className="feature-card" key={index}>
              <div className="icon-wrapper">
                <feature.icon size={32} strokeWidth={1.5} />
              </div>
              <h3>{feature.title}</h3>
              <p>{feature.desc}</p>
            </div>
          ))}
        </div>
      </section>

      {/* CTA SECTION */}
      <section className="cta">
        <div className="cta-card">
          <h2>Ready to Find Your Path?</h2>
          <p>Join thousands of students who discovered their ideal career through CareerAdvisor’s AI-powered assessment.</p>
          <button className="btn-primary large" onClick={() => navigate(isLoggedIn ? "/assessment" : "/register")}>
            {isLoggedIn ? "Take the Assessment Now" : "Get Started Free"}
          </button>
        </div>
      </section>

      {/* FOOTER */}
      <footer className="main-footer">
        <div className="footer-bottom">
          <div className="footer-bottom-inner">
            <div className="logo">
              <Compass className="logo-icon" /> Career<span className="accent">Advisor</span>
            </div>
            <p>&copy; {new Date().getFullYear()} CareerAdvisor. AI-Powered Career Guidance.</p>
          </div>
        </div>
      </footer>
    </div>
  );
}

export default Home;