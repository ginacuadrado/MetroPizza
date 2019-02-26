
package Models;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Manager extends Thread {
    
    private int time1, time2;           //Time the manager rests (time1) and time that the manager takes to close all orders and reset clock
    private Semaphore clockSemaphore;   //Countdown's Mutual Exclusivity Semaphore
    private String status;              //Indicates if the Waiter Chief is resting, waiting or updating the counter

    public Manager(int time2, Semaphore clockSemaphore) {
        this.time2 = time2;
        this.clockSemaphore = clockSemaphore;
        this.status = "RESTING";
    }

    //GETTER FOR MANAGER STATUS
    public String getStatus() {
        return status;
    }
 
    //DEFINITION OF THREAD RUN METHOD
    @Override
    public void run(){
        while(true){
            try{
                
                this.status = "RESTING";
                
                //Every hour the Manager goes check the clock
                Thread.sleep(Restaurant.hourSeconds);
                
                //If someone else is checking the clock, the Manager waits until it's free
                if(this.clockSemaphore.availablePermits()<1){
                    this.status = "WAITING";
                }
                
                //The manager goes check the clock
                this.clockSemaphore.acquire();
                
                if(Restaurant.countdown > 0){
                    //If the clock is different from 0, the manager goes rest
                    System.out.println("The manager checked the clock and will rest now.");
                    this.time1 = (int) (1000 * Restaurant.hourSeconds * (0.45 + new Random().nextFloat() * (2 - 0.45)));
                    System.out.println(this.time1);
                    this.status = "RESTING";
                    this.clockSemaphore.release();
                    Thread.sleep(this.time1);
                } else {
                    //If the counter equals 0, the Manager closes all orders and resets the clock
                    this.status = "CLOSING ORDERS";
                    Thread.sleep(this.time2);
                    Restaurant.total = Restaurant.total + Restaurant.orderCount;
                    Restaurant.sales = Restaurant.price * Restaurant.total;
                    Restaurant.countdown = 12;
                    WaiterChief.resetCounter();
                    this.clockSemaphore.release();
                }
                
            } catch(InterruptedException ex) {
                Logger.getLogger(WaiterChief.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
