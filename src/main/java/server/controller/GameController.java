package server.controller;

import org.model.*;
import org.model.enums.NumberOfRounds;
import org.model.enums.Turn;
import org.view.GameView;

public class GameController {
    GameView view = new GameView(this);

    Player player1;
    Player player2;

    Player round1Winner;
    Player round2Winner;
    Player round3Winner;

    int round1WinnerLp;
    int round2WinnerLp;
    int round3WinnerLp;

    NumberOfRounds numberOfRounds;

    Round round;
    DrawPhase drawPhase;
    MainPhaseController mainController;
    BattlePhaseController battleController;

    public GameController(User user1, User user2, NumberOfRounds numberOfRounds, Turn turn) throws CloneNotSupportedException {
        this.player1 = new Player(user1);
        this.player2 = new Player(user2);
        this.numberOfRounds = numberOfRounds;
        round = new Round(player1, player2, turn);
        mainController = new MainPhaseController(new MainPhase(round), view);
        battleController = new BattlePhaseController(new BattlePhase(round), view);
        drawPhase = new DrawPhase(round);
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Player getRound1Winner() {
        return round1Winner;
    }

    public void setRound1Winner(Player round1Winner) {
        this.round1Winner = round1Winner;
    }

    public Player getRound2Winner() {
        return round2Winner;
    }

    public void setRound2Winner(Player round2Winner) {
        this.round2Winner = round2Winner;
    }

    public Player getRound3Winner() {
        return round3Winner;
    }

    public void setRound3Winner(Player round3Winner) {
        this.round3Winner = round3Winner;
    }

    public NumberOfRounds getNumberOfRounds() {
        return numberOfRounds;
    }

    public Round getRound() {
        return round;
    }

    public DrawPhase getDrawPhase() {
        return drawPhase;
    }

    public MainPhaseController getMainController() {
        return mainController;
    }

    public BattlePhaseController getBattleController() {
        return battleController;
    }

    public void run() {
        view.run();
    }

    public void setPlayerCardsForGame() {
        try {
            resetHandAndRemainingCards(player1);
            resetHandAndRemainingCards(player2);
        } catch (CloneNotSupportedException exception) {
            exception.printStackTrace();
        }
    }

    private void resetHandAndRemainingCards(Player player1) throws CloneNotSupportedException {
        player1.fillArraylistWithCardClones(player1.getMainDuelDeck(), player1.getRemainingPlayerCardsInGame());
        player1.shuffleRemainingCards();
        player1.setCardsInHand();
    }

    public void giveOneRoundWinnerPrize(Player winner) {
        winner.getUser().changeScore(1000);
        winner.getUser().changeBalance(1000 + winner.getLifePoint());
    }

    public void giveTwoRoundWinnerPrize(Player winner, Player loser) {
        if (round1WinnerLp > round2WinnerLp) {
            winner.getUser().changeBalance(3000 + (3 * round1WinnerLp));
        } else winner.getUser().changeBalance(3000 + (3 * round2WinnerLp));
        winner.getUser().changeScore(3000);
        loser.getUser().changeBalance(300);
    }

    public void giveThreeRoundWinnerPrize1(Player winner, Player loser) {
        int maxLp = 0;
        maxLp = findMaxLp(winner);
        winner.getUser().changeBalance(3000 + (3 * maxLp));
        winner.getUser().changeScore(3000);
        loser.getUser().changeBalance(300);
    }

    public int findMaxLp(Player player) {
        int lp1 = -1;
        int lp2 = -1;
        int lp3 = -1;

        if (player == round1Winner) {
            lp1 = round1WinnerLp;
        }
        if (player == round2Winner) {
            lp2 = round2WinnerLp;
        }
        if (player == round3Winner) {
            lp3 = round3WinnerLp;
        }

        return sortLp(lp1, lp2, lp3);
    }

    public int sortLp(int lp1, int lp2, int lp3) {

        if (lp1 > lp2) {
            if (lp1 > lp3)
                return lp1;
        } else {
            return Math.max(lp2, lp3);
        }
        return 0;
    }

    private boolean isSomeoneSurrenderedOrLostByCheat(){
        return player1.isSurrenderedOrLostByCheat() || player2.isSurrenderedOrLostByCheat();
    }
}
