import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * MedicationTrackingSystem is the core controller of the pharmacy system.
 * It manages patients, doctors, medications, and prescriptions.
 * Old behavior is preserved, and new OOP improvements are added.
 */
public class MedicationTrackingSystem {

    // ===== LISTS (OLD FIELDS, KEPT) =====
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Medication> medications = new ArrayList<>();
    private ArrayList<Prescription> prescriptions = new ArrayList<>();

    // ===== FILE PATHS (UPDATED TO USE data/ FOLDER) =====
    private final String PATIENT_FILE = "data/patients.txt";
    private final String DOCTOR_FILE = "data/doctors.txt";
    private final String MEDICATION_FILE = "data/medications.txt";
    private final String PRESCRIPTION_FILE = "data/prescriptions.txt";

    /**
     * Loads all data from files when the system starts.
     */
    public void loadAllData() {
        loadPatients();
        loadDoctors();
        loadMedications();
        loadPrescriptions();
    }

    /**
     * Saves all data to files when the system exits.
     */
    public void saveAllData() {
        savePatients();
        saveDoctors();
        saveMedications();
        savePrescriptions();
    }

    // ============================================================
    // ===================== LOAD METHODS ==========================
    // ============================================================

    /**
     * Loads all patients from data/patients.txt
     * Format: id,name,age,phone
     */
    private void loadPatients() {
        patients.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_FILE))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length != 4) continue;

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String phone = parts[3];

                patients.add(new Patient(id, name, age, phone));
            }

        } catch (Exception e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
    }

    /**
     * Loads all doctors from data/doctors.txt
     * Format: id,name,age,phone,specialization
     */
    private void loadDoctors() {
        doctors.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(DOCTOR_FILE))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length != 5) continue;

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String phone = parts[3];
                String spec = parts[4];

                doctors.add(new Doctor(id, name, age, phone, spec));
            }

        } catch (Exception e) {
            System.out.println("Error loading doctors: " + e.getMessage());
        }
    }

    /**
     * Loads all medications from data/medications.txt
     * Format: id,name,dose,stock,expiryDate
     * expiryDate format: dd/MM/yyyy
     */
    private void loadMedications() {
        medications.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(MEDICATION_FILE))) {

            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length != 5) continue;

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String dose = parts[2];
                int stock = Integer.parseInt(parts[3]);
                Date expiry = sdf.parse(parts[4]);

                medications.add(new Medication(id, name, stock, expiry, dose));
            }

        } catch (Exception e) {
            System.out.println("Error loading medications: " + e.getMessage());
        }
    }

    /**
     * Loads all prescriptions from data/prescriptions.txt
     * Format: id,doctorId,patientId,medicationId,issueDate,expiryDate
     * Dates format: dd/MM/yyyy
     */
    private void loadPrescriptions() {
        prescriptions.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(PRESCRIPTION_FILE))) {

            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length != 6) continue;

                int id = Integer.parseInt(parts[0]);
                int doctorId = Integer.parseInt(parts[1]);
                int patientId = Integer.parseInt(parts[2]);
                int medicationId = Integer.parseInt(parts[3]);
                Date issue = sdf.parse(parts[4]);
                Date expiry = sdf.parse(parts[5]);

                // Old constructor using IDs only (kept)
                prescriptions.add(new Prescription(id, doctorId, patientId, medicationId, issue, expiry));
            }

        } catch (Exception e) {
            System.out.println("Error loading prescriptions: " + e.getMessage());
        }
    }

    // ============================================================
    // ===================== SAVE METHODS ==========================
    // ============================================================

    /**
     * Saves all patients to data/patients.txt
     * Format: id,name,age,phone
     */
    private void savePatients() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PATIENT_FILE))) {

            for (Patient p : patients) {
                pw.println(p.getId() + "," +
                           p.getName() + "," +
                           p.getAge() + "," +
                           p.getPhone());
            }

        } catch (Exception e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
    }

    /**
     * Saves all doctors to data/doctors.txt
     * Format: id,name,age,phone,specialization
     */
    private void saveDoctors() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DOCTOR_FILE))) {

            for (Doctor d : doctors) {
                pw.println(d.getId() + "," +
                           d.getName() + "," +
                           d.getAge() + "," +
                           d.getPhone() + "," +
                           d.getSpecialization());
            }

        } catch (Exception e) {
            System.out.println("Error saving doctors: " + e.getMessage());
        }
    }

    /**
     * Saves all medications to data/medications.txt
     * Format: id,name,dose,stock,expiryDate
     */
    private void saveMedications() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(MEDICATION_FILE))) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Medication m : medications) {
                pw.println(m.getId() + "," +
                           m.getName() + "," +
                           m.getDose() + "," +
                           m.getStock() + "," +
                           sdf.format(m.getExpiryDate()));
            }

        } catch (Exception e) {
            System.out.println("Error saving medications: " + e.getMessage());
        }
    }

    /**
     * Saves all prescriptions to data/prescriptions.txt
     * Format: id,doctorId,patientId,medicationId,issueDate,expiryDate
     */
    private void savePrescriptions() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PRESCRIPTION_FILE))) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Prescription p : prescriptions) {
                pw.println(p.getId() + "," +
                           p.getDoctorId() + "," +
                           p.getPatientId() + "," +
                           p.getMedicationId() + "," +
                           sdf.format(p.getIssueDate()) + "," +
                           sdf.format(p.getExpiryDate()));
            }

        } catch (Exception e) {
            System.out.println("Error saving prescriptions: " + e.getMessage());
        }
    }

    // ============================================================
    // ===================== ADD METHODS ===========================
    // ============================================================

    /**
     * Adds a new patient to the system.
     */
    public void addPatient(Patient p) {
        patients.add(p);
        savePatients();
    }

    /**
     * Adds a new doctor to the system.
     */
    public void addDoctor(Doctor d) {
        doctors.add(d);
        saveDoctors();
    }

    /**
     * Adds a new medication to the system.
     */
    public void addMedication(Medication m) {
        medications.add(m);
        saveMedications();
    }

    /**
     * Adds a new prescription to the system.
     * Uses object references and also links to patient and doctor.
     */
    public void addPrescription(Prescription p) {
        prescriptions.add(p);

        if (p.getPatient() != null) {
            p.getPatient().addPrescription(p);
        }

        if (p.getDoctor() != null) {
            p.getDoctor().addPrescription(p);
        }

        savePrescriptions();
    }

    // ============================================================
    // ===================== SEARCH METHODS ========================
    // ============================================================

    public Patient findPatientById(int id) {
        for (Patient p : patients) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Doctor findDoctorById(int id) {
        for (Doctor d : doctors) {
            if (d.getId() == id) return d;
        }
        return null;
    }

    public Medication findMedicationById(int id) {
        for (Medication m : medications) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    public Patient findPatientByName(String name) {
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    public Doctor findDoctorByName(String name) {
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(name)) return d;
        }
        return null;
    }

    public Medication findMedicationByName(String name) {
        for (Medication m : medications) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

// ============================================================
// ===================== EDIT / DELETE =========================
// ============================================================

/**
 * Edits doctor information.
 */
public void editDoctor(int id, String name, int age, String phone, String spec) {
    Doctor d = findDoctorById(id);
    if (d != null) {
        d.setName(name);
        d.setAge(age);
        d.setPhone(phone);
        d.setSpecialization(spec);
        saveDoctors();
    }
}

/**
 * Edits patient information.
 */
public void editPatient(int id, String name, int age, String phone) {
    Patient p = findPatientById(id);
    if (p != null) {
        p.setName(name);
        p.setAge(age);
        p.setPhone(phone);
        savePatients();
    }
}

/**
 * Deletes a doctor by ID.
 */
public void deleteDoctor(int id) {
    doctors.removeIf(d -> d.getId() == id);
    saveDoctors();
}

/**
 * Deletes a prescription by ID.
 */
public void deletePrescription(int presId) {

    Prescription target = null;

    // Find the prescription
    for (Prescription p : prescriptions) {
        if (p.getId() == presId) {
            target = p;
            break;
        }
    }

    if (target == null) {
        System.out.println("Prescription not found.");
        return;
    }

    // Remove from main list
    prescriptions.remove(target);

    // Remove from doctor
    Doctor d = findDoctorById(target.getDoctorId());
    if (d != null) {
        d.getPrescriptions().remove(target);
    }

    // Remove from patient
    Patient pat = findPatientById(target.getPatientId());
    if (pat != null) {
        pat.getPrescriptions().remove(target);
    }

    // Save file
    savePrescriptions();

    System.out.println("✔ Prescription deleted successfully.");
}


    // ============================================================
    // ===================== LINK OPERATIONS =======================
    // ============================================================

    /**
     * Adds a patient to a doctor using IDs and names.
     * Returns true if names match IDs, false otherwise.
     */
    public boolean addPatientToDoctorById(int docId, String docName,
                                          int patId, String patName) {

        Doctor d = findDoctorById(docId);
        Patient p = findPatientById(patId);

        if (d == null || p == null) return false;

        boolean nameMatch = d.getName().equalsIgnoreCase(docName)
                && p.getName().equalsIgnoreCase(patName);

        d.addPatient(p);
        saveDoctors();

        return nameMatch;
    }

    // ============================================================
    // ===================== ACCEPT PRESCRIPTIONS ==================
    // ============================================================

    /**
     * Accepts a prescription using names only.
     * Creates a Prescription object with Doctor, Patient, and Medication references.
     */
    public void acceptPrescription(int presId, String doctorName,
                                   String patientName, String medicationName) {

        Doctor d = findDoctorByName(doctorName);
        Patient p = findPatientByName(patientName);
        Medication m = findMedicationByName(medicationName);

        if (d == null || p == null || m == null) {
            System.out.println("Error: Doctor, Patient, or Medication not found.");
            return;
        }

        Date issueDate = new Date(); // today
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);   // expiry after 1 year
        Date expiryDate = cal.getTime();

        Prescription pres = new Prescription(presId, d, p, m, issueDate, expiryDate);

        addPrescription(pres);
    }

    /**
     * Accepts a prescription using IDs and names.
     * Returns true if names match IDs, false otherwise.
     */
    public boolean acceptPrescriptionById(int presId,
                                          int docId, String docName,
                                          int patId, String patName,
                                          int medId, String medName) {

        Doctor d = findDoctorById(docId);
        Patient p = findPatientById(patId);
        Medication m = findMedicationById(medId);

        if (d == null || p == null || m == null) {
            System.out.println("Error: Doctor, Patient, or Medication not found.");
            return false;
        }

        boolean namesMatch =
                d.getName().equalsIgnoreCase(docName) &&
                p.getName().equalsIgnoreCase(patName) &&
                m.getName().equalsIgnoreCase(medName);

        Date issueDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        Date expiryDate = cal.getTime();

        Prescription pres = new Prescription(presId, d, p, m, issueDate, expiryDate);

        addPrescription(pres);

        return namesMatch;
    }

    // ============================================================
    // ===================== LIST / REPORT METHODS =================
    // ============================================================

    /**
     * Lists all patients.
     */
    public void listPatients() {
        for (Patient p : patients) {
            System.out.println(p);
        }
    }

    /**
     * Lists all doctors.
     */
    public void listDoctors() {
        for (Doctor d : doctors) {
            System.out.println(d);
        }
    }

    /**
     * Lists all medications.
     */
    public void listMedications() {
        for (Medication m : medications) {
            System.out.println(m);
        }
    }

    /**
     * Lists all prescriptions.
     */
    public void listPrescriptions() {
        for (Prescription p : prescriptions) {
            System.out.println(p);
        }
    }

    /**
     * Lists expired medications (expiry date before today).
     */
    public void listExpiredMedications() {
        Date today = new Date();

        for (Medication m : medications) {
            if (m.getExpiryDate().before(today)) {
                System.out.println("Expired Medication: " + m);
            }
        }
    }

    /**
     * Lists medications expiring within the next 30 days.
     */
    public void listMedicationsExpiringSoon() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 30);
        Date soon = cal.getTime();

        for (Medication m : medications) {
            if (m.getExpiryDate().after(today) &&
                m.getExpiryDate().before(soon)) {
                System.out.println("Expiring Soon: " + m);
            }
        }
    }

    /**
     * Lists expired prescriptions (expiry date before today).
     */
    public void listExpiredPrescriptions() {
        Date today = new Date();

        for (Prescription p : prescriptions) {
            if (p.getExpiryDate().before(today)) {
                System.out.println("Expired Prescription: " + p);
            }
        }
    }

    /**
     * Lists prescriptions issued by a specific doctor (by name).
     */
    public void listPrescriptionsByDoctor(String doctorName) {
        Doctor d = findDoctorByName(doctorName);

        if (d == null) {
            System.out.println("Doctor not found.");
            return;
        }

        for (Prescription p : d.getPrescriptions()) {
            System.out.println(p);
        }
    }

    /**
     * Lists medications prescribed to a patient in the past year (by name).
     * Shows only medication names.
     */
    public void listPatientPrescriptionsPastYear(String patientName) {
        Patient p = findPatientByName(patientName);

        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date oneYearAgo = cal.getTime();

        System.out.println("Medications for " + p.getName() + " in the past year:");

        for (Prescription pres : p.getPrescriptions()) {
            if (pres.getIssueDate().after(oneYearAgo)) {
                System.out.println("- " + pres.getMedication().getName());
            }
        }
    }

    /**
     * Lists medications prescribed to a patient in the past year using ID + name.
     * Returns true if name matches ID, false otherwise.
     */
    public boolean listPatientPrescriptionsPastYearById(int patientId, String patientName) {
        Patient p = findPatientById(patientId);

        if (p == null) {
            System.out.println("Patient not found.");
            return false;
        }

        boolean nameMatch = p.getName().equalsIgnoreCase(patientName);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date oneYearAgo = cal.getTime();

        System.out.println("Medications for " + p.getName() + " in the past year:");

        for (Prescription pres : p.getPrescriptions()) {
            if (pres.getIssueDate().after(oneYearAgo)) {
                System.out.println("- " + pres.getMedication().getName());
            }
        }

        return nameMatch;
    }

    // ============================================================
    // ===================== RESTOCK & FULL REPORT =================
    // ============================================================

    /**
     * Restocks all medications with low stock.
     * Simple example: if stock < 10, add 20 units.
     */
    public void restockMedications() {
        for (Medication m : medications) {
            if (m.getStock() < 10) {
                m.setStock(m.getStock() + 20);
                System.out.println("Restocked: " + m.getName() +
                                   " new stock = " + m.getStock());
            }
        }
        saveMedications();
    }

    /**
     * Generates a full system report.
     * Shows all entities and key status information.
     */
    public void generateFullReport() {
        System.out.println("===== FULL SYSTEM REPORT =====");

        System.out.println("\n--- Patients ---");
        listPatients();

        System.out.println("\n--- Doctors ---");
        listDoctors();

        System.out.println("\n--- Medications ---");
        listMedications();

        System.out.println("\n--- Prescriptions ---");
        listPrescriptions();

        System.out.println("\n--- Expired Medications ---");
        listExpiredMedications();

        System.out.println("\n--- Medications Expiring Soon ---");
        listMedicationsExpiringSoon();

        System.out.println("\n--- Expired Prescriptions ---");
        listExpiredPrescriptions();

        System.out.println("===== END OF REPORT =====");
    }
}
