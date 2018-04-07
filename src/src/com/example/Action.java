package src.com.example;

import src.com.example.layout.Layout;
import src.com.example.layout.Room;
import src.com.example.things.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents the actions a player can take during a game.
 *
 * @author Rittika Adhikari & Sejal Parmar
 */
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
     * Duels an opponent in the room
     *
     * @param userInput the given user input
     * @param player the player object
     * @param currRoom the current room
     */
    public static Room actionDuel(String userInput, Player player, Room currRoom, Layout layout) {
        if (currRoom.getMonstersInRoom() == null && currRoom.getStudentsInRoom() == null) {
            System.out.println("There are no opponents to battle.");
            throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
        }

        String opponentName = userInput.substring(4).trim().toLowerCase();
        Opponent opponent;
        try {
            opponent = currRoom.findOpponentInRoom(opponentName, layout);
        } catch (Exception e) {
            System.out.println("This opponent does not exist.");
            throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
        }

        System.out.println("You are dueling " + opponent.getName() + "!");
        System.out.println(opponent.getDescription());

        player = Action.actionEngageDuel(player, opponent);

        if (player.getHealth() <= 0) {
            System.out.println("You have very low health from this battle");
            player.setHealth(0);
            for (Room room : layout.getRooms()) {
                if (room.getName().equalsIgnoreCase("hospital_wing")) return room;
            }
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    /**
     * Engage an opponent in duel
     * @param player the player object
     * @param opponent the opponent to engage
     * @return the new player object
     */
    private static Player actionEngageDuel(Player player, Opponent opponent) {
        boolean canDuel = true;
        double originalOpponentAttack = opponent.getAttack();
        double originalOpponentDefense = opponent.getDefense();
        double originalOpponentHealth = opponent.getHealth();

        while (canDuel) {
            StringBuilder message = new StringBuilder("You are now dueling ");
            message.append(opponent.getName()).append("\n");
            message.append("You can: attack ").append(opponent.getName());
            message.append(", attackwith ITEM_NAME, disengage, listspells, listitems, playerinfo, quit");
            System.out.println(message);

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            String[] splitInput = input.split("\\s+");

            try {
                PlayerAction.getEnum(splitInput[0].toLowerCase());
            } catch (Exception e) {
                Action.actionNotUnderstood(input);
                break;
            }

            PlayerAction firstWord = PlayerAction.valueOf(splitInput[0].toLowerCase());

            // check which action to take and calls helper function accordingly
            switch (firstWord) {
                case attack:
                    canDuel = player.attackOpponent(opponent);
                    break;
                case attackwith:
                    try {
                        canDuel = player.attackWithOpponent(opponent, input.substring(10).trim());
                        break;
                    } catch (Exception e) {
                        System.out.println("You do not have this item");
                    }
                    break;
                case quit:
                    Action.actionExit(input);
                    break;
                case playerinfo:
                    Action.actionPrintPlayerInfo(player);
                    break;
                case listspells:
                    Action.actionListSpells(player);
                    break;
                case listitems:
                    Action.actionListItems(player);
                    break;
                case disengage:
                    System.out.println("You have withdrawn from this battle");
                    canDuel = false;
                    break;
                default :
                    Action.actionNotUnderstood(input);
                    break;
            }
        }

        // restoring to max attack so you can battle them again later
        opponent.setAttack(originalOpponentAttack);
        opponent.setDefense(originalOpponentDefense);
        opponent.setHealth(originalOpponentHealth);

        return player;
    }


    /**
     * List all of the spells learned by the player
     * @param player the player object
     */
    public static void actionListSpells(Player player) {
        player.listSpellsLearned();
    }

    /**
     * List all of the items carried by the player
     * @param player the player object
     */
    public static void actionListItems(Player player) {
        player.listItemsCarrying();
    }

    /**
     * Drop an item
     * @param player the player object
     * @param itemName the name of the item
     * @param currRoom the current room
     */
    public static void actionDrop(Player player, String itemName, Room currRoom) {
        Item item;
        try {
            item = player.findItem(itemName);
        } catch (Exception e) {
            System.out.println("You cannot drop " + itemName);
            return;
        }

        player.dropItem(item);
        currRoom.addItem(item);
        System.out.println("You dropped " + item.getName());
    }

    /**
     * Take an item from a room
     * @param player the player object
     * @param itemName the item name
     * @param currRoom the current room
     */
    public static void actionTake(Player player, String itemName, Room currRoom) {
        Item item;
        try {
            item = currRoom.findItemInRoom(itemName);
        } catch (Exception e) {
            System.out.println("You cannot take " + itemName);
            return;
        }

        currRoom.removeItem(item);
        player.takeItem(item);
        System.out.println("You took " + item.getName());
    }

    /**
     * Eat food from a room
     * @param player the player object
     * @param foodName the food to eat
     * @param currRoom the current room
     */
    public static void actionEat(Player player, String foodName, Room currRoom) {
        Food food;
        try {
            food = currRoom.findFoodInRoom(foodName);
        } catch (Exception e) {
            System.out.println("You cannot eat that!");
            return;
        }

        player.takeFood(food);
        System.out.println("Yum! You ate " + food.getName());
    }

    /**
     * Moves a player to another room
     * @param currRoom the current room
     * @param directionName the direction to go in
     * @param layout the layout
     * @return the new room
     */
    public static Room actionGo(Room currRoom, String directionName, Layout layout) {
        try {
            return layout.findRoom(layout.getRooms(), directionName);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals(ErrorConstants.NOT_FOUND)) {
                StringBuilder badDirections = new StringBuilder("I can't go ");
                badDirections.append(directionName);
                System.out.println(badDirections);
                throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
            }
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    /**
     * Learns a spell from the room
     * @param player the player object
     * @param spellName the name of the spell
     * @param currRoom the current room
     */
    public static void actionLearnSpell(Player player, String spellName, Room currRoom) {
        Spell spell = null;
        try {
            spell = currRoom.findSpellInRoom(spellName);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals(ErrorConstants.NOT_FOUND)) {
                System.out.println("This spell does not exist.");
                return;
            }
        }

        player.learnSpell(spell);
        System.out.println("Congrats, you have learned " + spell.getName());

        //TODO: your level up functionality! check if you can level up
        player.levelUp();

        currRoom.removeSpell(spell);
    }

    /**
     * TODO: Here you can ask a series of Sorting Hat questions to sort your player
     * when they first enter the Great Hall.
     *
     * @param player the player object
     */
    public static void actionSortStudent(Player player) {

    }

    /**
     * TODO: Here you can ask a series of Wand Choosing questions to sort your player
     * when they first enter Ollivanders.
     *
     * Then, you can assign special damage powers to the wand in the room accordingly!
     *
     * @param player the player object
     *
     *
     */
    public static void actionChooseWand(Player player) {

    }


}
