public class TaskObject {
    String task;
    int customerId;
    int orderId;
    String productName;
    int orderQuantity;
    int orderTime;
    public TaskObject(String task, int orderId) {
        this.task = task;
        this.orderId = orderId;
    }
    public TaskObject(String task, int customerId, String productName, int orderQuantity, int orderTime) {
        this.task = task;
        this.customerId = customerId;
        this.productName = productName;
        this.orderQuantity = orderQuantity;
        this.orderTime = orderTime;
    }
}

// public class TaskObject {
//     String task;
//     int customerId;
//     int orderId;
//     String productName;
//     int time;
//     int orderQuantity;
//     int orderTime;
//     public TaskObject(String task) {
//         this.task = task;
//     }
//     public TaskObject(String task, int id) {
//         this.task = task;
//         if(task == "getorders") {
//             this.customerId = id;
//         } else if(task == "cancelorder") {
//             this.orderId = id;
//         }
//     }
//     public TaskObject(String task, String productName, int time) {
//         this.task = task;
//         this.productName = productName;
//         this.time = time;
//     }
//     public TaskObject(String task, String productName, int orderQuantity, int orderTime) {
//         this.task = task;
//         this.productName = productName;
//         this.orderQuantity = orderQuantity;
//         this.orderTime = orderTime;
//     }
// }