import java.io.Serializable;
import java.util.Objects;

public class Artist implements Serializable {
    private String username;

    public Artist(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(username, artist.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
