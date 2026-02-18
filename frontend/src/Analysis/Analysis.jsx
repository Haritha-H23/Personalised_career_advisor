import "./Analysis.css";

function Analysis() {
  return (
    <div className="analysis">
      <h2>Career Analysis</h2>

      <div className="progress">
        <label>Technical Skills 78%</label>
        <div className="bar">
          <div className="fill" style={{ width: "78%" }}></div>
        </div>
      </div>

      <div className="progress">
        <label>Leadership 65%</label>
        <div className="bar">
          <div className="fill" style={{ width: "65%" }}></div>
        </div>
      </div>

      <div className="progress">
        <label>Communication 82%</label>
        <div className="bar">
          <div className="fill" style={{ width: "82%" }}></div>
        </div>
      </div>
    </div>
  );
}

export default Analysis;
