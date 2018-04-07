package src.com.example;

import src.com.example.layout.Layout;
import src.com.example.layout.Room;
import src.com.example.things.Item;
import src.com.example.things.Monster;
import src.com.example.things.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The module that runs the game from given JSON.
 *
 * @author Rittika Adhikari & Sejal Parmar
 */
public class GamePlayer {

    /**
     * Plays entire game.
     * @param layout the layout object to take in
     */
    public static void playGame(Layout layout) {
        // initializes values
        String startingRoom = layout.getStartingRoom();
        ArrayList<Room> rooms = layout.getRooms();
        Player player = layout.getPlayer();

        Room currRoom = layout.findRoom(rooms, startingRoom);
        Room greatHall = layout.findRoom(rooms, "Great_Hall");
        boolean isStartingRoom = true;
        boolean visitedGreatHall = false;

        // while the user doesn't quit,
        // keep playing the game
        while (true) {
            System.out.println(currRoom.getDescription());

            isStartingRoom = checkIfStartingLocation(isStartingRoom, player);
            visitedGreatHall = checkIfGreatHall(visitedGreatHall, player);

            if (currRoom.getItems() == null) {
                System.out.println("There are no items in this room");
            } else {
                currRoom.listItemsInRoom();
            }

            if (currRoom.getFood() == null) {
                System.out.println("There is no food in this room");
            } else {
                currRoom.listFoodInRoom();
            }

            if (currRoom.getStudentsInRoom() == null) {
                System.out.println("There are no students to duel in this room");
            } else {
                currRoom.listStudentsInRoom();
            }

            if (currRoom.getMonstersInRoom() == null) {
                System.out.println("There are no monsters to duel in this room");
            } else {
                currRoom.listMonstersInRoom();
            }

            if (currRoom.getSpells() == null) {
                System.out.println("There are no spells in this room");
            } else {
                currRoom.listSpellsInRoom();
            }


            currRoom.listDirectionsFromRoom();
            // gets inputs from the UI
            // updates currRoom accordingly
            currRoom = handleUserInterface(player, currRoom, layout);
        }

    }

    /**
     * Handles the user's inputs according to their first character.
     * @param player the player object
     * @param currRoom the current room
     * @param layout the layout
     * @return the next room
     */
    private static Room handleUserInterface(Player player, Room currRoom, Layout layout) {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (true) {
            String[] splitInput = userInput.split("\\s+");

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
                case duel:
                    try {
                        currRoom = Action.actionDuel(userInput, player, currRoom, layout);
                        break;
                    } catch (Exception e) {
                        System.out.println("You cannot duel this.");
                    }
                    break;
                case travelto:
                    try {
                        currRoom = Action.actionGo(currRoom, userInput.substring(8).trim(), layout);
                        break;
                    } catch (Exception e) {
                        System.out.println("This room does not exist");
                    }
                    break;
                case listspells:
                    Action.actionListSpells(player);
                    break;
                case listitems:
                    Action.actionListItems(player);
                    break;
                case takeitem:
                    Action.actionTake(player, userInput.substring(8).trim(), currRoom);
                    break;
                case dropitem:
                    Action.actionDrop(player, userInput.substring(8).trim(), currRoom);
                    break;
                case eatfood:
                    Action.actionEat(player, userInput.substring(7).trim(), currRoom);
                    break;
                case learnspell:
                    Action.actionLearnSpell(player, userInput.substring(10).trim(), currRoom);
                    break;
                default :
                    Action.actionNotUnderstood(userInput);
                    break;
            }
            break;
        }
        return currRoom;

    }


    /**
     * Checks if the player is in the starting room
     * @param isStartingRoom boolean
     * @param player player object
     * @return boolean stating whether this is the starting room
     */
    public static boolean checkIfStartingLocation(boolean isStartingRoom, Player player) {
        if (isStartingRoom) {
            System.out.println("Your journey begins here");
            System.out.println("What is your name? ");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            player.setName(name);
            //TODO: Go to the Action class to make this method!
            Action.actionChooseWand(player);
            return false;
        } else {
            return false;
        }
    }

    /**
     * Checks if the player is in the great hall & hasn't visited the great hall
     * @param visitedGreatHall boolean stating if player has visited great hall
     * @param player player object
     * @return boolean stating whether they've visted great hall
     */
    public static boolean checkIfGreatHall(boolean visitedGreatHall, Player player) {
        if (visitedGreatHall) return true;
        else {
            //TODO: Go to the Action class to make this method!
            Action.actionSortStudent(player);
            return true;
        }
    }

}
