package model;

import enums.SpellOrTrapCardPosition;
import view.SpellEffectsView;

import java.util.Map;

public class SpellEffects {

    private Round round;
    private SpellEffectsView effectsView = new SpellEffectsView (this);

    SpellEffects(Round round) {
        this.round = round;
    }

    public void run(SpellCard activeCard) {

        switch (activeCard.getEffect()) {
            case SWORDS_OF_REVEALING_LIGHT:
            case YAMI:
            case FOREST:
            case RAIGEKI: {
                raigeki(activeCard);
                return;
            }
            case UMIIRUKA:
            case DARK_HOLE: {
                darkHole(activeCard);
                return;
            }
            case POT_OF_GREED: {
                potOfGreed(activeCard);
                return;
            }
            case SUPPLY_SQUAD:
            case TERRAFORMING:
            case BLACK_PENDANT:
            case CHANGEOFHEART:
            case CLOSED_FOREST:
            case MAGNUM_SHIELD:
            case TWIN_TWISTERS:
            case MONSTER_REBORN:
            case RING_OF_DEFENCE:
            case UNITED_WE_STAND:
            case SPELL_ABSORPTION:
            case MESSENGER_OF_PEACE:
            case ADVANCED_RITUAL_ART:
            case HARPIES_FEATHER_DUSTER: {
                harpiesFeatherDuster(activeCard);
                return;
            }
            case MYSTICAL_SPACE_TYPHOON: {
                mysticalSpaceTyphoon(activeCard);
            }
            case SWORD_OF_DARK_DESTRUCTION:

        }
    }

    private void mysticalSpaceTyphoon(SpellCard activeCard) {
        while (true) {
            effectsView.selectSpellZoneYouWantToSelectCardFrom();
            try {
                int whichZone = Integer.parseInt(effectsView.scanString());
                if(whichZone != 1 && whichZone != 2){
                    effectsView.invalidChoice();
                } else {
                    while(true){
                        Player playerThatLosesACard = whichZone == 1 ? round.getPlayerByTurn() : round.getRivalPlayerByTurn();
                        effectsView.selectLocationOfCardYouWantToDestroy();
                        int location = Integer.parseInt(effectsView.scanString());
                        if(location > 5 || location < 1){
                            effectsView.invalidLocation();
                        } else if (!playerThatLosesACard.doesHaveSpellOrTrapCardInThisPosition(location)){
                            effectsView.thereIsNoCardInThisLocation();
                        } else {
                            playerThatLosesACard.getSpellOrTrapCardsInZone().get(location).setGoingToGraveyard(true);
                            playerThatLosesACard.addCardToGraveyard(playerThatLosesACard.getSpellOrTrapCardsInZone().get(location));
                            playerThatLosesACard.removeCardFromCardsInZone(playerThatLosesACard.getSpellOrTrapCardsInZone().get(location), location);
                            effectsView.cardSentToGraveyardSuccessfully();
                            //card is going to graveyard. must call the run function
                            playerThatLosesACard.getSpellOrTrapCardsInZone().get(location).setGoingToGraveyard(true);
                            return;
                        }
                    }
                }
            } catch (NumberFormatException exception){
                effectsView.invalidChoice();
            }
        }
    }

    private void potOfGreed(SpellCard activeCard) {
        //TODO: needs to check if it has set recently
        Player player = round.getPlayerByTurn();
        player.addCardToHand(player.getRemainingPlayerCardsInGame().get(0));
        player.addCardToHand(player.getRemainingPlayerCardsInGame().get(1));
        player.getRemainingPlayerCardsInGame().remove(1);
        player.getRemainingPlayerCardsInGame().remove(0);
    }

    public void raigeki(SpellCard activeCard) {
        if (activeCard.getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            for (Map.Entry<Integer, MonsterCard> mapElement : round.getRivalPlayerByTurn().getMonsterCardsInZone().entrySet()) {
                round.getRivalPlayerByTurn().addCardToGraveyard(mapElement.getValue());
                round.getRivalPlayerByTurn().removeCardFromCardsInZone(mapElement.getValue(), round.getRivalPlayerByTurn().getLocationOfThisMonsterCardInZone(mapElement.getValue()));
            }
        }
    }

    public void harpiesFeatherDuster(SpellCard activeCard) {

        Player opponent = round.getRivalPlayerByTurn();
        if (activeCard.getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            for (Map.Entry<Integer, Card> mapElement : round.getRivalPlayerByTurn().getSpellOrTrapCardsInZone().entrySet()) {
                opponent.addCardToGraveyard(mapElement.getValue());
                opponent.removeCardFromCardsInZone(mapElement.getValue(), opponent.getLocationOfThisSpellOrTrapCardInZone(mapElement.getValue()));
            }
        }
    }

    public void darkHole(SpellCard activeCard) {
        if (activeCard.getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            Player player = round.getPlayerByTurn();
            Player opponent = round.getRivalPlayerByTurn();
            for (Map.Entry<Integer, MonsterCard> mapElement : player.getMonsterCardsInZone().entrySet()) {
                player.addCardToGraveyard(mapElement.getValue());
                player.removeCardFromCardsInZone(mapElement.getValue(), player.getLocationOfThisMonsterCardInZone(mapElement.getValue()));
            }
            for (Map.Entry<Integer, MonsterCard> mapElement : opponent.getMonsterCardsInZone().entrySet()) {
                opponent.addCardToGraveyard(mapElement.getValue());
                opponent.removeCardFromCardsInZone(mapElement.getValue(), opponent.getLocationOfThisMonsterCardInZone(mapElement.getValue()));
            }
        }
    }
}
