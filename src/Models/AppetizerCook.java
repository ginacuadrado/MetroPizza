
package Models;

import java.util.concurrent.Semaphore;

public class AppetizerCook extends Thread {
    
    private Table table;            
    private int time;                   //Time it takes to produce a dish
    private int appetizerCount = 0;     //Number of currently available dishes
    private int cookCount;              //Number of currently hired cooks
    private static int in = 0;
    private Semaphore A;
    private boolean hire;
    
 
public AppetizerCook(Table table, int cookCount, int time)
{
    this.table = table;
    this.time = time;
    this.cookCount= cookCount;
    this.A = new Semaphore(0);
    
}

    public boolean isHire() {
        return hire;
    }

    public int getAppetizerCount() {
        return appetizerCount;
    }

    public int getCookCount() {
        return cookCount;
    }

    public void setHire(boolean hire) {
        this.hire = hire;
    }

    public void setIn(int value) {
        in = value;
    }
    
    @Override
    public void run(){
        while(this.hire){
            this.table.setPlate(in, 1);
            in = (in + 1) % this.table.getMax();
            
            
        }
    }
}
