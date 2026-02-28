import { Routes, Route } from "react-router-dom";
import Navbar from "./Navbar/Navbar";

import Home from "./Home/Home";
import Login from "./Login/Login";
import Register from "./Register/Register";
import Dashboard from "./Dashboard/Dashboard";
import Analysis from "./Analysis/Analysis";
import Survey from "./Survey/Survey";

import "./App.css";

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/analysis" element={<Analysis />} />
        <Route path="/survey" element={<Survey />} />
      </Routes>
    </>
  );
}

export default App;
