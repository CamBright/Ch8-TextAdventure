import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the Space Adventure application. 
 * Space Adventure is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author  Cameron Brightwell
 * @version 2017
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Items RoomItem;
    private String ItemCase;
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
    
    /**
     * @return item in the current room.
     */
    public Items getItem() {
        return RoomItem;
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
     * Return a description of the room in the form.
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + "You see an item in the distance.";
    }
    
    /**
     * Return a description of the room in the form.
     * @return A medium description of this room
     */
    public String getMediumDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits.
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
    
    /**
     * Set the current room item.
     * @param Items Item.
     */
    public void setItem(Items Item) {
        RoomItem = Item;
    }
    
    /**
     * Describe the encounter of the item to the user.
     */
    public void encounter() 
    {
        System.out.println(RoomItem.getItemDescription());
        System.out.println("This Item Weighs " + RoomItem.getItemWeight() + " pounds.");
        System.out.println("What would you like to do?");
        System.out.println("eat? punch? take? or nothing");
        
    }
    
    /**
     * Connect the users action with what would happen with the specific item.
     * @param String action.
     * @return boolean ItemExist
     */
    public boolean action(String action)
    {
        boolean ItemExist = true;
        switch(action) {
            
            
            case "punch": ItemExist = RoomItem.actionPunch();
                break;
                
            case "eat": ItemExist = RoomItem.actionEat();  
                          
                break; 
                
            case "take": ItemExist = RoomItem.actionTake();
                          
                break;
            
            case "nothing": 
                break;
        }
        return ItemExist;
    }
    
    /**
     * Checks if there is an item in the room.
     * Checks if the item in the room killed the user.
     * @returns boolean ifDied.
     */
    public boolean checkIfDied() {
        if (RoomItem == null) {
            
            return false;
        } else {
        return RoomItem.ifDiedFromItem();
       }
    }
}

