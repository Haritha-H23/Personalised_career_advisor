import { useNavigate, useLocation } from "react-router-dom";
import { Compass, LogOut, User } from "lucide-react";
import "./Navbar.css";

function Navbar() {
  const navigate  = useNavigate();
  const location  = useLocation();
  const token          = localStorage.getItem("token");
  const name           = localStorage.getItem("name");
  const isLoggedIn     = !!token;
  const tookAssessment = !!localStorage.getItem("assessmentCompleted");

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    localStorage.removeItem("name");
    localStorage.removeItem("result");
    localStorage.removeItem("assessmentCompleted");
    navigate("/");
  };

  return (
    <header className="main-header">
      <div className="header-inner">
        <div className="logo" onClick={() => navigate("/")}>
          <Compass className="logo-icon" /> Career<span className="accent">Advisor</span>
        </div>

        <nav className="nav-menu">
          <a href="/" className={`nav-link ${location.pathname === "/" ? "active-link" : ""}`}>Home</a>

          {isLoggedIn && (
            <>
              <a href="/dashboard" className={`nav-link ${location.pathname === "/dashboard" ? "active-link" : ""}`}>Dashboard</a>
              {!tookAssessment && (
                <a href="/assessment" className={`nav-link ${location.pathname === "/assessment" ? "active-link" : ""}`}>Assessment</a>
              )}
              <a href="/alumni" className={`nav-link ${location.pathname === "/alumni" ? "active-link" : ""}`}>Alumni</a>
            </>
          )}

          {isLoggedIn ? (
            <div className="user-menu">
              <div className="user-pill">
                <User size={14} />
                <span>{name || "User"}</span>
              </div>
              <button className="logout-pill" onClick={handleLogout}>
                <LogOut size={14} /> Logout
              </button>
            </div>
          ) : (
            <button className="login-pill" onClick={() => navigate("/login")}>
              Sign In
            </button>
          )}
        </nav>
      </div>
    </header>
  );
}

export default Navbar;
