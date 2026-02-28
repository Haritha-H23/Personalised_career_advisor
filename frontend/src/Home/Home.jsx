import "./Home.css";
import { useNavigate } from "react-router-dom";

function Home() {const navigate = useNavigate();
  return (
    <div className="home-container">

      
      <section className="hero">
        <div className="hero-content">
          <h1>
            Your Career, <span>Personalised</span>
          </h1>
          <p>
            Get AI-driven career guidance, connect with alumni,
            prepare for certifications, and chart your path to success.
          </p>
          <div className="hero-buttons">
  <button
              className="primary-btn"
              onClick={() => navigate("/survey")}
            >
              Get Started →
            </button>

            <button
              className="secondary-btn"
              onClick={() => navigate("/login")}
            >
              Sign In
            </button>
</div>
        </div>
      </section>

      <section className="features">
        <h2>Everything You Need to Succeed</h2>
        <p className="features-subtext">
          Our platform combines AI analysis, expert networks, and structured learning paths.
        </p>

        <div className="feature-grid">
          <div className="feature-card">
            <h3>Career Analysis</h3>
            <p>AI-powered insights into your career trajectory and growth areas.</p>
          </div>

          <div className="feature-card">
            <h3>Alumni Network</h3>
            <p>Connect with alumni who've walked the path you aspire to.</p>
          </div>

          <div className="feature-card">
            <h3>Exam Prep</h3>
            <p>Tailored study plans and resources for certification exams.</p>
          </div>

          <div className="feature-card">
            <h3>Certifications</h3>
            <p>Discover the certifications that matter most for your goals.</p>
          </div>
        </div>
      </section>

      {/* CTA */}
      <section className="cta">
        <div className="cta-content">
          <h2>
            Ready to <span>Shape Your Future?</span>
          </h2>
          <p>
            Join thousands of professionals who’ve accelerated their careers
            with personalised AI-driven guidance.
          </p>
          <button className="cta-btn">
            Take the Career Survey →
          </button>
        </div>
      </section>

      {/* FOOTER */}
      <footer className="footer">
        <div className="footer-container">
          <div className="footer-brand">
            <h3>
              Career<span>Advisor</span>
            </h3>
            <p>
              AI-powered career guidance to help you grow,
              connect, and succeed.
            </p>
          </div>

          <div className="footer-links">
            <div>
             <a href="/dashboard">Dashboard</a>
             <a href="/alumini">Alumni</a>
             <a href="/certifications">Certifications</a>
             <a href="/exams">Exams</a>
            </div>

            <div>
              <h4>Company</h4>
              <a href="/about">About</a>
              <a href="/contact">Contact</a>
              <a href="/privacy">Privacy Policy</a>
            </div>
          </div>
        </div>

        <div className="footer-bottom">
          © {new Date().getFullYear()} CareerAdvisor. All rights reserved.
        </div>
      </footer>

    </div>
  );
}

export default Home;