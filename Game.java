/**
 *  This class is the main class of the Space Adventure application. 
 *  This is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author  Cameron Brightwell
 * @version 2017
 */
import java.util.ArrayList;
import java.util.Random;

public class Game 
{
    private Parser parser;
    private static Room currentRoom, previousRoom;
    private Items currentItem;
    private Room office, space, outside, pluto, neptune, uranus, saturn, jupiter, mars,
        mercury, venus, ceres;;
    private static ArrayList<Items> ItemList = new ArrayList<>();
    private boolean BoosterUsed = false;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        createItems();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
      
        // create the rooms
        space = new Room("in a numbing void sprinkled with trillions of unsetlingly bright yet underwelming sprites. (it's space) ");
        pluto = new Room("in a world of dark uncertainty, blinded by the absence of the suns warming glow");
        neptune = new Room("in a wacky world of wild willys ");
        uranus = new Room("in a land of poor pronunciation");
        saturn = new Room("in a plane of dust");
        jupiter = new Room("in a swallowing cloud of gas");
        ceres = new Room("You gaze upon a steep plain of rock and dust illuminated by the deep glow of the giant neighboring gas planet. (You are on the moon Ceres)");
        mars = new Room("in a mars bar");
        office = new Room("in an office (for some reason, you dont question it), sleep to begin your journey through space");
        mercury = new Room("in Mercury, a HOT place");
        venus = new Room("in Venus, an even HOTTER place.");
        outside = new Room("So, you have decided to quit your meaningless job and move on to bigger and better things. NO more wasting away in an office dreaming about the wonders of the world which you would never experience. You have decided to grasp your own life with both hands and yank it towards the direction of your dreams. I respect that, I really do. Have a fantastic life sweet prince.");
        
        // initialise room exits
        space.setExit("Mercury", mercury);
        space.setExit("Venus", venus);
        space.setExit("Mars", mars);
        space.setExit("Jupiter", jupiter);
        space.setExit("Saturn", saturn);
        space.setExit("Uranus", uranus);
        space.setExit("Neptune", neptune);
        space.setExit("Pluto", pluto);
        
        office.setExit("Leave", outside);

        mercury.setExit("launch", space);
        mercury.setExit("Venus", venus);
        mercury.setExit("Mars", mars);

        venus.setExit("launch", space);
        venus.setExit("Mercury", mercury);

        mars.setExit("launch", space);
        mars.setExit("Mercury", mercury);
        mars.setExit("Jupiter", jupiter);
        
        jupiter.setExit("launch", space);
        jupiter.setExit("Mars", mars);
        jupiter.setExit("Saturn", saturn);
        jupiter.setExit("Ceres", ceres);
        
        ceres.setExit("launch", space);
        ceres.setExit("Jupiter", jupiter);
        
        saturn.setExit("launch", space);
        saturn.setExit("Jupiter", jupiter);
        saturn.setExit("Uranus", uranus);
        
        uranus.setExit("launch", space);
        uranus.setExit("Saturn", saturn);
        uranus.setExit("Neptune", neptune);
        
        neptune.setExit("launch", space);
        neptune.setExit("Uranus", uranus);
        neptune.setExit("Pluto", pluto);
        
        pluto.setExit("launch", space);
        pluto.setExit("Neptune", neptune);
        
        

