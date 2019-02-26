
package Models;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaiterChief extends Thread{
    private static int counter;                //Hours counter
    private int time;                   //Time it takes to update the counter
    private Semaphore clockSemaphore;   //Countdown's Mutual Exclusivity Semaphore
    private String status;              //Indicates if the Waiter Chief is resting, waiting or updating the counter

    public WaiterChief(int counter, int time, Semaphore clockSemaphore) {
        WaiterChief.counter = counter;
        this.time = time;
        this.clockSemaphore = clockSemaphore;
        this.status = "RESTING";
    }

    //GETTER FOR WAITER CHIEF STATUS
    public String getStatus() {
        return status;
    }
    
    //RESET HOUR COUNTER
    public static void resetCounter(){
        WaiterChief.counter = 10;
        Restaurant.day++;
    }
    
    //DEFINITION OF THREAD RUN METHOD
    @Override
    public void run(){
        while(true){
            try {
                //Wait 1 hour to start updating clock
                this.status = "RESTING";
                Thread.sleep(Restaurant.hourSeconds*1000);
                
                //If someone else is checking the clock, the Waiter Chief waits until it's free
                if(this.clockSemaphore.availablePermits()<1){
                    this.status = "WAITING";
                }
                
                //Hour counter update process
                this.clockSemaphore.acquire();
                this.status = "UPDATING CLOCK";
                Thread.sleep(this.time);
                
                if(this.counter > 0){
                    this.counter--;
                    Restaurant.countdown = this.counter;
                    Restaurant.resetMessage();
                } else {
                    resetCounter();
                    Restaurant.countdown = this.counter;
                }
                
                this.clockSemaphore.release();
                
            } catch(InterruptedException ex) {
                Logger.getLogger(WaiterChief.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
