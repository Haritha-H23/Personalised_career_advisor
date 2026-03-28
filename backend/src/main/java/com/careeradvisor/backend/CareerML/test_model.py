import joblib
import numpy as np
import pandas as pd

model        = joblib.load("career_model.pkl")
le           = joblib.load("label_encoder.pkl")
FEATURE_COLS = joblib.load("feature_cols.pkl")

# Scores map exactly to assessment categories
test_cases = [
    {"name": "Tech / Developer",    "scores": [9, 9, 6, 8, 9,  9, 4, 3, 2, 7, 2]},
    {"name": "Creative / Designer", "scores": [4, 3, 7, 5, 4,  3, 9, 4, 3, 6, 3]},
    {"name": "Business / Manager",  "scores": [6, 7, 7, 6, 6,  4, 5, 9, 3, 5, 8]},
    {"name": "Healthcare / Social", "scores": [5, 5, 7, 6, 5,  3, 5, 4, 9, 4, 3]},
    {"name": "Science / Research",  "scores": [8, 9, 7, 9, 8,  6, 4, 3, 2, 9, 2]},
]
# FEATURE_COLS order: logical, numerical, verbal, analytical, problem, tech, creative, business, healthcare, research, social

for tc in test_cases:
    features = pd.DataFrame([tc["scores"]], columns=FEATURE_COLS)
    probs = model.predict_proba(features)[0]
    indices = np.argsort(probs)[::-1][:3]
    print(f"--- {tc['name']} ---")
    for rank, i in enumerate(indices, 1):
        print(f"  [{rank}] {le.classes_[i]:25s} | score: {probs[i]:.3f}")
    print()
