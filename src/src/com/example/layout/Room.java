package src.com.example.layout;

import src.com.example.things.Thing;

import java.util.ArrayList;

public class Room {
    public String name;
    public String description;
    public ArrayList<Thing> items;
    public ArrayList<String> directions;

    public String getDescription() {
        return description;
    }

    public ArrayList<Thing> getItems() {
        return items;
    }

    public ArrayList<String> getDirections() {
        return directions;
    }

    public String getName() {
        return name;
    }
}
