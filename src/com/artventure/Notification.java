import java.time.LocalDateTime;

public class Notification {
    private String message;
    private LocalDateTime date;
    private boolean read;

    public Notification(String message) {
        this.message = message;
        this.date = LocalDateTime.now();
        this.read = false;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isRead() {
        return read;
    }

    public void markAsRead() {
        this.read = true;
    }

    public void show() {
        String status = read ? "" : "[ΝΕΟ] ";
        System.out.println("- " + status + message + " (" + date.toLocalDate() + ")");
    }
}
