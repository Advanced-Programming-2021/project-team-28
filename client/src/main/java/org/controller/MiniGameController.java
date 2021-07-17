package org.controller;

import org.model.User;
import org.model.enums.MiniGame;
import org.model.enums.NumberOfRounds;
import org.model.enums.Status;
import org.view.MiniGameView;

public class MiniGameController {

    private MiniGameView view;

    private User user1;
    private User user2;

    private NumberOfRounds numberOfRounds;

    public MiniGameController(User user1 , User user2 , NumberOfRounds numberOfRounds) {
        this.view = new MiniGameView(this);
        this.user1 = user1;
        this.user2 = user2;
        this.numberOfRounds = numberOfRounds;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public NumberOfRounds getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(NumberOfRounds numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public void run(){
        try {
            view.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
