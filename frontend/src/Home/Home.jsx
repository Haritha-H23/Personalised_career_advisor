import "./Home.css";

function Home() {
  return (
    <div className="hero">
      <h1>
        Your Career, <span>Personalised</span>
      </h1>
      <p>
        Get AI-driven career guidance, connect with alumni, prepare for
        certifications, and chart your path to success.
      </p>
      <div className="hero-buttons">
        <button className="primary-btn">Get Started</button>
        <button className="secondary-btn">Sign In</button>
      </div>
    </div>
  );
}

export default Home;
