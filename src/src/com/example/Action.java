package src.com.example;

import src.com.example.layout.Room;
import src.com.example.things.Opponent;
import src.com.example.things.Player;

import java.util.ArrayList;

public class Action {
    /**
     * Prints that system doesn't understand action
     *
     * @param userInput given user input
     */
    public static void actionNotUnderstood(String userInput) {
        StringBuilder noUnderstanding = new StringBuilder("I don't understand '");
        noUnderstanding.append(userInput).append("'");
        System.out.println(noUnderstanding);
    }

    /**
     * Exits when user says quit or exit
     *
     * @param userInput given user input
     */
    public static void actionExit(String userInput) {
        if (userInput.trim().equalsIgnoreCase(PlayerAction.quit.name())) {
            System.exit(0);
        }
    }

    /**
     * Prints out the player's vital information
     *
     * @param player the given player object
     */
    public static void actionPrintPlayerInfo(Player player) {
        System.out.println(player.toString());
    }

    /**
     *
     * @param userInput
     * @param player
     * @param currRoom
     */
    public static void actionDuel(String userInput, Player player, Room currRoom) {
        if (currRoom.getMonstersInRoom().isEmpty()) {
            System.out.println("There are no opponents to battle.");
            return;
        }

        String opponentName = userInput.substring(4).trim().toLowerCase();
        Opponent opponent;
        try {
            opponent = currRoom.findOpponent(opponentName);
        } catch (Exception e) {
            System.out.println("This opponent does not exist.");
            return;
        }

    }

    /**
     * Lists all of the objects that the princess is carrying
     * @param princess princess player
     */
    public static void actionList(Princess princess) {
        princess.listWeaponsCarrying();
    }

    /**
     * Drops a weapon of the given name, if possible
     * @param princess princess player
     * @param weaponName weapon to drop
     * @param currLoc current location to add weapon to
     */
    public static void actionDrop(Princess princess, String weaponName, Location currLoc) {
        Weapon weapon;
        try {
            weapon = princess.findWeaponInArsenal(weaponName);
        } catch (Exception e) {
            System.out.println("You cannot drop " + weaponName);
            return;
        }

        princess.dropWeapon(weapon);
        currLoc.addWeapon(weapon);
        System.out.println("You dropped " + weapon.getName());
    }

    /**
     * Takes a weapon of the given name, if possible
     * @param princess princess player
     * @param weaponName weapon to drop
     * @param currLoc current location to take weapon from
     */
    public static void actionTake(Princess princess, String weaponName, Location currLoc) {
        Weapon weapon;
        try {
            weapon = currLoc.findWeaponInRoom(weaponName);
        } catch (Exception e) {
            System.out.println("You cannot take " + weaponName);
            return;
        }

        currLoc.removeWeapon(weapon);
        princess.takeWeapon(weapon);
        System.out.println("You took " + weapon.getName());
    }

    /**
     * Checks if you can travel
     * @param currLocation the current location
     * @param areMonsters whether there are monsters
     * @param locations the possible locations
     * @return your "new" location object
     */
    public static Location actionGo(Location currLocation, boolean areMonsters, ArrayList<Location> locations,
                                    Layout layout) {
        if (!areMonsters) {
            Location l = Location.findLocation(locations, currLocation.getToAdvance());
            if (!l.equals(currLocation)) {
                layout.setStartingTime(layout.getStartingTime() + currLocation.nextEpoch());
                System.out.println("After this epoch, it has been " + layout.getStartingTime() + " days.");
            }
            return l;
        } else {
            System.out.println("You cannot move, because there are still monsters here!");
            return currLocation;
        }
    }


}
