import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private int userID;
    private String username;
    private List<Post> posts;

    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;
        this.posts = new ArrayList<>();
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void showAllPosts() {
        if (posts == null || posts.isEmpty()) {
            System.out.println("\n📭 Δεν έχεις δημοσιεύσει ακόμα τίποτα.");
            return;
        }
    
        System.out.println("\n--- Οι Δημοσιεύσεις σου ---");
        for (int i = 0; i < posts.size(); i++) {
            System.out.print((i + 1) + ". ");
            posts.get(i).preview(); // καλεί την preview() κάθε υποκλάσης του Post
        }
    }
    
}