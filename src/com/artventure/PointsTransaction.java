import java.io.Serializable;
import java.time.LocalDateTime;;

public class PointsTransaction implements Serializable{
    private int points;
    private String activity;
    private String type;
    private LocalDateTime timestamp;

    public PointsTransaction(int points, String activity, String type) {
        this.points = points;
        this.activity = activity;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return timestamp + " - " + type + " " + points + " points (" + activity + ")";
    }   
}