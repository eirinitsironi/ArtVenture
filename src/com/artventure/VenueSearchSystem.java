import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VenueSearchSystem {
    private List<Venue> venues;

    public VenueSearchSystem(List<Venue> venues) {
        this.venues = venues;
    }

    public List<Venue> search(VenueFilter filter, LocalDate date) {
        return venues.stream()
                .filter(v -> filter.matches(v) && v.isAvailableOn(date))
                .collect(Collectors.toList());
    }

    public List<Venue> sortByPrice(List<Venue> results, boolean ascending) {
        results.sort(Comparator.comparingDouble(Venue::getRentingPrice));
        if (!ascending) {
            results.sort(Comparator.comparingDouble(Venue::getRentingPrice).reversed());
        }
        return results;
    }
}