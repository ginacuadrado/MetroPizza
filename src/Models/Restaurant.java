
package Models;

import Controllers.JSONController;
import Views.InitialView;
import java.util.concurrent.Semaphore;
import static javax.swing.JOptionPane.showMessageDialog;

public class Restaurant 
{
    //Buffer for appetizers, desserts, main dishes.
    private static Table aTable,dTable,mTable;
    
    //Semaphores needed for mutual exclusivity, cooks (by type) and waiters
    private static Semaphore semAC, semMC, semDC, semWA, semWM, semWD, semMEA, semMEM, semMED, semMEWA, semMEWM, semMEWD;
    
    //Day time in seconds
    private long hourSeconds;
    
    //Maximum number of dishes in each table
    private int maxAppetizer,maxMain,maxDessert;
    
    //Count of every type of dish
    public static int aCount=0, mCount=0, dCount=0;
    
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
    
    //Employee Counters 
    private int countACook, countDCook, countMCook, countWaiter;
    
    //Order counter
    public static int orderCount;
          
    //Declaration of an Array for keeping track of hired employees 
    private AppetizerCook[] appetizerCook;
    private MainCook[] mainCook;
    private DessertCook[] dessertCook;
    private Waiter[] waiter;
    
    //JSON Controller
    public JSONController json = new JSONController();
    
    
    public Restaurant()
    {
        
        //COUNTERS
          this.countACook=0;
          this.countDCook=0;
          this.countMCook=0;
          this.countWaiter=0;
        
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
            
        //INITIALIZING MUTUAL EXCLUSIVITY SEMAPHORES
            this.semMEA = new Semaphore(1);
            this.semMEM = new Semaphore(1);
            this.semMED = new Semaphore(1);
            this.semMEWA = new Semaphore(1);
            this.semMEWM = new Semaphore(1);
            this.semMEWD = new Semaphore(1);
        
        //INITIALIZING COOKS' SEMAPHORES
            this.semAC = new Semaphore(this.maxAppetizer);
            this.semMC = new Semaphore(this.maxMain);
            this.semDC = new Semaphore(this.maxDessert);
        
        //INITIALIZING WAITER SEMAPHORE
            this.semWA = new Semaphore(0);
            this.semWM = new Semaphore(0);
            this.semWD = new Semaphore(0);
        
        //Initializing arrays with employees
            this.appetizerCook = new AppetizerCook[this.maxACook];
            this.mainCook = new MainCook[this.maxMCook];
            this.dessertCook = new DessertCook[this.maxDCook];
            this.waiter = new Waiter[this.maxWaiter];
            
            //Hire initial number of Appetizers Cooks wanted in the restaurant
            for(int init=0; init < initACook; init++)
            {
                hireACook(1);
            }
            
            //Hire initial number of Main Cooks wanted in the restaurant
            for(int init=0; init < initMCook; init++)
            {
                hireMCook(1);
            }
            
            //Hire initial number of Desserts Cooks wanted in the restaurant
            for(int init=0; init < initDCook; init++)
            {
                hireDCook(1);
            }
            
            //Hire initial number of Waiters wanted in the restaurant
            for(int init=0; init < initWaiter; init++)
            {
                hireWaiter(1);
            }    
    }
  
//M E T H O D S
    
//HIRE AN APPETIZER COOK
public void hireACook(int value)
{
    int c = value;  //Number of cooks that need to be hired (optional if we need to hire more than one cook at a time)
       
    //Validation for maximum of Appetizer Cooks that can be hired
    if(countACook==maxACook)
    {
        showMessageDialog(null,"You can't hire any more Appetizer Cooks");  
    }
    else
    {
        for(int i=0; i < this.maxACook; i++)//Hiring a new Appetizer Cook for the restaurant
        {
            if((this.appetizerCook[i] == null) && c > 0)
            {
                this.appetizerCook[i]= new AppetizerCook(this.aTable, 1000, this.semMEA, this.semAC, this.semWA);
                this.appetizerCook[i].setID(i + 1);
                this.appetizerCook[i].setHire(true);
                this.appetizerCook[i].start();
                //System.out.println("Appetizer Cook " + (i+1) + " was hired");
                countACook++;
                c = c-1;
            }
        }
    }
}
          
//HIRE A MAIN COOK
public void hireMCook(int value)
{
    int c = value;  //Number of cooks that need to be hired (optional if we need to hire more than one cook at a time)

    if(countMCook==maxMCook)//Validation for maximum of Main Cooks that can be hired
    {
        showMessageDialog(null,"You can't hire any more Main Cooks");
    }
    else
    {    
        for(int i=0; i<this.maxMCook; i++)//Hiring a new Main Cook for the restaurant
        {
            if((this.mainCook[i] == null) && c > 0)
            {
                this.mainCook[i] = new MainCook(this.mTable, 1000, this.semMEM, this.semMC, this.semWM);
                this.mainCook[i].setID(i + 1);
                this.mainCook[i].setHire(true);
                this.mainCook[i].start();
                //System.out.println("Main Cook " + (i+1) + " was hired");
                countMCook++;
                c=c-1;
            }
        }
    } 
}
      
//HIRE A DESSERT COOK 
public void hireDCook(int value)
{
    int c = value;  //Number of cooks that need to be hired (optional if we need to hire more than one cook at a time)
    
    if(countDCook==maxDCook)//Validation for maximum of Dessert Cooks that can be hired 
    {
        showMessageDialog(null,"You can't hire any more Dessert Cooks");
    }
    else
    {
        for(int i=0; i<this.maxDCook; i++)//Hiring a new Dessert Cook for the restaurant
        {
            if((this.dessertCook[i] == null) && c > 0)
            {
                this.dessertCook[i] = new DessertCook(this.dTable, 1000, this.semMED, this.semDC, this.semWD);
                this.dessertCook[i].setID(i + 1);
                this.dessertCook[i].setHire(true);
                this.dessertCook[i].start();
                countDCook++;
                //System.out.println("Dessert Cook " + (i+1) + " was hired");
                c=c-1;
            }
        }
    }
}
    
//HIRE A WAITER 
public void hireWaiter(int value)
{
    int c = value;  //Number of waiters that need to be hired (optional if we need to hire more than one waiter at a time)
     
    if(countWaiter==maxWaiter)//Validation for maximum of Waiters that can be hired
    {
        showMessageDialog(null,"You can't hire any more Waiter");

    }
    else
    {   
        for(int i=0; i<this.maxWaiter; i++)//Hiring a new Waiter for the restaurant
        {
            if((this.waiter[i] == null) && c > 0)
            {
                this.waiter[i] = new Waiter(this.aTable, this.mTable, this.dTable, 1000, this.semMEWA, this.semMEWM, this.semMEWD, this.semWA, this.semWM, this.semWD, this.semAC, this.semMC, this.semDC);
                this.waiter[i].setHire(true);
                this.waiter[i].setID(i + 1);
                this.waiter[i].start();
                countWaiter++;
                //System.out.println("A Waiter was hired");
                c=c-1;
            }
        } 
    }
}

