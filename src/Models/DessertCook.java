
package Models;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DessertCook extends Thread {
      
    private Table table;                  //Table on which plates will be set
    private int time;                     //Time it takes to produce a dish
    private boolean hire;                 //Indicator whether a cook is hired or not
    private Semaphore SemME, SemDC, SemW; //Mutual Exclusivity, Dessert Cook and Waiter Semaphores
    private int id;
 
//CLASS CONSTRUCTOR
public DessertCook(Table table, int time, Semaphore SemME, Semaphore SemDC, Semaphore SemW)
{
    this.table = table;
    this.time = time;
    this.hire = false;
    this.SemME = SemME;
    this.SemDC = SemDC;
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
                //System.out.println("Dessert cook number " + this.id + " is cooking");
                this.SemDC.acquire();
                
                //Wait the set amount of time before producing a plate
                Thread.sleep(this.time);
                
                //Making sure to have ME when setting a plate on the table
                this.SemME.acquire();
                
                //Setting a plate on th table
                this.table.setPlate(Restaurant.inDesserts, 1);
                Restaurant.inDesserts = (Restaurant.inDesserts + 1) % this.table.getMax();
                Restaurant.addDessert();
                
                //System.out.println(Restaurant.dCount);
                
                this.SemW.release();
                this.SemME.release();
                
                //If a dessert cook is fired, reflect this after the finish the last dessert they started producing
                if(!this.hire){
                    Restaurant.displayDCook--;
                }
            } catch(InterruptedException ex) {
                Logger.getLogger(DessertCook.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
