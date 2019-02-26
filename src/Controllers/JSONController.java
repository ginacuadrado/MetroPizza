
package Controllers;

//Libraries
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.JOptionPane;    
 
//This class allows us to read JSON files
public class JSONController {

        //Initialized as public for convinience
        public int daySeconds,maxAppetizer,maxMain,maxDessert,initACook,initDCook,initMCook,maxACook,maxMCook,maxDCook,initWaiter, maxWaiter;
    
        public JSONController()
        {
            
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader("initValues.json"));   //JSON Object to be read
 
            JSONObject jsonObject = (JSONObject) obj;       //Initializes a JSON object
            
            //Get initial values from JSON
            this.daySeconds = Integer.parseInt((String) jsonObject.get("daySeconds"));
            this.maxAppetizer = Integer.parseInt((String) jsonObject.get("maxAppetizer"));
            this.maxMain = Integer.parseInt((String) jsonObject.get("maxMain"));
            this.maxDessert = Integer.parseInt((String) jsonObject.get("maxDessert"));
            this.initACook = Integer.parseInt((String) jsonObject.get("initACook"));
            this.initMCook = Integer.parseInt((String) jsonObject.get("initMCook"));
            this.initDCook = Integer.parseInt((String) jsonObject.get("initDCook"));
            this.maxACook = Integer.parseInt((String) jsonObject.get("maxACook"));
            this.maxMCook = Integer.parseInt((String) jsonObject.get("maxMCook"));
            this.maxDCook = Integer.parseInt((String) jsonObject.get("maxDCook"));
            this.initWaiter = Integer.parseInt((String) jsonObject.get("initWaiter"));
            this.maxWaiter = Integer.parseInt((String) jsonObject.get("maxWaiter"));
            
            //Validation for negative numbers
            if(        this.daySeconds<0
                    || this.maxAppetizer<=0
                    || this.maxMain<=0
                    || this.maxDessert<=0 
                    || this.initACook<0 
                    || this.initMCook<0 
                    || this.initDCook<0
                    || this.maxACook<=0 
                    || this.maxMCook<=0 
                    || this.maxDCook<=0 
                    || this.initWaiter<0 
                    || this.maxWaiter<=0)
            {
                System.out.println(this.daySeconds);
 
                throw new Exception("ERROR! The program can't start with negative values in the JSON. Please modify the values and retry the execution");
                
            }
            
            if (this.initACook > this.maxACook ||
                this.initMCook > this.maxMCook ||
                this.initDCook > this.maxDCook ||
                this.initWaiter > this.maxWaiter)
            {
                throw new Exception("ERROR! The initial number of employees must be less than the maximum number of them. Please modify the values and retry the execution");
            }
            

        } 
        
        catch (Exception e) 
        {     //Catching errors about invalid JSON and no-int values
             if(e instanceof FileNotFoundException)
             {
                JOptionPane.showMessageDialog(null, "ERROR! JSON file 'initialValues.json' was not found. Please, try again");
                System.exit(0);
             }
             
             else if(e instanceof NumberFormatException)
             {
                 JOptionPane.showMessageDialog(null, "ERROR! JSON file must only contain numbers. Please configure the JSON again");
                 System.exit(0);
             }
             
             else
             {
                 JOptionPane.showMessageDialog(null,e.getMessage());
                 System.exit(0);
             }
             
        }
        
        }

}