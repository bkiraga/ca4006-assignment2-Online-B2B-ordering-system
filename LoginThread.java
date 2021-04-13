public class LoginThread implements Runnable {
    int customerId;
    boolean finished;
    LoginThread(int customerId) {
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