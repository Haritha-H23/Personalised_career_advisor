import pandas as pd

# Load files
skills = pd.read_csv("Skills.txt", sep="\t")
abilities = pd.read_csv("Abilities.txt", sep="\t")
interests = pd.read_csv("Interests.txt", sep="\t")
occupations = pd.read_csv("Occupation Data.txt", sep="\t")

# Filter important elements
skills_f = skills[skills['Element Name'].isin([
    'Mathematics','Reading Comprehension','Complex Problem Solving'
])]

abilities_f = abilities[abilities['Element Name'].isin([
    'Deductive Reasoning','Problem Sensitivity'
])]

interests_f = interests[interests['Element Name'].isin([
    'Realistic','Investigative','Artistic','Social','Enterprising'
])]

# Combine all
df = pd.concat([skills_f, abilities_f, interests_f])

# Pivot
pivot = df.pivot_table(
    index='O*NET-SOC Code',
    columns='Element Name',
    values='Data Value'
).reset_index()

# Merge with occupations
pivot = pivot.merge(
    occupations[['O*NET-SOC Code','Title']],
    on='O*NET-SOC Code'
)

# Rename columns
pivot.rename(columns={
    'Deductive Reasoning':'logical',
    'Mathematics':'numerical',
    'Reading Comprehension':'verbal',
    'Problem Sensitivity':'analytical',
    'Complex Problem Solving':'problem',
    'Realistic':'tech',
    'Investigative':'research',
    'Artistic':'creative',
    'Social':'social',
    'Enterprising':'business',
    'Title':'career'
}, inplace=True)

# Fill missing values
pivot.fillna(0, inplace=True)

# Normalize (0–10 scale)
cols = ['logical','numerical','verbal','analytical','problem',
        'tech','research','creative','social','business']

for col in cols:
    pivot[col] = (pivot[col] / 5) * 10

# Clip values (IMPORTANT FIX)
pivot[cols] = pivot[cols].clip(0, 10)

# Save
pivot.to_csv("ml_dataset.csv", index=False)

print("✅ Dataset Ready")