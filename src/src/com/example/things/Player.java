package src.com.example.things;

import src.com.example.ErrorConstants;

import java.util.ArrayList;

public class Player extends Person {
    private int level;
    private ArrayList<Spell> spells;
    private ArrayList<Item> items;

    public int getLevel() {
        return level;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setLevel(int level) { this.level = level; }

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

    public boolean attackOpponent(Opponent opponent) {
        double damageOnOpponent = getAttack() - opponent.getDefense();
        if (damageOnOpponent < 0) {
            damageOnOpponent = 0;
        }
        opponent.setHealth(opponent.getHealth() - damageOnOpponent);

        if (opponent.getHealth() > 0) {
            double damageOnPlayer = opponent.getAttack() - getDefense();
            if (damageOnPlayer < 0) {
                damageOnPlayer = 0;
            }

            setHealth(getHealth() - damageOnPlayer);

            return false;
        }

        return true;
    }

    public boolean attackWithOpponent(Opponent opponent, String thingName) {
        Spell sp = null;
        for (Spell spell : spells) {
            if (thingName.equalsIgnoreCase(spell.getName())) {
                sp = spell;
                break;
            }
        }

        Item it = null;
        for (Item item : items) {
            if (thingName.equalsIgnoreCase(item.getName())) {
                it = item;
                break;
            }
        }

        if (sp == null && it == null) throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
        else {
            double damage = 0;
            if (it == null) damage += sp.getDamage();
            else damage += it.getDamage();

            double damageOnOpponent = getAttack() + damage - opponent.getDefense();
            if (damageOnOpponent < 0) {
                damageOnOpponent = 0;
            }
            opponent.setHealth(opponent.getHealth() - damageOnOpponent);

            if (opponent.getHealth() > 0) {
                double damageOnPlayer = opponent.getAttack() - getDefense();
                if (damageOnPlayer < 0) {
                    damageOnPlayer = 0;
                }

                setHealth(getHealth() - damageOnPlayer);

                return false;
            }
            return true;
        }
    }

    public void listSpellsLearned() {
        if (spells == null) {
            System.out.println("You have not learned any spells");
            return;
        }

        String spellsLearned = "You have learned: ";
        for (Spell spell : spells) {
            spellsLearned += spell.getName() + "\t";
        }

        System.out.println(spellsLearned);
    }

    public void listItemsCarrying() {
        if (items == null) {
            System.out.println("You have not gotten any items");
            return;
        }

        String itemsCarried = "You have: ";
        for (Item item : items) {
            itemsCarried += item.getName() + "\t";
        }

        System.out.println(itemsCarried);

    }

    public Item findItem(String itemName) {
        if (items == null) throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);

        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) return item;
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    public Spell findSpell(String spellName) {
        if (spells == null) throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);

        for (Spell spell : spells) {
            if (spell.getName().equalsIgnoreCase(spellName)) return spell;
        }

        throw new IllegalArgumentException(ErrorConstants.NOT_FOUND);
    }

    public void dropItem(Item item) {
        items.remove(item);
    }

    public void takeItem(Item item) {
        items.add(item);
    }

    public void learnSpell(Spell spell) {
        spells.add(spell);
    }

    public void takeFood(Food food) {
        setHealth(getHealth() + food.getBonus());
    }

    /**
     * TODO: Level up your player depending on the number of spells they have learned!
     * Then, print out your player's current level.
     */
    public void levelUp() {

    }


}
