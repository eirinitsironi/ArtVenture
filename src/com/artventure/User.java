import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int userID;
    private String username;
    private List<Post> posts;
    private List<Notification> notifications;
    private PointsActivity pointsActivity;
    private List<Drawing> drawings;
    private Cart cart;
    private List<Order> orders;
    private Balance balance;

    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;
        this.posts = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.pointsActivity = new PointsActivity(userID);
        this.drawings= new ArrayList<>();
        this.cart = new Cart();
        this.orders = new ArrayList<>();
        this.balance = new Balance(300);
    }

    //Getters
    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public PointsActivity getPointsActivity() {
        return pointsActivity;
    }

    public Cart getCart() {
        return cart;
    }
    
    public List<Order> getOrders() {
        return orders;
    }
    
    public void addOrder(Order order) {
        orders.add(order);
    }

    public Balance getBalance() {
        return balance;
    }

    //Post related
    public void addPost(Post post) {
        posts.add(post);
    }

    public void showAllPosts() {
        if (posts.isEmpty()) {
            System.out.println("\nNo posts yet.");
            return;
        }

        System.out.println("\n--- Your posts ---");
        for (int i = 0; i < posts.size(); i++) {
            System.out.print((i + 1) + ". ");
            posts.get(i).preview();
        }
    }

    //Notification related
    public void addNotification(Notification n) {
        notifications.add(n);
    }

    public int getUnreadNotificationCount() {
        int count = 0;
        for (Notification n : notifications) {
            if (!n.isRead()) {
                count++;
            }
        }
        return count;
    }

    public void showNotifications() {
        if (notifications.isEmpty()) {
            System.out.println("No notifications.");
            return;
        }

        System.out.println("\n--- Notifications ---");
        for (Notification n : notifications) {
            n.show();
            n.markAsRead();
        }
    }

    //Points related
    public void earnPointsFromQuiz() {
        pointsActivity.addPoints(5, "Quiz Completion"); 
    }

    public void earnPointsFromTicketPurchase(double ticketPrice) {
        int points = (int)(ticketPrice * 0.5);      //0.5 points per euro
        pointsActivity.addPoints(points, "Ticket Purchase");
    }

    public void earnPointsFromExhibitionReview() {
        pointsActivity.addPoints(10, "Exhibition Review");
    }

    public void addDrawing(Drawing drawing) {
        drawings.add(drawing);
    }
    
    public List<Drawing> getDrawings() {
        return drawings;
    }
}