 //FIRE AN APPETIZER COOK
public void fireACook(int value)
{
    int c = value;  //Number of waiters that need to be fired (optional if we need to fire more than one cook at a time)
     
    if(countACook==0)//Validation for maximum of Appetizer Cooks that can be fired
    {
        showMessageDialog(null,"You can't fire any more Appetizer Cooks");
    }
    else
    {
        for(int i=maxACook-1; i>-1; i--)//Firing a current Appetizer Cooks of the restaurant
        {
            if(!(this.appetizerCook[i] == null) && c > 0)
            {
                this.appetizerCook[i].setHire(false);
                this.appetizerCook[i] = null;
                //System.out.println("An Appetizer Cook was fired");
                countACook--;
                c=c-1;
            }
        } 
    }
}   

//FIRE A MAIN COOK
public void fireMCook(int value)
{
    int c = value;  //Number of Main Cooks that need to be fired (optional)
 
    if(countMCook==0)//Validation for maximum of Main Cooks that can be fired
    {
        showMessageDialog(null,"You can't fire any more Main Cooks");  
    }
    else
    {
        for(int i=maxMCook-1; i>-1; i--)//Firing a current Main Cooks of the restaurant
        {
           
            if(!(this.mainCook[i] == null) && c > 0)
            {
                this.mainCook[i].setHire(false);
                this.mainCook[i] = null;
                System.out.println("A Main Cook was fired");
                countMCook--;
                c=c-1;
            }
        }  
    }
}   

//FIRE A DESSERT COOK
public void fireDCook(int value)
{
    int c = value;  //Number of Dessert Cooks that need to be fired (optional)

    if(countDCook==0)//Validation for maximum of Dessert Cook that can be fired
    {
        showMessageDialog(null,"You can't fire any more Dessert Cooks");
   
    }
    else{   
       
        for(int i=maxDCook-1; i>-1; i--) //Firing a current Dessert Cook of the restaurant
        {
            if(!(this.dessertCook[i] == null) && c > 0)
            {
                this.dessertCook[i].setHire(false);
                this.dessertCook[i] = null;
                System.out.println("A Dessert Cook was fired");
                countDCook--;
                c=c-1;
            }
        }
    }
}   
     
//FIRE A WAITER 
public void fireWaiter(int value)
{
    int c = value;  //Number of waiters that need to be hired (optional)
    
    if(countWaiter==0)//Validation for maximum of Waiters that can be fired
    {
        showMessageDialog(null,"You can't fire any more Waiters");     
    }
    else
    { 
        for(int i=maxWaiter-1; i>-1; i--)//Firing a current Waiter of the restaurant
        {
            if(!(this.waiter[i] == null) && c > 0)
            {
                this.waiter[i].setHire(false);
                this.waiter[i] = null;
                System.out.println("A Waiter was fired");
                countWaiter--;
                c=c-1;
            }
        }
    }   
}

//GETTERS FOR COOKS AND WAITER COUNTERS
    public int getCountACook() 
    {
        return countACook;
    }

    public int getCountDCook() 
    {
        return countDCook;
    }

    public int getCountMCook() 
    {
        return countMCook;
    }

    public int getCountWaiter() 
    {
        return countWaiter;
    }

//METHODS TO ADD AND REMOVE DISHES
    public static void addAppetizer(){
        Restaurant.aCount++;
    }

    public static void addMain(){
        Restaurant.mCount++;
    }

    public static void addDessert(){
        Restaurant.dCount++;
    }

    public static void removeAppetizer(){
        Restaurant.aCount--;
    }

    public static void removeMain(){
        Restaurant.mCount--;
    }

    public static void removeDessert(){
        Restaurant.dCount--;
    }
    
//METHOD TO ADD FINISHED ORDERS
    public static void addOrder(){
        Restaurant.orderCount++;
    }
}
