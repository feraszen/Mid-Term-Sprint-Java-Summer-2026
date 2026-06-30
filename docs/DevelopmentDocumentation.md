📗 Development Documentation – Pharmacy Management System

1. Project Overview
The Pharmacy Management System is a Java-based OOP application designed to manage patients, doctors, medications, and prescriptions.
This document provides technical details for developers, including:
- Source code structure
- Build and run instructions
- Javadocs
- Development standards
- Database design (theoretical)
- Entity relationships
- GitHub workflow
 
-----------------
 
2. Source Code Directory Structure
Code
PharmacyManagementSystem/
│
├── src/
│   ├── Person.java
│   ├── Patient.java
│   ├── Doctor.java
│   ├── Medication.java
│   ├── Prescription.java
│   ├── MedicationTrackingSystem.java
│   └── Main.java
│
├── docs/
│   ├── UserDocumentation.md
│   ├── DevelopmentDocumentation.md
│   └── UML-Class-Diagram.png
│
└── README.md

✔ src/
Contains all Java source files.
✔ docs/
Contains documentation and UML diagrams.
✔ README.md
Project description and instructions for GitHub visitors.
 
-----------------
 
3. Build Process (Compilation & Execution)
Prerequisites
Java JDK 17 or later

Terminal / VS Code / IntelliJ

# Compile the project
Navigate to the src folder:
javac Main.java

Run the project
java Main
 
-----------------
 

4. Javadocs (Class-Level Documentation)
# Person.java

/**
 * Base class representing a general person in the system.
 * Contains shared attributes for Patient and Doctor.
 */

# Patient.java

/**
 * Represents a patient in the pharmacy.
 * Stores medications and prescriptions associated with the patient.
 */
# Doctor.java

/**
 * Represents a doctor.
 * Stores specialization and list of patients managed by the doctor.
 */

# Medication.java

/**
 * Represents a medication in the pharmacy.
 * Includes stock tracking and expiry date.
 */

# Prescription.java

/**
 * Represents a prescription issued by a doctor for a patient.
 * Automatically sets expiry date to one year after issue.
 */

# MedicationTrackingSystem.java

/**
 * Core controller class managing all system entities.
 * Provides CRUD operations, search, reporting, and linking functionality.
 */

# Main.java

/**
 * Entry point of the application.
 * Provides a menu-driven interface using Scanner.
 */
 
-----------------
 
5. Development Standards
✔ Naming Conventions
Classes: PascalCase
Methods: camelCase
Variables: camelCase
Constants: UPPER_CASE

✔ OOP Principles Used
Inheritance (Person → Patient, Doctor)
Composition (System contains lists of entities)
Encapsulation (private attributes + getters/setters)
Association (Doctor ↔ Patient, Prescription ↔ Medication)

✔ Code Quality
Clear separation of concerns
No hard-coded logic inside Main
All business logic inside MedicationTrackingSystem
Reusable methods
Clean and readable code
 
-----------------
 
6. Theoretical Database Design (ERD)
Note: The project does NOT use a real database, but documentation requires a theoretical design.

### Tables ###

## Person
Column	Type
id	    INT
name	VARCHAR
age	    INT
phone	VARCHAR

## Patient
Column	            Type
id (FK → Person.id)	INT

## Doctor
Column	            Type
id (FK → Person.id)	INT
specialization	    VARCHAR

## Medication
Column	    Type
id	        INT
name	    VARCHAR
stock	    INT
expiryDate	DATE

## Prescription
Column	                            Type
id	                                INT
doctorId (FK → Doctor.id)	        INT
patientId (FK → Patient.id)	        INT
medicationId (FK → Medication.id)	INT
issueDate	                        DATE
expiryDate	                        DATE
 
-----------------
 
7. Entity Relationship Diagram (ERD)
✔ Relationships
Person 1 → 1 Patient
Person 1 → 1 Doctor
Doctor 1 → * Patient
Patient 1 → * Prescription
Prescription 1 → 1 Medication
MedicationTrackingSystem 1 → * All Entities
 
-----------------
 
8. GitHub Workflow (Required for Marks)
Step 1: Initialize Repository
git init
git add .
git commit -m "Initial commit - Pharmacy Management System"

## Step 2: Push to GitHub
git branch -M main
git remote add origin https://github.com/<your-username>/<repo-name>.git
git push -u origin main

## Step 3: Create Branches
Examples:
git checkout -b documentation
git checkout -b uml-update
git checkout -b code-cleanup

## Step 4: Make Commits
git add .
git commit -m "Add UML diagram and documentation"
git push -u origin documentation

## Step 5: Create Pull Requests
Go to GitHub
Open PR from branch → main
Add description
Merge PR

✔ Required for grading:
Multiple branches
Multiple PRs
Organized repository
README.md
docs folder
UML diagram
 
-----------------
 
9. How to Clone the Project
Developers can clone the project using:
git clone https://github.com/<your-username>/<repo-name>.git
Then navigate to:
cd PharmacyManagementSystem/src
javac Main.java
java Main
 
-----------------
 
10. Summary
This Development Documentation provides:
Full technical overview
Build instructions
Javadocs
Coding standards
Database design
ERD
GitHub workflow

This file should be placed inside:
docs/DevelopmentDocumentation.md