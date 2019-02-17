
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
          
    //Buffer positions
    private int inAppetizers,outAppetizers,inMain,outMain,inDessert,outDessert;
    
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
            int i1=0;
            do {
                hireACook(1);
                i1++;
                
            }while(i1!=initACook);
            
            //Hire initial number of Main Cooks wanted in the restaurant
            int i2=0;
            do {
                hireMCook(1);
                i2++;
                
            }while(i2!=initMCook);
            
            //Hire initial number of Dessert Cooks wanted in the restaurant
            int i3=0;
            do {
                hireDCook(1);
                i3++;
                
            }while(i3!=initDCook);
            
            //Hire initial number of Waiters wanted in the restaurant
            int i4=0;
            do {
                hireWaiter(1);
                i4++;
                
            }while(i4!=initWaiter);
            
    }

    
//M E T H O D S
    
//HIRE AN APPETIZER COOK
    public void hireACook(int value)
    {
        int c = value;  //Number of cooks that need to be hired
        int h = 0;      //Counter to validate number of cooks
        
        //Validation for maximum of Appetizer Cooks that can be hired
        for(int i=0; i<this.maxACook; i++)
        {
            if(this.appetizerCook[i].isHire())
            {
                h++;
                if(h==this.maxACook)
                {
                     //showMessageDialog(null,"ERROR! You can't hire any more Appetizer Cooks");
                    System.out.println("ERROR! You can't hire any more Appetizer Cooks");
                    return;
                }           
            }          
        }
        
//Hiring a new Appetizer Cook for the restaurant
        for(int i=0; i<this.maxACook; i++)
        {
            if(!this.appetizerCook[i].isHire() && c > 0)
            {
                this.appetizerCook[i].setHire(true);
                c=c-1;
            }
        }
        
    }
      
    
//HIRE A MAIN COOK
    public void hireMCook(int value)
    {
        int c = value;  //Number of cooks that need to be hired
        int h = 0;      //Counter to validate number of cooks
        
        //Validation for maximum of Main Cooks that can be hired
        for(int i=0; i<this.maxMCook; i++)
        {
            if(this.mainCook[i].isHire())
            {
                h++;
                if(h==this.maxMCook)
                {
                     showMessageDialog(null,"ERROR! You can't hire any more Main Cooks");
                     return;
                }           
            }          
        }
        
        //Hiring a new Main Cook for the restaurant
        for(int i=0; i<this.maxMCook; i++)
        {
            if(!this.mainCook[i].isHire() && c > 0)
            {
                this.mainCook[i].setHire(true);
                c=c-1;
            }
        }
        
    }
    
    
//HIRE A DESSERT COOK 
    public void hireDCook(int value)
    {
        int c = value;  //Number of cooks that need to be hired
        int h = 0;      //Counter to validate number of cooks
        
        //Validation for maximum of Dessert Cooks that can be hired
        for(int i=0; i<this.maxDCook; i++)
        {
            if(this.dessertCook[i].isHire())
            {
                h++;
                if(h==this.maxDCook)
                {
                     showMessageDialog(null,"ERROR! You can't hire any more Dessert Cooks");
                     return;
                }           
            }          
        }
        
        //Hiring a new Dessert Cook for the restaurant
        for(int i=0; i<this.maxDCook; i++)
        {
            if(!this.dessertCook[i].isHire() && c > 0)
            {
                this.dessertCook[i].setHire(true);
                c=c-1;
            }
        }
        
    }
    

