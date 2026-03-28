import joblib, pandas as pd, numpy as np

model        = joblib.load("career_model.pkl")
le           = joblib.load("label_encoder.pkl")
FEATURE_COLS = joblib.load("feature_cols.pkl")

tests = [
    ("Tech/Developer",    {"logical":9,"numerical":9,"verbal":6,"analytical":8,"problem":9,"tech":9,"creative":4,"business":3,"research":7,"business_social":2,"wa_computers":9,"wa_analyze":8,"wa_decisions":8,"wa_physical":2}),
    ("Healthcare/Social", {"logical":5,"numerical":5,"verbal":7,"analytical":6,"problem":5,"tech":3,"creative":5,"business":4,"research":9,"business_social":3,"wa_caring":9,"wa_computers":3,"wa_physical":4}),
    ("Science/Research",  {"logical":8,"numerical":9,"verbal":7,"analytical":9,"problem":8,"tech":6,"creative":4,"business":3,"research":9,"business_social":2,"wa_analyze":9,"wa_computers":7,"wa_decisions":8}),
]

for name, scores in tests:
    row = {col: scores.get(col, 0) for col in FEATURE_COLS}
    probs = model.predict_proba(pd.DataFrame([row]))[0]
    idx = np.argsort(probs)[::-1][:3]
    print(f"--- {name} ---")
    for r, i in enumerate(idx, 1):
        print(f"  [{r}] {le.classes_[i]:25s} | {probs[i]:.3f}")
    print()
