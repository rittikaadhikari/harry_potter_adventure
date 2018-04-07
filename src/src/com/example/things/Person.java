package src.com.example.things;

public abstract class Person {
    public String name;
    public String description;
    public double attack;
    public double defense;
    public double health;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getHealth() {
        return health;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
       String output = "";
       output += "Name: " + name + "\nDescription: " + description + "\nAttack: " + attack + "\nDefense: " +
               defense + "\nHealth: " + health;
       return output;
    }

}
