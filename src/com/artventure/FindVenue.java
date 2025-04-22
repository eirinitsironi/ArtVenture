import java.time.LocalDateTime;

public class FindVenue {
    private int venueID;
    private String name;
    private String location;
    private int capacity;
    private List<LocalDate> availableDates;
    private float pricePerDay;
    private List<String> features;

    public FindVenue(int venueID, String name, String location, int capacity,
                     List<LocalDate> availableDates, float pricePerDay, List<String> features) {
        this.venueID = venueID;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.availableDates = availableDates;
        this.pricePerDay = pricePerDay;
        this.features = features;
}

public boolean checkAvailability(LocalDate from, LocalDate to) {
    for (LocalDate[] booking : bookings) {
        LocalDate bookedFrom = booking[0];
        LocalDate bookedTo = booking[1];

        if (!(to.isBefore(bookedFrom) || from.isAfter(bookedTo))) {
            return false;
        }
    }
    return true;
}

public float calculateTotalCost(LocalDate from, LocalDate to) {
    int days = 0;
    LocalDate current = from;

    while (!current.isAfter(to)) {
        days++;
        current = current.plusDays(1);
    }

    return days * pricePerDay;
}


    // Getters
    public String getName() { 
        return name; 
    }
    
    public String getLocation() { 
        return location; 
    }

    public int getCapacity() { 
        return capacity; 
    }

    public float getPricePerDay() { 
        return pricePerDay; 
    }

    public List<String> getFeatures() { 
        return features; 
    }
}