from flask import Flask, request, jsonify
import joblib
import numpy as np
from onet_mapper import get_career_cluster, get_description   # your mapper file

app = Flask(__name__)
model = joblib.load("career_model.pkl")

@app.route("/predict", methods=["POST"])
def predict():
    data = request.json

    # Input features
    scores = {
        "logical": data.get("logical", 0),
        "numerical": data.get("numerical", 0),
        "verbal": data.get("verbal", 0),
        "analytical": data.get("analytical", 0),
        "problem": data.get("problem", 0),
        "tech": data.get("tech", 0),
        "creative": data.get("creative", 0),
        "business": data.get("business", 0),
        "research": data.get("research", 0),
        "social": data.get("social", 0)
    }

    features = [list(scores.values())]

    probs = model.predict_proba(features)[0]
    classes = model.classes_

    # Sort highest probability first
    indices = np.argsort(probs)[::-1]

    results = []
    seen = set()

    for i in indices:
        raw_career = classes[i]
        mapped = get_career_cluster(raw_career)

        # avoid duplicates
        if mapped not in seen and probs[i] > 0.05:
            seen.add(mapped)

            results.append({
                "career": mapped,
                "actual_role": raw_career,
                "score": float(probs[i]),
                "description": get_description(raw_career)
            })

        if len(results) == 3:
            break

    # fallback
    if not results:
        results.append({
            "career": "General Career",
            "score": 0.5
        })

    return jsonify({
        "recommendations": results
    })

if __name__ == "__main__":
    app.run(port=5000)