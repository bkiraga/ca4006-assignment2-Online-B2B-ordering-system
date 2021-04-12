import java.rmi.Remote;
import java.rmi.RemoteException;;

public interface ServerInterface extends Remote {
    String getProducts() throws RemoteException;
    String login(int customerId) throws RemoteException;
    // int getCustomer() throws RemoteException;
}