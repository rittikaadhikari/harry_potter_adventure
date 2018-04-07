package src.com.example;

/**
 * An enum consisting of all possible actions
 * a player can take.
 *
 * @author Rittika Adhikari & Sejal Parmar
 */
public enum PlayerAction {
    quit,
    playerinfo,
    duel,
    attack,
    attackwith,
    disengage,
    travelto,
    listspells,
    listitems,
    takeitem,
    dropitem,
    learnspell,
    eatfood,
    showcommand;

    /**
     * Check if valid enum.
     * @param value the current string
     * @return the enum
     */
    public static PlayerAction getEnum(String value) {
        for(PlayerAction v : values())
            if(v == PlayerAction.valueOf(value))
                return v;
        throw new IllegalArgumentException(ErrorConstants.NOT_UNDERSTOOD);
    }
}


