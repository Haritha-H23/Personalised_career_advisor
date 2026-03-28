import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./Navbar/Navbar";

import Home from "./Home/Home";
import Login from "./Login/Login";
import Register from "./Register/Register";
import Dashboard from "./Dashboard/Dashboard";
import Analysis from "./Analysis/Analysis";
import Alumni from "./Alumini/Alumini";

import "./App.css";
import Assessment from "./Assessment/Assessment";
import LoadingScreen from "./Dashboard/LoadingScreen";

const ProtectedRoute = ({ children }) => {
  const token = localStorage.getItem("token");
  return token ? children : <Navigate to="/login" replace />;
};

const PublicRoute = ({ children }) => {
  const token = localStorage.getItem("token");
  return token ? <Navigate to="/dashboard" replace /> : children;
};

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login"    element={<PublicRoute><Login /></PublicRoute>} />
        <Route path="/register" element={<PublicRoute><Register /></PublicRoute>} />
        <Route path="/dashboard"  element={<ProtectedRoute><Dashboard /></ProtectedRoute>} />
        <Route path="/loading"    element={<ProtectedRoute><LoadingScreen /></ProtectedRoute>} />
        <Route path="/analysis"   element={<ProtectedRoute><Analysis /></ProtectedRoute>} />
        <Route path="/assessment" element={<ProtectedRoute><Assessment /></ProtectedRoute>} />
        <Route path="/alumni"     element={<ProtectedRoute><Alumni /></ProtectedRoute>} />
      </Routes>
    </>
  );
}

export default App;
