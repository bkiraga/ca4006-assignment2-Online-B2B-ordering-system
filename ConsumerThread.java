import java.util.concurrent.ArrayBlockingQueue;

public class ConsumerThread implements Runnable {
    ArrayBlockingQueue<TaskObject> taskQueue;
    DataStorage dataStorage;
    public ConsumerThread(ArrayBlockingQueue<TaskObject> taskQueue, DataStorage dataStorage) {
        this.taskQueue = taskQueue;
        this.dataStorage = dataStorage;
    }
    public void run() {
        while(true) {
            try {
                TaskObject task = taskQueue.take();
                if(task.task == "order") {
                    dataStorage.addOrder(task.customerId, task.productName, task.orderQuantity, task.orderTime);
                } else if(task.task == "cancelorder") {
                    dataStorage.cancelOrder(task.orderId);
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}