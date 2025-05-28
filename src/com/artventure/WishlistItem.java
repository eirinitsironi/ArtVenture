public class WishlistItem {
    private String id;
    private String type;
    private Object item;

    public WishlistItem(String id, String type, Object item) {
        this.id = id;
        this.type = type;
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Object getItem() {
        return item;
    }
}
