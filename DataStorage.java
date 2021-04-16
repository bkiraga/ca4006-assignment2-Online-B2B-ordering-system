import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.ArrayList; 
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DataStorage {
    HashMap<String, Product> productList;
    HashMap<Integer, Order> orderList;
    HashMap<Integer, ArrayList<Order>> orderListByCustomer;
    HashMap<String, ArrayList<Order>> orderListByProduct;
    ArrayBlockingQueue<TaskObject> taskQueue;
    Thread consumerThread;

    public DataStorage(ArrayBlockingQueue<TaskObject> taskQueue) {
        this.taskQueue = taskQueue;
        productList = new HashMap<String, Product>();
        orderList = new HashMap<Integer, Order>();
        orderListByCustomer = new HashMap<Integer, ArrayList<Order>>();
        orderListByProduct = new HashMap<String, ArrayList<Order>>();
        //initialise consumer thread
        consumerThread = new Thread(new ConsumerThread(taskQueue, this));
        consumerThread.start();
    }

    public void addProduct(String productName, int quantity, int restockDate, int restockQuantity) {
        Product product  = new Product(productName, quantity, restockDate, restockQuantity);
        productList.put(productName, product);
    }

    public void addOrder(int customerId, String productName, int quantity, int time) {
        if(productList.containsKey(productName) && (availableProductNumber(productName, time) > 0)) {
            Order order = new Order(customerId, productName, quantity, time);
            orderList.put(order.orderId, order);
            ArrayList<Order> customerOrders;
            if(orderListByCustomer.containsKey(customerId)) {
                customerOrders = orderListByCustomer.get(customerId);
                customerOrders.add(order);
                orderListByCustomer.put(customerId, customerOrders);
            } else {
                customerOrders = new ArrayList<Order>();
                customerOrders.add(order);
                orderListByCustomer.put(customerId, customerOrders);
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
        }
    }

    public void cancelOrder(int orderId) {
        if(orderList.containsKey(orderId)) {
            String product = orderList.get(orderId).productName;
            int customerId = orderList.get(orderId).customerId;
            orderList.remove(orderId);
            ArrayList<Order> orderListByProductEntry = orderListByProduct.get(product);
            for (int i = 0; i < orderListByProductEntry.size(); i++){
                if(orderListByProductEntry.get(i).orderId == orderId) {
                    orderListByProductEntry.remove(i);
                }
            }
            ArrayList<Order> orderListByCustomerEntry = orderListByCustomer.get(customerId);
            for (int i = 0; i < orderListByCustomerEntry.size(); i++){
                if(orderListByCustomerEntry.get(i).orderId == orderId) {
                    orderListByCustomerEntry.remove(i);
                }
            }
        }
    }

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
        String orderDate = timeToDate(time);
        if(Integer.parseInt(orderDate.split("/")[1]) >= product.restockDate) {
            return product.quantity + restockAmount - productOrderTotal + product.restockQuantity;
        }
        return product.quantity + restockAmount - productOrderTotal;
    }

    public String timeToDate(int time) {
        long seconds = time * 60;
        Date date = new Date(seconds * 1000L);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm/dd/MM/yy");
        format.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        String strTime = format.format(date);
        return strTime;
    }

    public String displayProductAvailability(String productName) {
        Date now = new Date();      
        Long timestamp = now.getTime()/1000;
        int time = timestamp.intValue()/60;
        String result = "";
        for(int i = 0; i < 6; i++) {
            int productNumber = availableProductNumber(productName, time);
            String date = timeToDate(time);
            result += productName + ": " + productNumber + " available at " + date + "\n";
            time += 43200;
        }
        return result;
    }

    public String getOrders(int customerId) {
        String orders = "No orders found";
        if(orderListByCustomer.containsKey(customerId)) {
            orders = "My orders: " + "\n";
            ArrayList<Order> orderList = orderListByCustomer.get(customerId);
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