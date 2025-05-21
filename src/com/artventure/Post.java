import java.time.LocalDateTime;

public abstract class Post {
    protected String title;
    protected String category;
    protected LocalDateTime createdAt;

    public Post(String title, String category) {
        this.title = title;
        this.category = category;
        this.createdAt = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public abstract void preview();

    public abstract void details();
}
