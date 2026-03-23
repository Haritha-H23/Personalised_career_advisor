import pandas as pd
from sklearn.ensemble import RandomForestClassifier
import joblib

# Load dataset
df = pd.read_csv("ml_dataset.csv")

# Keep only required columns
cols = ['logical','numerical','verbal','analytical','problem',
        'tech','creative','business','research','social','career']

df = df[cols]

VALID_KEYWORDS = [
    "developer", "engineer", "scientist", "analyst",
    "manager", "teacher", "doctor", "nurse",
    "designer", "accountant", "specialist"
]

df = df[df["career"].str.lower().str.contains("|".join(VALID_KEYWORDS))]

BAD_KEYWORDS = [
    "hydroelectric", "legislator", "rail", "mine",
    "operator", "technician", "plant"
]

df = df[~df["career"].str.lower().str.contains("|".join(BAD_KEYWORDS))]
# Split
X = df.drop("career", axis=1)
y = df["career"]

# Train model (STRONGER)
model = RandomForestClassifier(
    n_estimators=100,
    max_depth=15,
    random_state=42
)

model.fit(X, y)

# Save model
joblib.dump(model, "career_model.pkl")

print("✅ Model Trained Successfully!")