
/**
 * This class creates an inventory that remains constant throughout the game 
 * until the user picks up a new item, then the inventory will recalculate its 
 * atributes including the new item.
 *
 * @author Cameron Brightwell
 * @version 2017
 */
public class Inventory
{
    private static int fruit;
    private static int gold = 0;
    private static boolean booster = false;
    private static double fruitWeightTotal;
    private static double goldWeightTotal;
    private static double boosterWeightTotal;

    
    /**
     * Set Gold.
     * @param int Gold.
     */
    public static void setGold(int Gold) 
    {
        gold = Gold;
    }
    
    /**
     * Set Fruit.
     * @param int Fruit.
     */
    public static void setFruit(int Fruit)
    {
        fruit = Fruit;
    }
    
    /**
     * Set Booster.
     * @param boolean Booster.
     */
    public static void setBooster(boolean Booster) {
        booster = Booster;
    }
    
    /**
     * Set The weight of fruit the user is carrying in their inventory.
     * @param double FruitWeight.
     */
    public static void setFruitWeight(double FruitWeight)
    {
        fruitWeightTotal = FruitWeight;
    }
    
    /**
     * Set The weight of gold the user is carrying in their inventory.
     * @param double GoldWeight.
     */
    public static void setGoldWeight(double GoldWeight)
    {
        goldWeightTotal = GoldWeight;
    }
    
    /**
     * Set The weight of the booster that the user is carrying in their 
     * inventory.
     * @param double BoosterWeight.
     */
    public static void setBoosterWeight(double BoosterWeight) {
        boosterWeightTotal = BoosterWeight;
    }
    
    /**
     * @return int fruit.
     */
    public static int getFruit() {
        return fruit;
    }
    
    /**
     * @return int gold.
     */
    public static int getGold() {
        return gold;
    }
    
    /**
     * @return boolean booster.
     */
    public static boolean getBooster() {
        return booster;
    }
    
    /**
     * @return double fruitWeightTotal.
     */
    public static double getFruitWeight() {
        return fruitWeightTotal;
    }
    
    /**
     * @return double goldWeightTotal.
     */
    public static double getGoldWeight() {
        return goldWeightTotal;
    }
    
    /**
     * @return double boosterWeightTotal.
     */
    public static double getBoosterWeight() {
        return boosterWeightTotal;
    }
}
