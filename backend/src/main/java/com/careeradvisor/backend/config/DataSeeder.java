package com.careeradvisor.backend.config;

import com.careeradvisor.backend.entity.*;
import com.careeradvisor.backend.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CourseRepository courseRepo;
    private final CertificationRepository certRepo;
    private final JobRepository jobRepo;
    private final EventRepository eventRepo;
    private final AlumniTalkRepository alumniRepo;

    public DataSeeder(CourseRepository courseRepo, CertificationRepository certRepo,
                      JobRepository jobRepo, EventRepository eventRepo,
                      AlumniTalkRepository alumniRepo) {
        this.courseRepo = courseRepo;
        this.certRepo   = certRepo;
        this.jobRepo    = jobRepo;
        this.eventRepo  = eventRepo;
        this.alumniRepo = alumniRepo;
    }

    @Override
    public void run(String... args) {
        if (courseRepo.count() > 0 && certRepo.count() > 0
                && jobRepo.count() > 0 && eventRepo.count() > 0
                && alumniRepo.count() > 0) return;

        // ── COURSES ──────────────────────────────────────────────
        seedCourse("Software & IT", "The Complete Web Developer Bootcamp", "Udemy", "https://www.udemy.com/course/the-complete-web-development-bootcamp/", "Beginner");
        seedCourse("Software & IT", "CS50: Introduction to Computer Science", "edX", "https://www.edx.org/course/introduction-computer-science-harvardx-cs50x", "Beginner");
        seedCourse("Software & IT", "Data Structures and Algorithms", "Coursera", "https://www.coursera.org/specializations/data-structures-algorithms", "Intermediate");
        seedCourse("Software & IT", "Full Stack Open", "University of Helsinki", "https://fullstackopen.com/en/", "Intermediate");
        seedCourse("Software & IT", "System Design for Interviews", "Educative", "https://www.educative.io/courses/grokking-the-system-design-interview", "Advanced");

        seedCourse("Engineering", "Introduction to Engineering", "Coursera", "https://www.coursera.org/learn/engineering", "Beginner");
        seedCourse("Engineering", "AutoCAD Essential Training", "LinkedIn Learning", "https://www.linkedin.com/learning/autocad-essential-training", "Beginner");
        seedCourse("Engineering", "Mechanical Engineering Fundamentals", "edX", "https://www.edx.org/course/mechanical-engineering", "Intermediate");
        seedCourse("Engineering", "Circuit Design & PCB Layout", "Udemy", "https://www.udemy.com/course/pcb-design/", "Intermediate");
        seedCourse("Engineering", "MATLAB & Simulink for Engineers", "MathWorks", "https://matlabacademy.mathworks.com/", "Advanced");

        seedCourse("Science & Research", "Introduction to Data Science", "Coursera", "https://www.coursera.org/specializations/introduction-data-science", "Beginner");
        seedCourse("Science & Research", "Research Methods & Statistics", "edX", "https://www.edx.org/course/research-methods", "Beginner");
        seedCourse("Science & Research", "Machine Learning by Andrew Ng", "Coursera", "https://www.coursera.org/learn/machine-learning", "Intermediate");
        seedCourse("Science & Research", "Bioinformatics Specialization", "Coursera", "https://www.coursera.org/specializations/bioinformatics", "Advanced");

        seedCourse("Healthcare", "Introduction to Clinical Medicine", "Coursera", "https://www.coursera.org/learn/clinical-medicine", "Beginner");
        seedCourse("Healthcare", "Anatomy Specialization", "Coursera", "https://www.coursera.org/specializations/anatomy", "Beginner");
        seedCourse("Healthcare", "Medical Terminology", "edX", "https://www.edx.org/course/medical-terminology", "Beginner");
        seedCourse("Healthcare", "Pharmacology Fundamentals", "Coursera", "https://www.coursera.org/learn/pharmacology", "Intermediate");

        seedCourse("Management", "Business Foundations Specialization", "Coursera", "https://www.coursera.org/specializations/wharton-business-foundations", "Beginner");
        seedCourse("Management", "Leadership and Management", "edX", "https://www.edx.org/course/leadership-management", "Intermediate");
        seedCourse("Management", "MBA Core Curriculum", "Coursera", "https://www.coursera.org/specializations/mba-core", "Advanced");

        seedCourse("Business & Finance", "Financial Markets by Yale", "Coursera", "https://www.coursera.org/learn/financial-markets-global", "Beginner");
        seedCourse("Business & Finance", "Accounting Fundamentals", "edX", "https://www.edx.org/course/accounting-fundamentals", "Beginner");
        seedCourse("Business & Finance", "Investment Management", "Coursera", "https://www.coursera.org/specializations/investment-management", "Intermediate");

        seedCourse("Arts & Design", "Graphic Design Specialization", "Coursera", "https://www.coursera.org/specializations/graphic-design", "Beginner");
        seedCourse("Arts & Design", "UI/UX Design Bootcamp", "Udemy", "https://www.udemy.com/course/ui-ux-design-bootcamp/", "Beginner");
        seedCourse("Arts & Design", "Motion Graphics & Animation", "Skillshare", "https://www.skillshare.com/browse/motion-graphics", "Intermediate");

        seedCourse("Education", "Teaching as a Profession", "Coursera", "https://www.coursera.org/learn/teaching-profession", "Beginner");
        seedCourse("Education", "Curriculum Design & Development", "edX", "https://www.edx.org/course/curriculum-design", "Intermediate");

        seedCourse("Social Services", "Introduction to Psychology", "Coursera", "https://www.coursera.org/learn/introduction-psychology", "Beginner");
        seedCourse("Social Services", "Social Work Practice", "edX", "https://www.edx.org/course/social-work", "Intermediate");

        seedCourse("Manufacturing", "Lean Manufacturing Fundamentals", "Coursera", "https://www.coursera.org/learn/lean-manufacturing", "Beginner");
        seedCourse("Manufacturing", "Industrial Automation & PLC Programming", "Udemy", "https://www.udemy.com/course/plc-programming/", "Intermediate");
        seedCourse("Manufacturing", "Six Sigma Yellow Belt", "edX", "https://www.edx.org/course/six-sigma-yellow-belt", "Beginner");
        seedCourse("Manufacturing", "Supply Chain Management", "Coursera", "https://www.coursera.org/specializations/supply-chain-management", "Intermediate");
        seedCourse("Manufacturing", "Quality Management Systems (ISO 9001)", "Alison", "https://alison.com/course/iso-9001", "Advanced");

        seedCourse("Construction", "Construction Project Management", "Coursera", "https://www.coursera.org/learn/construction-project-management", "Beginner");
        seedCourse("Construction", "AutoCAD for Civil Engineers", "Udemy", "https://www.udemy.com/course/autocad-civil/", "Intermediate");
        seedCourse("Construction", "Building Information Modeling (BIM)", "edX", "https://www.edx.org/course/bim", "Intermediate");

        seedCourse("Transportation", "Logistics & Supply Chain Fundamentals", "Coursera", "https://www.coursera.org/learn/logistics", "Beginner");
        seedCourse("Transportation", "Fleet Management", "Alison", "https://alison.com/course/fleet-management", "Intermediate");

        seedCourse("Mechanical & Repair", "Automotive Engineering Fundamentals", "Udemy", "https://www.udemy.com/course/automotive-engineering/", "Beginner");
        seedCourse("Mechanical & Repair", "HVAC Systems Design", "Coursera", "https://www.coursera.org/learn/hvac", "Intermediate");

        seedCourse("Administration", "Office Administration & Management", "Alison", "https://alison.com/course/office-administration", "Beginner");
        seedCourse("Administration", "Business Communication", "Coursera", "https://www.coursera.org/learn/business-communication", "Beginner");

        seedCourse("Personal Services", "Professional Grooming & Styling", "Skillshare", "https://www.skillshare.com/browse/styling", "Beginner");
        seedCourse("Personal Services", "Event Planning & Management", "Udemy", "https://www.udemy.com/course/event-planning/", "Intermediate");

        seedCourse("Public Safety", "Emergency Management Fundamentals", "FEMA", "https://training.fema.gov/", "Beginner");
        seedCourse("Public Safety", "Cybersecurity Fundamentals", "Coursera", "https://www.coursera.org/specializations/cyber-security", "Intermediate");

        seedCourse("Healthcare Support", "Medical Coding & Billing", "Alison", "https://alison.com/course/medical-coding", "Beginner");
        seedCourse("Healthcare Support", "Patient Care Technician", "Coursera", "https://www.coursera.org/learn/patient-care", "Beginner");

        // ── CERTIFICATIONS ───────────────────────────────────────
        seedCert("Software & IT", "AWS Certified Solutions Architect", "Amazon Web Services", "https://aws.amazon.com/certification/certified-solutions-architect-associate/");
        seedCert("Software & IT", "Google Associate Cloud Engineer", "Google", "https://cloud.google.com/certification/cloud-engineer");
        seedCert("Software & IT", "Meta Front-End Developer Certificate", "Meta", "https://www.coursera.org/professional-certificates/meta-front-end-developer");
        seedCert("Software & IT", "Oracle Java SE Certification", "Oracle", "https://education.oracle.com/java-se-certification");

        seedCert("Engineering", "Certified Professional Engineer (PE)", "NCEES", "https://ncees.org/engineering/pe/");
        seedCert("Engineering", "Six Sigma Green Belt", "ASQ", "https://asq.org/cert/six-sigma-green-belt");
        seedCert("Engineering", "AutoCAD Certified Professional", "Autodesk", "https://www.autodesk.com/certification/");

        seedCert("Science & Research", "Google Data Analytics Certificate", "Google", "https://www.coursera.org/professional-certificates/google-data-analytics");
        seedCert("Science & Research", "IBM Data Science Certificate", "IBM", "https://www.coursera.org/professional-certificates/ibm-data-science");
        seedCert("Science & Research", "TensorFlow Developer Certificate", "Google", "https://www.tensorflow.org/certificate");

        seedCert("Healthcare", "Basic Life Support (BLS)", "American Heart Association", "https://www.heart.org/en/cpr/bls-certification");
        seedCert("Healthcare", "Certified Medical Assistant (CMA)", "AAMA", "https://www.aama-ntl.org/cma-aama-exam");
        seedCert("Healthcare", "USMLE Step 1", "NBME", "https://www.usmle.org/");

        seedCert("Management", "PMP - Project Management Professional", "PMI", "https://www.pmi.org/certifications/project-management-pmp");
        seedCert("Management", "Certified ScrumMaster (CSM)", "Scrum Alliance", "https://www.scrumalliance.org/get-certified/scrum-master-track/certified-scrummaster");

        seedCert("Business & Finance", "CFA - Chartered Financial Analyst", "CFA Institute", "https://www.cfainstitute.org/en/programs/cfa");
        seedCert("Business & Finance", "CPA - Certified Public Accountant", "AICPA", "https://www.aicpa.org/becomeacpa");

        seedCert("Arts & Design", "Adobe Certified Professional", "Adobe", "https://www.adobe.com/products/certify.html");
        seedCert("Arts & Design", "Google UX Design Certificate", "Google", "https://www.coursera.org/professional-certificates/google-ux-design");

        seedCert("Education", "CTET - Central Teacher Eligibility Test", "CBSE", "https://ctet.nic.in/");
        seedCert("Education", "Google Certified Educator", "Google", "https://edu.google.com/intl/ALL_in/for-educators/certification/");

        seedCert("Manufacturing", "Certified Manufacturing Engineer (CMfgE)", "SME", "https://www.sme.org/education/certifications/");
        seedCert("Manufacturing", "Six Sigma Green Belt", "ASQ", "https://asq.org/cert/six-sigma-green-belt");
        seedCert("Manufacturing", "Lean Six Sigma Black Belt", "IASSC", "https://www.iassc.org/six-sigma-certification/");
        seedCert("Manufacturing", "ISO 9001 Lead Auditor", "CQI & IRCA", "https://www.quality.org/");

        seedCert("Construction", "PMP - Project Management Professional", "PMI", "https://www.pmi.org/certifications/project-management-pmp");
        seedCert("Construction", "LEED Green Associate", "USGBC", "https://www.usgbc.org/credentials/leed-green-associate");
        seedCert("Construction", "AutoCAD Certified Professional", "Autodesk", "https://www.autodesk.com/certification/");

        seedCert("Transportation", "Certified Supply Chain Professional (CSCP)", "APICS", "https://www.ascm.org/certifications/cscp/");
        seedCert("Transportation", "Certified Logistics Associate (CLA)", "MSSC", "https://www.msscusa.org/");

        seedCert("Mechanical & Repair", "ASE Automotive Certification", "ASE", "https://www.ase.com/");
        seedCert("Mechanical & Repair", "HVAC Excellence Certification", "HVAC Excellence", "https://www.hvacexcellence.org/");

        seedCert("Administration", "Certified Administrative Professional (CAP)", "PACE", "https://www.pace.org/");
        seedCert("Administration", "Microsoft Office Specialist", "Microsoft", "https://learn.microsoft.com/en-us/certifications/mos-certification/");

        seedCert("Public Safety", "Certified Protection Professional (CPP)", "ASIS", "https://www.asisonline.org/certification/certified-protection-professional-cpp/");
        seedCert("Public Safety", "CompTIA Security+", "CompTIA", "https://www.comptia.org/certifications/security");

        seedCert("Healthcare Support", "Certified Nursing Assistant (CNA)", "NNAAP", "https://www.pearsonvue.com/nnaap/");
        seedCert("Healthcare Support", "Certified Medical Coder (CPC)", "AAPC", "https://www.aapc.com/certification/cpc/");

        // ── JOBS ─────────────────────────────────────────────────
        seedJob("Software & IT", "Software Engineer", "Google", "Bangalore, India", "https://careers.google.com/");
        seedJob("Software & IT", "Full Stack Developer", "Infosys", "Hyderabad, India", "https://www.infosys.com/careers/");
        seedJob("Software & IT", "Backend Developer", "Flipkart", "Bangalore, India", "https://www.flipkartcareers.com/");
        seedJob("Software & IT", "DevOps Engineer", "Amazon", "Remote", "https://www.amazon.jobs/");
        seedJob("Software & IT", "Mobile App Developer", "Swiggy", "Bangalore, India", "https://careers.swiggy.com/");

        seedJob("Engineering", "Mechanical Engineer", "Tata Motors", "Pune, India", "https://www.tatamotors.com/careers/");
        seedJob("Engineering", "Civil Engineer", "L&T Construction", "Chennai, India", "https://www.larsentoubro.com/careers/");
        seedJob("Engineering", "Electrical Engineer", "BHEL", "New Delhi, India", "https://www.bhel.com/careers");
        seedJob("Engineering", "Aerospace Engineer", "ISRO", "Bangalore, India", "https://www.isro.gov.in/careers");

        seedJob("Science & Research", "Data Scientist", "Microsoft", "Hyderabad, India", "https://careers.microsoft.com/");
        seedJob("Science & Research", "Research Scientist", "DRDO", "New Delhi, India", "https://www.drdo.gov.in/careers");
        seedJob("Science & Research", "ML Engineer", "Razorpay", "Bangalore, India", "https://razorpay.com/jobs/");
        seedJob("Science & Research", "Data Analyst", "Zomato", "Gurugram, India", "https://www.zomato.com/careers");

        seedJob("Healthcare", "MBBS Doctor", "AIIMS", "New Delhi, India", "https://www.aiims.edu/");
        seedJob("Healthcare", "Staff Nurse", "Apollo Hospitals", "Chennai, India", "https://www.apollohospitals.com/careers/");
        seedJob("Healthcare", "Pharmacist", "Sun Pharma", "Mumbai, India", "https://www.sunpharma.com/careers");

        seedJob("Management", "Product Manager", "Paytm", "Noida, India", "https://paytm.com/careers/");
        seedJob("Management", "Operations Manager", "Reliance Industries", "Mumbai, India", "https://www.ril.com/careers");
        seedJob("Management", "HR Manager", "Wipro", "Bangalore, India", "https://careers.wipro.com/");

        seedJob("Business & Finance", "Financial Analyst", "HDFC Bank", "Mumbai, India", "https://www.hdfcbank.com/careers");
        seedJob("Business & Finance", "Investment Banker", "Goldman Sachs", "Bangalore, India", "https://www.goldmansachs.com/careers/");
        seedJob("Business & Finance", "Chartered Accountant", "Deloitte", "Mumbai, India", "https://www2.deloitte.com/in/en/careers.html");

        seedJob("Arts & Design", "UI/UX Designer", "Zomato", "Gurugram, India", "https://www.zomato.com/careers");
        seedJob("Arts & Design", "Graphic Designer", "Canva", "Remote", "https://www.canva.com/careers/");
        seedJob("Arts & Design", "Motion Designer", "Hotstar", "Mumbai, India", "https://careers.hotstar.com/");

        seedJob("Education", "School Teacher", "Narayana Group", "Hyderabad, India", "https://www.narayanagroup.com/careers/");
        seedJob("Education", "Academic Counselor", "BYJU'S", "Bangalore, India", "https://byjus.com/careers/");

        seedJob("Social Services", "Counseling Psychologist", "iCall", "Mumbai, India", "https://icallhelpline.org/");
        seedJob("Social Services", "NGO Program Manager", "CRY India", "New Delhi, India", "https://www.cry.org/careers/");

        seedJob("Manufacturing", "Production Engineer", "Tata Steel", "Jamshedpur, India", "https://www.tatasteel.com/careers/");
        seedJob("Manufacturing", "Quality Control Manager", "Maruti Suzuki", "Gurugram, India", "https://www.marutisuzuki.com/corporate/careers");
        seedJob("Manufacturing", "Plant Manager", "Hindustan Unilever", "Mumbai, India", "https://www.hul.co.in/careers/");
        seedJob("Manufacturing", "Process Engineer", "Reliance Industries", "Jamnagar, India", "https://www.ril.com/careers");
        seedJob("Manufacturing", "Supply Chain Analyst", "Asian Paints", "Mumbai, India", "https://www.asianpaints.com/careers");

        seedJob("Construction", "Site Engineer", "L&T Construction", "Mumbai, India", "https://www.larsentoubro.com/careers/");
        seedJob("Construction", "Structural Engineer", "Shapoorji Pallonji", "Mumbai, India", "https://www.shapoorjipallonji.com/careers");
        seedJob("Construction", "Project Manager", "DLF Limited", "Gurugram, India", "https://www.dlf.in/careers");

        seedJob("Transportation", "Logistics Manager", "Blue Dart", "Mumbai, India", "https://www.bluedart.com/careers");
        seedJob("Transportation", "Supply Chain Manager", "Delhivery", "Gurugram, India", "https://www.delhivery.com/careers");
        seedJob("Transportation", "Fleet Operations Manager", "Ola", "Bangalore, India", "https://www.olacabs.com/careers");

        seedJob("Mechanical & Repair", "Automobile Service Engineer", "Bosch India", "Bangalore, India", "https://www.bosch.in/careers/");
        seedJob("Mechanical & Repair", "HVAC Technician", "Blue Star", "Mumbai, India", "https://www.bluestarindia.com/careers");
        seedJob("Mechanical & Repair", "Maintenance Engineer", "Siemens India", "Pune, India", "https://www.siemens.com/in/en/company/jobs.html");

        seedJob("Administration", "Office Administrator", "TCS", "Chennai, India", "https://www.tcs.com/careers");
        seedJob("Administration", "Executive Assistant", "HCL Technologies", "Noida, India", "https://www.hcltech.com/careers");

        seedJob("Personal Services", "Event Manager", "Wizcraft", "Mumbai, India", "https://www.wizcraft.com/careers");
        seedJob("Personal Services", "Fitness Trainer", "Cult.fit", "Bangalore, India", "https://www.cult.fit/careers");

        seedJob("Public Safety", "Security Analyst", "Wipro CyberSecurity", "Bangalore, India", "https://careers.wipro.com/");
        seedJob("Public Safety", "Fire Safety Officer", "ONGC", "Mumbai, India", "https://www.ongcindia.com/careers");

        seedJob("Healthcare Support", "Medical Coder", "Omega Healthcare", "Chennai, India", "https://www.omegahealthcare.com/careers");
        seedJob("Healthcare Support", "Nursing Assistant", "Fortis Healthcare", "New Delhi, India", "https://www.fortishealthcare.com/careers");

        // ── EVENTS ───────────────────────────────────────────────
        seedEvent("Software & IT", "Smart India Hackathon", "Hackathon", "Dec 2025", "https://www.sih.gov.in/");
        seedEvent("Software & IT", "Google Summer of Code", "Open Source", "May 2025", "https://summerofcode.withgoogle.com/");
        seedEvent("Software & IT", "HackWithInfy", "Hackathon", "Aug 2025", "https://hackwithinfy.com/");
        seedEvent("Software & IT", "Meta Hacker Cup", "Competition", "Sep 2025", "https://www.facebook.com/codingcompetitions/hacker-cup/");
        seedEvent("Software & IT", "GitHub Universe", "Conference", "Oct 2025", "https://githubuniverse.com/");

        seedEvent("Engineering", "ASME Student Design Competition", "Competition", "Mar 2025", "https://www.asme.org/events/competitions/student-design-competition");
        seedEvent("Engineering", "NASA Space Apps Challenge", "Hackathon", "Oct 2025", "https://www.spaceappschallenge.org/");
        seedEvent("Engineering", "IEEE TechSym", "Conference", "Nov 2025", "https://ieee.org/conferences/");

        seedEvent("Science & Research", "Kaggle Competitions", "Competition", "Ongoing", "https://www.kaggle.com/competitions");
        seedEvent("Science & Research", "International Science Olympiad", "Competition", "Jan 2026", "https://www.iso.org/");
        seedEvent("Science & Research", "NeurIPS Conference", "Conference", "Dec 2025", "https://neurips.cc/");

        seedEvent("Healthcare", "National Medical Olympiad", "Competition", "Feb 2026", "https://www.nmo.org.in/");
        seedEvent("Healthcare", "Health Hackathon India", "Hackathon", "Jul 2025", "https://healthhackathon.in/");

        seedEvent("Management", "Harvard Business Case Competition", "Competition", "Mar 2026", "https://www.hbs.edu/");
        seedEvent("Management", "CII Young Leaders Summit", "Conference", "Nov 2025", "https://www.cii.in/");

        seedEvent("Business & Finance", "CFA Research Challenge", "Competition", "Jan 2026", "https://www.cfainstitute.org/en/research/research-challenge");
        seedEvent("Business & Finance", "NSE Invest-O-Mania", "Competition", "Sep 2025", "https://www.nseindia.com/");

        seedEvent("Arts & Design", "Adobe Design Achievement Awards", "Competition", "Oct 2025", "https://www.behance.net/adaa");
        seedEvent("Arts & Design", "UX India Conference", "Conference", "Nov 2025", "https://ux-india.org/");

        seedEvent("Education", "National Teaching Innovation Award", "Competition", "Jan 2026", "https://www.education.gov.in/");

        seedEvent("Social Services", "Youth for Social Change Hackathon", "Hackathon", "Aug 2025", "https://youthforsocialchange.org/");

        seedEvent("Manufacturing", "CII Manufacturing Summit", "Conference", "Nov 2025", "https://www.cii.in/");
        seedEvent("Manufacturing", "India Manufacturing Show", "Exhibition", "Feb 2026", "https://www.indiamanufacturingshow.com/");
        seedEvent("Manufacturing", "National Quality Conclave", "Conference", "Jan 2026", "https://www.qualitycouncil.in/");

        seedEvent("Construction", "ACETECH Construction Expo", "Exhibition", "Dec 2025", "https://www.acetechindia.com/");
        seedEvent("Construction", "Smart Cities India Expo", "Conference", "May 2026", "https://www.smartcitiesindia.com/");

        seedEvent("Transportation", "Logistics & Supply Chain Summit", "Conference", "Mar 2026", "https://www.logisticssummit.in/");
        seedEvent("Transportation", "India Warehousing Show", "Exhibition", "Jun 2026", "https://www.indiawarehousingshow.com/");

        seedEvent("Mechanical & Repair", "Auto Expo India", "Exhibition", "Jan 2026", "https://www.autoexpoindia.com/");
        seedEvent("Mechanical & Repair", "HVAC India Expo", "Exhibition", "Apr 2026", "https://www.hvacindiaexpo.com/");

        seedEvent("Public Safety", "India Security Expo", "Exhibition", "Sep 2025", "https://www.indiasecurityexpo.com/");
        seedEvent("Public Safety", "Cyber Security Summit India", "Conference", "Oct 2025", "https://www.cybersecuritysummit.in/");

        seedEvent("Healthcare Support", "Medical Coding Conference India", "Conference", "Feb 2026", "https://www.aapc.com/events/");

        seedEvent("Administration", "Office Management Summit", "Conference", "Mar 2026", "https://www.officemanagement.in/");

        // ── ALUMNI TALKS ─────────────────────────────────────────
        seedAlumni("Software & IT", "Sundar Pichai", "CEO", "Google", "From IIT to Google CEO — My Journey", "https://www.youtube.com/watch?v=example1");
        seedAlumni("Software & IT", "Sridhar Vembu", "CEO", "Zoho", "Building a Product Company from India", "https://www.youtube.com/watch?v=example2");
        seedAlumni("Software & IT", "Nandan Nilekani", "Co-Founder", "Infosys", "Technology and Nation Building", "https://www.youtube.com/watch?v=example3");

        seedAlumni("Engineering", "Kiran Kumar", "Former Chairman", "ISRO", "Space Engineering — Challenges and Triumphs", "https://www.youtube.com/watch?v=example4");
        seedAlumni("Engineering", "Anand Mahindra", "Chairman", "Mahindra Group", "Engineering the Future of India", "https://www.youtube.com/watch?v=example5");

        seedAlumni("Science & Research", "Tessy Thomas", "Director General", "DRDO", "Women in Science and Missile Technology", "https://www.youtube.com/watch?v=example6");
        seedAlumni("Science & Research", "Venki Ramakrishnan", "Nobel Laureate", "MRC Cambridge", "The Path to a Nobel Prize in Science", "https://www.youtube.com/watch?v=example7");

        seedAlumni("Healthcare", "Devi Shetty", "Founder", "Narayana Health", "Making Healthcare Affordable for All", "https://www.youtube.com/watch?v=example8");
        seedAlumni("Healthcare", "Soumya Swaminathan", "Chief Scientist", "WHO", "Global Health and Medical Research", "https://www.youtube.com/watch?v=example9");

        seedAlumni("Management", "Indra Nooyi", "Former CEO", "PepsiCo", "Leadership Lessons from the Top", "https://www.youtube.com/watch?v=example10");
        seedAlumni("Management", "Rajiv Bajaj", "MD", "Bajaj Auto", "Strategy, Brand and Business Excellence", "https://www.youtube.com/watch?v=example11");

        seedAlumni("Business & Finance", "Uday Kotak", "Founder", "Kotak Mahindra Bank", "Building a Bank from Scratch", "https://www.youtube.com/watch?v=example12");
        seedAlumni("Business & Finance", "Radhakishan Damani", "Founder", "DMart", "Value Investing and Wealth Creation", "https://www.youtube.com/watch?v=example13");

        seedAlumni("Arts & Design", "Sabyasachi Mukherjee", "Founder", "Sabyasachi", "Creativity, Culture and Fashion Design", "https://www.youtube.com/watch?v=example14");
        seedAlumni("Arts & Design", "Prasoon Joshi", "CEO", "McCann India", "Advertising, Art and Storytelling", "https://www.youtube.com/watch?v=example15");

        seedAlumni("Education", "Byju Raveendran", "Founder", "BYJU'S", "Reimagining Education with Technology", "https://www.youtube.com/watch?v=example16");

        seedAlumni("Social Services", "Anshu Gupta", "Founder", "Goonj", "Social Entrepreneurship and Impact", "https://www.youtube.com/watch?v=example17");

        seedAlumni("Manufacturing", "Baba Kalyani", "Chairman", "Bharat Forge", "Building a World-Class Manufacturing Company", "https://www.youtube.com/watch?v=example18");
        seedAlumni("Manufacturing", "Pawan Munjal", "CEO", "Hero MotoCorp", "Innovation in Indian Manufacturing", "https://www.youtube.com/watch?v=example19");

        seedAlumni("Construction", "A.M. Naik", "Chairman Emeritus", "L&T", "Building India's Infrastructure", "https://www.youtube.com/watch?v=example20");

        seedAlumni("Transportation", "Sahil Barua", "CEO", "Delhivery", "Disrupting Logistics in India", "https://www.youtube.com/watch?v=example21");

        seedAlumni("Mechanical & Repair", "Ratan Tata", "Chairman Emeritus", "Tata Group", "Engineering Excellence and Innovation", "https://www.youtube.com/watch?v=example22");

        seedAlumni("Administration", "Nirmala Sitharaman", "Finance Minister", "Government of India", "Public Administration and Policy Making", "https://www.youtube.com/watch?v=example23");

        seedAlumni("Public Safety", "Kiran Bedi", "Former IPS Officer", "Government of India", "Leadership in Public Safety and Governance", "https://www.youtube.com/watch?v=example24");

        seedAlumni("Healthcare Support", "Anu Aga", "Former CEO", "Thermax", "Healthcare Support and Social Responsibility", "https://www.youtube.com/watch?v=example25");

        seedAlumni("Personal Services", "Ritesh Agarwal", "Founder", "OYO", "Building a Hospitality Empire", "https://www.youtube.com/watch?v=example26");

        System.out.println("[OK] Seed data loaded.");
    }

    private void seedCourse(String career, String title, String platform, String url, String level) {
        Course c = new Course();
        c.setCareer(career); c.setTitle(title); c.setPlatform(platform);
        c.setUrl(url); c.setLevel(level);
        courseRepo.save(c);
    }

    private void seedCert(String career, String title, String issuer, String url) {
        Certification c = new Certification();
        c.setCareer(career); c.setTitle(title); c.setIssuer(issuer); c.setUrl(url);
        certRepo.save(c);
    }

    private void seedJob(String career, String title, String company, String location, String url) {
        Job j = new Job();
        j.setCareer(career); j.setTitle(title); j.setCompany(company);
        j.setLocation(location); j.setUrl(url);
        jobRepo.save(j);
    }

    private void seedEvent(String career, String title, String type, String date, String url) {
        Event e = new Event();
        e.setCareer(career); e.setTitle(title); e.setType(type);
        e.setDate(date); e.setUrl(url);
        eventRepo.save(e);
    }

    private void seedAlumni(String career, String name, String role, String company, String topic, String videoUrl) {
        AlumniTalk a = new AlumniTalk();
        a.setCareer(career); a.setName(name); a.setRole(role);
        a.setCompany(company); a.setTopic(topic); a.setVideoUrl(videoUrl);
        alumniRepo.save(a);
    }
}
