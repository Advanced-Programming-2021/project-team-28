package model;

import enums.Turn;

import java.util.HashMap;

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


    public String getMapToString() {
        Player rivalPlayer = turn == Turn.FIRST_PLAYER ? secondPlayer : firstPlayer;
        Player playerAtTurn = turn == Turn.FIRST_PLAYER ? firstPlayer : secondPlayer;
        StringBuilder mapToStringBuilder = new StringBuilder();
        appendNicknameAndLifePoint(rivalPlayer, mapToStringBuilder);
        appendRivalOutOfZoneCards(rivalPlayer, mapToStringBuilder);
        HashMap<Integer, Card> rivalPlayerSpellsInZone = rivalPlayer.getSpellOrTrapCardsInZone();
        HashMap<Integer, MonsterCard> rivalPlayerMonstersInZone = rivalPlayer.getMonsterCardsInZone();
        appendRivalPlayerZone(mapToStringBuilder, rivalPlayerSpellsInZone, rivalPlayerMonstersInZone);
        appendRivalGraveyardAndFieldZone(rivalPlayer, mapToStringBuilder);
        mapToStringBuilder.append("\n\n--------------------------\n\n");
        appendPlayerAtTurnGraveYardAndFieldZone(playerAtTurn, mapToStringBuilder);
        HashMap<Integer, MonsterCard> playerAtTurnMonstersInZone = playerAtTurn.getMonsterCardsInZone();
        HashMap<Integer, Card> playerAtTurnSpellsInZone = playerAtTurn.getSpellOrTrapCardsInZone();
        appendPlayerAtTurnZone(mapToStringBuilder, playerAtTurnSpellsInZone, playerAtTurnMonstersInZone);
        appendPlayerAtTurnOutOfZoneCards(playerAtTurn, mapToStringBuilder);
        appendNicknameAndLifePoint(playerAtTurn, mapToStringBuilder);
        return mapToStringBuilder.toString();
    }

    private void appendPlayerAtTurnOutOfZoneCards(Player playerAtTurn, StringBuilder mapToStringBuilder) {
        mapToStringBuilder.append("\t\t\t\t\t\t");
        mapToStringBuilder.append(playerAtTurn.getRemainingPlayerCardsInGame().size());
        mapToStringBuilder.append("\n");
        for (int i=0; i<playerAtTurn.getCardsInHand().size(); i++){
            mapToStringBuilder.append("c\t");
        }
        mapToStringBuilder.append("\n");
    }

    private void appendRivalOutOfZoneCards(Player rivalPlayer, StringBuilder mapToStringBuilder) {
        mapToStringBuilder.append("\n");
        for (int i=0; i<rivalPlayer.getCardsInHand().size(); i++){
            mapToStringBuilder.append("\tc");
        }
        mapToStringBuilder.append("\n");
        mapToStringBuilder.append(rivalPlayer.getRemainingPlayerCardsInGame().size());
        mapToStringBuilder.append("\n");
    }

    private void appendNicknameAndLifePoint(Player rivalPlayer, StringBuilder mapToStringBuilder) {
        mapToStringBuilder.append(rivalPlayer.getUser().getNickname());
        mapToStringBuilder.append(" : ");
        mapToStringBuilder.append(rivalPlayer.getLifePoint());
    }

    private void appendPlayerAtTurnGraveYardAndFieldZone(Player playerAtTurn, StringBuilder mapToStringBuilder) {
        mapToStringBuilder.append(playerAtTurn.hasFieldSpellCardInZone() ? "O" : "E");
        mapToStringBuilder.append("\t\t\t\t\t\t");
        mapToStringBuilder.append(playerAtTurn.getCardsInGraveyard().size());
        mapToStringBuilder.append("\n");
    }

    private void appendRivalGraveyardAndFieldZone(Player rivalPlayer, StringBuilder mapToStringBuilder) {
        mapToStringBuilder.append(rivalPlayer.getCardsInGraveyard().size());
        mapToStringBuilder.append("\t\t\t\t\t\t");
        mapToStringBuilder.append(rivalPlayer.hasFieldSpellCardInZone() ? "O" : "E");
    }

    private void appendRivalPlayerZone(StringBuilder mapToStringBuilder, HashMap<Integer, Card> rivalPlayerSpellCardsInZone,
                                       HashMap<Integer, MonsterCard> rivalPlayerMonstersInZone) {
        mapToStringBuilder.append(getSpellCardInPositionToString(rivalPlayerSpellCardsInZone, 4));
        mapToStringBuilder.append(getSpellCardInPositionToString(rivalPlayerSpellCardsInZone, 2));
        mapToStringBuilder.append(getSpellCardInPositionToString(rivalPlayerSpellCardsInZone, 1));
        mapToStringBuilder.append(getSpellCardInPositionToString(rivalPlayerSpellCardsInZone, 3));
        mapToStringBuilder.append(getSpellCardInPositionToString(rivalPlayerSpellCardsInZone, 5));
        mapToStringBuilder.append("\n");
        mapToStringBuilder.append(getMonsterCardPositionToString(rivalPlayerMonstersInZone, 4));
        mapToStringBuilder.append(getMonsterCardPositionToString(rivalPlayerMonstersInZone, 2));
        mapToStringBuilder.append(getMonsterCardPositionToString(rivalPlayerMonstersInZone, 1));
        mapToStringBuilder.append(getMonsterCardPositionToString(rivalPlayerMonstersInZone, 3));
        mapToStringBuilder.append(getMonsterCardPositionToString(rivalPlayerMonstersInZone, 5));
        mapToStringBuilder.append("\n");
    }

    private void appendPlayerAtTurnZone(StringBuilder mapToStringBuilder, HashMap<Integer, Card> playerAtTurnSpellCardsInZone,
                                        HashMap<Integer, MonsterCard> playerAtTurnMonstersInZone){
        mapToStringBuilder.append(getMonsterCardPositionToString(playerAtTurnMonstersInZone, 5));
        mapToStringBuilder.append(getMonsterCardPositionToString(playerAtTurnMonstersInZone, 3));
        mapToStringBuilder.append(getMonsterCardPositionToString(playerAtTurnMonstersInZone, 1));
        mapToStringBuilder.append(getMonsterCardPositionToString(playerAtTurnMonstersInZone, 2));
        mapToStringBuilder.append(getMonsterCardPositionToString(playerAtTurnMonstersInZone, 4));
        mapToStringBuilder.append("\n");
        mapToStringBuilder.append(getSpellCardInPositionToString(playerAtTurnSpellCardsInZone, 5));
        mapToStringBuilder.append(getSpellCardInPositionToString(playerAtTurnSpellCardsInZone, 3));
        mapToStringBuilder.append(getSpellCardInPositionToString(playerAtTurnSpellCardsInZone, 1));
        mapToStringBuilder.append(getSpellCardInPositionToString(playerAtTurnSpellCardsInZone, 2));
        mapToStringBuilder.append(getSpellCardInPositionToString(playerAtTurnSpellCardsInZone, 4));
        mapToStringBuilder.append("\n");
    }

    private String getSpellCardInPositionToString(HashMap<Integer, Card> playerSpellsInZone, int location) {
        if (!playerSpellsInZone.containsKey(location)) {
            return "\tE";
        } else if (playerSpellsInZone.get(location) instanceof SpellCard) {
            SpellCard spellCard = (SpellCard) playerSpellsInZone.get(location);
            return spellCard.getPosition().getPositionInMap();
        } else if (playerSpellsInZone.get(location) instanceof TrapCard) {
            TrapCard trapCard = (TrapCard) playerSpellsInZone.get(location);
            return trapCard.getPosition().getPositionInMap();
        } else {
            return "\tE";
        }
    }

    private String getMonsterCardPositionToString(HashMap<Integer, MonsterCard> playerMonstersInZone, int location) {
        if (!playerMonstersInZone.containsKey(location)) {
            return "\tE";
        } else {
            return playerMonstersInZone.get(location).getPosition().getPositionInMap();
        }
    }
}
