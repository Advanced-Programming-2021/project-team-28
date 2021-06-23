package model;

import controller.MainPhaseController;
import enums.MonsterType;
import enums.SpellIcon;
import enums.SpellOrTrapCardPosition;
import view.SpellEffectsView;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static enums.MonsterCardPosition.DEFENSIVE_OCCUPIED;
import static enums.MonsterCardPosition.OFFENSIVE_OCCUPIED;

public class SpellEffects {


    private Round round;
    private MainPhaseController controller;
    private SpellEffectsView effectsView = new SpellEffectsView(this);


    public SpellEffects(Round round, MainPhaseController controller) {
        this.controller = controller;
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
            case UMIIRUKA: {
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
            case TERRAFORMING: {
                terraforming(activeCard);
                return;
            }
            case BLACK_PENDANT: {
                blackPendant(activeCard);
                return;
            }
            case CHANGEOFHEART:
            case CLOSED_FOREST: {
                closedForest(activeCard);
                return;
            }
            case MAGNUM_SHIELD:{
                magnumShield();
                return;
            }
            case TWIN_TWISTERS:
            case MONSTER_REBORN: {
                monsterReborn(activeCard);
                return;
            }
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
            case SWORD_OF_DARK_DESTRUCTION:{
                swordOfDarkDestruction(activeCard);
            }

        }
    }

//    public void runEquipSpells(SpellCard equipSpell , MonsterCard monsterToBeEquipped){
//
//    }

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
            }
            round.getRivalPlayerByTurn().getMonsterCardsInZone().clear();
        }
    }

    public void harpiesFeatherDuster(SpellCard activeCard) {

        Player opponent = round.getRivalPlayerByTurn();
        if (activeCard.getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            for (Map.Entry<Integer, Card> mapElement : round.getRivalPlayerByTurn().getSpellOrTrapCardsInZone().entrySet()) {
                opponent.addCardToGraveyard(mapElement.getValue());
                if(mapElement.getValue() instanceof SpellCard){
                    if(((SpellCard)mapElement.getValue()).getIcon() == SpellIcon.EQUIP){
                        mapElement.getValue().setGoingToGraveyard(true);
                        run(((SpellCard)mapElement.getValue()));
                        mapElement.getValue().setGoingToGraveyard(false);
                    }
                }
            }
            round.getRivalPlayerByTurn().getSpellOrTrapCardsInZone().clear();

            if(opponent.hasFieldSpellCardInZone()){
                opponent.addCardToGraveyard(opponent.getFieldZoneCard());
                opponent.getFieldZoneCard().setGoingToGraveyard(true);
                run((SpellCard) opponent.getFieldZoneCard());
                opponent.getFieldZoneCard().setGoingToGraveyard(false);
                opponent.setFieldZoneCard(null);

            }
        }
    }


    public void darkHole(SpellCard activeCard) {
        if (activeCard.getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            Player player = round.getPlayerByTurn();
            Player opponent = round.getRivalPlayerByTurn();
            player.addAllCardsOfMonsterZoneToGraveyard();
            player.getMonsterCardsInZone().clear();
            opponent.addAllCardsOfMonsterZoneToGraveyard();
            opponent.getMonsterCardsInZone().clear();
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

    

    public void monsterReborn(SpellCard card) {
        effectsView.whichGraveyard();
        while (true) {
            String graveyard = effectsView.scanString();
            if (graveyard.equals("y")) {
                if (round.getPlayerByTurn().getCardsInGraveyard().isEmpty()) {
                    effectsView.thisGraveyardIsEmpty();
                } else {
                    summonFromThisPlayerGraveyard(round.getPlayerByTurn(), card);
                    return;
                }
            } else if (graveyard.equals("o")) {
                if (round.getRivalPlayerByTurn().getCardsInGraveyard().isEmpty()) {
                    effectsView.thisGraveyardIsEmpty();
                } else {
                    summonFromThisPlayerGraveyard(round.getRivalPlayerByTurn(), card);
                    return;
                }
            } else if (graveyard.equals("cancel")) {
                effectsView.activationCancelled();
                card.setActivationCancelled(true);
                break;
            } else if (graveyard.equals("show graveyard")) {
                effectsView.printString(round.getPlayerByTurn().graveyardToString());
            } else if (graveyard.equals("show opponent graveyard")) {
                effectsView.printString(round.getRivalPlayerByTurn().graveyardToString());
            } else {
                effectsView.youShouldSpecialSummonRightNow();
            }
        }
    }


    private void summonFromThisPlayerGraveyard(Player player, SpellCard card) {
        effectsView.selectCardFromGraveyard();
        effectsView.printString(player.graveyardToString());
        while (true) {
            String cardLocation = effectsView.scanString();
            if (cardLocation.equals("cancel")) {
                card.setActivationCancelled(true);
                effectsView.activationCancelled();
                return;
            } else {
                try {
                    int location = Integer.parseInt(cardLocation);
                    if (location < 1 || location > player.getCardsInGraveyard().size()) {
                        effectsView.thereIsNoCardInThisLocation();
                    } else {
                        Card cardToBeSummoned = player.getCardsInGraveyard().get(location - 1);
                        if (!(cardToBeSummoned instanceof MonsterCard)) {
                            effectsView.thisIsNotMonsterCard();
                        } else {
                            String position;
                            while(true){
                                effectsView.enterMonsterPosition();
                                position = effectsView.scanString();
                                if (position.equals("OO")) {
                                    ((MonsterCard) cardToBeSummoned).setPosition(OFFENSIVE_OCCUPIED);
                                    break;
                                } else if (position.equals("DO")) {
                                    ((MonsterCard) cardToBeSummoned).setPosition(DEFENSIVE_OCCUPIED);
                                    break;
                                } else if (position.equals("cancel")){
                                    card.setActivationCancelled(true);
                                    effectsView.activationCancelled();
                                    return;
                                } else {
                                    effectsView.invalidChoice();
                                }
                            }
                            round.getPlayerByTurn().addCardToCardsInZone(cardToBeSummoned);
                            player.removeCardFromGraveyard(cardToBeSummoned);
                            ((MonsterCard) cardToBeSummoned).setSpecialSummoned(true);
                            controller.checkTrapActionsAfterSummon((MonsterCard) cardToBeSummoned);
                            controller.runFieldZoneSpells(round.getPlayerByTurn());
                            controller.runAllMonsterPowersInZone(round.getPlayerByTurn());
                            return;
                        }
                    }
                } catch (NumberFormatException exception) {
                    effectsView.invalidLocation();
                }
            }
        }
    }

    public void terraforming(SpellCard card) {
        ArrayList<Card> cards = round.getPlayerByTurn().getRemainingPlayerCardsInGame();
        if (card.getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i) instanceof SpellCard) {
                    if (((SpellCard) cards.get(i)).getIcon() == SpellIcon.FIELD) {
                        round.getPlayerByTurn().addCardToCardsInZone(cards.get(i));
                        cards.remove(i);
                        return;
                    }
                }
            }
        }
    }

    private void blackPendant(SpellCard card){
        if(!card.isGoingToGraveyard) {
            MonsterCard targetCard = getTarget();
            if (targetCard == null) {
                return;
            }
            targetCard.changeAttackPoint(500);
            targetCard.setEquipCard(card);
        }
        else {
            if (round.getPlayerByTurn().findEquipCardOwner(card) != null) {
                round.getPlayerByTurn().findEquipCardOwner(card).changeAttackPoint(-1 * 500);
                round.getPlayerByTurn().findEquipCardOwner(card).setEquipCard(null);
            }
        }
    }

    private MonsterCard getTarget(){
        int targetLocation;
        while(true){
            effectsView.enterTargetPosition();
            targetLocation = effectsView.intScanner();
            if(targetLocation == 0){
                return null;
            }
            if(targetLocation < 1 || targetLocation > 5){
                effectsView.invalidLocation();
                continue;
            }
            if(!round.getPlayerByTurn().doesHaveMonsterCardInThisLocation(targetLocation)){
                effectsView.thereIsNoCardInThisLocation();
                continue;
            }
            if(round.getPlayerByTurn().getMonsterCardByLocationFromZone(targetLocation).getEquipCard() != null){
                effectsView.thisCardAlreadyHasAnEquipSpell();
                continue;
            }

            return round.getPlayerByTurn().getMonsterCardByLocationFromZone(targetLocation);
        }
    }
    
    public void swordOfDarkDestruction(SpellCard card) {
        if(!card.isGoingToGraveyard) {
            MonsterCard targetCard = getSwordOfDarkDestructionTarget();
            if (targetCard == null) {
                return;
            }
            targetCard.changeAttackPoint(400);
            targetCard.changeDefencePoint(-1 * 200);
            targetCard.setEquipCard(card);
        }
        else {
            if (round.getPlayerByTurn().findEquipCardOwner(card) != null) {
                round.getPlayerByTurn().findEquipCardOwner(card).changeAttackPoint(-1 * 400);
                round.getPlayerByTurn().findEquipCardOwner(card).changeDefencePoint(200);
                round.getPlayerByTurn().findEquipCardOwner(card).setEquipCard(null);
            }
        }
    }

    private MonsterCard getSwordOfDarkDestructionTarget() {
        MonsterCard target;
       while (true){
           target = getTarget();
           if(target == null){
               return null;
           }
           if(target.getType() != MonsterType.FIEND){
               effectsView.theSelectedCardMustBeFiend();
               continue;
           }
           return target;
       }
    }

    private void magnumShield(SpellCard card){

    }


}




