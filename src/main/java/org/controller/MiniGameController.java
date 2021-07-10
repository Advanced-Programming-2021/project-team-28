package org.controller;

import org.model.enums.MiniGame;
import org.model.enums.Status;
import org.view.MiniGameView;

public class MiniGameController {

    MiniGameView view;

    public MiniGameController(MiniGameView view) {
        this.view = view;
    }

    public Status winnerSelection(MiniGame p1, MiniGame p2){
        if(p1 == MiniGame.SCISSORS && p2 == MiniGame.SCISSORS){return Status.DRAW;}
        if(p1 == MiniGame.ROCK && p2 == MiniGame.ROCK){return Status.DRAW;}
        if(p1 == MiniGame.PAPER && p2 == MiniGame.PAPER){return Status.DRAW;}
        if(p1 == MiniGame.SCISSORS && p2 == MiniGame.PAPER){return Status.P1;}
        if(p1 == MiniGame.SCISSORS && p2 == MiniGame.ROCK){return Status.P2;}
        if(p1 == MiniGame.ROCK && p2 == MiniGame.SCISSORS){return Status.P1;}
        if(p1 == MiniGame.ROCK && p2 == MiniGame.PAPER){return Status.P2;}
        if(p1 == MiniGame.PAPER && p2 == MiniGame.SCISSORS){return Status.P2;}
        if(p1 == MiniGame.PAPER && p2 == MiniGame.ROCK){return Status.P1;}
        return null;
    }
}
