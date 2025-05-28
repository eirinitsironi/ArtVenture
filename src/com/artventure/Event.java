import java.time.LocalDateTime;

public class Event extends Post {
    private String eventName;
    private Venue venue;
    private String imagePath;
    private LocalDateTime eventDateTime;
    private double ticketPrice;
    private String address;

    public Event(String id, String eventName, Venue venue, String imagePath, LocalDateTime eventDateTime,
                 String category, double ticketPrice, String address) {
        super(id, eventName, category);
        this.eventName = eventName;
        this.venue = venue;
        this.imagePath = imagePath;
        this.eventDateTime = eventDateTime;
        this.ticketPrice = ticketPrice;
        this.address = address;
    }

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

    @Override
    public void preview() {
        System.out.println("Event Preview: " + eventName + " @ " + venue.getName());
    }

    @Override
    public void details() {
        System.out.println("\n--- Event Details ---");
        System.out.println("Event Name: " + eventName);
        System.out.println("Venue: " + venue.getName());
        System.out.println("Event Date and Time: " + eventDateTime);
        System.out.println("Category: " + category);
        System.out.println("Ticket Price: " + ticketPrice + "€");
        System.out.println("Address: " + address);
        System.out.println("Posted At: " + createdAt);
    }

    public boolean validate() {
        return venue != null && eventDateTime != null && category != null && !category.isEmpty();
    }
}