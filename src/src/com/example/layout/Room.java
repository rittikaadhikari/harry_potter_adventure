package src.com.example.layout;

import src.com.example.things.Item;
import src.com.example.things.Thing;

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
}
