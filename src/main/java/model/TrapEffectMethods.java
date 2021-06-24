package model;

import controller.MainPhaseController;
import controller.PhaseController;
import enums.MonsterCardPosition;
import view.TrapEffectsView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static enums.MonsterCardPosition.DEFENSIVE_OCCUPIED;
import static enums.MonsterCardPosition.OFFENSIVE_OCCUPIED;

public class TrapEffectMethods {

    private static final TrapEffectsView view = new TrapEffectsView();

    public static void magicCylinder(Player player, Player rivalPlayer, Card rivalAttackerCard, TrapCard trapCard) {
        rivalPlayer.decreaseLifePoint(((MonsterCard) rivalAttackerCard).getAttackPoint());
        if(rivalPlayer.getLifePoint() < 0){
            rivalPlayer.setLifePoint(0);
        }
        ((MonsterCard) rivalAttackerCard).setCardActionCanceledByAnEffect(true);
        player.addCardToGraveyard(trapCard);
        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }

    public static void mirrorForce(Player player, Player rivalPlayer, Card rivalAttackerCard, TrapCard trapCard) {
        ((MonsterCard) rivalAttackerCard).setCardActionCanceledByAnEffect(true);
        for (Map.Entry<Integer, MonsterCard> mapElement : rivalPlayer.getMonsterCardsInZone().entrySet()){
            if(mapElement.getValue().getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED){
                rivalPlayer.addCardToGraveyard(mapElement.getValue());
            }
        }
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
        phase.getFirstPlayer().addAllCardsOfMonsterZoneToGraveyard();
        phase.getSecondPlayer().addAllCardsOfMonsterZoneToGraveyard();
        phase.getFirstPlayer().getMonsterCardsInZone().clear();
        phase.getSecondPlayer().getMonsterCardsInZone().clear();
        Player player = phase.getPlayerByTurn();
        player.addCardToGraveyard(trapCard);
        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }

    public static void trapHole(Player player, Player rivalPlayer, Card rivalSummonedCard, TrapCard trapCard) {
        Player whoSummonedAMonster = rivalSummonedCard.getOwnerUsername().equals(player.getUser().getUsername()) ? player : rivalPlayer;
        whoSummonedAMonster.addCardToGraveyard(rivalSummonedCard);
        whoSummonedAMonster.getMonsterCardsInZone().remove
                (whoSummonedAMonster.getLocationOfThisMonsterCardInZone((MonsterCard) rivalSummonedCard), rivalSummonedCard);
        player.addCardToGraveyard(trapCard);
        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
    }

    public static void solemnWarning(Player player, Player rivalPlayer, Card rivalSummonedCard, TrapCard trapCard) {
        Player whoSummonedAMonster = rivalSummonedCard.getOwnerUsername().equals(player.getUser().getUsername()) ? player : rivalPlayer;
        player.decreaseLifePoint(2000);
        if(player.getLifePoint() < 0){
            player.setLifePoint(0);
        }
        ((MonsterCard) rivalSummonedCard).setCardActionCanceledByAnEffect(true);
        whoSummonedAMonster.addCardToGraveyard(rivalSummonedCard);
        whoSummonedAMonster.getMonsterCardsInZone().remove
                (whoSummonedAMonster.getLocationOfThisMonsterCardInZone((MonsterCard) rivalSummonedCard), rivalSummonedCard);
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
            String cardName = view.scanString();
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
                trapCard.setActivationCancelled(true);
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
            String cardName = view.scanString();
            if (Card.isThisCardNameValid(cardName)) {
                if (player.doesHaveThisCardNameInThisPlace(cardName, player.getCardsInGraveyard())) {
                    Card card = player.getACardWithThisNameInThisPlace(cardName, player.getCardsInGraveyard());
                    if (card instanceof MonsterCard) {
                        player.removeCardFromGraveyard(card);
                        player.setSelectedCard(card);
                        ((MainPhaseController) controller).summonMonsterCard(player, (MonsterCard) card , MonsterCardPosition.OFFENSIVE_OCCUPIED);
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
                trapCard.setActivationCancelled(true);
                break;
            } else {
                view.invalidCardName();
            }
        }
    }

    public static void magicJammer(Player player, Player rivalPlayer, Card activatedCard, TrapCard trapCard){
        view.enterCardLocationInHand();
        while (true) {
            String cardLocation = view.scanString();
            if (cardLocation.equals("cancel")) {
                trapCard.setActivationCancelled(true);
                return;
            } else {
                try {
                    int location = Integer.parseInt(cardLocation);
                    if (location < 1 || location > player.getCardsInHand().size()) {
                        view.thereIsNoCardInThisLocation();
                    } else {
                        Card cardThatGoesToGraveyard = player.getCardsInHand().get(location - 1);
                        player.addCardToGraveyard(cardThatGoesToGraveyard);
                        player.removeCardFromHand(cardThatGoesToGraveyard);
                        ((SpellCard) activatedCard).setActivationCancelled(true);
                        rivalPlayer.addCardToGraveyard(activatedCard);
                        if(rivalPlayer.isSelectedCardFromHand()){
                            rivalPlayer.removeCardFromHand(activatedCard);
                        } else if (rivalPlayer.isSelectedCardFromSpellAndTrapZone()){
                            rivalPlayer.removeCardFromCardsInZone(activatedCard,
                                    rivalPlayer.getLocationOfThisSpellOrTrapCardInZone(activatedCard));
                        }
                        player.addCardToGraveyard(trapCard);
                        player.removeCardFromCardsInZone(trapCard, player.getLocationOfThisSpellOrTrapCardInZone(trapCard));
                        return;
                    }
                } catch (NumberFormatException exception) {
                   view.invalidLocation();
                }
            }
        }
    }
}
