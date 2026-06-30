import java.util.Date;

public class Prescription {

    private int id;
    private int doctorId;
    private int patientId;
    private int medicationId;

    private Date issueDate;
    private Date expiryDate;

    public Prescription(int id, int doctorId, int patientId, int medicationId,
                        Date issueDate, Date expiryDate) {

        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.medicationId = medicationId;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

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
