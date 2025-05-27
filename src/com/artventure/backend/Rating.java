package backend;

import java.io.Serializable;

public class Rating implements Serializable {
    private int reviewID;
    private User user;
    private Post post;
    private float rating;

    public Rating(int reviewID, User user, Post post, float rating) {
        this.reviewID = reviewID;
        this.user = user;
        this.post = post;
        this.rating = rating;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
