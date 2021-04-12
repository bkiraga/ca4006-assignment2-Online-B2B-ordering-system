import java.util.HashMap;

public class DataStorage {
    HashMap<String, Product> ProductList;
    HashMap<Integer, Order> OrderList;

    public void addProduct(String productName, int quantity, int restockRate, int restockQuantity) {
        Product product  = new Product(productName, quantity, restockRate, restockQuantity);
        ProductList.put(productName, product);
    }

    public void addOrder(int customerId, String productName, int quantity) {
        Order order = new Order(customerId, productName, quantity);
        OrderList.put(customerId, order);
    }

    public String displayProducts() {
        return "";
    }

    public String displayOrders() {
        return "";
    }
}