
package Models;

import java.util.concurrent.Semaphore;

public class Manager extends Thread 
{
    private boolean resting;    //This attribute defines if the Manager is whether resting or not

    public Manager()
    {
        this.resting=false;
    }

    
    //Get the current Manager status
    public boolean isResting() {
        return resting;
    }

    //Set the Manager status
    public void setResting(boolean resting) {
        this.resting = resting;
    }
    
}
