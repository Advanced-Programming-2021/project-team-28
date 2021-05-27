package controller;

import enums.TrapEffect;
import model.*;

public class TrapEffectController {
    public static void searchForThisEffect(Phase phase, Card rivalCard, TrapCard trapCard) {
        Player player = phase.getPlayerByTurn();
        Player rivalPlayer = phase.getRivalPlayerByTurn();
        TrapEffect effect = trapCard.getEffect();
        if (effect == TrapEffect.MAGIC_CYLINDER) {
            TrapEffectMethods.magicCylinder(player, rivalPlayer, rivalCard, trapCard);
        } else if (effect == TrapEffect.MIRROR_FORCE) {
            TrapEffectMethods.mirrorForce(player, rivalPlayer, rivalCard, trapCard);
        } else if (effect == TrapEffect.TRAP_HOLE) {
            TrapEffectMethods.trapHole(player, rivalPlayer, rivalCard, trapCard);
        } else if (effect == TrapEffect.SOLEMN_WARNING) {
            TrapEffectMethods.solemnWarning(player, rivalPlayer, rivalCard, trapCard);
        } else if (effect == TrapEffect.TORRENTIAL_TRIBUTE) {
            TrapEffectMethods.torrentialTribute(phase, trapCard);
        } else if (effect == TrapEffect.NEGATE_ATTACK) {
            TrapEffectMethods.negateAttack(phase, rivalCard, trapCard);
        } else if (effect == TrapEffect.TIME_SEAL) {
            TrapEffectMethods.timeSeal(player, rivalPlayer, trapCard);
        } else if (effect == TrapEffect.MIND_CRUSH) {
            TrapEffectMethods.mindCrush(player, rivalPlayer, trapCard);
        } else if (effect == TrapEffect.MAGIC_JAMMER) {

        } else if (effect == TrapEffect.CALL_OF_THE_HAUNTED) {

        }
    }
}
