import java.time.LocalDateTime;

public class Event extends Post {
    private String eventName;
    private Venue venue;
    private String imagePath;
    private LocalDateTime eventDateTime;
    private double ticketPrice;
    private String address;

    public Event(String eventName, Venue venue, String imagePath, LocalDateTime eventDateTime,
                     String category, double ticketPrice, String address) {
        super(category);
        this.eventName = eventName;
        this.venue = venue;
        this.imagePath = imagePath;
        this.eventDateTime = eventDateTime;
        this.ticketPrice = ticketPrice;
        this.address = address;
    }

    //Getters
    public String getEventName() {
        return eventName;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getImagePath() {
        return imagePath;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getAddress() {
        return address;
    }

    // --- Post functionality ---
    @Override
    public void preview() {
        System.out.println("Προεπισκόπηση Εκδήλωσης: " + eventName + " @ " + venue.getName());
    }

    @Override
    public void details() {
        System.out.println("\n--- Λεπτομέρειες Εκδήλωσης ---");
        System.out.println("Όνομα: " + eventName);
        System.out.println("Χώρος: " + venue.getName());
        System.out.println("Ημερομηνία & Ώρα: " + eventDateTime);
        System.out.println("Κατηγορία: " + category);
        System.out.println("Τιμή εισιτηρίου: " + ticketPrice + "€");
        System.out.println("Διεύθυνση: " + address);
        System.out.println("Ημερομηνία δημοσίευσης: " + createdAt);
    }

    public boolean validate() {
        return venue != null && eventDateTime != null && category != null && !category.isEmpty();
    }
}