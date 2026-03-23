import pandas as pd
import os

# ✅ Load dataset (safe path)
base_path = os.path.dirname(__file__)
file_path = os.path.join(base_path, "Occupation Data.txt")

df = pd.read_csv(file_path, sep="\t")

# ✅ Clean titles once (performance boost)
df["clean_title"] = df["Title"].str.lower()
df["Description"] = df["Description"].fillna("")

# 🎯 STEP 1: Better matching
def get_soc_code(job_title):
    job_title = job_title.lower()

    # partial match (both ways)
    row = df[df["clean_title"].str.contains(job_title, na=False)]

    if row.empty:
        row = df[df["clean_title"].apply(lambda x: job_title in x)]

    if not row.empty:
        return row.iloc[0]["O*NET-SOC Code"]

    return None


def get_description(job_title):
    job_title = job_title.lower()

    row = df[df["clean_title"].str.contains(job_title, na=False)]

    if not row.empty:
        return row.iloc[0]["Description"]

    return "No description available."

# 🎯 STEP 2: FULL SOC → CAREER MAPPING (NO LIMIT 🔥)
def map_soc_to_career(soc_code):
    if not soc_code:
        return "General"

    prefix = soc_code[:2]

    mapping = {
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
        "55": "Military"
    }

    return mapping.get(prefix, "General")


# 🎯 STEP 3: REFINE (MAKE IT USER FRIENDLY 🔥)
def refine_career(title, cluster):
    t = title.lower()

    if "developer" in t:
        return "Software Developer"
    elif "data scientist" in t:
        return "Data Scientist"
    elif "engineer" in t:
        return "Engineer"
    elif "scientist" in t:
        return "Scientist"
    elif "teacher" in t:
        return "Teacher"
    elif "doctor" in t or "physician" in t:
        return "Doctor"
    elif "nurse" in t:
        return "Nurse"
    elif "manager" in t:
        return "Manager"
    elif "designer" in t:
        return "Designer"

    return cluster


# 🎯 FINAL FUNCTION (USE THIS IN FLASK)
def get_career_cluster(job_title):
    soc_code = get_soc_code(job_title)

    if not soc_code:
        return "General"

    cluster = map_soc_to_career(soc_code)

    return refine_career(job_title, cluster)