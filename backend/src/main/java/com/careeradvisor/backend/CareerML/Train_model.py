import pandas as pd
import joblib
from lightgbm import LGBMClassifier
from imblearn.over_sampling import SMOTE
from sklearn.model_selection import train_test_split, StratifiedKFold, cross_val_score
from sklearn.metrics import classification_report
from sklearn.preprocessing import LabelEncoder

FEATURE_COLS = [
    "logical", "numerical", "verbal", "analytical", "problem",
    "tech", "creative", "business", "healthcare", "research", "social"
]

df = pd.read_csv("ml_dataset.csv")
df = df[FEATURE_COLS + ["career"]].dropna()

counts = df["career"].value_counts()
df = df[df["career"].isin(counts[counts >= 5].index)]

print(f"Samples: {len(df)}, Features: {len(FEATURE_COLS)}, Classes: {df['career'].nunique()}")
print(df["career"].value_counts())

le = LabelEncoder()
X = df[FEATURE_COLS].values
y = le.fit_transform(df["career"])

min_neighbors = min(5, df["career"].value_counts().min() - 1)
X_res, y_res = SMOTE(k_neighbors=min_neighbors, random_state=42).fit_resample(X, y)
print(f"\nAfter SMOTE: {len(X_res)} samples")

X_train, X_test, y_train, y_test = train_test_split(
    X_res, y_res, test_size=0.2, random_state=42, stratify=y_res
)

model = LGBMClassifier(
    n_estimators=500,
    learning_rate=0.05,
    max_depth=8,
    num_leaves=63,
    min_child_samples=5,
    class_weight="balanced",
    random_state=42,
    verbose=-1,
)
model.fit(X_train, y_train)

print("\n--- Test Set Evaluation ---")
print(classification_report(y_test, model.predict(X_test), target_names=le.classes_))

cv_scores = cross_val_score(model, X_res, y_res,
                            cv=StratifiedKFold(n_splits=5, shuffle=True, random_state=42),
                            scoring="accuracy")
print(f"5-Fold CV Accuracy: {cv_scores.mean():.3f} (+/- {cv_scores.std():.3f})")

joblib.dump(model,        "career_model.pkl")
joblib.dump(le,           "label_encoder.pkl")
joblib.dump(FEATURE_COLS, "feature_cols.pkl")
print("\n[OK] Model saved!")
