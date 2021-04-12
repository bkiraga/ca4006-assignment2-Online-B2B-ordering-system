import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements ServerInterface {

    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public String toUpperCase(String str) {
        return str.toUpperCase();
    }

    // @Override
    // public String toLowerCase(String str) {
    //     return str.toLowerCase();
    // }
}