package com.example;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


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

        Layout layout = Layout.loadLayoutFromFile("siebel.json");
        if (arguments.length > 0) {
            String url = arguments[0];
            // Make an HTTP request to the above URL
            try {
                loadLayoutFromJson(url);
            } catch (UnirestException e) {
                System.out.println("Network not responding");
            } catch (MalformedURLException e) {
                System.out.println("Bad URL: " + url);
            }
        }


        GamePlayer.playGame(layout);
    }

    /**
     * Makes an API request to the URL and returns a layout object
     * depending on the json.
     * @param url the given URL to parse json from
     * @throws UnirestException if network times out
     * @throws MalformedURLException if bad URL
     */
    public static void loadLayoutFromJson(String url) throws UnirestException, MalformedURLException {
        final HttpResponse<String> stringHttpResponse;

        // This will throw MalformedURLException if the url is malformed.
        new URL(url);

        stringHttpResponse = Unirest.get(url).asString();
        // Check to see if the request was successful; if so, convert the payload JSON into Java objects
        if (stringHttpResponse.getStatus() == STATUS_OK) {
            String json = stringHttpResponse.getBody();
            Gson gson = new Gson();
            final Layout layout = gson.fromJson(json, Layout.class);
            GamePlayer.playGame(layout);
        }

    }




}