
/**
 * Write a description of class Items here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Items
{
    // instance variables - replace the example below with your own
    private String description;
    private double weight;

    /**
     * Constructor for objects of class Items
     */
    public Items(String description, double weight)
    {
        // initialise instance variables
        this.description = description;
        this.weight = weight;
    }
    
    public String getItemDescription() 
    {
        return description;
    }
    
    public double getItemWeight() 
    {
       return weight; 
    }
}
