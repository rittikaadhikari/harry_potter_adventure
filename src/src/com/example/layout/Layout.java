package src.com.example.layout;

import com.google.gson.Gson;
import src.com.example.Data;
import src.com.example.things.*;
import java.util.ArrayList;

public class Layout {
    public String startingRoom;
    public ArrayList<Room> rooms;
    public static ArrayList<Student> students;
    public static ArrayList<Monster> monsters;
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

    /**
     * Loads the layout from a given filename.
     * @param filename given filename
     * @return a Layout object from JSON
     */
    public static Layout loadLayoutFromFile(String filename) {
        Gson gson = new Gson();
        return gson.fromJson(Data.getFileContentsAsString(filename), Layout.class);
    }



}
