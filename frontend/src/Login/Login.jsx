import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Login.css";

const Login = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({ email: "", password: "" });
  const [error, setError]       = useState("");
  const [loading, setLoading]   = useState(false);

  const handleChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      const response = await axios.post("http://localhost:8080/auth/login", formData);
      const { token, id, name } = response.data;
      localStorage.setItem("token",  token);
      localStorage.setItem("userId", id);
      localStorage.setItem("name",   name);

      // Fetch saved career result from DB
      try {
        const resultRes = await axios.get(`http://localhost:8080/assessment/career-result/${id}`);
        if (resultRes.data) {
          localStorage.setItem("result", JSON.stringify(resultRes.data));
          localStorage.setItem("assessmentCompleted", "true");
        } else {
          localStorage.removeItem("result");
          localStorage.removeItem("assessmentCompleted");
        }
      } catch {
        localStorage.removeItem("result");
        localStorage.removeItem("assessmentCompleted");
      }

      navigate("/dashboard");
    } catch (err) {
      setError(err.response?.data?.error || "Invalid email or password");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h2>Welcome Back!</h2>
        <p className="subtitle">Enter your credentials to access your account</p>

        {error && <div className="auth-error">{error}</div>}

        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <label htmlFor="email">Email</label>
            <input type="email" id="email" name="email"
              placeholder="name@company.com" onChange={handleChange} required />
          </div>
          <div className="input-group">
            <label htmlFor="password">Password</label>
            <input type="password" id="password" name="password"
              placeholder="••••••••" onChange={handleChange} required />
          </div>
          <button type="submit" className="auth-btn" disabled={loading}>
            {loading ? "Signing in..." : "Sign In"}
          </button>
        </form>

        <div className="divider"><span>or</span></div>

        <p className="switch-text">
          Don't have an account?{" "}
          <span onClick={() => navigate("/register")}>Sign Up</span>
        </p>
      </div>
    </div>
  );
};

export default Login;
