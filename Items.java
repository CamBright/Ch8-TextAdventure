
/**
 * This class sets the properties and encounters with items in the game.
 */
public class Items
{
    // variables of item class
    private String description;
    private String Case;
    private double weight;
    private int fruit;
    private int gold;
    private boolean booster;
    private double boosterWeightTotal; 
    private double fruitWeightTotal;
    private double goldWeightTotal;
    boolean ifDiedFromItem;

    /**
     * Constructor for objects of class Items
     * @param String description, double weight, String Case
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
    
    /**
     * @return Item Description.
     */
    public String getItemDescription() 
    {
        return description;
    }
    
    /**
     * @return Item Case.
     */
    public String getCase() {
        return Case;
    }
    
    /**
     * @return Item Weight.
     */
    public double getItemWeight() 
    {
       return weight; 
    }
    
    /**
     * Print item encounter when called.
     */
    public void encounter() 
    {
        System.out.println(description);
        System.out.println("This Item Weighs " + weight + " pounds.");
        System.out.println("What would you like to do?");
        System.out.println("eat? punch? take? or nothing");      
    }
    
    /**
     * Prompting user to comit the punch action with the item and checking to
     * see what happens with specific items.
     * @return False when item is encountered.
     * Set game over to true if user dies.
     */
    public boolean actionPunch() {
            if(Case == "fruit")
              {
                System.out.println("You squashed the fruit.");
              } else if(Case == "snake") {
                System.out.println("You killed the snake.");
              } else if(Case == "gold") {
                System.out.println("You hurt your hand (good job)");
              } else if(Case == "booster") {
                System.out.println("uuuhhhh... ka-boom");
                ifDiedFromItem = true;
              }
              return false;
    }
    
    /**
     * Prompting user to comit the eat action with the item and checking to
     * see what happens with specific items.
     * @return False when item is encountered.
     * Set game over to true if user dies.
     */
    public boolean actionEat() {
            if(Case == "fruit")
              {
                  System.out.println("You consumed the fruit.");
              } else if(Case == "snake") {
                  System.out.println("You ate the snake (you gain a stomach ache).");
              } else if(Case == "gold") {
                  System.out.println("You tried to eat the gold (You die).");
                  ifDiedFromItem = true;
              }  else if(Case == "booster") {
                  System.out.println("good job, you figured out just the right thing to do with the booster!");
                  System.out.println("you died");
                  ifDiedFromItem = true;
              }
              return false;
    }
    
    /**
     * Prompting user to comit the punch action with the item and checking to
     * see what happens with specific items.
     * @return False when item is encountered.
     * Set game over to true if user dies.
     * Set inventory with taken item.
     */
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
              } else if(Case == "booster") {
                  System.out.println("You gain the booster!");
                  booster = true;
                  Inventory.setBooster(true);
                  boosterWeightTotal = weight;
                  Inventory.setBoosterWeight(boosterWeightTotal);
              }
              return false;
    }
    
    /**
     * @return boolean ifDiedFromItem.
     */
    public boolean ifDiedFromItem() {
        return ifDiedFromItem;
    }
}
