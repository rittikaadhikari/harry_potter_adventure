package src.com.example.things;

public class Player extends Person {
    public int level;

    public int getLevel() {
        return level;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
       String output = super.toString();
       output += "\nLevel: " + level;
       return output;
    }

}
