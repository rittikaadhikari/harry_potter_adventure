package src.com.example.layout;

import src.com.example.things.Item;
import src.com.example.things.Thing;

import java.util.ArrayList;

public class Room {
    public String name;
    public String description;
    public ArrayList<Item> items;
    public ArrayList<String> directions;
    public ArrayList<String> studentsInRoom;
    public ArrayList<String> monstersInRoom;

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
}
