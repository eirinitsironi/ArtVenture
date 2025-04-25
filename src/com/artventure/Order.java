import java.time.LocalDateTime;
import java.util.*;

public class Order {

    public enum Status { PAID, CANCELLED, REFUNDED }

    private final String          orderId;
    private final List<CartItem>  items;
    private final double          totalCost;
    private final double          couponUsed;
    private Status                status;
    private final LocalDateTime   createdAt;
    private final String          userId;

    public Order(List<CartItem> items, double totalCost, double couponUsed, String userId) {
        this.orderId    = UUID.randomUUID().toString();
        this.items      = Collections.unmodifiableList(items);
        this.totalCost  = totalCost;
        this.couponUsed = couponUsed;
        this.status     = Status.PAID;
        this.createdAt  = LocalDateTime.now();
        this.userId     = userId;
    }

    public String getOrderId()           { return orderId; }
    public List<CartItem> getItems()     { return items; }
    public double getTotalCost()         { return totalCost; }
    public double getCouponUsed()        { return couponUsed; }
    public Status getStatus()            { return status; }
    public LocalDateTime getCreatedAt()  { return createdAt; }
    public String getUserId()            { return userId; }

    public void cancel()  { if (status == Status.PAID) status = Status.CANCELLED; }
    public void refund()  { if (status == Status.PAID) status = Status.REFUNDED;  }
}