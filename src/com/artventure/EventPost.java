import java.time.LocalDateTime;

public class EventPost extends Post {
    private String eventName;
    private Venue venue;
    private String imagePath;
    private LocalDateTime eventDateTime;
    private double ticketPrice;
    private String address;

    public EventPost(String eventName, Venue venue, String imagePath, LocalDateTime eventDateTime,
                     String category, double ticketPrice, String address) {
        super(category);
        this.eventName = eventName;
        this.venue = venue;
        this.imagePath = imagePath;
        this.eventDateTime = eventDateTime;
        this.ticketPrice = ticketPrice;
        this.address = address;
    }

    @Override
    public void preview() {
        System.out.println("Προεπισκόπηση Εκδήλωσης: " + eventName + " @ " + venue.getName());
    }

    public boolean validate() {
        return venue != null && eventDateTime != null && category != null;
    }
}
