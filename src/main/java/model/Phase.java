package model;

import Enums.Turn;

abstract class Phase {
    protected Player firstPlayer;
    protected Player secondPlayer;
    protected Turn turn;

    public Phase(Player firstPlayer, Player secondPlayer, Turn turn) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.turn = turn;
    }
    public void run(){}
}
