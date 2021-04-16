public class Product {
    String name;
    int quantity;
    int restockDate;
    int restockQuantity;
    public Product(String name, int quantity, int restockDate, int restockQuantity) {
        this.name = name;
        this.quantity = quantity;
        this.restockDate = restockDate;
        this.restockQuantity = restockQuantity;
    }

    public String toString() {
        return "Product: " + this.name + " | Quantity: " + this.quantity + " | RestockDate: " + this.restockDate + " | RestockQuantity: " + this.restockQuantity;
    }
}