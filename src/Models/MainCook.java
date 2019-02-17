
package Models;

import java.util.concurrent.Semaphore;

public class MainCook extends Thread {
    
    private Table table;     //Shared 
    private int time;
    private int cookCount;
    private int in=0,out=0;
    private boolean hire=false;
    
 
public MainCook()
{
   
}

    public boolean isHire() {
        return hire;
    }

    public void setHire(boolean hire) {
        this.hire = hire;
    }
    
 
}
