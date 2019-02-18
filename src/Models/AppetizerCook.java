
package Models;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppetizerCook extends Thread {
    
    private Table table;            
    private int time;                   //Time it takes to produce a dish
    private static int in = 0;
    private boolean hire;
    
 
public AppetizerCook(Table table, int time)
{
    this.table = table;
    this.time = time;
    this.hire = false;
    
}

    public boolean isHire() {
        return hire;
    }

    public void setHire(boolean hire) {
        this.hire = hire;
    }

    public void setIn(int value) {
        in = value;
    }
    
    @Override
    public void run(){
        try{
            while(this.hire){
                Thread.sleep(this.time);
                this.table.setPlate(in, 1);
                in = (in + 1) % this.table.getMax();
                Restaurant.addAppetizer();
            }
        } catch(InterruptedException ex) {
            Logger.getLogger(AppetizerCook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
