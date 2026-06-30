📘 User Documentation – Pharmacy Management System

1. Overview
The Pharmacy Management System is a Java-based application designed to manage patients, doctors, medications, and prescriptions within a pharmacy environment.
The system follows Object-Oriented Programming (OOP) principles and provides a complete workflow for:

- Adding, editing, and deleting patients, doctors, and medications
- Issuing prescriptions
- Linking patients to doctors
- Tracking medication stock and expiry
- Generating detailed system reports
- Searching for entities by name
- Managing prescription history

The system is operated through a menu-driven console interface using the Scanner class.

-----------------------
 
2. System Features

✔ Add / Delete / Edit
Add new patients, doctors, or medications
Edit their details
Delete them from the system

✔ Search
Search by name:
Patient
Doctor
Medication

✔ Prescription Management
Accept a prescription from a doctor
Link prescription to patient and medication
Automatically set prescription expiry (1 year)

✔ Doctor–Patient Relationship
Assign a patient to a doctor
View all patients managed by a doctor

✔ Medication Management
Track stock
Restock medications (random amount added)
Check expired medications
Check medications expiring soon (within 30 days)

✔ Reports
Full system report
All prescriptions
Prescriptions by doctor
Patient prescriptions in the past year
Expired medications
Expired prescriptions

-----------------------
 
3. Class Descriptions

3.1 Person (Super Class)
Represents a general person in the system.
Attributes:
. ID
. Name
. Age
. Phone
. Used as a base class for:
. Patient
. Doctor

3.2 Patient
. Represents a patient in the pharmacy.
. Attributes:
. List of medications
. List of prescriptions

Functions:
. Add medication
. Add prescription
. Retrieve medication/prescription history

3.3 Doctor
. Represents a doctor.
. Attributes:
. Specialization
. List of patients

Functions:
. Add patient
. Retrieve patient list

3.4 Medication
Represents a medication.
Attributes:
. ID
. Name
. Stock
. Expiry date
Functions:
Modify stock
Retrieve medication details

3.5 Prescription
Represents a prescription issued by a doctor.
Attributes:
. ID
. Doctor
. Patient
. Medication
. Issue date
. Expiry date (1 year after issue)

3.6 MedicationTrackingSystem
The core controller of the entire application.
Manages:
. Patients
. Doctors
. Medications
. Prescriptions

Provides:
. Add / Edit / Delete
. Search
. Reports
. Linking patients to doctors
. Accepting prescriptions
. Restocking medications

-----------------------
 
4. UML Class Diagram

Place your UML image here after converting PlantUML to PNG
![UML Diagram](UML-Class-Diagram.png)
Make sure the image is stored in:
docs/UML-Class-Diagram.png

-----------------------
 
5. How to Run the Application

## Prerequisites
Java JDK 17 or later
VS Code / IntelliJ / Terminal

## Steps
1- Open terminal inside the project folder
2- Navigate to the src directory
3- Compile the project:
   javac Main.java
4- Run the project:
   java Main

-----------------------
 
6. Menu Guide (User Interface)
When you run the program, you will see a menu with options:

1. Add Patient
2. Add Doctor
3. Add Medication
4. Add Patient to Doctor
5. Accept Prescription
6. Edit Doctor
7. Delete Doctor
8. Restock Medications
9. List Patients
10. List Doctors
11. List Medications
12. List Prescriptions
13. Prescriptions by Doctor
14. Patient Prescriptions (Past Year)
15. List Expired Medications
16. Medications Expiring Soon
17. List Expired Prescriptions
18. Edit Patient
19. Full System Report
0. Exit

How to use the menu
Enter the number of the operation you want
Follow the prompts
The system will display results immediately

-----------------------
 
7. Example Workflow

## Adding a Patient
Enter Patient ID:
Enter Patient Name:
Enter Patient Age:
Enter Patient Phone:

## Accepting a Prescription
Enter Prescription ID:
Enter Doctor Name:
Enter Patient Name:
Enter Medication Name:

## Generating Full Report
Shows:
All patients
All doctors
All medications
All prescriptions

-----------------------
 
8. Summary
This documentation provides everything a user needs to understand and operate the Pharmacy Management System:
System overview
Features
Class descriptions
UML diagram
How to run the program
Menu guide
Example usage

This file should be placed inside:
docs/UserDocumentation.md
