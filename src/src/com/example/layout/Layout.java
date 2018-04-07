package src.com.example.layout;

import com.google.gson.Gson;
import src.com.example.Data;
import src.com.example.ErrorConstants;
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
    private ArrayList<Student> students;
    /**
     * all the monster objects
     */
    private ArrayList<Monster> monsters;
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
     * Loads the layout from a given filename.
     *
     * @param filename given filename
     * @return a Layout object from JSON
     */
    public static Layout loadLayoutFromFile(String filename) {
        Gson gson = new Gson();
        return gson.fromJson(Data.getFileContentsAsString(filename), Layout.class);
    }


    public Student findStudentToDuel(String studentName) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(studentName)) {
                return student;
            }
        }

        return null;
    }

    public Monster findMonsterToDuel(String monsterName) {
        for (Monster monster : monsters) {
            if (monster.getName().equalsIgnoreCase(monsterName)) {
                return monster;
            }
        }

        return null;
    }

    public Room findRoom(ArrayList<Room> rooms, String startingRoom) {
        for (Room room : rooms) {
            if (startingRoom.equalsIgnoreCase(room.getName())) return room;
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

}
