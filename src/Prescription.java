import java.util.Date;

/**
 * Prescription class represents a medical prescription.
 * Supports both ID-based and object-based linking.
 */
public class Prescription {

    private int id;

    // OLD FIELDS (kept)
    private int doctorId;
    private int patientId;
    private int medicationId;

    private Date issueDate;
    private Date expiryDate;

    // NEW FIELDS (object references)
    private Doctor doctor;
    private Patient patient;
    private Medication medication;

    // ===== OLD CONSTRUCTOR (IDs only) =====
    public Prescription(int id, int doctorId, int patientId, int medicationId,
                        Date issueDate, Date expiryDate) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.medicationId = medicationId;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

    // ===== NEW CONSTRUCTOR (object references) =====
    public Prescription(int id, Doctor doctor, Patient patient, Medication medication,
                        Date issueDate, Date expiryDate) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;

        // Also store IDs for saving to file
        this.doctorId = doctor.getId();
        this.patientId = patient.getId();
        this.medicationId = medication.getId();
    }

    // ===== GETTERS =====
    public int getId() { return id; }
    public int getDoctorId() { return doctorId; }
    public int getPatientId() { return patientId; }
    public int getMedicationId() { return medicationId; }

    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public Medication getMedication() { return medication; }

    public Date getIssueDate() { return issueDate; }
    public Date getExpiryDate() { return expiryDate; }

    @Override
    public String toString() {
        return "Prescription{" +
                "ID=" + id +
                ", DoctorID=" + doctorId +
                ", PatientID=" + patientId +
                ", MedicationID=" + medicationId +
                ", IssueDate=" + issueDate +
                ", ExpiryDate=" + expiryDate +
                '}';
    }
}
