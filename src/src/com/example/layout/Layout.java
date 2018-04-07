package src.com.example.layout;

import com.google.gson.Gson;
import src.com.example.Data;
import src.com.example.ErrorConstants;
import src.com.example.things.*;

import java.util.ArrayList;

/**
 * This class represents the overall layout of the
 * JSON/Adventure game.
 *
 * @author Rittika Adhikari & Sejal Parmar
 */
public class Layout {
    private String startingRoom;
    private ArrayList<Room> rooms;
    private ArrayList<Student> students;
    private ArrayList<Monster> monsters;
    private Player player;


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


    /**
     * Finds a student to duel in the layout
     * @param studentName the name of the student
     * @return the student object to duel
     */
    public Student findStudentToDuel(String studentName) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(studentName)) {
                return student;
            }
        }

        return null;
    }

    /**
     * Finds a monster to duel in the layout
     * @param monsterName the name of the monster
     * @return the monster object to duel
     */
    public Monster findMonsterToDuel(String monsterName) {
        for (Monster monster : monsters) {
            if (monster.getName().equalsIgnoreCase(monsterName)) {
                return monster;
            }
        }

        return null;
    }

    /**
     * Find the room to go to.
     * @param rooms the arraylist of rooms
     * @param startingRoom the name of the room to find
     * @return the room object to go to.
     */
    public Room findRoom(ArrayList<Room> rooms, String startingRoom) {
        for (Room room : rooms) {
            if (startingRoom.equalsIgnoreCase(room.getName())) return room;
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

}
