import java.util.ArrayList;

/**
 * Patient class represents a patient in the system.
 * Supports storing prescriptions linked to the patient.
 */
public class Patient {

    private int id;
    private String name;
    private int age;
    private String phone;

    // NEW: list of prescriptions
    private ArrayList<Prescription> prescriptions = new ArrayList<>();

    public Patient(int id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    // ===== GETTERS =====
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }

    // ===== SETTERS =====
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setPhone(String phone) { this.phone = phone; }

    // ===== PRESCRIPTION LINK =====
    public void addPrescription(Prescription p) {
        prescriptions.add(p);
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Age=" + age +
                ", Phone='" + phone + '\'' +
                '}';
    }
}
