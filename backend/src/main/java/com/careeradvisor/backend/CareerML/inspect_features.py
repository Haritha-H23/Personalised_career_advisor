import pandas as pd

wa = pd.read_csv("Work Activities.txt", sep="\t")
print("Work Activities elements (sample):")
print(wa["Element Name"].unique()[:20])
print()

ed = pd.read_csv("Education, Training, and Experience.txt", sep="\t")
print("Education columns:", ed.columns.tolist())
print(ed["Element Name"].unique()[:10])
