import pandas as pd
df = pd.read_csv("ml_dataset.csv")
print("Shape:", df.shape)
print("Columns:", list(df.columns))
print("\nCareer value counts (top 30):")
print(df["career"].value_counts().head(30))
print("\nStats:")
print(df[["logical","numerical","verbal","analytical","problem","tech","creative","business","research","social"]].describe())
