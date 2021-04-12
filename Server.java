import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements ServerInterface {
    DataStorage dataStorage;
    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        dataStorage = new DataStorage();
    }

    @Override
    public String toUpperCase(String str) {
        return str.toUpperCase();
    }

    @Override
    public String getProducts() {
        return dataStorage.displayAllProducts();
    }
}