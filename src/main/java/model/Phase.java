package model;

import enums.Turn;

public abstract class Phase {
    protected Player firstPlayer;
    protected Player secondPlayer;
    protected Turn turn;

    public Phase(Player firstPlayer, Player secondPlayer, Turn turn) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.turn = turn;
    }

    public Turn getTurn() {
        return turn;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Player getPlayerByTurn (){
        if(turn == Turn.FIRST_PLAYER){
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }

    public Player getRivalPlayerByTurn(){
        if(turn == Turn.FIRST_PLAYER){
            return secondPlayer;
        } else {
            return firstPlayer;
        }
    }

}
