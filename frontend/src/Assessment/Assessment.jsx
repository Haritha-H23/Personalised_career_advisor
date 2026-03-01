import React, { useEffect, useState } from "react";
import axios from "axios";
import { FaBrain, FaHeart, FaArrowRight, FaArrowLeft } from "react-icons/fa"; // Imported icons
import "./Assessment.css";

const Assessment = () => {
  const [questions, setQuestions] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [answers, setAnswers] = useState({});
  const [assessmentStarted, setAssessmentStarted] = useState(false);

  useEffect(() => {
    fetchQuestions();
  }, []);

  const fetchQuestions = async () => {
    try {
      const token = localStorage.getItem("token");
      const res = await axios.get("http://localhost:8080/assessment/questions", {
        headers: { Authorization: `Bearer ${token}` }
      });
      setQuestions(res.data);
    } catch (error) {
      console.error("Error fetching questions", error);
    }
  };

  const handleOptionSelect = (questionId, option) => {
    setAnswers({
      ...answers,
      [questionId]: option
    });
  };

  const handleNext = () => {
    if (currentIndex < questions.length - 1) {
      setCurrentIndex(currentIndex + 1);
    }
  };

  const handleSubmit = async () => {
    const userId = localStorage.getItem("userId");
    const formattedAnswers = Object.keys(answers).map((qId) => ({
      userId: userId,
      questionId: qId,
      selectedAnswer: answers[qId]
    }));

    try {
        const token = localStorage.getItem("token");
      await axios.post(
  `http://localhost:8080/assessment/submit/${userId}`,
  formattedAnswers,
  {
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
);
      alert("Assessment Submitted Successfully!");
    } catch (error) {
      console.error("Submission failed", error);
    }
  };

  if (questions.length === 0) return <div className="loading">Loading...</div>;

  // STAGE 1: Landing/Instruction Screen
  if (!assessmentStarted) {
    return (
      <div className="assessment-container">
        <div className="question-card landing-card">
          <div className="icon-box"><FaBrain /></div> {/* Updated to Icon */}
          <h1>Career Assessment</h1>
          <p className="description">
            Complete 35 questions across two sections to discover your ideal career path. Takes about 15–20 minutes.
          </p>
          <div className="test-types">
            <div className="test-type">
              <div className="icon"><FaBrain /></div> {/* Updated to Icon */}
              <h3>Aptitude Test</h3>
              <p>25 objective MCQs across Logical, Numerical, Verbal, Analytical, and Problem Solving</p>
            </div>
            <div className="test-type">
              <div className="icon"><FaHeart /></div> {/* Updated to Icon */}
              <h3>Interest Test</h3>
              <p>10 scenario-based questions to map your passions to career domains</p>
            </div>
          </div>
          <button className="begin-btn" onClick={() => setAssessmentStarted(true)}>
            Begin Assessment
          </button>
        </div>
      </div>
    );
  }

  // STAGE 2: Actual Assessment Questions
  const question = questions[currentIndex];
  const progressPercentage = ((currentIndex + 1) / questions.length) * 100;

  return (
    <div className="assessment-container">
      <div className="question-card">
        <div className="header-meta">
          <span className="test-title">Aptitude Test</span>
          <span className="page-number">{currentIndex + 1} / {questions.length}</span>
        </div>
        
        {/* Visual Progress Bar */}
        <div className="progress-bar-container">
          <div className="progress-bar-fill" style={{ width: `${progressPercentage}%` }}></div>
        </div>
        
        <p className="section-subtitle">Logical Reasoning</p>

        <h3 className="question-text">{question.questionText}</h3>

        <div className="options">
          {["A", "B", "C", "D"].map((opt) => (
            <button
              key={opt}
              className={`option-btn ${
                answers[question.id] === opt ? "selected" : ""
              }`}
              onClick={() => handleOptionSelect(question.id, opt)}
            >
              <span className="option-letter">{opt}</span> {question[`option${opt}`]}
            </button>
          ))}
        </div>

        <div className="action-row">
          <button className="prev-btn" onClick={() => setCurrentIndex(Math.max(0, currentIndex - 1))} disabled={currentIndex === 0}>
            <FaArrowLeft /> Previous {/* Updated to Icon */}
          </button>
          {currentIndex < questions.length - 1 ? (
            <button className="next-btn" onClick={handleNext}>
              Next <FaArrowRight /> {/* Updated to Icon */}
            </button>
          ) : (
            <button className="submit-btn" onClick={handleSubmit}>
              Submit Assessment
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default Assessment;