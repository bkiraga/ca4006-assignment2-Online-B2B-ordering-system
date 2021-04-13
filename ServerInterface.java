import java.rmi.Remote;
import java.rmi.RemoteException;;

public interface ServerInterface extends Remote {
    String getProducts() throws RemoteException;
    String login(int customerId) throws RemoteException;
    String order(int customerId, String productName, int orderQuantity, int orderTime) throws RemoteException;
}