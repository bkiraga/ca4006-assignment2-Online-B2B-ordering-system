import java.util.Random;

public class Order {
    int customerId;
    String productName;
    int quantity;
    int time;
    int orderId;
    public Order(int customerId, String productName, int quantity, int time) {
        this.customerId = customerId;
        this.productName = productName;
        this.quantity = quantity;
        this.time = time;
        orderId = generateOrdId();
    }

    public int generateOrdId() {
        Random random = new Random();
        return random.nextInt(1000000) + 1000;
    }

    public String toString() {
        return "CustomerId: " + this.customerId + " | ProductName: " + this.productName + " | Quantity: " + this.quantity + " | Time: " + this.time + " | OrderID: " + orderId;
    }
}