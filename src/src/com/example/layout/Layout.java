package src.com.example.layout;

import com.google.gson.Gson;
import src.com.example.Data;
import src.com.example.things.*;

import java.util.ArrayList;

public class Layout {
    /**
     * the room you start in
     */
    private String startingRoom;
    /**
     * all the room objects
     */
    private ArrayList<Room> rooms;
    /**
     * all the student objects
     */
    private static ArrayList<Student> students;
    /**
     * all the monster objects
     */
    private static ArrayList<Monster> monsters;
    /**
     * all the food objects
     */
    private ArrayList<Food> food;
    /**
     * all the spell objects
     */
    private ArrayList<Spell> spells;
    /**
     * the player object
     */
    private Player player;

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


    public static Student findStudentToDuel(String studentName) {
        for (Student student : students) {
            if (student.getName().toLowerCase().equals(studentName.toLowerCase())) {
                return student;
            }
        }

        return null;
    }

    public static Monster findMonsterToDuel(String monsterName) {
        for (Monster monster : monsters) {
            if (monster.getName().toLowerCase().equalsIgnoreCase(monsterName)) {
                return monster;
            }
        }

        return null;
    }

}
