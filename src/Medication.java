import java.util.Calendar;
import java.util.Date;

public class Medication {

    private int id;
    private String name;
    private int stock;
    private Date expiryDate;

    private String dose;

    public Medication(int id, String name, int stock, Date expiryDate, String dose) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.expiryDate = expiryDate;
        this.dose = dose;
    }

    public Medication(int id, String name, int stock, String dose) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.dose = dose;
        this.expiryDate = generateRandomExpiryDate();
    }

    private Date generateRandomExpiryDate() {
        Calendar cal = Calendar.getInstance();
        int randomDays = (int)(Math.random() * 730) - 365;
        cal.add(Calendar.DAY_OF_YEAR, randomDays);
        return cal.getTime();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Dose='" + dose + '\'' +
                ", Stock=" + stock +
                ", ExpiryDate=" + expiryDate +
                '}';
    }
}
