package model;

import enums.Turn;

public class DrawPhase extends Phase{
    public DrawPhase(Player firstPlayer, Player secondPlayer, Turn turn, int turnsPlayed) {
        super(firstPlayer, secondPlayer, turn, turnsPlayed);
    }
}
