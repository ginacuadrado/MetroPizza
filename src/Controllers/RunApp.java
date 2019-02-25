
package Controllers;


import Models.Restaurant;
import Views.InitialView;

public class RunApp {
    
    
    private InitialView view;
    private ViewController vc;
    
    public RunApp()
    {
      
        this.view= new InitialView();
        view.setVisible(true);
        this.vc = new ViewController(view);
        this.vc.start();
       
    }
   
     public static void main(String[] args) 
     {
         
       RunApp run = new RunApp();
        
     }
}
