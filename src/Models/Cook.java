
package Models;

import java.util.concurrent.Semaphore;

public class Cook  extends Thread {
    
    private Table table;
    //private Semaphore sema1, sema2, sema3;
    private int time, type;
    private boolean hired;
    
    public Cook(Table table, int time, boolean hired, int type){
        this.table = table;
        this.time = time;
        this.hired = hired;
        this.type = type;
    }
    
    @Override 
        public void run(){
            while(this.hired){
                switch (this.type){
                    case 0:
                        
                        break;
                        
                    case 1:
                        
                        break;
                        
                    case 2:
                        
                        break;
                        
                    default:
                        System.out.println("Error en producción de platos");
                        break;
                }
            }
        }
}
