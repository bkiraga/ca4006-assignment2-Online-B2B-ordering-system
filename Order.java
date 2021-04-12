public class Order {
    int customerId;
    String productName;
    int quantity;
    public Order(int customerId, String productName, int quantity) {
        this.customerId = customerId;
        this.productName = productName;
        this.quantity = quantity;
    }
}