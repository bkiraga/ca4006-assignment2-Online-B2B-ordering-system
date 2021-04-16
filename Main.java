import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    //test file (not important to the project)
    public static void main(String[] args) {
        ArrayBlockingQueue<TaskObject> taskQueue = new ArrayBlockingQueue<TaskObject>(10);
        DataStorage ds = new DataStorage(taskQueue);
        ds.addProduct("laptop", 28, 6, 2);
        System.out.println(ds.displayProductAvailability("laptop"));
        // ds.addProduct("tv", 22, 7, 3);
        // ds.addProduct("ps4", 20, 3, 4);
        // ds.addProduct("xbox", 18, 3, 4);
        // ds.addProduct("pc", 14, 6, 2);
        // System.out.println(ds.addOrder(1234, "laptop", 1, 1640606300/60));
        // System.out.println(ds.addOrder(1234, "tv", 2, 1640606300/60));
        // System.out.println(ds.customerOrderList.containsKey(1234));
        // System.out.println(ds.availableProductNumber("laptop", 1640606400/60));
        // System.out.println(ds.getOrders(1234));
    }
}