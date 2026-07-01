import java.util.Calendar;
import java.util.Date;

public class Medication {

    // ===== OLD FIELDS (kept exactly as they were) =====
    private int id;            // Medication ID
    private String name;       // Medication name
    private int stock;         // Quantity in stock
    private Date expiryDate;   // Expiry date of the medication
    private String dose;       // Medication dosage

    /**
     * OLD CONSTRUCTOR — kept exactly as it was.
     * Used when the expiry date is manually provided.
     */
    public Medication(int id, String name, int stock, Date expiryDate, String dose) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.expiryDate = expiryDate;
        this.dose = dose;
    }

    /**
     * NEW CONSTRUCTOR — generates a random expiry date.
     * This satisfies the requirement: "expiry date inclusive of the past".
     */
    public Medication(int id, String name, int stock, String dose) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.dose = dose;
        this.expiryDate = generateRandomExpiryDate(); // random date
    }

    /**
     * Generates a random expiry date between -365 and +365 days from today.
     * This means the medication may already be expired or may expire in the future.
     */
    private Date generateRandomExpiryDate() {
        Calendar cal = Calendar.getInstance();

        // Random number between -365 and +365
        int randomDays = (int)(Math.random() * 730) - 365;

        // Add the random number of days to today's date
        cal.add(Calendar.DAY_OF_YEAR, randomDays);

        return cal.getTime();
    }

    // ===== GETTERS (kept as they were) =====
    public int getId() { return id; }
    public String getName() { return name; }
    public int getStock() { return stock; }
    public Date getExpiryDate() { return expiryDate; }
    public String getDose() { return dose; }

    // ===== SETTERS (kept as they were) =====
    public void setStock(int stock) { this.stock = stock; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }
    public void setDose(String dose) { this.dose = dose; }

    /**
     * OLD toString() — kept exactly as it was.
     * Shows medication details including expiry date.
     */
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
