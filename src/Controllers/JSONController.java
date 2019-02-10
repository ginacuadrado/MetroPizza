
package Controllers;

//Libraries
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.JOptionPane;    
 
//This class allows us to read JSON files
public class JSONController {
    
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader("initValues.json"));   //JSON Object to be read
 
            JSONObject jsonObject = (JSONObject) obj;       //Initializes a JSON object
            
            //Get initial values from JSON
            int daySeconds = Integer.parseInt((String) jsonObject.get("daySeconds"));
            int maxAppetizer = Integer.parseInt((String) jsonObject.get("maxAppetizer"));
            int maxMain = Integer.parseInt((String) jsonObject.get("maxMain"));
            int maxDessert = Integer.parseInt((String) jsonObject.get("maxDessert"));
            int initACook = Integer.parseInt((String) jsonObject.get("initACook"));
            int initMCook = Integer.parseInt((String) jsonObject.get("initMCook"));
            int initDCook = Integer.parseInt((String) jsonObject.get("initDCook"));
            int maxACook = Integer.parseInt((String) jsonObject.get("maxACook"));
            int maxMCook = Integer.parseInt((String) jsonObject.get("maxMCook"));
            int maxDCook = Integer.parseInt((String) jsonObject.get("maxDCook"));
            int initWaiter = Integer.parseInt((String) jsonObject.get("initWaiter"));
            int maxWaiter = Integer.parseInt((String) jsonObject.get("maxWaiter"));

          
        } catch (Exception e) {     //Catching errors
             if(e instanceof FileNotFoundException){
                JOptionPane.showMessageDialog(null, "ERROR! JSON file 'initialValues.json' was not found");
        }
    }
}
}