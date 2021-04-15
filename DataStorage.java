import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList; 
import java.util.Date;

public class DataStorage {
    HashMap<String, Product> productList;
    HashMap<Integer, Order> orderList;
    HashMap<Integer, ArrayList<Order>> customerOrderList;
    HashMap<String, ArrayList<Order>> orderListByProduct;

    public DataStorage() {
        productList = new HashMap<String, Product>();
        orderList = new HashMap<Integer, Order>();
        customerOrderList = new HashMap<Integer, ArrayList<Order>>();
        orderListByProduct = new HashMap<String, ArrayList<Order>>();
    }

    public void addProduct(String productName, int quantity, int restockRate, int restockQuantity) {
        Product product  = new Product(productName, quantity, restockRate, restockQuantity);
        productList.put(productName, product);
    }

    public String addOrder(int customerId, String productName, int quantity, int time) {
        // String response = null;
        if(productList.containsKey(productName) && (availableProductNumber(productName, time) > 0)) {
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
            ArrayList<Order> productOrders;
            if(orderListByProduct.containsKey(productName)) {
                productOrders = orderListByProduct.get(productName);
                productOrders.add(order);
                orderListByProduct.put(productName, productOrders);
            } else {
                productOrders = new ArrayList<Order>();
                productOrders.add(order);
                orderListByProduct.put(productName, productOrders);
            }
            return "Order Placed";
        } else {
            return "Cannot process order";
        }
    }

    // public String cancelOrder(int ) {

    // }

    public int availableProductNumber(String productName, int time) {
        Product product = productList.get(productName);
        Date now = new Date();      
        Long currentTimeSec = now.getTime()/1000;
        int currentTime = currentTimeSec.intValue()/60;
        int timeDifferenceDays = (time - currentTime)/60/24;
        int restockAmount = (timeDifferenceDays / 30) * product.restockQuantity;
        int productOrderTotal = 0;
        if(orderListByProduct.containsKey(productName)) {
            ArrayList<Order> productOrders = orderListByProduct.get(productName);
            for(int i = 0; i < productOrders.size(); i++){
                if(productOrders.get(i).time < time) {
                    productOrderTotal += productOrders.get(i).quantity;
                }
            }
        }
        return product.quantity + restockAmount - productOrderTotal;
    }

    public String getOrders(int customerId) {
        String orders = "No orders found";
        if(customerOrderList.containsKey(customerId)) {
            orders = "My orders: " + "\n";
            ArrayList<Order> orderList = customerOrderList.get(customerId);
            for (int i = 0; i < orderList.size(); i++) {
                orders += orderList.get(i).toString() + "\n";
            }
        }
        return orders;
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