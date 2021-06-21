package model;

import enums.MonsterType;
import enums.SpellIcon;
import enums.SpellOrTrapCardPosition;
import view.SpellEffectsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpellEffects {


    private Round round;
    private SpellEffectsView effectsView = new SpellEffectsView(this);


    public SpellEffects(Round round) {
        this.round = round;
    }


    public void run(SpellCard activeCard) {

        switch (activeCard.getEffect()) {
            case SWORDS_OF_REVEALING_LIGHT:
            case YAMI: {
                yami(activeCard);
                return;
            }
            case FOREST: {
                forest(activeCard);
                return;
            }
            case RAIGEKI: {
                raigeki(activeCard);
                return;
            }
            case UMIIRUKA:{
                umiiruka(activeCard);
                return;
            }
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
            case CLOSED_FOREST:{
                closedForest(activeCard);
                return;
            }
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
                return;
            }
            case SWORD_OF_DARK_DESTRUCTION:

        }
    }
    //type: quick
    private void mysticalSpaceTyphoon(SpellCard activeCard) {
        while (true) {
            effectsView.selectSpellZoneYouWantToSelectCardFrom();
            try {
                int whichZone = Integer.parseInt(effectsView.scanString());
                if (whichZone != 1 && whichZone != 2) {
                    effectsView.invalidChoice();
                } else {
                    while (true) {
                        Player playerThatLosesACard = whichZone == 1 ? round.getPlayerByTurn() : round.getRivalPlayerByTurn();
                        effectsView.selectLocationOfCardYouWantToDestroy();
                        int location = Integer.parseInt(effectsView.scanString());
                        if (location > 5 || location < 1) {
                            effectsView.invalidLocation();
                        } else if (!playerThatLosesACard.doesHaveSpellOrTrapCardInThisPosition(location)) {
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
            } catch (NumberFormatException exception) {
                effectsView.invalidChoice();
            }
        }
    }

    private void potOfGreed(SpellCard activeCard) {
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

    private void yami(SpellCard card) {
        HashMap<Integer, MonsterCard> player1Field = round.getFirstPlayer().getMonsterCardsInZone();
        HashMap<Integer, MonsterCard> player2Field = round.getSecondPlayer().getMonsterCardsInZone();
        if (!card.isGoingToGraveyard) {
            for (Map.Entry<Integer, MonsterCard> mapElement : player1Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.FIEND || mapElement.getValue().getType() == MonsterType.SPELL_CASTER) {
                    if (!mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(200);
                        mapElement.getValue().changeDefencePoint(200);
                        mapElement.getValue().setEffectedByFieldSpell(true);
                    }
                }
                if (mapElement.getValue().getType() == MonsterType.FAIRY) {
                    if (!mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(-1 * 200);
                        mapElement.getValue().changeDefencePoint(-1 * 200);
                        mapElement.getValue().setEffectedByFieldSpell(true);
                    }
                }
            }
            for (Map.Entry<Integer, MonsterCard> mapElement : player2Field.entrySet()) {
                   if (mapElement.getValue().getType() == MonsterType.FIEND || mapElement.getValue().getType() == MonsterType.SPELL_CASTER) {
                    if (!mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(200);
                        mapElement.getValue().changeDefencePoint(200);
                        mapElement.getValue().setEffectedByFieldSpell(true);
                    }
                }
                if (mapElement.getValue().getType() == MonsterType.FAIRY) {
                    if (!mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(-1 * 200);
                        mapElement.getValue().changeDefencePoint(-1 * 200);
                        mapElement.getValue().setEffectedByFieldSpell(true);
                    }
                }
            }
        } else {
            for (Map.Entry<Integer, MonsterCard> mapElement : player1Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.FIEND || mapElement.getValue().getType() == MonsterType.SPELL_CASTER) {
                    if (mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(-1 * 200);
                        mapElement.getValue().changeDefencePoint(-1 * 200);
                        mapElement.getValue().setEffectedByFieldSpell(false);
                    }
                }
                if (mapElement.getValue().getType() == MonsterType.FAIRY) {
                    if (mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(200);
                        mapElement.getValue().changeDefencePoint(200);
                        mapElement.getValue().setEffectedByFieldSpell(false);
                    }
                }
            }
            for (Map.Entry<Integer, MonsterCard> mapElement : player2Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.FIEND || mapElement.getValue().getType() == MonsterType.SPELL_CASTER) {
                    if (mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(-1 * 200);
                        mapElement.getValue().changeDefencePoint(-1 * 200);
                        mapElement.getValue().setEffectedByFieldSpell(false);
                    }
                }
                if (mapElement.getValue().getType() == MonsterType.FAIRY) {
                    if (mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(200);
                        mapElement.getValue().changeDefencePoint(200);
                        mapElement.getValue().setEffectedByFieldSpell(false);
                    }
                }
            }
        }
    }

    private void forest(SpellCard card) {
        HashMap<Integer, MonsterCard> player1Field = round.getFirstPlayer().getMonsterCardsInZone();
        HashMap<Integer, MonsterCard> player2Field = round.getSecondPlayer().getMonsterCardsInZone();
        if (!card.isGoingToGraveyard) {
            for (Map.Entry<Integer, MonsterCard> mapElement : player1Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.INSECT || mapElement.getValue().getType() == MonsterType.BEAST_WARRIOR || mapElement.getValue().getType() == MonsterType.BEAST) {
                    if (!mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(200);
                        mapElement.getValue().changeDefencePoint(200);
                        mapElement.getValue().setEffectedByFieldSpell(true);
                    }
                }
            }

            for (Map.Entry<Integer, MonsterCard> mapElement : player2Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.INSECT || mapElement.getValue().getType() == MonsterType.BEAST_WARRIOR || mapElement.getValue().getType() == MonsterType.BEAST) {
                    if (!mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(200);
                        mapElement.getValue().changeDefencePoint(200);
                        mapElement.getValue().setEffectedByFieldSpell(true);
                    }
                }
            }
        } else {
            for (Map.Entry<Integer, MonsterCard> mapElement : player1Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.INSECT || mapElement.getValue().getType() == MonsterType.BEAST_WARRIOR || mapElement.getValue().getType() == MonsterType.BEAST) {
                    if (mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(-1 * 200);
                        mapElement.getValue().changeDefencePoint(-1 * 200);
                        mapElement.getValue().setEffectedByFieldSpell(false);
                    }
                }
            }

            for (Map.Entry<Integer, MonsterCard> mapElement : player2Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.INSECT || mapElement.getValue().getType() == MonsterType.BEAST_WARRIOR || mapElement.getValue().getType() == MonsterType.BEAST) {
                    if (mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(-1 * 200);
                        mapElement.getValue().changeDefencePoint(-1 * 200);
                        mapElement.getValue().setEffectedByFieldSpell(false);
                    }
                }
            }
        }
    }

    private void closedForest(SpellCard card) {
        User user = User.getUserByUsername(card.getOwnerUsername());
        Player player1 = round.getFirstPlayer();
        Player player2 = round.getSecondPlayer();
        if (!card.isGoingToGraveyard) {
            if (user == player1.getUser()) {
                int graveSize = player1.getCardsInGraveyard().size();
                for (Map.Entry<Integer, MonsterCard> mapElement : player1.getMonsterCardsInZone().entrySet()) {
                    if (mapElement.getValue().getType() == MonsterType.BEAST) {
                        if (!mapElement.getValue().isEffectedByFieldSpell()) {
                            mapElement.getValue().changeAttackPoint(100 * graveSize);
                            mapElement.getValue().setEffectedByFieldSpell(true);
                        }
                    }
                }
            } else {
                int graveSize = player2.getCardsInGraveyard().size();
                for (Map.Entry<Integer, MonsterCard> mapElement : player2.getMonsterCardsInZone().entrySet()) {
                    if (mapElement.getValue().getType() == MonsterType.BEAST) {
                        if (!mapElement.getValue().isEffectedByFieldSpell()) {
                            mapElement.getValue().changeAttackPoint(100 * graveSize);
                            mapElement.getValue().setEffectedByFieldSpell(true);
                        }
                    }
                }
            }
        } else {
            if (user == player1.getUser()) {
                int graveSize = player1.getCardsInGraveyard().size();
                for (Map.Entry<Integer, MonsterCard> mapElement : player1.getMonsterCardsInZone().entrySet()) {
                    if (mapElement.getValue().getType() == MonsterType.BEAST) {
                        if (mapElement.getValue().isEffectedByFieldSpell())
                            mapElement.getValue().changeAttackPoint(-1 * 100 * graveSize);
                        mapElement.getValue().setEffectedByFieldSpell(false);
                    }
                }
            } else {
                int graveSize = player2.getCardsInGraveyard().size();
                for (Map.Entry<Integer, MonsterCard> mapElement : player2.getMonsterCardsInZone().entrySet()) {
                    if (mapElement.getValue().getType() == MonsterType.BEAST) {
                        if (mapElement.getValue().isEffectedByFieldSpell()) {
                            mapElement.getValue().changeAttackPoint(-1 * 100 * graveSize);
                            mapElement.getValue().setEffectedByFieldSpell(false);
                        }
                    }
                }
            }
        }
    }

    private void umiiruka(SpellCard card) {

        HashMap<Integer, MonsterCard> player1Field = round.getFirstPlayer().getMonsterCardsInZone();
        HashMap<Integer, MonsterCard> player2Field = round.getSecondPlayer().getMonsterCardsInZone();

        if (!card.isGoingToGraveyard) {
            for (Map.Entry<Integer, MonsterCard> mapElement : player1Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.AQUA) {
                    if (!mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(500);
                        mapElement.getValue().changeDefencePoint(-1 * 400);
                        mapElement.getValue().setEffectedByFieldSpell(true);
                    }
                }
            }

            for (Map.Entry<Integer, MonsterCard> mapElement : player2Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.AQUA) {
                    if (!mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(500);
                        mapElement.getValue().changeDefencePoint(-1 * 400);
                        mapElement.getValue().setEffectedByFieldSpell(true);
                    }
                }
            }
        } else {
            for (Map.Entry<Integer, MonsterCard> mapElement : player1Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.AQUA) {
                    if (mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(-1 * 500);
                        mapElement.getValue().changeDefencePoint(400);
                        mapElement.getValue().setEffectedByFieldSpell(false);
                    }
                }
            }

            for (Map.Entry<Integer, MonsterCard> mapElement : player2Field.entrySet()) {
                if (mapElement.getValue().getType() == MonsterType.AQUA) {
                    if (mapElement.getValue().isEffectedByFieldSpell()) {
                        mapElement.getValue().changeAttackPoint(-1 * 500);
                        mapElement.getValue().changeDefencePoint(400);
                        mapElement.getValue().setEffectedByFieldSpell(false);
                    }
                }
            }
        }
    }

    public void swordOfDarkDestruction(SpellCard card) {
        if (card.getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            if (!card.hasDeployedEffect()) {

            }
        }
    }

    public void monsterReborn(SpellCard card) {
        int cardPositionInGraveyard;
        Card cardToBeSummoned;

        if (card.getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            effectsView.whichGraveyard();
            String graveyard = effectsView.scanString();


            if (!(graveyard.equals("o") || graveyard.equals("y"))) {
                round.getPlayerByTurn().addCardToGraveyard(card);
                return;
            } else {
                if (graveyard.equals("y")) {
                    effectsView.selectCardFromGraveyard();
                    round.getPlayerByTurn().graveyardToString();
                    cardPositionInGraveyard = effectsView.scanNumber();
                    cardToBeSummoned = round.getPlayerByTurn().getCardsInGraveyard().get(cardPositionInGraveyard);
                    if (!(cardToBeSummoned instanceof MonsterCard)) {
                        effectsView.thisIsNotMonsterCard();
                        round.getPlayerByTurn().addCardToGraveyard(card);
                        return;
                    }
                    round.getPlayerByTurn().addCardToCardsInZone(cardToBeSummoned);
                    round.getPlayerByTurn().removeCardFromGraveyard(cardToBeSummoned);
                    round.getPlayerByTurn().addCardToGraveyard(card);
                } else {
                    effectsView.selectCardFromGraveyard();
                    round.getRivalPlayerByTurn().graveyardToString();
                    cardPositionInGraveyard = effectsView.scanNumber();
                    cardToBeSummoned = round.getRivalPlayerByTurn().getCardsInGraveyard().get(cardPositionInGraveyard);
                    if (!(cardToBeSummoned instanceof MonsterCard)) {
                        effectsView.thisIsNotMonsterCard();
                        round.getPlayerByTurn().addCardToGraveyard(card);
                        return;
                    }
                    round.getPlayerByTurn().addCardToCardsInZone(cardToBeSummoned);
                    round.getRivalPlayerByTurn().removeCardFromGraveyard(cardToBeSummoned);
                    round.getPlayerByTurn().addCardToGraveyard(card);
                }
            }
        }
    }

    public void terraforming(SpellCard card) {
        ArrayList<Card> cards = round.getPlayerByTurn().getRemainingPlayerCardsInGame();
        if (card.getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            for (int i = 0; i < cards.size(); i++) {
                if(cards.get(i) instanceof SpellCard){
                    if(((SpellCard) cards.get(i)).getIcon() == SpellIcon.FIELD){
                        round.getPlayerByTurn().addCardToCardsInZone(cards.get(i));
                        cards.remove(i);
                    }
                }
            }
        }
    }
}




