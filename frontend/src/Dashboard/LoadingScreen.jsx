import { useEffect, useState } from "react";
import {
  FiCpu,
  FiBarChart2,
  FiSettings,
  FiTarget,
  FiZap
} from "react-icons/fi";
import "./LoadingScreen.css";

const LoadingScreen = () => {

  const messages = [
    "Analyzing your skills...",
    "Matching with career data...",
    "Evaluating your strengths...",
    "Finding best career paths...",
    "Finalizing recommendations..."
  ];

 const icons = [
  <FiCpu />,
  <FiBarChart2 />,
  <FiSettings />,
  <FiTarget />,
  <FiZap />
];

  const [index, setIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setIndex((prev) => (prev + 1) % messages.length);
    }, 1200);

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="loading-container">

      <div className="loading-box">
        <div className="icon">{icons[index]}</div>
        <h2 className="text">{messages[index]}</h2>

        <div className="loader-bar">
          <div className="progress"></div>
        </div>
      </div>

    </div>
  );
};

export default LoadingScreen;