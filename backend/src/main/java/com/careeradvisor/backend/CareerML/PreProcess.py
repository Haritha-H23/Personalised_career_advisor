import pandas as pd

SOC_CLUSTER = {
    "11": "Management",
    "13": "Business & Finance",
    "15": "Software & IT",
    "17": "Engineering",
    "19": "Science & Research",
    "21": "Social Services",
    "23": "Law",
    "25": "Education",
    "27": "Arts & Design",
    "29": "Healthcare",
    "31": "Healthcare Support",
    "33": "Public Safety",
    "35": "Hospitality",
    "37": "Maintenance",
    "39": "Personal Services",
    "41": "Sales",
    "43": "Administration",
    "45": "Agriculture",
    "47": "Construction",
    "49": "Mechanical & Repair",
    "51": "Manufacturing",
    "53": "Transportation",
    "55": "Military",
}

MERGE_MAP = {
    "Law":              "Administration",
    "Maintenance":      "Construction",
    "Military":         "Public Safety",
    "Agriculture":      "Science & Research",
    "Hospitality":      "Personal Services",
}

skills      = pd.read_csv("Skills.txt",          sep="\t")
abilities   = pd.read_csv("Abilities.txt",       sep="\t")
interests   = pd.read_csv("Interests.txt",       sep="\t")
occupations = pd.read_csv("Occupation Data.txt", sep="\t")

skills_f = skills[skills["Element Name"].isin([
    "Mathematics", "Reading Comprehension", "Complex Problem Solving",
    "Critical Thinking", "Active Learning"
])]
abilities_f = abilities[abilities["Element Name"].isin([
    "Deductive Reasoning", "Problem Sensitivity",
    "Inductive Reasoning", "Mathematical Reasoning"
])]
interests_f = interests[interests["Element Name"].isin([
    "Realistic", "Investigative", "Artistic",
    "Social", "Enterprising"
])]

df = pd.concat([skills_f, abilities_f, interests_f])

pivot = df.pivot_table(
    index="O*NET-SOC Code",
    columns="Element Name",
    values="Data Value"
).reset_index()

pivot = pivot.merge(occupations[["O*NET-SOC Code", "Title"]], on="O*NET-SOC Code")

pivot["career"] = pivot["O*NET-SOC Code"].str[:2].map(SOC_CLUSTER)
pivot = pivot.dropna(subset=["career"])
pivot["career"] = pivot["career"].replace(MERGE_MAP)

pivot.rename(columns={
    "Deductive Reasoning":   "logical",
    "Inductive Reasoning":   "analytical",
    "Mathematics":           "numerical",
    "Reading Comprehension": "verbal",
    "Problem Sensitivity":   "problem",
    "Complex Problem Solving": "tech",
    "Investigative":         "research",
    "Artistic":              "creative",
    "Social":                "healthcare",   # maps to healthcareInterest
    "Enterprising":          "business",
    "Realistic":             "social",       # maps to socialInterest
    "Active Learning":       "active_learning",
    "Critical Thinking":     "critical_thinking",
    "Mathematical Reasoning":"math_reasoning",
}, inplace=True)

# Exactly the 11 features the assessment produces
FEATURE_COLS = [
    "logical", "numerical", "verbal", "analytical", "problem",
    "tech", "creative", "business", "healthcare", "research", "social"
]

pivot = pivot.fillna(0)

for col in FEATURE_COLS:
    if col in pivot.columns:
        pivot[col] = (pivot[col] / 7) * 10

pivot[FEATURE_COLS] = pivot[FEATURE_COLS].clip(0, 10)

pivot[["O*NET-SOC Code", "Title", "career"] + FEATURE_COLS].to_csv("ml_dataset.csv", index=False)

print("[OK] Dataset Ready")
print(f"   Rows: {len(pivot)}, Features: {len(FEATURE_COLS)}, Classes: {pivot['career'].nunique()}")
print(pivot["career"].value_counts())
