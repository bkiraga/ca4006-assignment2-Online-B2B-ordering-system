import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ClientMain {

    public static int convertTimeStrToUnix(String timeStr) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm/dd/MM/yy");
        try {
            Date date = format.parse(timeStr);
            Long timestamp = date.getTime()/1000;
            return timestamp.intValue()/60;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Client client = new Client();
        client.startClient();

        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("Input command >");
            String command = in.nextLine();
            String products = null;
            String myOrders = null;
            if(command.equalsIgnoreCase("exit")) {
                break;
            }
            if(command.matches("^login [0-9]{4}$")) { //login {4 digits}
                try {
                    int customerId = Integer.parseInt(command.split(" ")[1]);
                    String response = client.login(customerId);
                    System.out.println(response);
                } catch(Exception e) {
                    System.out.print("Error: " + e.getMessage());
                }
            }
            if(command.equalsIgnoreCase("logout")) {
                try {
                    String response = client.logout();
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
            //order {quantity} {time/day/month/year} eg: order laptop 2 12:00/27/12/21
            if(command.matches("^order \\D+ \\d+ ([0-1][0-9]|[2][0-3]):[0-5][0-9]\\/(([0][1-9]|[1][0-9]|[2][0-8])\\/[0][2]|([0][1-9]|[1-2][1-9]|[3][0])\\/([0][4]|[0][6]|[0][9]|[1][1])|([0][1-9]|[1-2][1-9]|[3][0-1])\\/([0][1]|[0][3]|[0][5]|[0][7]|[0][8]|[1][0]|[1][2]))\\/[2][1-9]$")) {
                try {
                    String productName = command.split(" ")[1];
                    int orderQuantity = Integer.parseInt(command.split(" ")[2]);
                    String timeStr = command.split(" ")[3];
                    int orderTime = convertTimeStrToUnix(timeStr);
                    String orderResponse = client.order(productName, orderQuantity, orderTime);
                    System.out.println(orderResponse);
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
            if(command.matches("^availability \\D+ ([0-1][0-9]|[2][0-3]):[0-5][0-9]\\/(([0][1-9]|[1][0-9]|[2][0-8])\\/[0][2]|([0][1-9]|[1-2][1-9]|[3][0])\\/([0][4]|[0][6]|[0][9]|[1][1])|([0][1-9]|[1-2][1-9]|[3][0-1])\\/([0][1]|[0][3]|[0][5]|[0][7]|[0][8]|[1][0]|[1][2]))\\/[2][1-9]$")) {
                try {
                    String productName = command.split(" ")[1];
                    String timeStr = command.split(" ")[2];
                    int orderTime = convertTimeStrToUnix(timeStr);
                    int productAvailability = client.checkAvailability(productName, orderTime);
                    System.out.println(productName + ": " + productAvailability + " available at: " + timeStr);
                } catch(Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            if(command.equalsIgnoreCase("myorders")) {
                try {
                    myOrders = client.getOrders();
                    System.out.println(myOrders);
                } catch(Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            if(command.matches("^cancel \\d+$")) {
                try {
                    int orderId = Integer.parseInt(command.split(" ")[1]);
                    String response = client.cancelOrder(orderId);
                    System.out.println(response);
                } catch(Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        in.close();
    }
}