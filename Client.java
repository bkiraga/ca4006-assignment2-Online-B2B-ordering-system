import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Client {
    private ServerInterface server;
    int customerId = 0;
    public Client() {}

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        server = (ServerInterface)registry.lookup("Server");
    }

    public String login(int customerId) {
        String response = null;
        try {
            response = server.login(customerId);
            this.customerId = customerId;
        } catch(RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
        return response;
    }

    public String logout() {
        String response = "Already loggedout";
        if(this.customerId != 0) {
            try {
                response = server.logout(this.customerId);
                this.customerId = 0;
            } catch(RemoteException e) {
                e.printStackTrace();
                throw new RuntimeException("Could not connect to server");
            }
        }
        return response;
    }

    public boolean getLoginStatus(int customerId) {
        if(this.customerId != 0) {
            try {
                return server.getLoginStatus(customerId);
            } catch (RemoteException e) {
                e.printStackTrace();
                throw new RuntimeException("Could not connect to server");
            }
        }
        return false;
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

    public String order(String productName, int orderQuantity, int orderTime) {
        int customerId = this.customerId;
        String order = "Login to order";
        if (customerId != 0) {
            try {
                order = server.order(customerId, productName, orderQuantity, orderTime);
            } catch(Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Could not connect to server");
            }
        }
        return order;
    }

    public int checkAvailability(String productName, int time) {
        int availability = 0;
        try {
            availability = server.checkAvailability(productName, time);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
        return availability;
    }

    public String checkAvailabilityForSixMonths(String productName) {
        String availability = null;
        try {
            availability = server.checkAvailabilityForSixMonths(productName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
        return availability;
    }

    public String getOrders() {
        int customerId = this.customerId;
        String orders = "Login to get orders";
        if (customerId != 0) {
            try {
                orders = server.getOrders(customerId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Could not connect to server");
            }
        }
        return orders;
    }

    public String cancelOrder(int orderId) {
        int customerId = this.customerId;
        String response = "Login to cancel order";
        if(customerId != 0) {
            try {
                response = server.cancelOrder(orderId);
            } catch(Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Could not connect to server");
            }
        }
        return response;
    }

    //random tasks for test mode
    public void randomTasks() {
        Random random = new Random();
        int id = random.nextInt(8000) + 1000;
        int taskCount = random.nextInt(4) + 1;
        int taskId = random.nextInt(4);
        this.login(id);
        for(int i = 0; i < taskCount; i++) {
            try {
                Thread.sleep(random.nextInt(5000) + 1000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            if(taskId == 0) {
                try {
                    this.getOrders();
                } catch( Exception e) {
                    e.printStackTrace();
                }
            }
            else if(taskId == 1) {
                try {
                    this.checkAvailabilityForSixMonths("laptop");
                } catch( Exception e) {
                    e.printStackTrace();
                }
            }
            else if(taskId == 2) {
                try {
                    this.order("laptop", random.nextInt(1)+10, random.nextInt(30000000) + 27000000);
                } catch( Exception e) {
                    e.printStackTrace();
                }
            }
            else if(taskId == 3) {
                try {
                    this.getProducts();
                } catch( Exception e) {
                    e.printStackTrace();
                }
            }
            else if(taskId == 4) {
                try {
                    this.cancelOrder(random.nextInt(1000000) + 1000);
                } catch( Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.logout();
    }

}