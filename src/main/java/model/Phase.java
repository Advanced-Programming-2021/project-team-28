package model;

import enums.Turn;

import java.util.HashMap;

public abstract class Phase {
    protected Player firstPlayer;
    protected Player secondPlayer;
    protected Turn turn;
    protected Round round;
    protected int turnsPlayed;
    protected boolean isEndedByATrapCard = false;

    public Phase (Round round){
        this.round = round;
        this.firstPlayer = round.getFirstPlayer();
        this.secondPlayer = round.getSecondPlayer();
        this.turn = round.getTurn();
        this.turnsPlayed = round.getTurnsPlayed();
    }

    public Turn getTurn() {
        return turn;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public int getTurnsPlayed() {
        return turnsPlayed;
    }

    public void setTurnsPlayed(int turnsPlayed) {
        this.turnsPlayed = turnsPlayed;
    }

    public boolean isEndedByATrapCard() {
        return isEndedByATrapCard;
    }

    public void setEndedByATrapCard(boolean endedByATrapCard) {
        isEndedByATrapCard = endedByATrapCard;
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

    public void changeTurn() {
        this.turn = turn.opposite;
    }

    public String getMapToString() {
        Player rivalPlayer = turn == Turn.FIRST_PLAYER ? secondPlayer : firstPlayer;
        Player playerAtTurn = turn == Turn.FIRST_PLAYER ? firstPlayer : secondPlayer;
        StringBuilder mapToStringBuilder = new StringBuilder();
        mapToStringBuilder.append("\n");
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
        mapToStringBuilder.append("\n");
        return mapToStringBuilder.toString();
    }

    private void appendPlayerAtTurnOutOfZoneCards(Player playerAtTurn, StringBuilder mapToStringBuilder) {
        mapToStringBuilder.append("\t\t\t\t\t\t");
        mapToStringBuilder.append(playerAtTurn.getRemainingPlayerCardsInGame().size());
        mapToStringBuilder.append("\n");
        if(playerAtTurn.getCardsInHand().size() > 5){
            for (int i=0; i<playerAtTurn.getCardsInHand().size(); i++){
                mapToStringBuilder.append("c\t");
            }
        } else {
            for (int i=0; i<playerAtTurn.getCardsInHand().size(); i++){
                mapToStringBuilder.append("\tc");
            }
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
