package model;

import enums.NumberOfRounds;
import enums.Turn;

public class Game {
    Player player1;
    Player player2;

    Player round1Winner;
    Player round2Winner;
    Player round3Winner;

    int round1WinnerLp;
    int round2WinnerLp;
    int round3WinnerLp;

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
            if(player1.getNumberOfRoundsWon() == 1){giveOneRoundWinnerPrize(player1);}
            else{giveOneRoundWinnerPrize(player2);}
        }
        else if(this.numberOfRounds == NumberOfRounds.THREE_ROUND_MATCH){

            Round round1 = new Round(player1 , player2 , Turn.FIRST_PLAYER);
            round1.run();
            round1Winner = round1.getWinner();
            round1WinnerLp = round1.getWinner().getLifePoint();

            Round round2 = new Round(player1 , player2 , Turn.FIRST_PLAYER);
            round2.run();
            round2Winner = round2.getWinner();
            round2WinnerLp = round2.getWinner().getLifePoint();


            if(player1.getNumberOfRoundsWon() == 2){
                giveTwoRoundWinnerPrize(player1 , player2);
                return;
            }
            else if(player2.getNumberOfRoundsWon() == 2){
                giveTwoRoundWinnerPrize(player2 , player1);
                return;
            }

            Round round3 = new Round(player1 , player2 , Turn.FIRST_PLAYER);
            round3Winner = round3.getWinner();
            round3WinnerLp = round3.getWinner().getLifePoint();
            if(player1.getNumberOfRoundsWon() == 2){giveThreeRoundWinnerPrize1(player1 , player2);return;}
            else if(player2.getNumberOfRoundsWon() == 2){giveThreeRoundWinnerPrize1(player2 , player1);return;}
        }
    }

    public void giveOneRoundWinnerPrize(Player winner){
        winner.getUser().changeScore(1000);
        winner.getUser().changeBalance(1000 + winner.getLifePoint());
    }

    public void giveTwoRoundWinnerPrize(Player winner , Player loser){
        if(round1WinnerLp > round2WinnerLp){
            winner.getUser().changeBalance(3000 + (3 * round1WinnerLp));
        }
        else winner.getUser().changeBalance(3000 + (3 * round2WinnerLp));
        winner.getUser().changeScore(3000);
        loser.getUser().changeBalance(300);
    }

    public void giveThreeRoundWinnerPrize1(Player winner , Player loser){
        int maxLp = 0;
        maxLp = findMaxLp(winner);
        winner.getUser().changeBalance(3000 + (3 * maxLp));
        winner.getUser().changeScore(3000);
        loser.getUser().changeBalance(300);
    }

    public int findMaxLp(Player player){
        int lp1 = -1;
        int lp2 = -1;
        int lp3 = -1;

        if(player == round1Winner){
            lp1 = round1WinnerLp;
        }
        if(player == round2Winner){
            lp2 = round2WinnerLp;
        }
        if(player == round3Winner){
            lp3 = round3WinnerLp;
        }

        return sortLp(lp1 , lp2 , lp3);
    }

    public int sortLp(int lp1 , int lp2 , int lp3){

        if(lp1 > lp2){
            if(lp1 > lp3)
                return lp1;
        }
        else {
            if(lp2 > lp3)
                return lp2;
            else
                return lp3;
        }
        return 0;
    }
}
