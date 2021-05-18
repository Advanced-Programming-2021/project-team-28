package controller;

import enums.TrapEffect;
import model.Card;
import model.Phase;
import model.TrapCard;
import model.TrapEffectMethods;

public class TrapEffectController {
    public static void searchForThisEffect(Phase phase, Card rivalCard, Card ourCard, TrapCard trapCard) {
        TrapEffect effect = trapCard.getEffect();
        if (effect == TrapEffect.MAGIC_CYLINDER) {
            TrapEffectMethods.magicCylinder(phase.getRivalPlayerByTurn(), rivalCard);
        } else if (effect == TrapEffect.MIRROR_FORCE) {
            TrapEffectMethods.mirrorForce(phase.getRivalPlayerByTurn(), rivalCard);
        } else if (effect == TrapEffect.MIND_CRUSH) {

        } else if (effect == TrapEffect.CALL_OF_THE_HAUNTED) {

        } else if (effect == TrapEffect.TRAP_HOLE) {

        } else if (effect == TrapEffect.TIME_SEAL) {

        } else if (effect == TrapEffect.SOLEMN_WARNING) {

        } else if (effect == TrapEffect.TORRENTIAL_TRIBUTE) {

        } else if (effect == TrapEffect.VANITYS_EMPTINESS) {

        } else if (effect == TrapEffect.WALL_OF_REVEALING_LIGHT) {

        } else if(effect == TrapEffect.NEGATE_ATTACK){
            TrapEffectMethods.negateAttack(phase, rivalCard);
        } else if(effect == TrapEffect.MAGIC_JAMMER){

        }
    }
}
