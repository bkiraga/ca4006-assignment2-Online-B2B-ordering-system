import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Client client = new Client();
        client.startClient();

        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("Input command >");
            String command = in.nextLine();
            String products = null;
            if(command.equalsIgnoreCase("exit")) break;
            if(command.equalsIgnoreCase("getproducts")) {
                try {
                    products = client.getProducts();
                    System.out.println("Products: > " + "\n" + products);
                } catch(Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }

            }
            // String result = null;
            // try {
            //     result = client.toUpperCase(command);
            // } catch(Exception e) {
            //     System.out.println("Error: " + e.getMessage());
            // }
            // System.out.println("Result > " + result);
        }
    }
}