package model;

import controller.MainPhaseController;
import controller.PhaseController;
import enums.MonsterCardPosition;
import view.TrapEffectsView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TrapEffectMethods {

    private static final TrapEffectsView view = new TrapEffectsView();

    public static void magicCylinder(Player player, Player rivalPlayer, Card rivalAttackerCard, TrapCard trapCard) {
        rivalPlayer.decreaseLifePoint(((MonsterCard) rivalAttackerCard).getAttackPoint());
        ((MonsterCard) rivalAttackerCard).setCardActionCanceledByAnEffect(true);
        player.addCardToGraveyard(trapCard);
        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }

    public static void mirrorForce(Player player, Player rivalPlayer, Card rivalAttackerCard, TrapCard trapCard) {
        ((MonsterCard) rivalAttackerCard).setCardActionCanceledByAnEffect(true);
        Set<Map.Entry<Integer, MonsterCard>> entrySet = rivalPlayer.getMonsterCardsInZone().entrySet();
        entrySet.removeIf(entry -> entry.getValue().getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED);
        player.addCardToGraveyard(trapCard);
        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }

    public static void negateAttack(Phase phase, Card rivalAttackerCard, TrapCard trapCard) {
        ((MonsterCard) rivalAttackerCard).setCardActionCanceledByAnEffect(true);
        phase.setEndedByATrapCard(true);
        Player player = phase.getPlayerByTurn();
        player.addCardToGraveyard(trapCard);
        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }

    public static void torrentialTribute(Phase phase, TrapCard trapCard) {
        phase.getFirstPlayer().getMonsterCardsInZone().clear();
        phase.getSecondPlayer().getMonsterCardsInZone().clear();
        Player player = phase.getPlayerByTurn();
        player.addCardToGraveyard(trapCard);
        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }

    public static void trapHole(Player player, Player rivalPlayer, Card rivalSummonedCard, TrapCard trapCard) {
        rivalPlayer.getMonsterCardsInZone().remove
                (rivalPlayer.getLocationOfThisMonsterCardInZone((MonsterCard) rivalSummonedCard), rivalSummonedCard);
        player.addCardToGraveyard(trapCard);
        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }

    public static void solemnWarning(Player player, Player rivalPlayer, Card rivalSummonedCard, TrapCard trapCard) {
        player.decreaseLifePoint(2000);
        ((MonsterCard) rivalSummonedCard).setCardActionCanceledByAnEffect(true);
        rivalPlayer.getMonsterCardsInZone().remove
                (rivalPlayer.getLocationOfThisMonsterCardInZone((MonsterCard) rivalSummonedCard), rivalSummonedCard);
        player.addCardToGraveyard(trapCard);
        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }

    public static void timeSeal(Player player, Player rivalPlayer, TrapCard trapCard) {
        rivalPlayer.setAbleToAddCardInDrawPhase(false);
        player.addCardToGraveyard(trapCard);
        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }

    public static void mindCrush(Player player, Player rivalPlayer, TrapCard trapCard) {
        view.enterCardName();
        while (true) {
            String cardName = view.scanCardName();
            if (Card.isThisCardNameValid(cardName)) {
                if (rivalPlayer.doesHaveThisCardNameInThisPlace(cardName, rivalPlayer.getCardsInHand())) {
                    rivalPlayer.addAllCardsWithThisNameToGraveyard(cardName);
                } else {
                    ArrayList<Card> playerHand = player.getCardsInHand();
                    Random random = new Random();
                    int randomInt = Math.abs((random.nextInt()) % (playerHand.size()));
                    Card cardToRemove = playerHand.get(randomInt);
                    playerHand.remove(cardToRemove);
                    player.addCardToGraveyard(cardToRemove);
                }
                player.addCardToGraveyard(trapCard);
                player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
                break;
            } else if (cardName.equals("cancel")) {
                break;
            } else {
                view.invalidCardName();
            }
        }
    }

    public static void callOfTheHaunted(PhaseController controller, Player player, TrapCard trapCard) {
        if(player.isMonsterCardZoneFull()){
            view.yourMonsterCardZoneIsFull();
            return;
        }
        view.yourCardsInGraveyard();
        controller.controlShowGraveyardCommand();
        view.enterCardName();
        while (true) {
            String cardName = view.scanCardName();
            if (Card.isThisCardNameValid(cardName)) {
                if (player.doesHaveThisCardNameInThisPlace(cardName, player.getCardsInGraveyard())) {
                    Card card = player.getACardWithThisNameInThisPlace(cardName, player.getCardsInGraveyard());
                    if (card instanceof MonsterCard) {
                        player.removeCardFromGraveyard(card);
                        player.setSelectedCard(card);
                        ((MainPhaseController) controller).summonMonsterCard(player, (MonsterCard) card);
                        player.addCardToGraveyard(trapCard);
                        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
                        break;
                    } else {
                        view.thisCardIsNotAMonster();
                    }
                } else {
                    view.youDoNotHaveThisCardInGraveYard();
                }
            } else if (cardName.equals("cancel")) {
                break;
            } else {
                view.invalidCardName();
            }
        }
    }
}
