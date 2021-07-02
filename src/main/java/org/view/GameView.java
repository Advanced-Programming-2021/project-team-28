package org.view;

import org.model.Game;
import org.model.User;

public class GameView {

    Game game;

    public GameView(Game game){
        this.game = game;
    }

    public void showMatchWinner(User winner , int winnerScore , int loserScore){
        System.out.println(winner.getUsername() + " won the whole match with score: " + winnerScore + "-" + loserScore);
    }

    public void showRoundWinner(User winner , int winnerScore , int loserScore){
        System.out.println(winner.getUsername() + " won the game and the score is: " + winnerScore + "-" + loserScore);
    }
}
