package src.com.example.layout;

import src.com.example.things.*;
import java.util.ArrayList;

public class Layout {
    public String startingRoom;
    public ArrayList<Room> rooms;
    public ArrayList<Student> students;
    public ArrayList<Monster> monsters;
    public ArrayList<Food> food;
    public ArrayList<Spell> spells;
    public Player player;

    public String getStartingRoom() {
        return startingRoom;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Food> getFood() {
        return food;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

}
