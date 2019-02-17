
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

          
        } catch (Exception e) {     //Catching errors
             if(e instanceof FileNotFoundException){
                JOptionPane.showMessageDialog(null, "ERROR! JSON file 'initialValues.json' was not found");
        }
    }}

}