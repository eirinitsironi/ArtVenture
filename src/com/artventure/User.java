import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private int userID;
    private String username;

    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }
}
