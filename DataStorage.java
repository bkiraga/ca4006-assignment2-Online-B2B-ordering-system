import java.util.HashMap;
import java.util.Map;

public class DataStorage {
    HashMap<String, Product> ProductList;
    HashMap<Integer, Order> OrderList;

    public DataStorage() {
        ProductList = new HashMap<String, Product>();
        OrderList = new HashMap<Integer, Order>();
    }

    public void addProduct(String productName, int quantity, int restockRate, int restockQuantity) {
        Product product  = new Product(productName, quantity, restockRate, restockQuantity);
        ProductList.put(productName, product);
    }

    public void addOrder(int customerId, String productName, int quantity) {
        Order order = new Order(customerId, productName, quantity);
        OrderList.put(customerId, order);
    }

    public String displayAllProducts() {
        String products = "";
        for(Map.Entry<String, Product> entry : ProductList.entrySet()) {
            String product = this.displayProduct(entry.getKey());
            products += "\n" + product;
            
        }
        return products;
    }

    public String displayProduct(String productName) {
        return ProductList.get(productName).toString();
    }

    public String displayOrders() {
        return "";
    }
}