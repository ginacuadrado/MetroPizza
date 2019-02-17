
package Models;

public class Table {
    
    private int[] table;    //Shared array 
    private int max;        //Maximum number of plates in the table
 
    //Constructor
    public Table(int max)
    {
        this.max= max;
        this.table= new int[max];  
    }

    //Get the maximum number of plates in the table
    public int getMax() 
    {
        return max;
    }

}
