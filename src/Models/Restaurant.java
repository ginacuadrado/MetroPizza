
package Models;

import Controllers.JSONController;
import static javax.swing.JOptionPane.showMessageDialog;

public class Restaurant 
{
    //Buffer for appetizers, desserts, main dishes.
    private static Table aTable,dTable,mTable;
    
    //Day time in seconds
    private long hourSeconds;
    
    //Maximum number of dishes in each table
    private int maxAppetizer,maxMain,maxDessert;
    
    //Count of every type of dish
    public static int aCount=0, mCount=0, dCount=0;
    
    //Count of every type of employee
    public static int acCount=0, mcCount=0, dcCount=0, wCount=0;
    
    //Initial number of cooks
    private int initACook,initMCook,initDCook;
    
    //Maximun number of cooks
    private int maxACook,maxMCook,maxDCook;
    
    //Initial number of waiters
    private int initWaiter;
    
    //Maximum number of waiters
    private int maxWaiter;
          
    //Buffer positions
    public static int inAppetizers,outAppetizers,inMain,outMain,inDesserts,outDesserts;
    
    //Declaration of an Array for keeping track of hired employees 
    private AppetizerCook[] appetizerCook;
    private MainCook[] mainCook;
    private DessertCook[] dessertCook;
    private Waiter[] waiter;
    
    //JSON Controller
    public JSONController json = new JSONController();
    
    
    public Restaurant()
    {
        //TABLES
            //Initializing JSON Values for the table
            this.maxAppetizer = this.json.maxAppetizer;
            this.maxMain = this.json.maxMain;
            this.maxDessert = this.json.maxDessert;

            //Initializing tables with its own maximum for each type of dish
            this.aTable = new Table(this.json.maxAppetizer);
            this.dTable = new Table(this.json.maxDessert);
            this.mTable = new Table(this.json.maxMain);
        
        //INITIAL VALUES FOR EMPLOYEES AT THE BEGGINING OF THE EXECUTION 
            this.initACook = this.json.initACook;
            this.initDCook = this.json.initDCook;
            this.initMCook = this.json.initMCook;
            this.initWaiter = this.json.initWaiter;
        
        //ASSIGNING INITIAL IN AND OUT VALUES
            this.inAppetizers = 0;
            this.outAppetizers = 0;
            this.inDesserts = 0;
            this.outDesserts = 0;
            this.inMain = 0;
            this.outMain = 0;
            
        //ARRAYS FOR EMPLOYEES
            //Initializing maximum number of employees of each type
            this.maxACook = this.json.maxACook;
            this.maxMCook = this.json.maxMCook;
            this.maxDCook = this.json.maxDCook;
            this.maxWaiter = this.json.maxWaiter;
            
        //Initializing arrays with employees
            this.appetizerCook = new AppetizerCook[this.maxACook];
            this.mainCook = new MainCook[this.maxMCook];
            this.dessertCook = new DessertCook[this.maxDCook];
            this.waiter = new Waiter[this.maxWaiter];
            
            //Initializing array with Appetizer Cooks
            for(int init=0; init<maxACook; init++)
            {
            //    this.appetizerCook[init]= new AppetizerCook(aTable,750);
            }
            
            //Initializing array with Main Cooks
            for(int init=0; init<maxMCook; init++)
            {
            //    this.mainCook[init]= new MainCook();
            }
            
            //Initializing array with Dessert Cooks
            for(int init=0; init<maxDCook; init++)
            {
            //    this.dessertCook[init]= new DessertCook();
            }
            
            //Initializing array with Waiters
            for(int init=0; init<maxWaiter; init++)
            {
            //    this.waiter[init] = new Waiter();
            }
            /*
            //Hire initial number of Appetizers Cooks wanted in the restaurant
            int i=0;
            do {
                hireACook(1);
                i++;
            }while(i<initACook);
            
            //Hire initial number of Main Cooks wanted in the restaurant
            i=0;
            do {
                hireMCook(1);
                i++;
                
            }while(i<initMCook);
            
            //Hire initial number of Waiters wanted in the restaurant
            i=0;
            do {
                hireWaiter(1);
                i++;
            }while(i<initWaiter);*/
            
    }

    
//M E T H O D S
    
//HIRE AN APPETIZER COOK
    public void hireACook(int value)
    {
        int c = value;  //Number of cooks that need to be hired
        
        //Validation for maximum of Appetizer Cooks that can be hired
        if(Restaurant.acCount==this.maxACook){
            //showMessageDialog(null,"ERROR! You can't hire any more Appetizer Cooks");
            System.out.println("ERROR! You can't hire any more Appetizer Cooks");
            return;
        }
        
        //Hiring a new Appetizer Cook for the restaurant
        for(int i=0; i<this.maxACook; i++)
        {
            if(!this.appetizerCook[i].isHire() && c > 0)
            {
                this.appetizerCook[i].setHire(true);
                c=c-1;
                Restaurant.acCount++;
            }
        }
    }
      
    
//HIRE A MAIN COOK
    public void hireMCook(int value)
    {
        int c = value;  //Number of cooks that need to be hired
        
        //Validation for maximum of Main Cooks that can be hired
        if(Restaurant.mcCount==this.maxMCook){
            showMessageDialog(null,"ERROR! You can't hire any more Main Cooks");
            return;
        }
        
        //Hiring a new Main Cook for the restaurant
        for(int i=0; i<this.maxMCook; i++)
        {
            if(!this.mainCook[i].isHire() && c > 0)
            {
                this.mainCook[i].setHire(true);
                c=c-1;
                Restaurant.mcCount++;
            }
        }
    }
    
    
//HIRE A DESSERT COOK 
    public void hireDCook(int value)
    {
        int c = value;  //Number of cooks that need to be hired
        
        //Validation for maximum of Dessert Cooks that can be hired
        if(Restaurant.dcCount==this.maxDCook){
            showMessageDialog(null,"ERROR! You can't hire any more Dessert Cooks");
            return;
        }
        
        //Hiring a new Dessert Cook for the restaurant
        for(int i=0; i<this.maxDCook; i++)
        {
            if(!this.dessertCook[i].isHire() && c > 0)
            {
                this.dessertCook[i].setHire(true);
                c=c-1;
                Restaurant.dcCount++;
            }
        }
    }
    

//HIRE A WAITER 
    public void hireWaiter(int value)
    {
        int c = value;  //Number of waiters that need to be hired
        
        //Validation for maximum of Dessert Cooks that can be hired
        if(Restaurant.wCount==this.maxWaiter){
            System.out.println("ERROR! You can't hire any more Waiters");
            return;
        }
        
        //Hiring a new Waiter for the restaurant
        for(int i=0; i<this.maxWaiter; i++)
        {
            if(!this.waiter[i].isHire() && c > 0)
            {
                this.waiter[i].setHire(true);
                c=c-1;
                Restaurant.wCount++;
            }
        }
    }

