import java.io.Serializable;
import java.util.*;

public class PointsActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userID;
    private int totalPoints;
    private List<PointsTransaction> transactionHistory;

    public PointsActivity(int userID) {
        this.userID = userID;
        this.totalPoints = 0;       //initialize 
        this.transactionHistory = new ArrayList<>();
    }

    public void addPoints(int points, String activityType) {
        if (points > 0) {
            totalPoints += points;
            transactionHistory.add(new PointsTransaction(points, activityType, "Earned"));
            System.out.println("\nEarned " + points + " points from " + activityType + ".");
        }
    }

    //redeem points for a discount
    public boolean redeemPoints(int points, String discountType) {
        if (points <= 0) return false;
        if (totalPoints >= points) {
            totalPoints -= points;
            transactionHistory.add(new PointsTransaction(points, discountType, "Redeemed"));
            System.out.println("Redeemed " + points + " points for " + discountType);
            return true;
        }
        else {
            System.out.println("Not enough points. You need " + (points - totalPoints) + " more.\n");
            System.out.println("You can earn points by taking quizzes, buying tickets and reviewing exhibitions!");
            return false;
        }
    }

    //view transaction history
    public void showTransactionsHistory() {
        System.out.println("\n=== Points History ===");
        for (PointsTransaction t : transactionHistory) {
            System.out.println(t);
        }
        System.out.println("Total available: " + totalPoints + " points");
    }

    //getters
    public int getTotalPoints() {
        return totalPoints;
    }

    public List<PointsTransaction> getPointsTransactionHistory() {
        return transactionHistory;
    }
}
