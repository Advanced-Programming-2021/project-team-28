package model;

import enums.MonsterCardPosition;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TrapEffectMethods {
    public static void magicCylinder(Player rivalPlayer, Card rivalAttackerCard) {
        rivalPlayer.decreaseLifePoint(((MonsterCard) rivalAttackerCard).getAttackPoint());
        ((MonsterCard) rivalAttackerCard).setCardAttackCanceledByAnEffect(true);
    }

    public static void mirrorForce(Player rivalPlayer, Card rivalAttackerCard) {
        ((MonsterCard) rivalAttackerCard).setCardAttackCanceledByAnEffect(true);
        Set<Map.Entry<Integer, MonsterCard>> entrySet = rivalPlayer.getMonsterCardsInZone().entrySet();
        entrySet.removeIf(entry -> entry.getValue().getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED);
    }

    public static void negateAttack(Phase phase, Card rivalAttackerCard){
        ((MonsterCard) rivalAttackerCard).setCardAttackCanceledByAnEffect(true);
        phase.setEndedByATrapCard(true);
    }
}