 //FIRE A APPETIZER COOK
    public void fireACook(int value)
    {
        int c = value;  //Number of waiters that need to be fired
        
        //Validation for maximum of Waiter that can be hired
        if(Restaurant.acCount==0){
            System.out.println("ERROR! You don't have any more Appetizer Cooks to fire");
            return;
        }
        
        //Firing a current Appetizer Cooks of the restaurant
        for(int i=maxACook-1; i>-1; i--)
        {
            if(this.appetizerCook[i].isHire() && c > 0)
            {
                this.appetizerCook[i].setHire(false);
                System.out.println("An Appetizer Cook was fired");
                c=c-1;
                Restaurant.acCount--;
            }
        }
    }   

 //FIRE A MAIN COOK
    public void fireMCook(int value)
    {
        int c = value;  //Number of waiters that need to be fired
        
        //Validation for maximum of Main Cook that can be fired
        if(Restaurant.mcCount==0){
            System.out.println("ERROR! You don't have any Main Cooks to fire");
            return;
        }
                
        //Firing a current Main Cooks of the restaurant
        for(int i=maxMCook; i>-1; i--)
        {
            if(this.mainCook[i].isHire() && c > 0)
            {
                this.mainCook[i].setHire(false);
                System.out.println("A Main Cook was fired");
                c=c-1;
                Restaurant.mcCount--;
            }
        }
    }   


 //FIRE A DESSERT COOK
    public void fireDCook(int value)
    {
        int c = value;  //Number of waiters that need to be fired
        
        //Validation for maximum of Dessert Cook that can be fired
        if(Restaurant.dcCount==0){
            System.out.println("ERROR! You don't have any Dessert Cooks to fire");
            return;
        }
        
        //Firing a current Dessert Cooks of the restaurant
        for(int i=maxDCook; i>-1; i--)
        {
            if(this.dessertCook[i].isHire() && c > 0)
            {
                this.dessertCook[i].setHire(false);
                System.out.println("A Dessert Cook was fired");
                c=c-1;
                Restaurant.dcCount--;
            }
        }
    }   
    

    
//FIRE A WAITER 
    public void fireWaiter(int value)
    {
        int c = value;  //Number of waiters that need to be hired
        
        //Validation for maximum of Waiter that can be hired
        if(Restaurant.wCount==0){
            System.out.println("ERROR! You don't have any waiters to fire");
            return;
        }
        
        //Firing a current Waiter of the restaurant
        for(int i=maxWaiter; i>-1; i--)
        {
            if(this.waiter[i].isHire() && c > 0)
            {
                this.waiter[i].setHire(false);
                c=c-1;
                Restaurant.wCount--;
            }
        }
    }

//ADD 1 APPETIZER
    public static void addAppetizer(){
        Restaurant.aCount++;
    }

//ADD 1 MAIN DISH
    public static void addMain(){
        Restaurant.mCount++;
    }

//ADD 1 DESSERT
    public static void addDessert(){
        Restaurant.dCount++;
    }

    /*
//ADD 1 APPETIZER COOK
    public static void addACook(){
        Restaurant.acCount++;
    }
    
//ADD 1 MAIN DISH COOK
    public static void addMCook(){
        Restaurant.mcCount++;
    }
//ADD 1 DESSERT COOK
    public static void addDCook(){
        Restaurant.dcCount++;
    }
    
//ADD 1 WAITER
    public static void addWaiter(){
        Restaurant.wCount++;
    }*/
}
