
package Models;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Waiter extends Thread{
    
    
    private Table aTable, mTable, dTable;                                       //Shared
    private int time;                                                           //Time it takes to assemble an order
    private Semaphore MEA, MEM, MED, waitA, waitM, waitD, semAC, semMC, semDC;  //Necessary Semaphores to assemble an order
    private boolean hire;                                                       //Indicator for whether a waiter is hired or not
    private int id;
    
    //CLASS CONSTRUCTOR
    public Waiter(Table aTable, Table mTable, Table dTable, int time, Semaphore MEA, Semaphore MEM, Semaphore MED, Semaphore waitA, Semaphore waitM, Semaphore waitD, Semaphore semAC, Semaphore semMC, Semaphore semDC) {
        this.aTable = aTable;
        this.mTable = mTable;
        this.dTable = dTable;
        this.time = time;
        this.MEA = MEA;
        this.MEM = MEM;
        this.MED = MED;
        this.waitA = waitA;
        this.waitM = waitM;
        this.waitD = waitD;
        this.semAC = semAC;
        this.semMC = semMC;
        this.semDC = semDC;
        this.hire = false;
    }

//GETTERS AND SETTERS FOR HIRE ATRIBUTE
    public boolean isHire() {
        return hire;
    }

    public void setHire(boolean hire) {
        this.hire = hire;
    }
    
//ID GETTER AND SETTER
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
    
//DEFINITION OF THREAD RUN METHOD
    @Override
    public void run(){
        //System.out.println("Hello! I am waiter number " + this.id);
        while(this.hire){
           try{
               //Acquire permits to take dishes
               this.waitA.acquire(3);
               this.waitM.acquire(2);
               this.waitD.acquire();
               
               //TAKE DISHES OFF TABLES TO ASSEMBLE AN ORDER
               
               //Taking 3 appetizers
               this.MEA.acquire();          //Appetizer Mutual Exclusivity
               for(int i = 0; i < 3; i++){
                   this.aTable.setPlate(Restaurant.outAppetizers, 0);
                   Restaurant.outAppetizers = (Restaurant.outAppetizers + 1) % this.aTable.getMax();
                   Restaurant.removeAppetizer();
               }
               this.MEA.release();
               
               //Taking 2 main dishes
               this.MEM.acquire();          //Main Dish Mutual Exclusivity
               for(int i = 0; i < 2; i++){
                   this.mTable.setPlate(Restaurant.outMain, 0);
                   Restaurant.outMain = (Restaurant.outMain + 1) % this.mTable.getMax();
                   Restaurant.removeMain();
               }
               this.MEM.release();
               
               //Taking 1 desser
               this.MED.acquire();          //Dessert Mutual Exclusivity
               this.dTable.setPlate(Restaurant.outDesserts, 0);
               Restaurant.outDesserts = (Restaurant.outDesserts + 1) % this.dTable.getMax();
               Restaurant.removeDessert();
               this.MED.release();
               
               //Releasing permits to continue cooking
               this.semAC.release(3);   //Release 3 appetizers
               this.semMC.release(2);   //Release 2 main dishes
               this.semDC.release();    //Release 1 dessert
               
               //Waiter goes assemble the order
               Thread.sleep(this.time);
               
               //Waiter adds order to order count
               Restaurant.addOrder();
               
               //If a waiter is fired, reflect this after they finish they last order they started assembling
               if(!this.hire){
                   Restaurant.countWaiter--;
               }
           } catch(InterruptedException ex) {
               Logger.getLogger(AppetizerCook.class.getName()).log(Level.SEVERE, null, ex);
               Logger.getLogger(DessertCook.class.getName()).log(Level.SEVERE, null, ex);
               Logger.getLogger(MainCook.class.getName()).log(Level.SEVERE, null, ex);
               Logger.getLogger(Waiter.class.getName()).log(Level.SEVERE, null, ex);
           } 
        }
    }
}
