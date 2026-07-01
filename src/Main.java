import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        MedicationTrackingSystem system = new MedicationTrackingSystem();
        Scanner sc = new Scanner(System.in);

        // Load all saved data from text files
        system.loadAllData();
        System.out.println("✔ Data loaded successfully.");

        int choice = -1;

        while (choice != 0) {

            // ===== MENU DISPLAY =====
            System.out.println("\n===== PHARMACY MEDICATION TRACKING SYSTEM =====");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Add Medication (Manual Expiry Date)");
            System.out.println("4. Add Patient to Doctor (BY ID + NAME)");
            System.out.println("5. Accept Prescription (BY NAME)");
            System.out.println("6. Edit Doctor");
            System.out.println("7. Delete Doctor");
            System.out.println("8. Restock Medications");
            System.out.println("9. List Patients");
            System.out.println("10. List Doctors");
            System.out.println("11. List Medications");
            System.out.println("12. List Prescriptions");
            System.out.println("13. Prescriptions by Doctor");
            System.out.println("14. Patient Prescriptions (Past Year) — BY NAME");
            System.out.println("15. List Expired Medications");
            System.out.println("16. Medications Expiring Soon");
            System.out.println("17. List Expired Prescriptions");
            System.out.println("18. Edit Patient");
            System.out.println("19. Full System Report");
            System.out.println("20. Accept Prescription (BY ID + NAME)");
            System.out.println("21. Delete Prescription");
            System.out.println("22. Patient Prescriptions (Past Year) — BY ID + NAME");
            System.out.println("23. Add Medication (Random Expiry Date)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = sc.nextInt(); // Read user choice
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Clear invalid input
                continue;
            }

            sc.nextLine(); // Clear buffer

            switch (choice) {

                case 1:
                    // Add new patient
                    System.out.print("Enter Patient ID: ");
                    int pid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Patient Name: ");
                    String pname = sc.nextLine();

                    System.out.print("Enter Patient Age: ");
                    int page = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Patient Phone: ");
                    String pphone = sc.nextLine();

                    system.addPatient(new Patient(pid, pname, page, pphone));
                    break;

                case 2:
                    // Add new doctor
                    System.out.print("Enter Doctor ID: ");
                    int did = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Doctor Name: ");
                    String dname = sc.nextLine();

                    System.out.print("Enter Doctor Age: ");
                    int dage = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Doctor Phone: ");
                    String dphone = sc.nextLine();

                    System.out.print("Enter Doctor Specialization: ");
                    String spec = sc.nextLine();

                    system.addDoctor(new Doctor(did, dname, dage, dphone, spec));
                    break;

                case 3:
                    // Add medication with manual expiry date
                    System.out.print("Enter Medication ID: ");
                    int mid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Medication Name: ");
                    String mname = sc.nextLine();

                    System.out.print("Enter Medication Dose: ");
                    String mdose = sc.nextLine();

                    System.out.print("Enter Medication Stock: ");
                    int mstock = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Expiry Date (dd/MM/yyyy): ");
                    String exp = sc.nextLine();

                    try {
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                        system.addMedication(new Medication(mid, mname, mstock, sdf.parse(exp), mdose));
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                    }
                    break;

                case 4:
                    // Add patient to doctor using ID + name
                    System.out.print("Enter Doctor ID: ");
                    int docId2 = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Doctor Name: ");
                    String docName2 = sc.nextLine();

                    System.out.print("Enter Patient ID: ");
                    int patId2 = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Patient Name: ");
                    String patName2 = sc.nextLine();

                    boolean match = system.addPatientToDoctorById(docId2, docName2, patId2, patName2);

                    if (!match) {
                        System.out.println("⚠ WARNING: Names do not match the IDs provided.");
                        System.out.print("Do you want to continue anyway? (Y/N): ");
                        String confirm = sc.nextLine();

                        if (!confirm.equalsIgnoreCase("Y")) {
                            System.out.println("Operation cancelled.");
                            break;
                        }
                    }

                    System.out.println("✔ Patient added to doctor using ID linking!");
                    break;

                case 5:
                    // Accept prescription using names
                    System.out.print("Enter Prescription ID: ");
                    int presId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Doctor Name: ");
                    String dn2 = sc.nextLine();

                    System.out.print("Enter Patient Name: ");
                    String pn2 = sc.nextLine();

                    System.out.print("Enter Medication Name: ");
                    String mn2 = sc.nextLine();

                    system.acceptPrescription(presId, dn2, pn2, mn2);
                    break;

                case 6:
                    // Edit doctor
                    System.out.print("Enter Doctor ID: ");
                    int edid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newDName = sc.nextLine();

                    System.out.print("Enter New Age: ");
                    int newDAge = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Phone: ");
                    String newDPhone = sc.nextLine();

                    System.out.print("Enter New Specialization: ");
                    String newDSpec = sc.nextLine();

                    system.editDoctor(edid, newDName, newDAge, newDPhone, newDSpec);
                    break;

                case 7:
                    // Delete doctor
                    System.out.print("Enter Doctor ID to delete: ");
                    int delDid = sc.nextInt();
                    sc.nextLine();

                    system.deleteDoctor(delDid);
                    break;

                case 8:
                    // Restock medications
                    system.restockMedications();
                    break;

                case 9:
                    // List patients
                    system.listPatients();
                    break;

                case 10:
                    // List doctors
                    system.listDoctors();
                    break;

                case 11:
                    // List medications
                    system.listMedications();
                    break;

                case 12:
                    // List prescriptions
                    system.listPrescriptions();
                    break;

                case 13:
                    // List prescriptions by doctor
                    System.out.print("Enter Doctor Name: ");
                    String dn3 = sc.nextLine();

                    system.listPrescriptionsByDoctor(dn3);
                    break;

                case 14:
                    // List patient prescriptions (past year)
                    System.out.print("Enter Patient Name: ");
                    String pn3 = sc.nextLine();

                    system.listPatientPrescriptionsPastYear(pn3);
                    break;

                case 15:
                    // List expired medications
                    system.listExpiredMedications();
                    break;

                case 16:
                    // List medications expiring soon
                    system.listMedicationsExpiringSoon();
                    break;

                case 17:
                    // List expired prescriptions
                    system.listExpiredPrescriptions();
                    break;

                case 18:
                    // Edit patient
                    System.out.print("Enter Patient ID: ");
                    int epId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String epName = sc.nextLine();

                    System.out.print("Enter New Age: ");
                    int epAge = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Phone: ");
                    String epPhone = sc.nextLine();

                    system.editPatient(epId, epName, epAge, epPhone);
                    break;

                case 19:
                    // Full system report
                    system.generateFullReport();
                    break;

                case 20:
                    // Accept prescription using ID + name
                    System.out.print("Enter Prescription ID: ");
                    int newPresId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Doctor ID: ");
                    int docId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Doctor Name: ");
                    String docName = sc.nextLine();

                    System.out.print("Enter Patient ID: ");
                    int patId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Patient Name: ");
                    String patName = sc.nextLine();

                    System.out.print("Enter Medication ID: ");
                    int medId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Medication Name: ");
                    String medName = sc.nextLine();

                    boolean namesMatch = system.acceptPrescriptionById(
                            newPresId,
                            docId, docName,
                            patId, patName,
                            medId, medName
                    );

                    if (!namesMatch) {
                        System.out.println("⚠ WARNING: Names do not match the IDs provided.");
                        System.out.print("Do you want to continue anyway? (Y/N): ");
                        String confirm = sc.nextLine();

                        if (!confirm.equalsIgnoreCase("Y")) {
                            System.out.println("Prescription cancelled.");
                            break;
                        }
                    }

                    System.out.println("✔ Prescription accepted using ID linking!");
                    break;
                
                case 21:
                    System.out.print("Enter Prescription ID to delete: ");
                    int delPid = sc.nextInt();
                    sc.nextLine();
                    system.deletePrescription(delPid);
                    break;

                case 22:
                    // Patient prescriptions (past year) using ID + name
                    System.out.print("Enter Patient ID: ");
                    int ppId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Patient Name: ");
                    String ppName = sc.nextLine();

                    boolean ok = system.listPatientPrescriptionsPastYearById(ppId, ppName);

                    if (!ok) {
                        System.out.println("⚠ WARNING: Name does not match ID.");
                        System.out.print("Continue anyway? (Y/N): ");
                        String confirm = sc.nextLine();

                        if (!confirm.equalsIgnoreCase("Y")) {
                            System.out.println("Operation cancelled.");
                            break;
                        }
                    }
                    break;

                case 23:
                    // Add medication with random expiry date
                    System.out.print("Enter Medication ID: ");
                    int rid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Medication Name: ");
                    String rname = sc.nextLine();

                    System.out.print("Enter Medication Dose: ");
                    String rdose = sc.nextLine();

                    System.out.print("Enter Medication Stock: ");
                    int rstock = sc.nextInt();
                    sc.nextLine();

                    Medication randomMed = new Medication(rid, rname, rstock, rdose);

                    system.addMedication(randomMed);

                    System.out.println("✔ Medication added with RANDOM expiry date: " +
                            new java.text.SimpleDateFormat("dd/MM/yyyy")
                                    .format(randomMed.getExpiryDate()));
                    break;

                case 0:
                    // Exit system
                    System.out.println("Saving data...");
                    system.saveAllData();
                    System.out.println("✔ Data saved. Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
