package model;

import enums.MonsterCardPosition;
import enums.Turn;

public class BattlePhase extends Phase{
    public BattlePhase(Player firstPlayer, Player secondPlayer, Turn turn, int turnsPlayed) {
        super(firstPlayer, secondPlayer, turn, turnsPlayed);
    }

    public int attackDirect(){
        Player player = getPlayerByTurn();
        Player rivalPlayer = getRivalPlayerByTurn();
        int damageOfAttackingCard = ((MonsterCard) player.getSelectedCard()).getAttackPoint();
        if(rivalPlayer.getLifePoint() < damageOfAttackingCard){
            int attackDamage = rivalPlayer.getLifePoint();
            rivalPlayer.setLifePoint(0);
            return attackDamage;
        }
        rivalPlayer.decreaseLifePoint(damageOfAttackingCard);
        ((MonsterCard) player.getSelectedCard()).setHasBattledInBattlePhase(true);
        return damageOfAttackingCard;
    }

    public String attackToCardAndReturnAttackReport(int location){
        MonsterCard attackerCard = (MonsterCard) getPlayerByTurn().getSelectedCard();
        MonsterCard defenderCard = getRivalPlayerByTurn().getMonsterCardsInZone().get(location);
        if(defenderCard.getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED){
            attackerCard.setHasBattledInBattlePhase(true);
            return attackToOffensiveOccupiedCardAndReturnReport(location, attackerCard, defenderCard);
        } else {
            attackerCard.setHasBattledInBattlePhase(true);
            return attackToDefensiveCardAndReturnReport(location, attackerCard, defenderCard);
        }

    }

    private String attackToDefensiveCardAndReturnReport(int location, MonsterCard attackerCard, MonsterCard defenderCard) {
        int damage = attackerCard.getAttackPoint() - defenderCard.getDefencePoint();
        if(damage > 0){

            getRivalPlayerByTurn().addCardToGraveyard(defenderCard);
            getRivalPlayerByTurn().removeCardFromCardsInZone(defenderCard, location);
            if(defenderCard.getPosition() == MonsterCardPosition.DEFENSIVE_HIDDEN){
                return "opponent’s monster card was " + defenderCard.getName() + " and the defense position monster is destroyed";
            } else {
                return "the defense position monster is destroyed";
            }
        } else if (damage == 0){
            if(defenderCard.getPosition() == MonsterCardPosition.DEFENSIVE_HIDDEN){
                return "opponent’s monster card was " + defenderCard.getName() + " and no card is destroyed";
            } else {
                return "no card is destroyed";
            }
        } else {
            getPlayerByTurn().decreaseLifePoint(-damage);
            if(defenderCard.getPosition() == MonsterCardPosition.DEFENSIVE_HIDDEN){
                return "opponent’s monster card was " + defenderCard.getName() + " and no card is destroyed and you received "
                + -damage + " battle damage";
            } else {
                return "no card is destroyed and you received " + -damage + " battle damage";
            }
        }
    }

    private String attackToOffensiveOccupiedCardAndReturnReport(int location, MonsterCard attackerCard, MonsterCard defenderCard) {
        int damage = attackerCard.getAttackPoint() - defenderCard.getAttackPoint();
        if(damage > 0){
            getRivalPlayerByTurn().decreaseLifePoint(damage);
            getRivalPlayerByTurn().addCardToGraveyard(defenderCard);
            getRivalPlayerByTurn().removeCardFromCardsInZone(defenderCard, location);
            return "your opponent’s monster is destroyed and your opponent receives " + damage + " battle damage";
        } else if (damage == 0){
            getPlayerByTurn().addCardToGraveyard(attackerCard);
            getPlayerByTurn().removeCardFromCardsInZone(attackerCard, getPlayerByTurn().getLocationOfThisMonsterCardInZone(attackerCard));
            getRivalPlayerByTurn().addCardToGraveyard(defenderCard);
            getRivalPlayerByTurn().removeCardFromCardsInZone(defenderCard, location);
            return "both you and your opponent monster cards are destroyed and no one receives damage";
        } else {
            getPlayerByTurn().decreaseLifePoint(-damage);
            getPlayerByTurn().addCardToGraveyard(attackerCard);
            getPlayerByTurn().removeCardFromCardsInZone(attackerCard, getPlayerByTurn().getLocationOfThisMonsterCardInZone(attackerCard));
            return "Your monster card is destroyed and you received " + -damage + " battle damage";
        }
    }
}
