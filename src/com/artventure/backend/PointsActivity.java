package backend;

import java.io.Serializable;
import java.util.*;

public class PointsActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int totalPoints;
    private List<PointsTransaction> transactionHistory;

    public PointsActivity(int userID) {
        this.totalPoints = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public void addPoints(int points, String activityType) {
        if (points > 0) {
            totalPoints += points;
            transactionHistory.add(new PointsTransaction(points, activityType, "Earned"));
            System.out.println("\nEarned " + points + " points from " + activityType + ".");
        }
    }

    public boolean redeemPoints(int points, String discountType) {
        if (points <= 0) return false;
        if (totalPoints >= points) {
            totalPoints -= points;
            transactionHistory.add(new PointsTransaction(points, discountType, "Redeemed"));
            System.out.println("Redeemed " + points + " points for " + discountType);
            return true;
        } else {
            System.out.println("Not enough points. You need " + (points - totalPoints) + " more.\n");
            System.out.println("You can earn points by taking quizzes, buying tickets and reviewing exhibitions!");
            return false;
        }
    }

    public void showTransactionsHistory() {
        System.out.println("\n=== Points History ===");
        for (PointsTransaction t : transactionHistory) {
            System.out.println(t);
        }
        System.out.println("Total available: " + totalPoints + " points");
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public List<PointsTransaction> getPointsTransactionHistory() {
        return transactionHistory;
    }

    public Map<String, Integer> getAvailableDiscounts() {
        Map<String, Integer> discounts = new LinkedHashMap<>();
        if (totalPoints >= 50) discounts.put("5% discount", 50);
        if (totalPoints >= 150) discounts.put("15% discount", 150);
        if (totalPoints >= 300) discounts.put("30% discount", 300);
        return discounts;
    }
}
