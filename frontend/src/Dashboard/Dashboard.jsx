import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

const Dashboard = () => {
  const navigate = useNavigate();

  const isCompleted = localStorage.getItem("assessmentCompleted");
  const data = JSON.parse(localStorage.getItem("result"));

  if (!isCompleted || !data) {
    return (
      <div className="dashboard center">
        <div className="assessment-card">
          <h2>Start Your Career Assessment</h2>
          <p>Unlock personalized career insights.</p>
          <button onClick={() => navigate("/assessment")} className="btn-primary">
            Take Assessment
          </button>
        </div>
      </div>
    );
  }

  const score = data.score;
  const recommendations = data.prediction.recommendations;
  const topCareer = recommendations[0];

  return (
    <div className="dashboard">

      <h1 className="title">Career Dashboard</h1>

      {/* 🔥 HERO CARD */}
      <div className="hero-card">
        <h2>Your Best Match</h2>
        <h1>{topCareer.career}</h1>

        <p className="role">{topCareer.actual_role}</p>

        <div className="badge">
          {(topCareer.score * 100).toFixed(0)}% Match
        </div>

        <p className="description">
          {topCareer.description}
        </p>
        {console.log(topCareer.description)}
      </div>

      {/* 🔥 OTHER CAREERS */}
      <h2 className="section-title">Other Recommendations</h2>

      <div className="card-grid">
  {recommendations.slice(1).map((item, index) => (
    <div className="career-card" key={index}>
      <h3>{item.career}</h3>

      <p className="role">{item.actual_role}</p>

      <div className="mini-badge">
        {(item.score * 100).toFixed(0)}%
      </div>

      <p className="description small">
        {item.description}
      </p>
    </div>
  ))}
</div>

      {/* 🔥 SKILLS */}
      <h2 className="section-title">Skill Profile</h2>

      <div className="skills">
        {[
          { name: "Logical", value: score.logicalScore },
          { name: "Numerical", value: score.numericalScore },
          { name: "Verbal", value: score.verbalScore },
          { name: "Analytical", value: score.analyticalScore },
          { name: "Problem Solving", value: score.problemSolvingScore }
        ].map((item, index) => (
          <div className="skill-card" key={index}>
            <span>{item.name}</span>
            <strong>{item.value}</strong>
          </div>
        ))}
      </div>

    </div>
  );
};

export default Dashboard;