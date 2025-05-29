package backend;

import java.io.Serializable;
import java.util.*;

public class Cart implements Serializable {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item = new CartItem(product, item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public boolean removeItem(String productName) {
    return items.removeIf(item -> item.getProduct().getName().equalsIgnoreCase(productName));
    }

    public double getTotalCost() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\n--- Your Cart ---");
        for (CartItem item : items) {
            System.out.println(item.getQuantity() + " x " + item.getProduct().getName() + " = " + item.getSubtotal() + "$");
        }
        System.out.println("Total: " + getTotalCost() + "$");
    }

    public void checkout(User user) {
    if (isEmpty()) {
        System.out.println("Cart is empty. Nothing to checkout.");
        return;
    }

    showCart();

    Scanner scanner = new Scanner(System.in);

    double total = getTotalCost();

    Map<String, Integer> availableDiscounts = Discount.getAvailableDiscounts(
    user.getPointsActivity().getTotalPoints());

    if (!availableDiscounts.isEmpty()) {
        System.out.println("\nAvailable point-based discounts:");
        int i = 1;
        List<String> discountNames = new ArrayList<>(availableDiscounts.keySet());

        for (String discount : discountNames) {
            int cost = availableDiscounts.get(discount);
            System.out.println(i + ". " + discount + " (Cost: " + cost + " points)");
            i++;
        }

        System.out.print("Choose discount number (or 0 to skip): ");
        String input = scanner.nextLine();

        try {
            int choice = Integer.parseInt(input);
            if (choice > 0 && choice <= discountNames.size()) {
                String selectedDiscount = discountNames.get(choice - 1);
                int pointsNeeded = availableDiscounts.get(selectedDiscount);
                double percent = 0;

                if (selectedDiscount.contains("5%")) percent = 0.05;
                else if (selectedDiscount.contains("15%")) percent = 0.15;
                else if (selectedDiscount.contains("30%")) percent = 0.30;

                if (user.getPointsActivity().redeemPoints(pointsNeeded, selectedDiscount)) {
                    double discountAmount = total * percent;
                    total -= discountAmount;
                    System.out.println("Discount of " + String.format("%.2f", discountAmount) + "$ applied!");
                    System.out.println("New total: " + String.format("%.2f", total) + "$");
                }
            } else if (choice != 0) {
                System.out.println("Invalid discount choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    double walletBalance = user.getBalance().getTotalBalance();
    if (walletBalance < total) {
        System.out.println("Insufficient funds in your wallet. You have: " + walletBalance + "$, but need: " + total + "$");
        return;
    }

    System.out.print("Confirm checkout and pay " + String.format("%.2f", total) + "$? (yes/no): ");
    String confirm = scanner.nextLine();

    if (confirm.equalsIgnoreCase("yes")) {
        user.getBalance().deduct(total);

        Order order = new Order(new ArrayList<>(getItems()), total);
        user.addOrder(order);
        clear();

        int earnedPoints = (int)(total * 0.4);
        user.getPointsActivity().addPoints(earnedPoints, "Product Purchase");

        System.out.println("Order placed! You earned " + earnedPoints + " points.");
    } else {
        System.out.println("Checkout cancelled.");
    }
    
}

}
