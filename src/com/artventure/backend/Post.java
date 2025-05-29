package backend;

import java.time.LocalDateTime;

public abstract class Post {
    protected String id;
    protected String title;
    protected String category;
    protected LocalDateTime createdAt;

    public Post(String id, String title, String category) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public abstract void preview();
    public abstract void details();
}

