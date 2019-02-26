
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
            this.view.getNumberADishes().setText(Integer.toString(Restaurant.aCount));
            this.view.getNumberMDishes().setText(Integer.toString(Restaurant.mCount));
            this.view.getNumberDDishes().setText(Integer.toString(Restaurant.dCount));
            this.view.getNumberOrders().setText(Integer.toString(Restaurant.orderCount));
            this.view.getHourScale().setText("1 HOUR LASTS " + Integer.toString(Restaurant.hourSeconds) + " SECONDS");
            this.view.getDayHours().setText(Integer.toString(Restaurant.countdown) + " HOURS");
            this.view.getChiefStatus().setText(this.view.res.getChief().getStatus());
        }
        
}
        
           
}