        currentRoom = office;  // start game in the office
    }
    
    private void createItems() {
        // Create items.
        Items spaceFruit, spaceGold, spaceSnake, spaceBooster;
      
        // initialize item descriptions, weight and case.
        spaceFruit = new Items("An unknown and totaly edible alien substance (it's a space fruit).", 3.0, "fruit");
        spaceGold = new Items("Its beautiful (it's a ingot of space gold)", 30.0, "gold");
        spaceSnake = new Items("It Moves! (it's a space Snake)", 7.0, "snake");
        spaceBooster = new Items("It's the key! (it seems as though it can be used to progress the game! or something like that..)", 400.0, "booster");
        
        // add items to an array.
        ItemList.add(spaceFruit);
        ItemList.add(spaceGold);
        ItemList.add(spaceSnake);
        ItemList.add(spaceBooster);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.  
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            finished = currentRoom.checkIfDied(); // Check if user died
            if(BoosterUsed == true) { // End game if booster is used.
                System.out.println("You use the booster to blast off into the void, you embark on your next big adventure... which is all just in your head. You wake up in your office, and begin working.");
                finished = true;
            }
            if(currentRoom == space && Inventory.getBooster() == true) { // Prompt user of booster.
               System.out.println("You have the booster, press enter 'use' to use it");
            }
            if(currentRoom == outside) // End game if user is outsie the office.
            {
               System.out.println(currentRoom.getShortDescription());
               finished = true;
            } 
        }
        System.out.println("Thank you for playing.  Good bye."); // End game message.
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Office adventure!");
        System.out.println("Office adventure is dope, lets start.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getMediumDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();
        
        String action;

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case TRAVEL:
                goRoom(command);
                break;
                
            case GO:
                goRoom(command);
                break;
                
            case BACK:
                goBack();
                break;
                
            case WAKE:
                goOffice();
                break;
                
            case SLEEP:
                goSpace();
                break;
                
            case INSPECT:
                Encounter();
                break;
                
            case TAKE:
                action = "take";
                commitAction(action);
                break;
                
            case EAT:
                action = "eat";
                commitAction(action);
                break;
                
            case PUNCH:
                action = "punch";
                commitAction(action);
                break;
                
            case USE:
                useBooster();
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case INVENTORY:
             
                System.out.println("You have " + Inventory.getGold() + " gold, which weighs " + Inventory.getGoldWeight() + " pounds.");
                System.out.println("You have " + Inventory.getFruit() + " fruit, which weighs " + Inventory.getFruitWeight() + " pounds.");
                if(Inventory.getBooster() == true) {
                   System.out.println("You have the booster!!(use it in space!), it weighs " + Inventory.getBoosterWeight() + " pounds."); 
                }
                
            
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the solar system.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Travel where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Don't do that! You WILL die");
        }
        else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            if(currentRoom == ceres) { // Set ceres item to booster.
                currentItem = ItemList.get(3);
                currentRoom.setItem(currentItem);
            } else { // Randomize items.
            currentItem = randomItems();
            currentRoom.setItem(currentItem);
           }
           if(nextRoom != outside) {
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }
    
    /**
     * Wake player up back into the office.
     */
    private void goOffice()
    {
      if(currentRoom != office)
      {
          currentItem = null;
          currentRoom = office;
          System.out.println(currentRoom.getMediumDescription());
      } else {
          System.out.println("You are currently awake.");
      }
    }
    
    /**
     * Player falls asleep, going to space!
     */
    private void goSpace()
    {
        if(currentRoom == office) 
        {
          currentItem = null;
          currentRoom = space;
          System.out.println(currentRoom.getMediumDescription());
        } else {
          System.out.println("You are asleep");
        }
    }
    
    /**
     * Implimenting the go back one room command.
     */
    private void goBack() {       
        currentRoom = previousRoom;
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Call when user encounters an item.
     */
    private void Encounter()
    {
        if(currentItem == null) {
            System.out.println("There is no item here");
            
        } else {
        currentRoom.encounter();
       }
    }
    
    /**
     * Call when player chooses an action
     * @param String action.
     */
    private void commitAction(String action)
    {
        if(currentItem == null) { // If there is no item, tell the player.
            System.out.println("There is no item to interact with here.");
        } else {boolean checkDead = false;
        boolean currentItemExist = currentRoom.action(action);
        checkDead = currentRoom.checkIfDied();
        if(checkDead == true)
        {
            System.out.println("Well done");
        } else {
        System.out.println(currentRoom.getMediumDescription());
        
        }
        if (currentItemExist == false) {
            currentItem = null;
        }
      }
    }   

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Impliment the use command to finish the came
     */ 
    public void useBooster() {
        if(Inventory.getBooster() == true) {
            BoosterUsed = true;
        }
    }
    
    /**
     * Randomize items in an array list.
     * @return random item from array list.
     */
    public Items randomItems() {
        Random oRand = new Random(); 
        return (ItemList.get(oRand.nextInt(2)));       
    }
}
