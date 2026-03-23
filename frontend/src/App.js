import { Routes, Route } from "react-router-dom";
import Navbar from "./Navbar/Navbar";

import Home from "./Home/Home";
import Login from "./Login/Login";
import Register from "./Register/Register";
import Dashboard from "./Dashboard/Dashboard";
import Analysis from "./Analysis/Analysis";

import "./App.css";
import Assessment from "./Assessment/Assessment";
import LoadingScreen from "./Dashboard/LoadingScreen";

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/loading" element={<LoadingScreen />} />
        <Route path="/analysis" element={<Analysis />} />
        <Route path="/assessment" element={<Assessment />} />
      </Routes>
    </>
  );
}

export default App;
