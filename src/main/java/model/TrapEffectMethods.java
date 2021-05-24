package model;

import enums.MonsterCardPosition;

import java.util.Map;
import java.util.Set;

public class TrapEffectMethods {
    public static void magicCylinder(Player rivalPlayer, Card rivalAttackerCard) {
        rivalPlayer.decreaseLifePoint(((MonsterCard) rivalAttackerCard).getAttackPoint());
        ((MonsterCard) rivalAttackerCard).setCardActionCanceledByAnEffect(true);
    }

    public static void mirrorForce(Player rivalPlayer, Card rivalAttackerCard) {
        ((MonsterCard) rivalAttackerCard).setCardActionCanceledByAnEffect(true);
        Set<Map.Entry<Integer, MonsterCard>> entrySet = rivalPlayer.getMonsterCardsInZone().entrySet();
        entrySet.removeIf(entry -> entry.getValue().getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED);
    }

    public static void negateAttack(Phase phase, Card rivalAttackerCard) {
        ((MonsterCard) rivalAttackerCard).setCardActionCanceledByAnEffect(true);
        phase.setEndedByATrapCard(true);
    }

    public static void torrentialTribute(Phase phase) {
        phase.getPlayerByTurn().getMonsterCardsInZone().clear();
        phase.getRivalPlayerByTurn().getMonsterCardsInZone().clear();
    }

    public static void trapHole(Player rivalPlayer, Card rivalSummonedCard) {
        rivalPlayer.getMonsterCardsInZone().remove
                (rivalPlayer.getLocationOfThisMonsterCardInZone((MonsterCard) rivalSummonedCard), rivalSummonedCard);
    }

    public static void solemnWarning(Player player, Player rivalPlayer, Card rivalSummonedCard) {
        player.decreaseLifePoint(2000);
        ((MonsterCard) rivalSummonedCard).setCardActionCanceledByAnEffect(true);
        rivalPlayer.getMonsterCardsInZone().remove
                (rivalPlayer.getLocationOfThisMonsterCardInZone((MonsterCard) rivalSummonedCard), rivalSummonedCard);
    }
}
