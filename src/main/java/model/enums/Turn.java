package model.enums;

public enum Turn {

    FIRST_PLAYER, SECOND_PLAYER;
    public Turn opposite;
    static {
        FIRST_PLAYER.opposite = SECOND_PLAYER;
        SECOND_PLAYER.opposite = FIRST_PLAYER;
    }


}

