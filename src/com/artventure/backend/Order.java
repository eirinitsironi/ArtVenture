package backend;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private List<CartItem> items;
    private double total;
    private LocalDateTime orderDate;
    private boolean pointsEarned;

    public Order(List<CartItem> items, double total) {
        this.items = items;
        this.total = total;
        this.orderDate = LocalDateTime.now();
        this.pointsEarned = false;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean hasEarnedPoints() {
        return pointsEarned;
    }

    public void setPointsEarned() {
        this.pointsEarned = true;
    }

    public void showOrder() {
        System.out.println("Order on " + orderDate + " | Total: " + total + "€");
        for (CartItem item : items) {
            System.out.println("- " + item.getQuantity() + " x " + item.getProduct().getName());
        }
    }
}