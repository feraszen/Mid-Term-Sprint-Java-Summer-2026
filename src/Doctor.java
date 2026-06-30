import java.util.ArrayList;

public class Doctor {

    private int id;
    private String name;
    private int age;
    private String phone;
    private String specialization;

    private ArrayList<Patient> patients = new ArrayList<>();

    public Doctor(int id, String name, int age, String phone, String specialization) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpecialization() {
        return specialization;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    @Override
    public String toString() {
        return "Doctor{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Age=" + age +
                ", Phone='" + phone + '\'' +
                ", Specialization='" + specialization + '\'' +
                '}';
    }
}
