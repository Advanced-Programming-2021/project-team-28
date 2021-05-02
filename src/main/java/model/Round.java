package model;

import Enums.Turn;

public class Round {
    private Player firstPlayer;
    private Player secondPlayer;
    private Turn turn;

    public Round (Player firstPlayer, Player secondPlayer, Turn turn){
        this.turn = turn;
        setFirstPlayer(firstPlayer);
        setSecondPlayer(secondPlayer);
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Turn getTurn() {
        return turn;
    }

    public void changeTurn(){
        if(turn == Turn.FIRST_PLAYER){
            turn = Turn.SECOND_PLAYER;
        } else {
            turn = Turn.FIRST_PLAYER;
        }
    }

//    public String getMapToString (){
//
//    }
}
