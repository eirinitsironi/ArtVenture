import java.time.LocalDate;

public class Booking {
    private Venue venue;
    private LocalDate date;
    private double totalCost;

    public Booking(Venue venue, LocalDate date) {
        this.venue = venue;
        this.date = date;
        this.totalCost = venue.getRentingPrice();
    }

    public boolean confirm(Balance userBalance) {
        if (!venue.isAvailableOn(date)) {
            System.out.println("Venue not available on selected date.");
            return false;
        }

        if (userBalance.deduct(totalCost)) {
            venue.bookDate(date);
            System.out.println("Booking confirmed! Venue: " + venue.getName() + ", Date: " + date);
            return true;
        } else {
            System.out.println("Insufficient balance.");
            return false;
        }
    }

    public double getTotalCost() {
        return totalCost;
    }
}