//HIRE A WAITER 
    public void hireWaiter(int value)
    {
        int c = value;  //Number of waiters that need to be hired
        int h = 0;      //Counter to validate number of waiters
        
        //Validation for maximum of Dessert Cooks that can be hired
        for(int i=0; i<this.maxWaiter; i++)
        {
            if(this.waiter[i].isHire())
            {
                h++;
                if(h==this.maxWaiter)
                {
                     //showMessageDialog(null,"");
                     System.out.println("ERROR! You can't hire any more Waiters");
                     return;
                }           
            }          
        }
        
        //Hiring a new Waiter for the restaurant
        for(int i=0; i<this.maxWaiter; i++)
        {
            if(!this.waiter[i].isHire() && c > 0)
            {
                this.waiter[i].setHire(true);
                c=c-1;
            }
        }
        
    }

 //FIRE A APPETIZER COOK
    public void fireACook(int value)
    {
        int c = value;  //Number of waiters that need to be fired
        int h = 0;      //Counter to validate number of waiters
        
        //Validation for maximum of Waiter that can be hired
        for(int i=0; i<maxACook; i++)
        {
            if(!this.appetizerCook[i].isHire())
            {
                h++;
                if(h==this.maxACook)
                {
                     //showMessageDialog(null,"");
                    System.out.println("ERROR! You don't have any more Appetizer Cooks to fire");
                     return;
                }           
            }          
        }
        
        //Firing a current Appetizer Cooks of the restaurant
        for(int i=maxACook-1; i>-1; i--)
        {
            if(this.appetizerCook[i].isHire() && c > 0)
            {
                this.appetizerCook[i].setHire(false);
                System.out.println("An Appetizer Cook was fired");
                c=c-1;
            }
        }
        
    }   

 //FIRE A MAIN COOK
    public void fireMCook(int value)
    {
        int c = value;  //Number of waiters that need to be fired
        int h = 0;      //Counter to validate number of waiters
        
        //Validation for maximum of Main Cook that can be fired
        for(int i=0; i<maxMCook; i++)
        {
            if(!this.mainCook[i].isHire())
            {
                h++;
                if(h==this.maxMCook)
                {
                     //showMessageDialog(null,"");
                    System.out.println("ERROR! You don't have any Main Cooks to fire");
                     return;
                }           
            }          
        }
        
        //Firing a current Main Cooks of the restaurant
        for(int i=maxMCook; i>-1; i--)
        {
            if(this.mainCook[i].isHire() && c > 0)
            {
                this.mainCook[i].setHire(false);
                System.out.println("A Main Cook was fired");
                c=c-1;
            }
        }
        
    }   


 //FIRE A DESSERT COOK
    public void fireDCook(int value)
    {
        int c = value;  //Number of waiters that need to be fired
        int h = 0;      //Counter to validate number of waiters
        
        
        //Validation for maximum of Dessert Cook that can be fired
        for(int i=0; i<maxDCook; i++)
        {
            if(!this.dessertCook[i].isHire())
            {
                h++;
                if(h==this.maxDCook)
                {
                     //showMessageDialog(null,"");
                    System.out.println("ERROR! You don't have any Dessert Cooks to fire");
                     return;
                }           
            }          
        }
        
        //Firing a current Dessert Cooks of the restaurant
        for(int i=maxDCook; i>-1; i--)
        {
            if(this.dessertCook[i].isHire() && c > 0)
            {
                this.dessertCook[i].setHire(false);
                System.out.println("A Dessert Cook was fired");
                c=c-1;
                
            }
        }
        
    }   
    

    
//FIRE A WAITER 
    public void fireWaiter(int value)
    {
        int c = value;  //Number of waiters that need to be hired
        int h = 0;      //Counter to validate number of waiters
        
        //Validation for maximum of Waiter that can be hired
        for(int i=0; i<maxWaiter; i++)
        {
            if(!this.waiter[i].isHire())
            {
                h++;
                if(h==this.maxWaiter)
                {
                     //showMessageDialog(null,"");
                    System.out.println("ERROR! You don't have any waiters to fire");
                     return;
                }           
            }          
        }
        
        //Firing a current Waiter of the restaurant
        for(int i=maxWaiter; i>-1; i--)
        {
            if(this.waiter[i].isHire() && c > 0)
            {
                this.waiter[i].setHire(false);
                c=c-1;
            }
        }
        
    }
    
    
}
