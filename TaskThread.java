import java.util.concurrent.ArrayBlockingQueue;

public class TaskThread implements Runnable {
    String task;
    int customerId;
    int orderId;
    String productName;
    int orderQuantity;
    int orderTime;
    ArrayBlockingQueue<TaskObject> taskQueue;
    public TaskThread(ArrayBlockingQueue<TaskObject> taskQueue, String task, int orderId) {
        this.taskQueue = taskQueue;
        this.task = task;
        this.orderId = orderId;
    }
    public TaskThread(ArrayBlockingQueue<TaskObject> taskQueue, String task, int customerId, String productName, int orderQuantity, int orderTime) {
        this.taskQueue = taskQueue;
        this.task = task;
        this.customerId = customerId;
        this.productName = productName;
        this.orderQuantity = orderQuantity;
        this.orderTime = orderTime;
    }
    public void run() {
        if(this.task == "order") {
            TaskObject orderTask = new TaskObject("order", customerId, productName, orderQuantity, orderTime);
            try {
                taskQueue.put(orderTask);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        } else if(this.task == "cancelorder") {
            TaskObject cancelOrderTask = new TaskObject("cancelorder", orderId);
            try {
                taskQueue.put(cancelOrderTask);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// import java.util.concurrent.ArrayBlockingQueue;

// public class TaskThread implements Runnable {
//     String task;
//     int customerId;
//     int orderId;
//     String productName;
//     int time;
//     int orderQuantity;
//     int orderTime;
//     ArrayBlockingQueue<TaskObject> taskQueue;
//     public TaskThread(ArrayBlockingQueue<TaskObject> taskQueue, String task) {
//         this.taskQueue = taskQueue;
//         this.task = task;
//     }
//     public TaskThread(ArrayBlockingQueue<TaskObject> taskQueue, String task, int id) {
//         this.taskQueue = taskQueue;
//         this.task = task;
//         if(task == "getorders") {
//             this.customerId = id;
//         } else if(task == "cancelorder") {
//             this.orderId = id;
//         }
//     }
//     public TaskThread(ArrayBlockingQueue<TaskObject> taskQueue, String task, String productName, int time) {
//         this.taskQueue = taskQueue;
//         this.task = task;
//         this.productName = productName;
//         this.time = time;
//     }
//     public TaskThread(ArrayBlockingQueue<TaskObject> taskQueue, String task, String productName, int orderQuantity, int orderTime) {
//         this.taskQueue = taskQueue;
//         this.task = task;
//         this.productName = productName;
//         this.orderQuantity = orderQuantity;
//         this.orderTime = orderTime;
//     }
//     public void run() {
//         if(this.task == "getproducts") {
//             TaskObject getProductsTask = new TaskObject("getproducts");
//             try {
//                 taskQueue.put(getProductsTask);
//             } catch(InterruptedException e) {
//                 e.printStackTrace();
//             }
//         } else if(this.task == "order") {
//             TaskObject orderTask = new TaskObject("order", productName, orderQuantity, orderTime);
//             try {
//                 taskQueue.put(orderTask);
//             } catch(InterruptedException e) {
//                 e.printStackTrace();
//             }
//         } else if(this.task == "checkavailability") {
//             TaskObject checkAvailabilityTask = new TaskObject("checkavailability", productName, time);
//             try {
//                 taskQueue.put(checkAvailabilityTask);
//             } catch(InterruptedException e) {
//                 e.printStackTrace();
//             }
//         } else if(this.task == "getorders") {
//             TaskObject getOrdersTask = new TaskObject("getorders", customerId);
//             try {
//                 taskQueue.put(getOrdersTask);
//             } catch(InterruptedException e) {
//                 e.printStackTrace();
//             }
//         } else if(this.task == "cancelorder") {
//             TaskObject cancelOrderTask = new TaskObject("cancelorder", orderId);
//             try {
//                 taskQueue.put(cancelOrderTask);
//             } catch(InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }