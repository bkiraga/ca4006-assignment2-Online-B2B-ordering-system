import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Date;

public class Server implements ServerInterface {
    DataStorage dataStorage;
    HashMap<Integer, TaskThread> taskThreads;
    ExecutorService taskThreadPool;
    HashMap<Integer, Boolean> loggedInUsers;
    ArrayBlockingQueue<TaskObject> taskQueue;

    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        loggedInUsers = new HashMap<Integer, Boolean>();
        taskQueue = new ArrayBlockingQueue<TaskObject>(10);
        dataStorage = new DataStorage(taskQueue);
        taskThreads = new HashMap<Integer, TaskThread>();
        taskThreadPool = Executors.newFixedThreadPool(10);
    }

    @Override
    public String getProducts() {
        return dataStorage.displayAllProducts();
    }

    @Override
    public String login(int customerId) {
        loggedInUsers.put(customerId, true);
        return "login successfull";
    }

    @Override
    public Boolean getLoginStatus(int customerId) {
        if(loggedInUsers.containsKey(customerId)){
            return loggedInUsers.get(customerId);
        } else {
            return false;
        }
    }

    @Override
    public String logout(int customerId) {
        loggedInUsers.put(customerId, false);
        return "logout successfull";
    }

    @Override
    public String order(int customerId, String productName, int orderQuantity, int orderTime) {
        if(dataStorage.productList.containsKey(productName) && (dataStorage.availableProductNumber(productName, orderTime) > 0)) {
            TaskThread orderThread = new TaskThread(taskQueue, "order",customerId, productName, orderQuantity, orderTime);
            taskThreadPool.execute(orderThread);
            // dataStorage.addOrder(customerId, productName, orderQuantity, orderTime);
            return "order successfull";
        }
        return "order unsuccessfull";
    }

    @Override
    public int checkAvailability(String productName, int time) {
        return dataStorage.availableProductNumber(productName, time);
    }

    @Override
    public String checkAvailabilityForSixMonths(String productName) {
        return dataStorage.displayProductAvailability(productName);
    }

    @Override
    public String getOrders(int customerId) {
        return dataStorage.getOrders(customerId);
    }

    @Override
    public String cancelOrder(int orderId) {
        if(dataStorage.orderList.containsKey(orderId)) {
            TaskThread cancelOrderThread = new TaskThread(taskQueue, "cancelorder", orderId);
            taskThreadPool.execute(cancelOrderThread);
            // return dataStorage.cancelOrder(orderId);
            return "Order Cancelled";
        }
        return "Order not found";
    }
}