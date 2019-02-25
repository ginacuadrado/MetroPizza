
package Models;

import java.util.concurrent.Semaphore;

public class MainCook extends Thread {
    
    private Table table;                  //Table on which plates will be set
    private int time;                     //Time it takes to produce a dish
    private boolean hire;                 //Indicator whether a cook is hired or not
    private Semaphore SemME, SemMC, SemW; //Mutual Exclusivity, Main Cook and Waiter Semaphores
    
//CLASS CONSTRUCTOR
public MainCook(/*Table table, int time, int in, Semaphore SemME, Semaphore SemMC, Semaphore SemW*/)
{
    /*this.table = table;
    this.time = time;*/
    this.hire = false;
    /*this.SemME = SemME;
    this.SemMC = SemMC;
    this.SemW = SemW;*/
}

    public boolean isHire() {
        return hire;
    }

    public void setHire(boolean hire) {
        this.hire = hire;
    }
    
 
}
