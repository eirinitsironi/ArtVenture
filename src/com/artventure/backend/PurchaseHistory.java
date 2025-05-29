package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PurchaseHistory implements Serializable {
    private List<Order> orders;

    public PurchaseHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void showHistory() {
        if (orders.isEmpty()) {
            System.out.println("No purchases yet.");
            return;
        }

        System.out.println("\n--- Purchase History ---");
        for (Order order : orders) {
            order.showOrder();
        }
    }
}
