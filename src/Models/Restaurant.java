
package Models;

import Controllers.JSONController;
import static javax.swing.JOptionPane.showMessageDialog;

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
    
    //Counters 
        private int countACook, countDCook, countMCook, countWaiter;
          
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
            this.maxAppetizer= this.json.maxAppetizer;
            this.maxMain= this.json.maxMain;
            this.maxDessert= this.json.maxDessert;

            //Initializing tables with its own maximum for each type of dish
            this.aTable= new Table(this.json.maxAppetizer);
            this.dTable = new Table(this.json.maxDessert);
            this.mTable = new Table(this.json.maxMain);
        
        //INITIAL VALUES FOR EMPLOYEES AT THE BEGGINING OF THE EXECUTION 
            this.initACook=this.json.initACook;
            this.initDCook=this.json.initDCook;
            this.initMCook=this.json.initMCook;
            this.initWaiter=this.json.initWaiter;
    
        //ARRAYS FOR EMPLOYEES
            //Initializing maximum number of employees of each type
            this.maxACook=this.json.maxACook;
            this.maxMCook=this.json.maxMCook;
            this.maxDCook=this.json.maxDCook;
            this.maxWaiter=this.json.maxWaiter;
            
            //Initializing arrays with employees
            this.appetizerCook = new AppetizerCook[this.maxACook];
            this.mainCook= new MainCook[this.maxMCook];
            this.dessertCook= new DessertCook[this.maxDCook];
            this.waiter=new Waiter[this.maxWaiter];
            
            //Initializing array with Appetizer Cooks
            for(int init=0; init <maxACook; init++)
            {
                this.appetizerCook[init]= new AppetizerCook();
            }
            
            //Initializing array with Main Cooks
            for(int init=0; init <maxMCook; init++)
            {
                this.mainCook[init]= new MainCook();
            }
            
            //Initializing array with Dessert Cooks
            for(int init=0; init <maxDCook; init++)
            {
                this.dessertCook[init]= new DessertCook();
            }
            
            //Initializing array with Waiters
            for(int init=0; init <maxWaiter; init++)
            {
                this.waiter[init]= new Waiter();
            }
            
            //Hire initial number of Appetizers Cooks wanted in the restaurant
            for(int init=0; init <initACook; init++)
            {
                hireACook(1);
            }
            
            //Hire initial number of Main Cooks wanted in the restaurant
            for(int init=0; init <initMCook; init++)
            {
                hireMCook(1);
            }
            
            //Hire initial number of Desserts Cooks wanted in the restaurant
            for(int init=0; init <initDCook; init++)
            {
                hireDCook(1);
            }
            
            //Hire initial number of Waiters wanted in the restaurant
            for(int init=0; init <initWaiter; init++)
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
        for(int i=0; i<this.maxACook; i++)//Hiring a new Appetizer Cook for the restaurant
        {
            if(!this.appetizerCook[i].isHire() && c > 0)
            {
                this.appetizerCook[i].setHire(true);
                System.out.println("An Appetizer Cook was hired");
                countACook++;
                c=c-1;
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
            if(!this.mainCook[i].isHire() && c > 0)
            {
                this.mainCook[i].setHire(true);
                System.out.println("A Main Cook was hired");
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
            if(!this.dessertCook[i].isHire() && c > 0)
            {
                this.dessertCook[i].setHire(true);
                countDCook++;
                System.out.println("A Dessert Cook was hired");
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
            if(!this.waiter[i].isHire() && c > 0)
            {
                this.waiter[i].setHire(true);
                countWaiter++;
                System.out.println("A Waiter was hired");
                c=c-1;
            }
        } 
    }
}

 //FIRE A APPETIZER COOK
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
            if(this.appetizerCook[i].isHire() && c > 0)
            {
                this.appetizerCook[i].setHire(false);
                System.out.println("An Appetizer Cook was fired");
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
           
            if(this.mainCook[i].isHire() && c > 0)
            {
                
                this.mainCook[i].setHire(false);
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
            if(this.dessertCook[i].isHire() && c > 0)
            {
                this.dessertCook[i].setHire(false);
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
            if(this.waiter[i].isHire() && c > 0)
            {
                this.waiter[i].setHire(false);
                System.out.println("A Waiter was fired");
                countWaiter--;
                c=c-1;
            }
        }
    }   
}

//Getter of cooks and waiters
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
    
    
    
    
    
    
}
