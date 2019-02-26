
package Models;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaiterChief extends Thread{
    private int counter;                //Hours counter
    private int time;                   //Time it takes to update the counter
    private Semaphore clockSemaphore;   //Countdown's Mutual Exclusivity Semaphore
    private String status;              //Indicates if the Waiter Chief is resting, waiting or updating the counter

    public WaiterChief(int counter, int time, Semaphore clockSemaphore) {
        this.counter = counter;
        this.time = time;
        this.clockSemaphore = clockSemaphore;
        this.status = "WAITING";
    }

    //GETTER FOR WAITER CHIEF STATUS
    public String getStatus() {
        return status;
    }
    
    //RESET HOUR COUNTER
    public void resetCounter(){
        this.counter = 12;
    }
    
    //DEFINITION OF THREAD RUN METHOD
    @Override
    public void run(){
        while(true){
            try {
                //Wait 1 hour to start updating clock
                this.status = "WAITING";
                Thread.sleep(Restaurant.hourSeconds*1000);
                
                //Hour counter update process
                this.status = "UPDATING CLOCK";
                this.clockSemaphore.acquire();
                Thread.sleep(this.time);
                
                if(this.counter > 0){
                    this.counter--;
                    Restaurant.countdown = this.counter;
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
