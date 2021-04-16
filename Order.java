import java.util.Random;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;

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

    public String timeToDate(int time) {
        long seconds = time * 60;
        Date date = new Date(seconds * 1000L);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm/dd/MM/yy");
        format.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        String strTime = format.format(date);
        return strTime;
    }

    public String toString() {
        return "CustomerId: " + this.customerId + " | ProductName: " + this.productName + " | Quantity: " + this.quantity + " | Time: " + this.timeToDate(this.time) + " | OrderID: " + orderId;
    }
}