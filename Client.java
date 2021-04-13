import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private ServerInterface server;
    int customerId;
    public Client() {}

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        server = (ServerInterface)registry.lookup("Server");
    }

    public String login(int customerId) {
        String response = null;
        try {
            response = server.login(customerId);
            this.customerId = customerId;
        } catch(RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
        return response;
    }

    public String getProducts() {
        String products = null;
        try {
            products = server.getProducts();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
        return products;
    }

    public String order(String productName, int orderQuantity, int orderTime) {
        int customerId = this.customerId;
        String order = null;
        try {
            order = server.order(customerId, productName, orderQuantity, orderTime);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
        return order;
    }

    public String checkAvailability(String productName, int time) {
        String availability = null;
        try {
            availability = server.checkAvailability(productName, time);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
        return availability;
    }

    public String getOrders() {
        int customerId = this.customerId;
        String orders = null;
        try {
            orders = server.getOrders(customerId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
        return orders;
    }

    public String cancelOrder(int orderId) {
        String response = null;
        try {
            response = server.cancelOrder(orderId);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
        return response;
    }

}