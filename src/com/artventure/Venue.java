import java.time.LocalDate;
import java.util.List;

public class Venue {
    private String id;
    private String name;
    private String address;
    private String contactInfo;
    private double rentingPrice;
    private int capacity;
    private List<LocalDate> availableDates;

    public Venue(String id, String name, String address, String contactInfo, double rentingPrice, int capacity, List<LocalDate> availableDates) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.rentingPrice = rentingPrice;
        this.capacity = capacity;
        this.availableDates = availableDates;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContactInfo() { return contactInfo; }
    public double getRentingPrice() { return rentingPrice; }
    public int getCapacity() { return capacity; }
    public List<LocalDate> getAvailableDates() { return availableDates; }

    public boolean isAvailableOn(LocalDate date) {
        return availableDates.contains(date);
    }

    public void bookDate(LocalDate date) {
        availableDates.remove(date);
    }

    @Override
    public String toString() {
        return name + " | " + address + " | " + rentingPrice + "$ | Cap: " + capacity;
    }
}

