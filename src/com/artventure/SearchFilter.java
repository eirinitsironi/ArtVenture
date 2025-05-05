import java.time.LocalDateTime;
import java.util.List;

public class SearchFilter {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private float minPrice;
    private float maxPrice;

    public SearchFilter(LocalDateTime dateFrom, LocalDateTime dateTo, float minPrice, float maxPrice) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public List<SearchResult> applySearch() {
        System.out.println("Date range: " + dateFrom + "to" + dateTo);
        System.out.println("Price range: " + minPrice + "to" + maxPrice);

        return List.of();
    }

    //Getters

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }
}
