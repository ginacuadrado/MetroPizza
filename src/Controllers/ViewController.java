
package Controllers;
import Models.Restaurant;
import Views.InitialView;


public class ViewController extends Thread
{
    private boolean active;
    private InitialView view;
        
    
    public ViewController(InitialView view)
    {
        this.active=true;
        this.view= view;
    }
  
public void run()
{
        while(this.active)
        {
            this.view.getNumberACooks().setText(Integer.toString(this.view.res.getCountACook()));
            this.view.getNumberDCooks().setText(Integer.toString(this.view.res.getCountDCook()));
            this.view.getNumberMCooks().setText(Integer.toString(this.view.res.getCountMCook()));
            this.view.getNumberWaiters().setText(Integer.toString(this.view.res.getCountWaiter()));
        }
        
}
        
           
}
