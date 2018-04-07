package src.com.example.layout;

import com.google.gson.Gson;
import src.com.example.Data;
import src.com.example.things.*;

import java.util.ArrayList;

public class Layout {
    /**
     * the room you start in
     */
    public String startingRoom;
    /**
     * all the room objects
     */
    public ArrayList<Room> rooms;
    /**
     * all the student objects
     */
    public ArrayList<Student> students;
    /**
     * all the monster objects
     */
    public ArrayList<Monster> monsters;
    /**
     * all the food objects
     */
    public ArrayList<Food> food;
    /**
     * all the spell objects
     */
    public ArrayList<Spell> spells;
    /**
     * the player object
     */
    public Player player;

    /**
     * gets starting room.
     *
     * @return starting room as String
     */
    public String getStartingRoom() {
        return startingRoom;
    }

    /**
     * gets all the rooms in an arraylist.
     *
     * @return rooms
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * gets all students in an arraylist.
     *
     * @return students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * gets all monsters in arraylist.
     *
     * @return monsters
     */
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    /**
     * gets the player object
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * gets all food in an arraylist.
     *
     * @return food
     */
    public ArrayList<Food> getFood() {
        return food;
    }

    /**
     * gets all spells in an arraylist.
     *
     * @return spells
     */
    public ArrayList<Spell> getSpells() {
        return spells;
    }

    /**
     * Loads the layout from a given filename.
     *
     * @param filename given filename
     * @return a Layout object from JSON
     */
    public static Layout loadLayoutFromFile(String filename) {
        Gson gson = new Gson();
        return gson.fromJson(Data.getFileContentsAsString(filename), Layout.class);
    }

}
