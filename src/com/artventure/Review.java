public class Review {
    private int reviewID;
    private User user;
    private Post post;
    private float rating;
    private String comment;

    public Review(int reviewID, User user, Post post, float rating, String comment) {
        this.reviewID = reviewID;
        this.user = user;
        this.post = post;
        this.rating = rating;
        this.comment = comment;
    }

    public boolean isValidRating() {
        return rating >= 1 && rating <= 5;
    }

    public void editReview(int newRating, String newComment, boolean confirmOverwrite) {
        System.out.println("Make a new review, the old one will be deleted!");
        if (!confirmOverwrite) {
            this.rating = newRating;
            this.comment = newComment;
            System.out.println("Review updated.");
        } else {
            System.out.println("Review update cancelled.");
        }
    }

    //Getters
    public int getReviewID() {
        return reviewID;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    

    public String toString() {
        return "Review ID: " + reviewID + ", User: " + user.getUsername() +
               ", Post: " + post.getTitle() + ", Rating: " + rating +
               ", Comment: \"" + comment + "\"";
    }
}
