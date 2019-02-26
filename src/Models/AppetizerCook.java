
package Models;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppetizerCook extends Thread {
    
    private Table table;                  //Table on which plates will be set
    private int time;                     //Time it takes to produce a dish
    private boolean hire;                 //Indicator whether a cook is hired or not
    private Semaphore SemME, SemAC, SemW; //Mutual Exclusivity, Appetizer Cook and Waiter Semaphores
    private int id;
    
//CLASS CONSTRUCTOR
public AppetizerCook(Table table, int time, Semaphore SemME, Semaphore SemAC, Semaphore SemW)
{
    this.table = table;
    this.time = time;
    this.hire = false;
    this.SemME = SemME;
    this.SemAC = SemAC;
    this.SemW = SemW;
}

//GETTER AND SETTER FOR COOK STATUS
    public boolean isHire() {
        return hire;
    }

    public void setHire(boolean hire) {
        this.hire = hire;
    }

    public int getID(){
        return this.id;
    }
    
    public void setID(int i){
        this.id = i;
    }
    
//DEFINITION OF THREAD RUN METHOD
    @Override
    public void run(){
       while(this.hire){
            try{
                //System.out.println("Appetizer cook number " + this.id + " is cooking");
                this.SemAC.acquire();
                //Wait the set amount of time before producing a plate
                Thread.sleep(this.time);
                
                //Making sure to have ME when setting a plate on the table
                this.SemME.acquire();
                
                //Setting a plate on th table
                this.table.setPlate(Restaurant.inAppetizers, 1);
                Restaurant.inAppetizers = (Restaurant.inAppetizers + 1) % this.table.getMax();
                Restaurant.addAppetizer();
                
                //System.out.println(Restaurant.aCount);
                
                this.SemW.release();
                this.SemME.release();
                
                //If an appetizer cook is fired, reflect this after the finish the last appetizer they started producing
                if(!this.hire){
                    Restaurant.countACook--;
                }
                
            } catch(InterruptedException ex) {
                Logger.getLogger(AppetizerCook.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
