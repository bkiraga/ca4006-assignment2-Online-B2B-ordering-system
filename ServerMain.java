import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Server server = new Server();
        server.dataStorage.addProduct("laptop", 28, 5, 2);
        server.dataStorage.addProduct("tv", 22, 7, 3);
        server.dataStorage.addProduct("ps4", 20, 3, 4);
        server.dataStorage.addProduct("xbox", 18, 3, 4);
        server.dataStorage.addProduct("pc", 14, 6, 2);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("Server", server);
        System.out.println("Server started");
    }
}