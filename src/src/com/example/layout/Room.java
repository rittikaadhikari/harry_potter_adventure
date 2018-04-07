package src.com.example.layout;

import src.com.example.ErrorConstants;
import src.com.example.things.*;

import java.util.ArrayList;

/**
 * This class represents the format of a room.
 *
 * @author Rittika Adhikari & Sejal Parmar
 */
public class Room {
    private String name;
    private String description;
    private ArrayList<Item> items;
    private ArrayList<String> directions;
    private ArrayList<String> studentsInRoom;
    private ArrayList<String> monstersInRoom;
    private ArrayList<Food> food;
    private ArrayList<Spell> spells;


    public String getDescription() {
        return description;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<String> getDirections() {
        return directions;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getStudentsInRoom() {
        return studentsInRoom;
    }

    public ArrayList<String> getMonstersInRoom() {
        return monstersInRoom;
    }

    public ArrayList<Food> getFood() { return food; }

    public ArrayList<Spell> getSpells() { return spells; }


    /**
     * Finds the opponent in room
     * @param opponentName the name of the opponent to duel
     * @param layout the layout
     * @return the opponent object to duel
     * @throws IllegalArgumentException if not found
     */
    public Opponent findOpponentInRoom(String opponentName, Layout layout) throws IllegalArgumentException {
        System.out.println(opponentName);
        if (studentsInRoom != null) {
            for (String student : studentsInRoom) {
                if (student.equalsIgnoreCase(opponentName)) {
                    return layout.findStudentToDuel(opponentName);
                }
            }
        }

        if (monstersInRoom != null) {
            for (String monster : monstersInRoom) {
                if (monster.equalsIgnoreCase(opponentName)) {
                    return layout.findMonsterToDuel(opponentName);
                }
            }
        }


        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    /**
     * Finds a spell in the room
     * @param spellName the name of the spell
     * @return the spell object
     * @throws IllegalArgumentException if not found
     */
    public Spell findSpellInRoom(String spellName) throws IllegalArgumentException {
        if (spells == null) throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
        for (Spell spell : spells) {
            if (spell.getName().equalsIgnoreCase(spellName)) return spell;
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    /**
     * Finds the food in the room
     * @param foodName the name of the food
     * @return the food
     * @throws IllegalArgumentException if not found
     */
    public Food findFoodInRoom(String foodName) throws IllegalArgumentException {
        if (food == null) throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
        for (Food food : food) {
            if (food.getName().equalsIgnoreCase(foodName)) return food;
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    /**
     * Finds an item in the room
     * @param itemName the name of item
     * @return the item
     * @throws IllegalArgumentException if not found
     */
    public Item findItemInRoom(String itemName) throws IllegalArgumentException {
        if (items == null) throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) return item;
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    /**
     * Adds an item to the room
     * @param item the item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes an item from room
     * @param item the item to remove
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Removes a spell from room
     * @param spell the spell to remove
     */
    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    /**
     * Lists the items in the room
     */
    public void listItemsInRoom() {
        String output = "The items in this room are: ";
        for (Item item : items) {
            output += item.getName() + "\t";
        }

        System.out.println(output);
    }

    /**
     * Lists the food in the room
     */
    public void listFoodInRoom() {
        String output = "The food in this room is: ";
        for (Food food : food) {
            output += food.getName() + "\t";
        }

        System.out.println(output);
    }

    /**
     * Lists the students in the room
     */
    public void listStudentsInRoom() {
        String output = "You can duel the following students: ";
        for (String student : studentsInRoom) {
            output += student + "\t";
        }

        System.out.println(output);
    }

    /**
     * Lists the monsters in the room
     */
    public void listMonstersInRoom() {
        String output = "You can duel the following monsters: ";
        for (String monster : monstersInRoom) {
            output += monster + "\t";
        }

        System.out.println(output);
    }

    /**
     * Lists the directions in the room
     */
    public void listDirectionsFromRoom() {
        String output = "You can go: ";
        for (String direction : directions) {
            output += direction + "\t";
        }

        System.out.println(output);
    }

    /**
     * Lists the spells in the room.
     */
    public void listSpellsInRoom() {
        String output = "You can learn the following spells: ";
        for (Spell spell : spells) {
            output += spell.getName() + "\t";
        }

        System.out.println(output);
    }
}
