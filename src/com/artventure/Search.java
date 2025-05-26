import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Search {
    private String keyword;
    private String category;
    private SearchFilter filter;

    public Search(String keyword, String category, SearchFilter filter) {
        this.keyword = keyword;
        this.category = category;
        this.filter = filter;
    }

    public List<SearchResult> performSearch(List<Post> allPosts) {
        List<SearchResult> results = new ArrayList<>();

        for (Post post : allPosts) {
            boolean matches = true;

            // Φίλτρο λέξης-κλειδιού
            if (keyword != null && !keyword.isEmpty() &&
                (post.getTitle() == null || !post.getTitle().toLowerCase().contains(keyword.toLowerCase()))) {
                matches = false;
            }

            // Φίλτρο κατηγορίας
            if (category != null && !category.isEmpty() &&
                (post.getCategory() == null || !post.getCategory().equalsIgnoreCase(category))) {
                matches = false;
            }

            // Φίλτρο ημερομηνίας
            LocalDateTime createdAt = post.getCreatedAt();
            if (filter.getDateFrom() != null && createdAt.isBefore(filter.getDateFrom())) {
                matches = false;
            }
            if (filter.getDateTo() != null && createdAt.isAfter(filter.getDateTo())) {
                matches = false;
            }

            // Φίλτρο τιμής (αν είναι Priced)
            if (post instanceof Priced) {
                double price = ((Priced) post).getPrice();
                if (price < filter.getMinPrice() || price > filter.getMaxPrice()) {
                    matches = false;
                }
            }

            if (matches) {
                results.add(new SearchResult(post.hashCode()));
            }
        }

        return results;
    }
}
