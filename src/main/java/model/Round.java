package model;

import Enums.Turn;

public class Round {
    private Player firstPlayer;
    private Player secondPlayer;
    private Turn turn;
    private int turnsPlayed;
    private Player winner = null;
    private boolean isDrawHappened = false;


    public Round(Player firstPlayer, Player secondPlayer, Turn turn) {
        this.turn = turn;
        setFirstPlayer(firstPlayer);
        setSecondPlayer(secondPlayer);
    }

    public void run() {

        while (true) {
            DrawPhase drawPhase = new DrawPhase(firstPlayer, secondPlayer, turn);
            drawPhase.run();
            checkTheWinner();
            if (isSomeOneWon() || isDrawHappened) break;
            MainPhase1 mainPhase1 = new MainPhase1(firstPlayer, secondPlayer, turn);
            mainPhase1.run();
            checkTheWinner();
            if (isSomeOneWon() || isDrawHappened) break;
            BattlePhase battlePhase = new BattlePhase(firstPlayer, secondPlayer, turn);
            battlePhase.run();
            checkTheWinner();
            if (isSomeOneWon() || isDrawHappened) break;
            MainPhase2 mainPhase2 = new MainPhase2(firstPlayer, secondPlayer, turn);
            mainPhase2.run();
            checkTheWinner();
            if (isSomeOneWon() || isDrawHappened) break;
            ++turnsPlayed;
            changeTurn();

        }
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

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean isSomeOneWon() {
        if (this.winner == null) return false;
        else return true;
    }

    public boolean isDrawHappened() {
        return isDrawHappened;
    }

    public void setDrawHappened(boolean drawHappened) {
        isDrawHappened = drawHappened;
    }


    public int getTurnsPlayed() {
        return turnsPlayed;
    }

    public void changeTurn() {
        if (turn == Turn.FIRST_PLAYER) {
            turn = Turn.SECOND_PLAYER;
        } else {
            turn = Turn.FIRST_PLAYER;
        }
    }

    public void checkTheWinner() {
        if (firstPlayer.getLifePoint() == 0
                && secondPlayer.getLifePoint() == 0) {
            isDrawHappened = true;
        } else if (firstPlayer.getLifePoint() == 0) {
            this.winner = secondPlayer;
        } else if (secondPlayer.getLifePoint() == 0) {
            this.winner = firstPlayer;
        }

    }


}
