import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MedicationTrackingSystem {

    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Medication> medications = new ArrayList<>();
    private ArrayList<Prescription> prescriptions = new ArrayList<>();

    private final String PATIENTS_FILE = "src/data/patients.txt";
    private final String DOCTORS_FILE = "src/data/doctors.txt";
    private final String MEDICATIONS_FILE = "src/data/medications.txt";
    private final String PRESCRIPTIONS_FILE = "src/data/prescriptions.txt";

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // ============================================================
    // ======================= LOAD DATA ===========================
    // ============================================================

    public void loadAllData() {
        loadPatients();
        loadDoctors();
        loadMedications();
        loadPrescriptions();
        linkLoadedPrescriptions();
    }

    private void loadPatients() {
        try {
            File file = new File(PATIENTS_FILE);
            if (!file.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String phone = parts[3];

                patients.add(new Patient(id, name, age, phone));
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error loading patients.");
        }
    }

    private void loadDoctors() {
        try {
            File file = new File(DOCTORS_FILE);
            if (!file.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String phone = parts[3];
                String spec = parts[4];

                doctors.add(new Doctor(id, name, age, phone, spec));
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error loading doctors.");
        }
    }

    private void loadMedications() {
        try {
            File file = new File(MEDICATIONS_FILE);
            if (!file.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String dose = parts[2];
                int stock = Integer.parseInt(parts[3]);
                Date expiry = sdf.parse(parts[4]);

                medications.add(new Medication(id, name, stock, expiry, dose));
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error loading medications.");
        }
    }

    private void loadPrescriptions() {
        try {
            File file = new File(PRESCRIPTIONS_FILE);
            if (!file.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                int id = Integer.parseInt(parts[0]);
                int doctorId = Integer.parseInt(parts[1]);
                int patientId = Integer.parseInt(parts[2]);
                int medicationId = Integer.parseInt(parts[3]);
                Date issueDate = sdf.parse(parts[4]);
                Date expiryDate = sdf.parse(parts[5]);

                prescriptions.add(new Prescription(id, doctorId, patientId, medicationId, issueDate, expiryDate));
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error loading prescriptions.");
        }
    }

    // ============================================================
    // =============== LINK PRESCRIPTIONS TO PATIENTS =============
    // ============================================================

    private void linkLoadedPrescriptions() {
        for (Prescription p : prescriptions) {
            Patient pat = findPatientById(p.getPatientId());
            if (pat != null) {
                pat.addPrescription(p);
            }
        }
    }

    // ============================================================
    // ======================= SAVE DATA ===========================
    // ============================================================

    public void saveAllData() {
        savePatients();
        saveDoctors();
        saveMedications();
        savePrescriptions();
    }

    private void savePatients() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PATIENTS_FILE));

            for (Patient p : patients) {
                bw.write(p.getId() + "|" + p.getName() + "|" + p.getAge() + "|" + p.getPhone());
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving patients.");
        }
    }

    private void saveDoctors() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(DOCTORS_FILE));

            for (Doctor d : doctors) {
                bw.write(d.getId() + "|" + d.getName() + "|" + d.getAge() + "|" + d.getPhone() + "|" + d.getSpecialization());
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving doctors.");
        }
    }

    private void saveMedications() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(MEDICATIONS_FILE));

            for (Medication m : medications) {
                bw.write(m.getId() + "|" + m.getName() + "|" + m.getDose() + "|" + m.getStock() + "|" + sdf.format(m.getExpiryDate()));
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving medications.");
        }
    }

    private void savePrescriptions() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PRESCRIPTIONS_FILE));

            for (Prescription p : prescriptions) {
                bw.write(
                        p.getId() + "|" +
                        p.getDoctorId() + "|" +
                        p.getPatientId() + "|" +
                        p.getMedicationId() + "|" +
                        sdf.format(p.getIssueDate()) + "|" +
                        sdf.format(p.getExpiryDate())
                );
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving prescriptions.");
        }
    }

    // ============================================================
    // ======================= CORE METHODS ========================
    // ============================================================

    public void addPatient(Patient p) {
        patients.add(p);
        saveAllData();
    }

    public void addDoctor(Doctor d) {
        doctors.add(d);
        saveAllData();
    }

    public void addMedication(Medication m) {
        medications.add(m);
        saveAllData();
    }

    public void addPrescription(Prescription p) {
        prescriptions.add(p);

        Patient pat = findPatientById(p.getPatientId());
        if (pat != null) {
            pat.addPrescription(p);
        }

        saveAllData();
    }

    // ============================================================
    // ======================= FIND METHODS ========================
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

    public Medication findMedicationByName(String name) {
        for (Medication m : medications) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    public Doctor findDoctorByName(String name) {
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(name)) return d;
        }
        return null;
    }

    public Patient findPatientByName(String name) {
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    // ============================================================
    // ======================= REPORTS =============================
    // ============================================================

    public void listPatients() {
        for (Patient p : patients) System.out.println(p);
    }

    public void listDoctors() {
        for (Doctor d : doctors) System.out.println(d);
    }

    public void listMedications() {
        for (Medication m : medications) System.out.println(m);
    }

    public void listPrescriptions() {
        for (Prescription p : prescriptions) System.out.println(p);
    }

    public void listExpiredMedications() {
        Date today = new Date();
        for (Medication m : medications) {
            if (m.getExpiryDate().before(today)) {
                System.out.println("Expired: " + m);
            }
        }
    }

    public void listMedicationsExpiringSoon() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 30);
        Date soon = cal.getTime();

        for (Medication m : medications) {
            if (m.getExpiryDate().after(new Date()) && m.getExpiryDate().before(soon)) {
                System.out.println("Expiring Soon: " + m);
            }
        }
    }

    public void listExpiredPrescriptions() {
        Date today = new Date();
        for (Prescription p : prescriptions) {
            if (p.getExpiryDate().before(today)) {
                System.out.println("Expired Prescription: " + p);
            }
        }
    }

    public void generateFullReport() {
        System.out.println("=== FULL SYSTEM REPORT ===");
        listPatients();
        listDoctors();
        listMedications();
        listPrescriptions();
    }

    // ============================================================
    // =============== EXTRA METHODS (PROJECT FEATURES) ===========
    // ============================================================

    public boolean addPatientToDoctorById(int doctorId, String doctorName,
                                          int patientId, String patientName) {

        Doctor d = findDoctorById(doctorId);
        Patient p = findPatientById(patientId);

        if (d == null || p == null) return false;

        boolean namesMatch = d.getName().equalsIgnoreCase(doctorName)
                && p.getName().equalsIgnoreCase(patientName);

        d.addPatient(p);
        saveAllData();

        return namesMatch;
    }

    public void acceptPrescription(int presId, String doctorName, String patientName, String medicationName) {

        Doctor d = findDoctorByName(doctorName);
        Patient p = findPatientByName(patientName);
        Medication m = findMedicationByName(medicationName);

        if (d == null || p == null || m == null) {
            System.out.println("Error: Doctor, Patient, or Medication not found.");
            return;
        }

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 30);
        Date expiry = cal.getTime();

        Prescription pres = new Prescription(presId, d.getId(), p.getId(), m.getId(), today, expiry);

        addPrescription(pres);
    }

    public boolean acceptPrescriptionById(int presId,
                                          int doctorId, String doctorName,
                                          int patientId, String patientName,
                                          int medicationId, String medicationName) {

        Doctor d = findDoctorById(doctorId);
        Patient p = findPatientById(patientId);
        Medication m = findMedicationById(medicationId);

        if (d == null || p == null || m == null) return false;

        boolean namesMatch = d.getName().equalsIgnoreCase(doctorName)
                && p.getName().equalsIgnoreCase(patientName)
                && m.getName().equalsIgnoreCase(medicationName);

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 30);
        Date expiry = cal.getTime();

        Prescription pres = new Prescription(presId, doctorId, patientId, medicationId, today, expiry);

        addPrescription(pres);

        return namesMatch;
    }

    public void editDoctor(int id, String name, int age, String phone, String spec) {
        Doctor d = findDoctorById(id);
        if (d == null) {
            System.out.println("Doctor not found.");
            return;
        }

        d.setName(name);
        d.setAge(age);
        d.setPhone(phone);
        d.setSpecialization(spec);

        saveAllData();
    }

    public void deleteDoctor(int id) {
        Doctor d = findDoctorById(id);
        if (d == null) {
            System.out.println("Doctor not found.");
            return;
        }

        doctors.remove(d);
        saveAllData();
    }

    public void restockMedications() {
        for (Medication m : medications) {
            m.setStock(m.getStock() + 50);
        }
        saveAllData();
        System.out.println("✔ All medications restocked by +50.");
    }

    public void listPrescriptionsByDoctor(String doctorName) {
        Doctor d = findDoctorByName(doctorName);
        if (d == null) {
            System.out.println("Doctor not found.");
            return;
        }

        for (Prescription p : prescriptions) {
            if (p.getDoctorId() == d.getId()) {
                System.out.println(p);
            }
        }
    }

    public void listPatientPrescriptionsPastYear(String patientName) {
        Patient p = findPatientByName(patientName);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date oneYearAgo = cal.getTime();

        for (Prescription pres : p.getPrescriptions()) {
            if (pres.getIssueDate().after(oneYearAgo)) {
                System.out.println(pres);
            }
        }
    }

    public void editPatient(int id, String name, int age, String phone) {
        Patient p = findPatientById(id);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        p.setName(name);
        p.setAge(age);
        p.setPhone(phone);

        saveAllData();
    }

    public boolean listPatientPrescriptionsPastYearById(int id, String name) {
        Patient p = findPatientById(id);
        if (p == null) return false;

        boolean nameMatch = p.getName().equalsIgnoreCase(name);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date oneYearAgo = cal.getTime();

        for (Prescription pres : p.getPrescriptions()) {
            if (pres.getIssueDate().after(oneYearAgo)) {
                System.out.println(pres);
            }
        }

        return nameMatch;
    }
}
