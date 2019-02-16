
package Models;

public class Restaurant 
{
    //Buffer for appetizers, desserts, main dishes.
    private Table aTable,dTable,mTable;
    
    //Day time in seconds
    private int daySeconds;
    
    //Maximum number of dishes in each table
    private int maxAppetizer,maxMain,maxDessert;
    
    //Initial number of cooks
    private int initACook,initMCook,initDCook;
    
    //Maximun number of cooks
    private int maxACook,maxMCook,maxDCook;
    
    //Initial number of waiters
    private int initWaiter;
    
    //Maximum number of waiters
    private int maxWaiter;
          
    //Buffer positions
    private int inAppetizers,outAppetizers,inMain,outMain,inDessert,outDessert;
    
    //
    
    public Restaurant(int daySeconds,int maxAppetizer, int maxMain, int maxDessert,int initACook, int initMCook, int initDCook, int maxACook, int maxMCook, int maxDCook, int initWaiter, int maxWaiter)
    {
        //TABLES
            //Initializing JSON Values for the table
            this.maxAppetizer= maxAppetizer;
            this.maxMain= maxMain;
            this.maxDessert= maxDessert;

            //Initializing tables with its own maximum for each type of dish
            this.aTable= new Table(maxAppetizer);
            this.dTable = new Table(maxDessert);
            this.mTable = new Table(maxMain);
        
        //
        
        
        
    }
    
}
