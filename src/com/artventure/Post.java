import java.time.LocalDateTime;

public abstract class Post {
    protected String category;
    protected LocalDateTime createdAt;

    public Post(String category) {
        this.category = category;
        this.createdAt = LocalDateTime.now();
    }

    public abstract void preview();

    public abstract void details();
}
