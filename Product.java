public class Product {
    String name;
    int quantity;
    int restockRate;
    int restockQuantity;
    public Product(String name, int quantity, int restockRate, int restockQuantity) {
        this.name = name;
        this.quantity = quantity;
        this.restockRate = restockRate;
        this.restockQuantity = restockQuantity;
    }

    public String toString() {
        return "Product: " + this.name + " | Quantity: " + this.quantity + " | RestockRate: " + this.restockRate + " | RestockQuantity: " + this.restockQuantity;
    }
}