
package Models;

import java.util.concurrent.Semaphore;

public class AppetizerCook extends Thread {
    
    private Table table;     //Shared 
    private int time;
    private int cookCount;
    public int in=0,out=0;
    private boolean hire=false;
    
 
public AppetizerCook()
{
    
}

    public boolean isHire() {
        return hire;
    }


    public void setHire(boolean hire) {
        this.hire = hire;
    }
    
    



}
