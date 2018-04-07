package src.com.example.layout;

import src.com.example.ErrorConstants;
import src.com.example.things.*;

import java.util.ArrayList;

public class Room {
    /** name of the room */
    public String name;
    /** the room's description */
    public String description;
    /** the items the room contains */
    public ArrayList<Item> items;
    /** where you can go from this room */
    public ArrayList<String> directions;
    /** students you can duel, if any */
    public ArrayList<String> studentsInRoom;
    /** monsters you can duel, if any */
    public ArrayList<String> monstersInRoom;
    public ArrayList<Food> food;
    public ArrayList<Spell> spells;

    /**
     * gets room description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets room's items.
     * @return items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * gets where you can go from this room.
     * @return directions
     */
    public ArrayList<String> getDirections() {
        return directions;
    }

    /**
     * gets room name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * gets all the students in the room.
     * @return students
     */
    public ArrayList<String> getStudentsInRoom() {
        return studentsInRoom;
    }

    /**
     * gets all the monsters in the room.
     * @return monsters
     */
    public ArrayList<String> getMonstersInRoom() {
        return monstersInRoom;
    }

    public ArrayList<Food> getFood() { return food; }

    public ArrayList<Spell> getSpells() { return spells; }


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

    public Spell findSpellInRoom(String spellName) throws IllegalArgumentException {
        if (spells == null) throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
        for (Spell spell : spells) {
            if (spell.getName().equalsIgnoreCase(spellName)) return spell;
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    public Food findFoodInRoom(String foodName) throws IllegalArgumentException {
        if (food == null) throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
        for (Food food : food) {
            if (food.getName().equalsIgnoreCase(foodName)) return food;
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    public Item findItemInRoom(String itemName) throws IllegalArgumentException {
        if (items == null) throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) return item;
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    public void listItemsInRoom() {
        String output = "The items in this room are: ";
        for (Item item : items) {
            output += item.getName() + "\t";
        }

        System.out.println(output);
    }

    public void listFoodInRoom() {
        String output = "The food in this room is: ";
        for (Food food : food) {
            output += food.getName() + "\t";
        }

        System.out.println(output);
    }

    public void listStudentsInRoom() {
        String output = "You can duel the following students: ";
        for (String student : studentsInRoom) {
            output += student + "\t";
        }

        System.out.println(output);
    }

    public void listMonstersInRoom() {
        String output = "You can duel the following monsters: ";
        for (String monster : monstersInRoom) {
            output += monster + "\t";
        }

        System.out.println(output);
    }

    public void listDirectionsFromRoom() {
        String output = "You can go: ";
        for (String direction : directions) {
            output += direction + "\t";
        }

        System.out.println(output);
    }


    public void listSpellsInRoom() {
        String output = "You can learn the following spells: ";
        for (Spell spell : spells) {
            output += spell.getName() + "\t";
        }

        System.out.println(output);
    }
}
