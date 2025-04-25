import java.util.*;
import java.util.stream.Collectors;

public class Cart {

    private final List<CartItem> items = new ArrayList<>();
    private final String userId;

    public Cart(String userId) { this.userId = userId; }

    public void addProduct(Product p, int qty) {
        items.stream()
             .filter(ci -> ci.getProduct().getId().equals(p.getId()))
             .findFirst()
             .ifPresentOrElse(
                 ci -> ci.setQuantity(ci.getQuantity() + qty),
                 () -> items.add(new CartItem(p, qty))
             );
    }

    public void removeProduct(String productId) {
        items.removeIf(ci -> ci.getProduct().getId().equals(productId));
    }

    public void updateQuantity(String productId, int newQty) {
        items.stream()
             .filter(ci -> ci.getProduct().getId().equals(productId))
             .findFirst()
             .ifPresent(ci -> ci.setQuantity(newQty));
    }

    public void clear() { items.clear(); }

    public boolean isEmpty() { return items.isEmpty(); }

    public double getTotalCost() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public List<CartItem> snapshot() {
        return items.stream()
                    .map(ci -> new CartItem(ci.getProduct(), ci.getQuantity()))
                    .collect(Collectors.toUnmodifiableList());
    }

    public Optional<Order> checkout(double couponAmt) {
        double cost = getTotalCost();
        if (couponAmt < cost) return Optional.empty();

        Order order = new Order(snapshot(), cost, couponAmt, userId);
        clear();
        return Optional.of(order);
    }
}
