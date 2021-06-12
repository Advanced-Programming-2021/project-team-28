package model;

import controller.BattlePhaseController;
import controller.MainPhaseController;
import enums.Turn;

import java.util.Map;


public class Round {
    private Player firstPlayer;
    private Player secondPlayer;
    private Turn turn;
    private int turnsPlayed = 0;
    private Player winner = null;
    private boolean isDrawHappened = false;


    public Round(Player firstPlayer, Player secondPlayer, Turn turn) {
        this.turn = turn;
        setFirstPlayer(firstPlayer);
        setSecondPlayer(secondPlayer);
    }

    public void run() {
        while (true) {
            DrawPhase drawPhase = new DrawPhase( this);
            drawPhase.run();
            checkTheWinner();
            if (isSomeOneWon() || isDrawHappened) break;
            MainPhase mainPhase1 = new MainPhase(this);
            MainPhaseController mainPhaseController = new MainPhaseController(mainPhase1);
            mainPhaseController.run();
            checkTheWinner();
            if (isSomeOneWon() || isDrawHappened) break;
            BattlePhase battlePhase = new BattlePhase(this);
            new BattlePhaseController(battlePhase).run();
            checkTheWinner();
            if (isSomeOneWon() || isDrawHappened) break;
            mainPhase1.setWhatMainPhase(2);
            mainPhaseController.run();
            checkTheWinner();
            if (isSomeOneWon() || isDrawHappened) break;
            setAllHasBattledInThisTurnFalse();
            ++turnsPlayed;
            changeTurn();
        }
        resetPlayersData();
    }

    private void resetPlayersData() {
        resetPlayerData(firstPlayer);
        resetPlayerData(secondPlayer);
    }

    private void resetPlayerData(Player player) {
        player.setLifePoint(8000);
        player.setSelectedCard(null);
        player.setAbleToAddCardInDrawPhase(true);
        player.setAbleToActivateTrapCard(true);
        player.setSelectedCardVisible(false);
        player.getRemainingPlayerCardsInGame().clear();
        player.getMonsterCardsInZone().clear();
        player.getSpellOrTrapCardsInZone().clear();
        player.getCardsInHand().clear();
        player.getCardsInGraveyard().clear();
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
        this.turn = turn.opposite;
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

    public Player getPlayerByTurn (){
        if(turn == Turn.FIRST_PLAYER){
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }

    public Player getRivalPlayerByTurn(){
        if(turn == Turn.FIRST_PLAYER){
            return secondPlayer;
        } else {
            return firstPlayer;
        }
    }

    public void setAllHasBattledInThisTurnFalse(){
        for(Map.Entry<Integer, MonsterCard> locationCard : getPlayerByTurn().getMonsterCardsInZone().entrySet()){
            locationCard.getValue().setHasBattledInBattlePhase(false);
        }
        for(Map.Entry<Integer, MonsterCard> locationCard : getRivalPlayerByTurn().getMonsterCardsInZone().entrySet()){
            locationCard.getValue().setHasBattledInBattlePhase(false);
        }
    }
}
