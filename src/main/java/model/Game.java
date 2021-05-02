package model;

import Enums.NumberOfRounds;
import Enums.Turn;
import view.GameView;

public class Game {
    Player player1;
    Player player2;
    NumberOfRounds numberOfRounds;

    public Game(User user1 , User user2 , NumberOfRounds numberOfRounds) throws CloneNotSupportedException {
        this.player1 = new Player(user1);
        this.player2 = new Player(user2);
        this.numberOfRounds = numberOfRounds;
    }

    public void run(){
        if(this.numberOfRounds == NumberOfRounds.ONE_ROUND_MATCH){
            Round round = new Round(player1 , player2 , Turn.FIRST_PLAYER);
            round.run();
            if(player1.getNumberOfRoundsWon() == 1){;}
            else{;}
        }
        else if(this.numberOfRounds == NumberOfRounds.THREE_ROUND_MATCH){
            Round round1 = new Round(player1 , player2 , Turn.FIRST_PLAYER);
            round1.run();
            Round round2 = new Round(player1 , player2 , Turn.FIRST_PLAYER);
            round2.run();
            if(player1.getNumberOfRoundsWon() == 2){;}
            else if(player2.getNumberOfRoundsWon() == 2){;}
            Round round3 = new Round(player1 , player2 , Turn.FIRST_PLAYER);
            if(player1.getNumberOfRoundsWon() == 2){;}
            else if(player2.getNumberOfRoundsWon() == 2){;}
        }
    }

}
