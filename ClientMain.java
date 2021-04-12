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
            // int customer = 0;
            if(command.equalsIgnoreCase("exit")) {
                break;
            }
            if(command.matches("^login [0-9]{4}$")) { //login {4 digits}
                try {
                    // System.out.println(command);
                    int customerId = Integer.parseInt(command.split(" ")[1]);
                    // System.out.println(id);
                    String response = client.login(customerId);
                    System.out.println(response);
                } catch(Exception e) {
                    System.out.print("Error: " + e.getMessage());
                }
            }
            if(command.equalsIgnoreCase("getproducts")) {
                try {
                    products = client.getProducts();
                    System.out.println("Products: > " + "\n" + products);
                } catch(Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            // if(command.equalsIgnoreCase("customer")) {
            //     try {
            //         customer = client.getCustomer();
            //         System.out.println(customer);
            //     } catch(Exception e) {
            //         System.out.println("Error: " + e.getMessage());
            //     }
            // }
        }
        in.close();
    }
}