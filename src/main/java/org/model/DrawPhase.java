package org.model;

import org.model.enums.PhaseName;
import org.view.PhaseView;

import java.util.Collections;


public class DrawPhase extends Phase {

    public DrawPhase(Round round) {
        super(round);
    }

    public void run() {
        if(!getPlayerByTurn().isSurrenderedOrLostByCheat()){
            new PhaseView().printPhaseName(PhaseName.DRAW_PHASE);
        }
        if (getPlayerByTurn().getRemainingPlayerCardsInGame().size() == 0) {
            changeTurn();
            this.round.setWinner(getPlayerByTurn());
        } else if(!getPlayerByTurn().isAbleToAddCardInDrawPhase()){
            getPlayerByTurn().setAbleToAddCardInDrawPhase(true);
        } else {
            getPlayerByTurn().getCardsInHand().add(getPlayerByTurn().getRemainingPlayerCardsInGame().get(0));
            getPlayerByTurn().getRemainingPlayerCardsInGame().remove(0);
            Collections.shuffle(getPlayerByTurn().getRemainingPlayerCardsInGame());
        }
    }

}