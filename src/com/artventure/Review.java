import java.time.LocalDateTime;

public class Review {
    private int reviewID;
    private User user;
    private Post post;
    private int rating;
    private string comment;

    public Review(int reviewID, User user, Post post, int rating, String comment) {
        this.reviewID = reviewID;
        this.user = user;
        this.post = post;
        this.rating = rating;
        this.comment = comment;
    }

    public boolean isValidRating() {
        return rating != null;
    }

    public void editReview(int newRating, String newComment, boolean confirmOverwrite) {
        System.out.println("Make a new review, the old one will be deleted!");
        if (!confirmOverwrite) {
            this.rating = newRating;
            this.comment = newComment;
        }
    }


    //Getters
    public int getreviewID() {
        return reviewID;
    }

    public int getreviewID() {
        return reviewID;
    }

    public string getcomment() {
        return comment;
    }