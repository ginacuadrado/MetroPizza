
package Models;

import java.util.concurrent.Semaphore;

public class DessertCook extends Thread {
    
     
    private Table table;     //Shared 
    private int time;
    private int cookCount;
    private int in=0,out=0;
    private boolean hire=false;
    
 
public DessertCook()
{
    
}

    public boolean isHire() {
        return hire;
    }

    public void setHire(boolean hire) {
        this.hire = hire;
    }
    
}
