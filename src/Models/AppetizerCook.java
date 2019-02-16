
package Models;

import java.util.concurrent.Semaphore;

public class AppetizerCook extends Thread {
    
    private Table table;     //Shared 
    private int time;
    private int appetizerCount=0;   //
    private int cookCount;
    private int in=0,out=0;
    private boolean hire
    
 
public AppetizerCook(Table table, int cookCount, int time)
{
    this.table = table;
    this.time= time;
    this.cookCount= cookCount;
    
}



}
