package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Wishlist {
    private final List<WishlistItem> items;

    public Wishlist(String wishlistID, User user) {
        this.items = new ArrayList<>();
    }

    public void addItem(WishlistItem item) {
        items.add(item);
    }

    public boolean removeItem(String itemId) {
        int initialSize = items.size();
        items.removeIf(item -> item.getId().equals(itemId));
        return items.size() != initialSize;
    }

    public List<WishlistItem> getItems() {
        return new ArrayList<>(items); 
    }

    public List<WishlistItem> getItemsByType(String type) {
        return items.stream()
                   .filter(item -> item.getType().equals(type))
                   .collect(Collectors.toList());
    }

    public void clearWishlist() {
        items.clear();
    }

    public boolean containsItem(String itemId) {
        return items.stream().anyMatch(item -> item.getId().equals(itemId));
    }
}

class WishlistItem {
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}