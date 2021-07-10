package org.model;

import org.controller.BattlePhaseController;
import org.controller.MainPhaseController;
import org.model.enums.PhaseName;
import org.model.enums.Turn;
import org.view.PhaseView;
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
            standbyPhase();
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
            endPhase();
            tasksAfterChangingTurn();
            changeTurn();
        }
        resetPlayersData();
    }

    public void tasksAfterChangingTurn() {
        setAllHasBattledInThisTurnFalse();
        setAllHasSetInTHisTurnFalseForTwoPlayers();
        ++turnsPlayed;
    }

    private void standbyPhase() {
        new PhaseView().printPhaseName(PhaseName.STANDBY_PHASE);
    }

    private void endPhase() {
        PhaseView view = new PhaseView();
        view.printPhaseName(PhaseName.END_PHASE);
        view.itIsRivalTurn(getRivalPlayerByTurn().getUser().getUsername());
    }

    private void resetPlayersData() {
        resetPlayerData(firstPlayer);
        resetPlayerData(secondPlayer);
    }

    private void resetPlayerData(Player player) {
        if(!player.isSurrenderedOrLostByCheat()){
            player.setLifePoint(8000);
        }
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
            locationCard.getValue().setPositionChangedInThisTurn(false);
            locationCard.getValue().setSummonedInThisTurn(false);
        }
        for(Map.Entry<Integer, MonsterCard> locationCard : getRivalPlayerByTurn().getMonsterCardsInZone().entrySet()){
            locationCard.getValue().setHasBattledInBattlePhase(false);
            locationCard.getValue().setPositionChangedInThisTurn(false);
            locationCard.getValue().setSummonedInThisTurn(false);
        }
    }

    private void setAllHasSetInTHisTurnFalseForTwoPlayers() {
        setAllHasSetInThisTurnFalseForAPlayer(getFirstPlayer());
        setAllHasSetInThisTurnFalseForAPlayer(getSecondPlayer());
    }

    public void setAllHasSetInThisTurnFalseForAPlayer(Player player){
        for(Map.Entry<Integer, Card> locationCard : player.getSpellOrTrapCardsInZone().entrySet()){
            if(locationCard.getValue() instanceof TrapCard){
                ((TrapCard) locationCard.getValue()).setHasSetInThisTurn(false);
            }
        }
    }
}
