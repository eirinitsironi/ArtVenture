package backend;

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
    private List<Painting> wishlist;
    private List<Museum> visitedMuseums;
    private List<Painting> ratedPaintings;
    private List<Review> reviews;
    private List<Rating> ratings;


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
        this.wishlist = new ArrayList<>();
        this.visitedMuseums = new ArrayList<>();
        this.ratedPaintings = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.ratings = new ArrayList<>();
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

    //Review related
    public void addReview(Review review) {
        reviews.add(review);
    }
    public List<Review> getReviews() {
        return reviews;
    }

    //Wrapped related
    // Wishlist
    public List<Painting> getWishlist() {
        return wishlist;
    }

    public void addToWishlist(Painting painting) {
        wishlist.add(painting);
    }

    // Visited Museums
    public List<Museum> getVisitedMuseums() {
        return visitedMuseums;
    }

    public void addVisitedMuseum(Museum museum) {
        visitedMuseums.add(museum);
    }

    // Rated Paintings
    public List<Painting> getRatedPaintings() {
        return ratedPaintings;
    }

    public void addRatedPainting(Painting painting) {
        ratedPaintings.add(painting);
    }

    public void ratePost(Painting painting, float rating) {
        for (Rating r : ratings) {
            if (r.getPost().equals(painting)) {
                r.setRating(rating);        //ενημέρωση υπάρχουσας βαθμολογίας
                return;
            }
        }
    int newReviewID = ratings.size() + 1; 
    ratings.add(new Rating(newReviewID, this, painting, rating));
    }

    public float getRatingFor(Painting painting) {
    for (Rating r : ratings) {
        if (r.getPost().equals(painting)) {
            return r.getRating();
        }
    }
    return 0.0f; 
    }
}


