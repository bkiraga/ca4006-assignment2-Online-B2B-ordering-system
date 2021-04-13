import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList; 

public class DataStorage {
    HashMap<String, Product> productList;
    HashMap<Integer, Order> orderList;
    HashMap<Integer, ArrayList<Order>> customerOrderList;

    public DataStorage() {
        productList = new HashMap<String, Product>();
        orderList = new HashMap<Integer, Order>();
        customerOrderList = new HashMap<Integer, ArrayList<Order>>();
    }

    public void addProduct(String productName, int quantity, int restockRate, int restockQuantity) {
        Product product  = new Product(productName, quantity, restockRate, restockQuantity);
        productList.put(productName, product);
    }

    public void addOrder(int customerId, String productName, int quantity, int time) {
        Order order = new Order(customerId, productName, quantity, time);
        orderList.put(order.orderId, order);
        ArrayList<Order> customerOrders;
        if(customerOrderList.containsKey(customerId)) {
            customerOrders = customerOrderList.get(customerId);
            customerOrders.add(order);
            customerOrderList.put(customerId, customerOrders);
        } else {
            customerOrders = new ArrayList<Order>();
            customerOrders.add(order);
            customerOrderList.put(customerId, customerOrders);
        }
    }

    public String displayAllProducts() {
        String products = "";
        for(Map.Entry<String, Product> entry : productList.entrySet()) {
            String product = this.displayProduct(entry.getKey());
            products += product + "\n";
            
        }
        return products;
    }

    public String displayProduct(String productName) {
        return productList.get(productName).toString();
    }

    public String displayOrder(int orderId) {
        return orderList.get(orderId).toString();
    }

    public String displayAllOrders() {
        String orders = "";
        for(Map.Entry<Integer, Order> entry : orderList.entrySet()) {
            String order = this.displayOrder(entry.getKey());
            orders += order + "\n";
            
        }
        return orders;
    }
}