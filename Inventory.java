
/**
 * Write a description of class Inventory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Inventory
{
    // instance variables - replace the example below with your own
    private static int fruit;
    private static int gold = 0;
    private static double fruitWeightTotal;
    private static double goldWeightTotal;

    public static void setGold(int Gold) 
    {
        gold = Gold;
    }
    
    public static void setFruit(int Fruit)
    {
        fruit = Fruit;
    }
    
    public static void setFruitWeight(double FruitWeight)
    {
        fruitWeightTotal = FruitWeight;
    }
    
    public static void setGoldWeight(double GoldWeight)
    {
        goldWeightTotal = GoldWeight;
    }
    
    public static int getFruit() {
        return fruit;
    }
    
    public static int getGold() {
        return gold;
    }
    
    public static double getFruitWeight() {
        return fruitWeightTotal;
    }
    
    public static double getGoldWeight() {
        return goldWeightTotal;
    }
}
