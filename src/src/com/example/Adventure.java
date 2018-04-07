package src.com.example;

import com.google.gson.Gson;
import src.com.example.layout.Layout;



/**
 * Takes the user on a wild adventure around Siebel Center.
 *
 * @author Rittika Adhikari
 * @netid rittika2
 */
public class Adventure {

    private static final int STATUS_OK = 200;

    /**
     * Accesses the json from either the given URL in arguments or the
     * default file JSON.
     * @param arguments the URL to parse json from
     */
    public static void main(String [] arguments) {

        Layout layout = Layout.loadLayoutFromFile("hogwarts.json");

        GamePlayer.playGame(layout);
    }




}