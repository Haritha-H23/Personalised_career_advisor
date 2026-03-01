import { Link } from "react-router-dom";
import "./Navbar.css";
import { useNavigate } from "react-router-dom";
import { Compass } from "lucide-react";

function Navbar() {
  const navigate = useNavigate();
  return (
    <header className="main-header">
        <div className="header-inner">
          <div className="logo" onClick={() => navigate("/")}>
            <Compass className="logo-icon" /> Career<span className="accent">Advisor</span>
          </div>
          <nav className="nav-menu">
            <a href="/" className="nav-link">Home</a>
            <a href="/dashboard" className="nav-link">Dashboard</a>
            <a href="/dashboard" className="nav-link">Assesment</a>
            <button className="login-pill" onClick={() => navigate("/login")}>
              Sign In
            </button>
          </nav>
        </div>
      </header>
  );
}

export default Navbar;
