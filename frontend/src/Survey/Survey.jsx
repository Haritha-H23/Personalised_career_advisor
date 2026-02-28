import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Survey.css";

function Survey() {
  const [interest, setInterest] = useState("");
  const navigate = useNavigate();

  const handleSubmit = () => {
    localStorage.setItem("interest", interest);
    navigate("/dashboard");
  };

  return (
    <div className="survey-container">
      <div className="survey-card">
        <h2>Career Survey</h2>
        <select onChange={(e) => setInterest(e.target.value)}>
          <option value="">Select Interest</option>
          <option value="alumni">Industry Jobs</option>
          <option value="certifications">Certifications</option>
          <option value="analysis">Skill Analysis</option>
          <option value="exams">Competitive Exams</option>
        </select>
        <button onClick={handleSubmit}>Submit</button>
      </div>
    </div>
  );
}

export default Survey;
