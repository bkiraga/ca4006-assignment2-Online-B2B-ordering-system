import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.ArrayList;

public class Server implements ServerInterface {
    DataStorage dataStorage;
    HashMap<Integer, UserThread> userThreads;
    ExecutorService userThreadPool;
    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        dataStorage = new DataStorage();
        userThreads = new HashMap<Integer, UserThread>();
        userThreadPool = Executors.newFixedThreadPool(10);
    }

    @Override
    public String getProducts() {
        return dataStorage.displayAllProducts();
    }

    @Override
    public String login(int customerId) {
        UserThread loginThread = new UserThread(customerId);
        userThreads.put(customerId, loginThread);
        userThreadPool.execute(loginThread);
        return "login successfull";
    }

    @Override
    public String order(int customerId, String productName, int orderQuantity, int orderTime) {
        //find if id exists on the hashmap
        //check if product exists check if quantity is possible at given time
        dataStorage.addOrder(customerId, productName, orderQuantity, orderTime);
        return "order successfull";
    }

    @Override
    public int checkAvailability(String productName, int time) {
        return dataStorage.availableProductNumber(productName, time);
    }

    @Override
    public String getOrders(int customerId) {
        return dataStorage.getOrders(customerId);
        // return null;
    }

    @Override
    public String cancelOrder(int orderId) {
        return "abc";
    }
}