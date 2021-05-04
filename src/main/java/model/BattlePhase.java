package model;

import enums.Turn;

public class BattlePhase extends Phase{
    public BattlePhase(Player firstPlayer, Player secondPlayer, Turn turn) {
        super(firstPlayer, secondPlayer, turn);
    }
}
