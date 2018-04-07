package src.com.example.things;

public class Player extends Person {
    private int level;

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
