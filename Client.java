import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private ServerInterface server;
    public Client() {}

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        server = (ServerInterface)registry.lookup("Server");
    }

    public String toUpperCase(String argument) {
        String result = null;
        try {
            result = server.toUpperCase(argument);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
        return result;
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
}