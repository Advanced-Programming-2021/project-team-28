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
        } else if (effect == TrapEffect.TRAP_HOLE) {
            TrapEffectMethods.trapHole(phase.getRivalPlayerByTurn(), rivalCard);
        } else if (effect == TrapEffect.SOLEMN_WARNING) {
            TrapEffectMethods.solemnWarning(phase.getPlayerByTurn(), phase.getRivalPlayerByTurn(), rivalCard);
        } else if (effect == TrapEffect.TORRENTIAL_TRIBUTE) {
            TrapEffectMethods.torrentialTribute(phase);
        } else if (effect == TrapEffect.NEGATE_ATTACK) {
            TrapEffectMethods.negateAttack(phase, rivalCard);
        } else if (effect == TrapEffect.MAGIC_JAMMER) {

        } else if (effect == TrapEffect.MIND_CRUSH) {

        } else if (effect == TrapEffect.CALL_OF_THE_HAUNTED) {

        } else if (effect == TrapEffect.TIME_SEAL) {

        }
        phase.getPlayerByTurn().addCardToGraveyard(trapCard);
        phase.getPlayerByTurn().removeCardFromCardsInZone(trapCard,
                phase.getPlayerByTurn().getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }
}
