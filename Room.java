import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Items RoomItem;
    private String ItemCase;
    private boolean died = false;
    private int gold, fruit;
    private double goldWeightTotal, fruitWeightTotal;// stores exits of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        this.gold = 0;
        this.fruit = 0;
        this.goldWeightTotal = 0.0;
        this.fruitWeightTotal = 0.0;
        
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    public void setItem(Items Item) 
    {
        RoomItem = Item;
    }
    
    public void setItemCase(String Case)
    {
        ItemCase = Case;
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + "You see an item in the distance.";
    }
    
    public String getMediumDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Places you can go:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public void encounter() 
    {
        System.out.println(RoomItem.getItemDescription());
        System.out.println("This Item Weighs" + RoomItem.getItemWeight() + " pounds.");
        System.out.println("What would you like to do?");
        System.out.println("eat? punch? take? or nothing");
        
    }
    
    public void action(String action)
    {
        switch(action) {
            
            case "punch":        if(ItemCase == "fruit")
                          {
                              System.out.println("You squashed the fruit.");
                          } else if(ItemCase == "snake") {
                              System.out.println("You killed the snake.");
                          } else if(ItemCase == "gold") {
                              System.out.println("You hurt your hand (good job)");
                          } 
                          
                    
                break;
                
            case "eat":         if(ItemCase == "fruit")
                          {
                              System.out.println("You consumed the fruit.");
                          } else if(ItemCase == "snake") {
                              System.out.println("You ate the snake (you gain a stomach ache).");
                          } else if(ItemCase == "gold") {
                              System.out.println("You tried to eat the gold (You die).");
                              this.died = true;
                          } 
                          
                break;
                
            case "take":     if(ItemCase == "fruit")
                          {
                              System.out.println("You gain the fruit.");
                              this.fruit += 1;
                              this.fruitWeightTotal += RoomItem.getItemWeight();
                          } else if(ItemCase == "snake") {
                              System.out.println("You try to take the snake (you die),");
                              this.died = true;
                          } else if(ItemCase == "gold") {
                              System.out.println("You gain 1 gold");
                              this.gold += 1;
                              this.goldWeightTotal += RoomItem.getItemWeight();
                          } 
                          
                break;
            
            case "nothing": 
                break;
        }
    }
    
    public boolean checkifDied() 
    {
        return this.died;
    }
    
    public int getGold() 
    {
        return this.gold;
    }
    
    public int getFruit()
    {
        return this.fruit;
    }
    
    public double getFruitWeight()
    {
        return this.fruitWeightTotal;
    }
    
    public double getGoldWeight()
    {
        return this.goldWeightTotal;
    }
}

