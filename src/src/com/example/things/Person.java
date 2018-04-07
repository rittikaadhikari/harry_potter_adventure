package src.com.example.things;

/**
 * An abstract class representing people.
 *
 * @author Rittika Adhikari & Sejal Parmar
 */
public abstract class Person {
    private String name;
    private String description;
    private double attack;
    private double defense;
    private double health;


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

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The toString method that returns the name, description, attack, defense, and health
     * of the person at this moment.
     * @return a string containing info about person.
     */
    @Override
    public String toString() {
       String output = "";
       output += "Name: " + name + "\nDescription: " + description + "\nAttack: " + attack + "\nDefense: " +
               defense + "\nHealth: " + health;
       return output;
    }

}
