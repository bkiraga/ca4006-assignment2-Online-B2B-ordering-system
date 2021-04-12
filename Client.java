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

    // public int getCustomer() {
    //     int customer = 0;
    //     try {
    //         customer = server.getCustomer();
    //     } catch (RemoteException e) {
    //         e.printStackTrace();
    //         throw new RuntimeException("Could not connect to server");
    //     }
    //     return customer;
    // }
}