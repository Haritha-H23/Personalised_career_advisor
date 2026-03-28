from flask import Flask, request, jsonify
import joblib
import numpy as np
import pandas as pd

app = Flask(__name__)
model       = joblib.load("career_model.pkl")
le          = joblib.load("label_encoder.pkl")
FEATURE_COLS = joblib.load("feature_cols.pkl")

@app.route("/predict", methods=["POST"])
def predict():
    data = request.json
    scores = {col: float(data.get(col, 0)) for col in FEATURE_COLS}
    features = pd.DataFrame([scores])

    probs = model.predict_proba(features)[0]
    indices = np.argsort(probs)[::-1]

    results = [
        {"career": le.classes_[i], "score": round(float(probs[i]), 3)}
        for i in indices[:3]
        if probs[i] > 0.05
    ]

    if not results:
        results = [{"career": "General", "score": 0.5}]

    return jsonify({"recommendations": results})

if __name__ == "__main__":
    app.run(port=5000)
