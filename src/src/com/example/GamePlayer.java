package src.com.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The module that runs the game from given JSON.
 *
 * @author Rittika Adhikari
 */
public class GamePlayer {

    /**
     * Plays entire game.
     * @param layout the layout object to take in
     */
    public static void playGame(Layout layout) {
        // initializes values
        String startingLocation = layout.getStartingLocation();
        String endingLocation = layout.getEndingLocation();
        ArrayList<Location> locations = layout.getLocations();
        Player player = layout.getPlayer();

        Location currLoc = Location.findLocation(locations, startingLocation);
        Location endLoc = Location.findLocation(locations, endingLocation);
        boolean isStartingRoom = true;
        boolean isEndingRoom = false;

        // while the room is not the ending room,
        // keep playing the game
        while (!isEndingRoom) {
            System.out.println(currLoc.getDescription());

            isStartingRoom = checkIfStartingLocation(isStartingRoom);
            isEndingRoom = checkIfEndingLocation(currLoc.getName(), endLoc);

            ArrayList<Weapon> weapons = currLoc.getWeapons();
            Location.listWeaponsInLocation(weapons);

            ArrayList<Monster> monsters = new ArrayList<>();
            if (currLoc instanceof BattleField) {
                monsters = ((BattleField) currLoc).getMonsters();
                ((BattleField) currLoc).listMonsters();
            } else {
                System.out.println("You are in a castle, so there are no monsters.");
            }

            boolean areMonsters = true;
            String toAdvance = null;
            if (monsters.size() == 0) {
                toAdvance = currLoc.getToAdvance();
                System.out.println("You may now travel to " + toAdvance);
                areMonsters = false;
            }


            // gets inputs from the UI
            // updates currLoc accordingly
            currLoc = handleUserInterface(player, currLoc, areMonsters, locations, layout);
        }

    }


    /**
     * Updates the current location based on the user interface
     * @param player the player object passed in
     * @param currLoc the current location
     * @param areMonsters whether there are monsters
     * @param locations the locations
     * @param layout the layout
     * @return the new location
     */
    private static Location handleUserInterface(Player player, Location currLoc, boolean areMonsters,
                                                ArrayList<Location> locations, Layout layout) {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (true) {
            String[] splitInput = userInput.split("\\s+");

            String weaponName = userInput.substring(4).trim();

            try {
                PlayerAction.getEnum(splitInput[0].toLowerCase());
            } catch (Exception e) {
                Action.actionNotUnderstood(userInput);
                break;
            }

            PlayerAction firstWord = PlayerAction.valueOf(splitInput[0].toLowerCase());

            // check which action to take and calls helper function accordingly
            switch (firstWord) {
                case quit:
                    Action.actionExit(userInput);
                    break;
                case playerinfo:
                    Action.actionPrintPlayerInfo(player);
                    break;
                case battle:
                    Action.actionBattle(userInput, player, currLoc, layout);
                    break;
                case travel:
                    currLoc = Action.actionGo(currLoc, areMonsters, locations, layout);
                    break;
                case take:
                    Action.actionTake(player, weaponName, currLoc);
                    break;
                case drop:
                    Action.actionDrop(player, weaponName, currLoc);
                    break;
                case list:
                    Action.actionList(player);
                    break;
                case weaponinfo:
                    weaponName = userInput.substring(10).trim();
                    Action.actionPrintWeaponInfo(weaponName, player);
                    break;
                case enchant:
                    weaponName = userInput.substring(7).trim();
                    Action.actionEnchant(player, weaponName);
                    break;
                default :
                    Action.actionNotUnderstood(userInput);
                    break;
            }
            break;
        }
        return currLoc;

    }


    /**
     * Checks if currRoom is starting room
     * @param isStartingRoom boolean that says if startingRoom
     * @return new value for isStartingRoom
     */
    public static boolean checkIfStartingLocation(boolean isStartingRoom) {
        if (isStartingRoom) {
            System.out.println("Your journey begins here");
            return false;
        } else {
            return false;
        }
    }

    /**
     * Checks if currLoc is endingLocation
     * @param currLocName name of currLoc
     * @param endingLocation endingLocation object
     * @return new value for isEndingRoom
     */
    public static boolean checkIfEndingLocation(String currLocName, Location endingLocation) {
        if (currLocName.equals(endingLocation.getName())) {
            System.exit(0);
            return true;
        } else {
            return false;
        }
    }
}
