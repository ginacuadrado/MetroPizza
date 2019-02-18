
package Models;

public class Table {
    
    private int[] table;    //Shared array 
    private int max;        //Maximum number of plates that can be on the table
    
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
    
    //Set the value in a specific spot of the table
    public void setPlate(int pointer, int value){
        this.table[pointer] = value;
    }

}
