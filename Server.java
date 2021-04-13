import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.concurrent.*;

public class Server implements ServerInterface {
    DataStorage dataStorage;
    HashMap<Integer, LoginThread> loginThreads;
    ExecutorService loginThreadPool;
    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        dataStorage = new DataStorage();
        loginThreads = new HashMap<Integer, LoginThread>();
        loginThreadPool = Executors.newFixedThreadPool(10);
    }

    @Override
    public String getProducts() {
        return dataStorage.displayAllProducts();
    }

    @Override
    public String login(int customerId) {
        LoginThread loginThread = new LoginThread(customerId);
        loginThreads.put(customerId, loginThread);
        loginThreadPool.execute(loginThread);
        return "login successfull";
    }

    @Override
    public String order(int customerId, String productName, int orderQuantity, int orderTime) {
        //find if id exists o nthe hashmap
        //check if product exists check if quantity is possible at given time
        return "order successfull";
    }
}