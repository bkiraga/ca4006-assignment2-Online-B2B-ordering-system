public class UserThread implements Runnable {
    int customerId;
    boolean finished;
    UserThread(int customerId) {
        this.customerId = customerId;
        finished = false;
    }

    public void exit() {
        finished = true;
    }

    public void run() {
        while(finished == false) {
            
        }
    }
}