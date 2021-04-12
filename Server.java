import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements ServerInterface {
    DataStorage dataStorage;
    // int customer;
    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        dataStorage = new DataStorage();
    }

    @Override
    public String getProducts() {
        return dataStorage.displayAllProducts();
    }

    @Override
    public String login(int customerId) {
        // customer = customerId;
        return "login successfull";
    }

    // @Override
    // public int getCustomer() {
    //     return this.customer;
    // }
}