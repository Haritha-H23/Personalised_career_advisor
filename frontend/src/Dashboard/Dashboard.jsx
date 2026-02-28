import "./Dashboard.css";

function Dashboard() {
  return (
    <div className="dashboard">
      <h2>Dashboard</h2>

      <div className="cards">
        <div className="card">
          <h3>Courses Completed</h3>
          <p>12</p>
        </div>

        <div className="card">
          <h3>Certifications</h3>
          <p>3</p>
        </div>

        <div className="card">
          <h3>Alumni Connections</h3>
          <p>28</p>
        </div>

        <div className="card">
          <h3>Career Score</h3>
          <p>87%</p>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
