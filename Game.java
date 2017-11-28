/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
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
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room office, space, outside;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room pluto, neptune, uranus, saturn, jupiter, mars,
        mercury, venus;
        
        Items spaceFruit, spaceGold, spaceSnake;
      
        // create the rooms
        space = new Room("in a numbing void sprinkled with trillions of unsetlingly bright yet underwelming sprites. (it's space) ");
        pluto = new Room("in a world of dark uncertainty, blinded by the absence of the suns warming glow");
        neptune = new Room("in a wacky world of wild willys ");
        uranus = new Room("in a land of poor pronunciation");
        saturn = new Room("in a plane of dust");
        jupiter = new Room("in a swallowing cloud of gas");
        mars = new Room("in a mars bar");
        office = new Room("in an office (for some reason, you dont question it), sleep to begin your journey through space");
        mercury = new Room("in Mercury, a HOT place");
        venus = new Room("in Venus, an even HOTTER place.");
        outside = new Room("So, you have decided to quit your meaningless job and move on to bigger and better things. NO more wasting away in an office dreaming about the wonders of the world which you would never experience. You have decided to grasp your own life with both hands and yank it towards the direction of your dreams. I respect that, I really do. Have a fantastic life sweet prince.");
        
        // create the items
        spaceFruit = new Items("An unknown and totaly edible alien substance (it's a space fruit).", 3.0);
        spaceGold = new Items("Its beautiful (it's a ingot of space gold)", 30.0);
        spaceSnake = new Items("It Moves! (it's a space Snake)", 7.0);
        // initialise room exits
        space.setExit("Mercury", mercury);
        space.setExit("Venus", venus);
        space.setExit("Mars", mars);
        space.setExit("Jupiter", jupiter);
        space.setExit("Saturn", saturn);
        space.setExit("Uranus", uranus);
        space.setExit("Neptune", neptune);
        space.setExit("Pluto", pluto);
        space.setItem(spaceSnake);
        space.setItemCase("snake");
        
        office.setExit("Leave", outside);

        mercury.setExit("launch", space);
        mercury.setExit("Venus", venus);
        mercury.setExit("Mars", mars);
        mercury.setItem(spaceFruit);
        mercury.setItemCase("fruit");

        venus.setExit("launch", space);
        venus.setExit("Mercury", mercury);
        venus.setItem(spaceSnake);
        venus.setItemCase("snake");

        mars.setExit("launch", space);
        mars.setExit("Mercury", mercury);
        mars.setExit("Jupiter", jupiter);
        mars.setItem(spaceGold);
        mars.setItemCase("gold");
        
        jupiter.setExit("launch", space);
        jupiter.setExit("Mars", mars);
        jupiter.setExit("Saturn", saturn);
        jupiter.setItem(spaceFruit);
        jupiter.setItemCase("fruit");
        
        saturn.setExit("launch", space);
        saturn.setExit("Jupiter", jupiter);
        saturn.setExit("Uranus", uranus);
        saturn.setItem(spaceSnake);
        saturn.setItemCase("snake");
        
        uranus.setExit("launch", space);
        uranus.setExit("Saturn", saturn);
        uranus.setExit("Neptune", neptune);
        uranus.setItem(spaceGold);
        uranus.setItemCase("gold");
        
        neptune.setExit("launch", space);
        neptune.setExit("Uranus", uranus);
        neptune.setExit("Pluto", pluto);
        neptune.setItem(spaceFruit);
        neptune.setItemCase("fruit");
        
        pluto.setExit("launch", space);
        pluto.setExit("Neptune", neptune);
        pluto.setItem(spaceSnake);
        pluto.setItemCase("snake");
        
        

        currentRoom = office;  // start game in the office
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
            finished = currentRoom.checkifDied();
            if(currentRoom == outside) 
            {
               System.out.println(currentRoom.getShortDescription());
               finished = true;
            } 
        }
        System.out.println("Thank you for playing.  Good bye.");
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
        System.out.println(currentRoom.getLongDescription());
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
                
            case WAKE:
                goOffice();
                break;
                
            case SLEEP:
                goSpace();
                break;
                
            case INSPECT:
                Encounter();
                break;
                
            case ACTION:
                commitAction(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case INVENTORY:
                System.out.println("You have " + currentRoom.getGold() + " gold, which weighs " + currentRoom.getGoldWeight() + " pounds.");
                System.out.println("You have " + currentRoom.getFruit() + " fruit, which weighs " + currentRoom.getFruitWeight() + " pounds.");
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
            System.out.println("m do that! You WILL die");
        }
        else {
            currentRoom = nextRoom;
            if(nextRoom != outside) {
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }
    
    private void goOffice()
    {
      if(currentRoom != office)
      {
          currentRoom = office;
          System.out.println(currentRoom.getMediumDescription());
      } else {
          System.out.println("You are currently awake.");
      }
    }
    
    private void goSpace()
    {
        if(currentRoom == office) 
        {
          currentRoom = space;
          System.out.println(currentRoom.getMediumDescription());
        } else {
          System.out.println("You are asleep");
        }
    }
    
    private void Encounter()
    {
        currentRoom.encounter();
    }
    
    private void commitAction(Command command)
    {
        boolean checkDead = false;
        String action = command.getSecondWord();
        currentRoom.action(action);
        checkDead = currentRoom.checkifDied();
        if(checkDead == true)
        {
            System.out.println("Well done");
        } else {
        System.out.println(currentRoom.getMediumDescription());
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
}
