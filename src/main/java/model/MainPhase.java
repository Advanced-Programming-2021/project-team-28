package model;

import enums.Turn;

abstract class MainPhase extends Phase {
    public MainPhase(Player firstPlayer, Player secondPlayer, Turn turn) {
        super(firstPlayer, secondPlayer, turn);
    }
}
