public class Order {
    int customerId;
    String productName;
    int quantity;
    int time;
    public Order(int customerId, String productName, int quantity, int time) {
        this.customerId = customerId;
        this.productName = productName;
        this.quantity = quantity;
        this.time = time;
    }

    public String toString() {
        return "CustomerId: " + this.customerId + " | ProductName: " + this.productName + " | Quantity: " + this.quantity + " | Time: " + this.time;
    }
}