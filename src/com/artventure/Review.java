//import java.time.LocalDateTime;

public class Review {
    private int reviewID;
    private User user;
    private Post post;
    private int rating;
    private String comment;

    public Review(int reviewID, User user, Post post, int rating, String comment) {
        this.reviewID = reviewID;
        this.user = user;
        this.post = post;
        this.rating = rating;
        this.comment = comment;
    }

    public boolean isValidRating() {
        return rating > 0;
    }

    public void editReview(int newRating, String newComment, boolean confirmOverwrite) {
        System.out.println("Make a new review, the old one will be deleted!");
        if (!confirmOverwrite) {
            this.rating = newRating;
            this.comment = newComment;
        }
    }

    //Getters
    public int getReviewID() {
        return reviewID;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}