package model;

import enums.Turn;

import java.util.Collections;


public class DrawPhase extends Phase {
    private Round round;

    public DrawPhase(Player firstPlayer, Player secondPlayer, Turn turn, Round round) {
        super(firstPlayer, secondPlayer, turn);
        this.round = round;
    }

    public void run() {
        if (getPlayerByTurn().getRemainingPlayerCardsInGame().size() == 0) {
            changeTurn();
            this.round.setWinner(getPlayerByTurn());
        } else {
            getPlayerByTurn().getCardsInHand().add(getPlayerByTurn().getRemainingPlayerCardsInGame().get(0));
            getPlayerByTurn().getRemainingPlayerCardsInGame().remove(0);
            Collections.shuffle(getPlayerByTurn().getRemainingPlayerCardsInGame());
        }
    }

}