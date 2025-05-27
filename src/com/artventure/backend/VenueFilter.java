package backend;

public class VenueFilter {
    private String town;
    private Double minPrice;
    private Double maxPrice;
    private Integer maxCapacity;

    public VenueFilter(String town, Double minPrice, Double maxPrice, Integer maxCapacity) {
        this.town = town;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.maxCapacity = maxCapacity;
    }

    public boolean matches(Venue venue) {
        if (town != null && !venue.getAddress().toLowerCase().contains(town.toLowerCase())) {
            return false;
        }

        if (minPrice != null && venue.getRentingPrice() < minPrice) {
            return false;
        }

        if (maxPrice != null && venue.getRentingPrice() > maxPrice) {
            return false;
        }

        if (maxCapacity != null && venue.getCapacity() > maxCapacity) {
            return false;
        }

        return true;
    }
}

