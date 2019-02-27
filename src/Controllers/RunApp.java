/*26-2-2019
Application Developed By:
Gina Cuadrado       Github: ginacuadrado
Samuel Velasquez    Github: Samuel-1802
Operative Systems
Universidad Metropolitana de Caracas*/

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
