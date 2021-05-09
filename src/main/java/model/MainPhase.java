package model;

import enums.Turn;

abstract class MainPhase extends Phase {
    public MainPhase(Player firstPlayer, Player secondPlayer, Turn turn, int turnsPlayed) {
        super(firstPlayer, secondPlayer, turn, turnsPlayed);
    }
}
