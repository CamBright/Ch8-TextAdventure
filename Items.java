
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
    private String Case;
    private double weight;
    private int fruit;
    private int gold;
    private double fruitWeightTotal;
    private double goldWeightTotal;
    boolean ifDiedFromItem;

    /**
     * Constructor for objects of class Items
     */
    public Items(String description, double weight, String Case)
    {
        // initialise instance variables
        this.description = description;
        this.Case = Case;
        this.weight = weight;
        this.fruit = 0;
        this.gold = 0;
        this.fruitWeightTotal = 0.0;
        this.goldWeightTotal = 0.0;
        this.ifDiedFromItem = false;
    }
    
    public String getItemDescription() 
    {
        return description;
    }
    
    public String getCase() {
        return Case;
    }
    
    public double getItemWeight() 
    {
       return weight; 
    }
    
    public void encounter() 
    {
        System.out.println(description);
        System.out.println("This Item Weighs " + weight + " pounds.");
        System.out.println("What would you like to do?");
        System.out.println("eat? punch? take? or nothing");      
    }
    
    public boolean actionPunch() {
            if(Case == "fruit")
              {
                System.out.println("You squashed the fruit.");
              } else if(Case == "snake") {
                System.out.println("You killed the snake.");
              } else if(Case == "gold") {
                System.out.println("You hurt your hand (good job)");
              } 
              return false;
    }
    
    public boolean actionEat() {
            if(Case == "fruit")
              {
                  System.out.println("You consumed the fruit.");
              } else if(Case == "snake") {
                  System.out.println("You ate the snake (you gain a stomach ache).");
              } else if(Case == "gold") {
                  System.out.println("You tried to eat the gold (You die).");
                  ifDiedFromItem = true;
              } 
              return false;
    }
    
    public boolean actionTake() {
            if(Case == "fruit")
              {
                  System.out.println("You gain the fruit.");
                  fruit += 1;
                  Inventory.setFruit(fruit);
                  fruitWeightTotal += weight;
                  Inventory.setFruitWeight(fruitWeightTotal);
              } else if(Case == "snake") {
                  System.out.println("You try to take the snake (you die),");
                  ifDiedFromItem = true;
              } else if(Case == "gold") {
                  System.out.println("You gain 1 gold");
                  gold += 1;
                  Inventory.setGold(this.gold);
                  goldWeightTotal += weight;
                  Inventory.setGoldWeight(goldWeightTotal);
              }
              return false;
    }
    
    public boolean ifDiedFromItem() {
        return this.ifDiedFromItem;
    }
}
