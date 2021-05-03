package model;

import Enums.MonsterCardPosition;
import Enums.SpellOrTrapCardPosition;
import Enums.Turn;

import java.util.HashMap;

public class Round {
    private Player firstPlayer;
    private Player secondPlayer;
    private Turn turn;

    public Round(Player firstPlayer, Player secondPlayer, Turn turn) {
        this.turn = turn;
        setFirstPlayer(firstPlayer);
        setSecondPlayer(secondPlayer);
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

    public void changeTurn() {
        if (turn == Turn.FIRST_PLAYER) {
            turn = Turn.SECOND_PLAYER;
        } else {
            turn = Turn.FIRST_PLAYER;
        }
    }

    public String getMapToString() {
        Player rivalPlayer = turn == Turn.FIRST_PLAYER ? secondPlayer : firstPlayer;
        Player playerAtTurn = turn == Turn.FIRST_PLAYER ? firstPlayer : secondPlayer;
        StringBuilder mapToStringBuilder = new StringBuilder();
        mapToStringBuilder.append(rivalPlayer.getUser().getNickname());
        mapToStringBuilder.append(" : ");
        mapToStringBuilder.append(rivalPlayer.getLifePoint());
        mapToStringBuilder.append("\n");
        mapToStringBuilder.append("\tc".repeat(rivalPlayer.getCardsInHand().size()));
        mapToStringBuilder.append("\n");
        mapToStringBuilder.append(rivalPlayer.getRemainingPlayerCardsInGame().size());
        mapToStringBuilder.append("\n");
        HashMap<Integer, Card> rivalPlayerSpellsInZone = rivalPlayer.getSpellOrTrapCardsInZone();
        HashMap<Integer, MonsterCard> rivalPlayerMonstersInZone = rivalPlayer.getMonsterCardsInZone();
        appendRivalPlayerZone(mapToStringBuilder, rivalPlayerSpellsInZone, rivalPlayerMonstersInZone);
        mapToStringBuilder.append(rivalPlayer.getCardsInGraveyard().size());
        mapToStringBuilder.append("\t\t\t\t\t\t");
        mapToStringBuilder.append(rivalPlayer.hasFieldSpellCardInZone() ? "O" : "E");
        mapToStringBuilder.append("\n\n--------------------------\n\n");
        mapToStringBuilder.append(playerAtTurn.hasFieldSpellCardInZone() ? "O" : "E");
        mapToStringBuilder.append("\t\t\t\t\t\t");
        mapToStringBuilder.append(playerAtTurn.getCardsInGraveyard().size());
        mapToStringBuilder.append("\n");
        HashMap<Integer, MonsterCard> playerAtTurnMonstersInZone = playerAtTurn.getMonsterCardsInZone();
        HashMap<Integer, Card> playerAtTurnSpellsInZone = playerAtTurn.getSpellOrTrapCardsInZone();
        appendPlayerAtTurnZone(mapToStringBuilder, playerAtTurnSpellsInZone, playerAtTurnMonstersInZone);
        mapToStringBuilder.append("\t\t\t\t\t\t");
        mapToStringBuilder.append(playerAtTurn.getRemainingPlayerCardsInGame().size());
        mapToStringBuilder.append("\n");
        mapToStringBuilder.append("c\t".repeat(playerAtTurn.getCardsInHand().size()));
        mapToStringBuilder.append("\n");
        mapToStringBuilder.append(playerAtTurn.getUser().getNickname());
        mapToStringBuilder.append(" : ");
        mapToStringBuilder.append(playerAtTurn.getLifePoint());
        return mapToStringBuilder.toString();
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
        } else if (playerSpellsInZone.get(location) instanceof SpellCard &&
                ((SpellCard) playerSpellsInZone.get(location)).getPosition() == SpellOrTrapCardPosition.HIDDEN) {
            return "\tH";
        } else if (playerSpellsInZone.get(location) instanceof TrapCard &&
                ((TrapCard) playerSpellsInZone.get(location)).getPosition() == SpellOrTrapCardPosition.HIDDEN) {
            return "\tH";
        } else {
            return "\tE";
        }
    }

    private String getMonsterCardPositionToString(HashMap<Integer, MonsterCard> playerMonstersInZone, int location) {
        if (!playerMonstersInZone.containsKey(location)) {
            return "\tE";
        } else if (playerMonstersInZone.get(location).getPosition() == MonsterCardPosition.DEFENSIVE_HIDDEN) {
            return "\tDH";
        } else if (playerMonstersInZone.get(location).getPosition() == MonsterCardPosition.DEFENSIVE_OCCUPIED) {
            return "\tDO";
        } else if (playerMonstersInZone.get(location).getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED) {
            return "\tOO";
        } else {
            return "\tE";
        }
    }
}
