import java.time.LocalDateTime;
import java.util.List;

public class Search {
    private String keyword;
    private String category;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private float minPrice;
    private float maxPrice;
    private List<SearchResult> search;

    public Search(String keyword, String category, LocalDateTime dateFrom, LocalDateTime dateTo, float minPrice, float maxPrice) {
        this.keyword = keyword;
        this.category = category;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.search = search;
    }


    //Getters

    public List<SearchResult> getSearch() {
        return search;
    }

    public void getSearch(List<SearchResult> search) {
        this.search = search;
    }
}
