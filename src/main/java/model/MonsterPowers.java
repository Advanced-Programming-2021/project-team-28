package model;

import enums.*;
import view.MonsterPowerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MonsterPowers {

    public MonsterPowerView view = new MonsterPowerView(this);

    Phase phase;

    public MonsterPowers(Phase phase) {
        this.phase = phase;
    }

    public void run(MonsterCard activeCard, MonsterCard opponentCard)  {
        switch (activeCard.getSpecialPower()) {
            case NONE:
                return;
            case MAN_EATER_BUG: {
                manEaterBug(activeCard);
                return;
            }
            case MARSHMALLON:
            case SUIJIN:
            case SCANNER:
            case YOMI_SHIP: {
                yomiShip(activeCard, opponentCard);
                return;
            }
            case TEXCHANGER:
            case THE_TRICKY:
            case GATE_GUARDIAN:
            case MIRAGE_DRAGON: {
                mirageDragon(activeCard);
                return;
            }
            case COMMAND_KNIGHT:
            case THE_CALCULATOR: {
                theCalculator(activeCard);
                return;
            }
            case EXPLODER_DRAGON:
            case HERALD_OF_CREATION:
            case BEAST_KING_BARBAROS:
            case TERRATIGER_THE_EMPOWERED_WARRIOR:
        }
    }

    private void manEaterBug(MonsterCard manEaterBug) {

        int opponentCardLocation;
        if (manEaterBug.isFlipped()) {
            manEaterBug.setFlipped(false);
            boolean needsToChangeTurn = false;
            if (manEaterBug.getOwnerUsername().equals(phase.getRivalPlayerByTurn().getUser().getUsername())) {
                needsToChangeTurn = true;
                view.nowItIsRivalTurn(phase.getRivalPlayerByTurn().getUser().getNickname());
                phase.changeTurn();
            }
            if (!phase.getRivalPlayerByTurn().isMonsterCardZoneEmpty()) {
                view.printError("please enter a location to destroy enemy card : ");
                opponentCardLocation = view.manEaterBug();
                if (opponentCardLocation < 1 || opponentCardLocation > 5) {
                    view.printError("there is no place with this number");
                    return;
                }
                if (!phase.getRivalPlayerByTurn().doesHaveMonsterCardInThisLocation(opponentCardLocation)) {
                    view.printError("there is no card in selected zone");
                    return;
                }
                MonsterCard opponentCard = phase.getRivalPlayerByTurn().getMonsterCardByLocationFromZone(opponentCardLocation);
                if (phase.getRivalPlayerByTurn().getSelectedCard().equals(opponentCard)) {
                    opponentCard.setCardActionCanceledByAnEffect(true);
                }
                opponentCard.setGoingToGraveyard(true);
                phase.getRivalPlayerByTurn().addCardToGraveyard(opponentCard);
                phase.getRivalPlayerByTurn().removeCardFromCardsInZone(opponentCard, opponentCardLocation);
            }
            if (needsToChangeTurn) {
                view.nowItIsRivalTurn(phase.getRivalPlayerByTurn().getUser().getNickname());
                phase.changeTurn();
            }

        }
    }

    private void theCalculator(MonsterCard activeCard) {
        int levelSum = 0;
        Player player = User.getUserByUsername(activeCard.getOwnerUsername()) == phase.getFirstPlayer().getUser() ? phase.getFirstPlayer() : phase.getSecondPlayer();
        for (Map.Entry<Integer, MonsterCard> monsterZone : player.getMonsterCardsInZone().entrySet()) {
            if (monsterZone.getValue().getPosition() == MonsterCardPosition.DEFENSIVE_OCCUPIED || monsterZone.getValue().getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED) {
                levelSum += monsterZone.getValue().getLevel();
            }
        }
        activeCard.setAttackPoint(300 * levelSum);
    }

    private void mirageDragon(MonsterCard activeCard) {
        if (activeCard.getPosition() == MonsterCardPosition.DEFENSIVE_OCCUPIED || activeCard.getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED) {
            if (!activeCard.isGoingToGraveyard) {
                if (User.getUserByUsername(activeCard.getOwnerUsername()) == phase.getFirstPlayer().getUser()) {
                    phase.getSecondPlayer().setAbleToActivateTrapCard(false);
                } else {
                    phase.getFirstPlayer().setAbleToActivateTrapCard(false);
                }
            }
        }

    }

    private void yomiShip(MonsterCard yomiShip, MonsterCard attacker) {
        if (yomiShip.isGoingToGraveyard()) {
            Player attackerPlayer = phase.getPlayerByTurn();
            attacker.setGoingToGraveyard(true);
            if (!(attacker.getSpecialPower() == MonsterPower.YOMI_SHIP)) {
                run(attacker, null);
            }
            attackerPlayer.addCardToGraveyard(attacker);
            attackerPlayer.removeCardFromCardsInZone(attacker, attackerPlayer.getLocationOfThisMonsterCardInZone(attacker));
        }
    }

    public boolean ritualSummoned(MonsterCard card)  {
        boolean status;
        int levelSum = 0;
        HashMap<Integer, Card> cards = phase.getPlayerByTurn().getSpellOrTrapCardsInZone();
        HashMap<Integer, MonsterCard> monsters = phase.getPlayerByTurn().getMonsterCardsInZone();

        for (Map.Entry<Integer, MonsterCard> mapElement : monsters.entrySet()){
            if(mapElement.getValue() != null && (mapElement.getValue().getSpecialPower() == MonsterPower.NONE)){
                levelSum += mapElement.getValue().getLevel();
            }
        }
        if(levelSum < card.getLevel()){
            view.printError("there is no way you could ritual summon a monster");
            return false;
        }


        for (Map.Entry<Integer, Card> mapElement : cards.entrySet()) {
            if (((SpellCard) mapElement.getValue()).getEffect() == SpellEffect.ADVANCED_RITUAL_ART && ((SpellCard) mapElement.getValue()).getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
                status = ritualSummonProcedure(card);
                if (status) {
                    phase.getPlayerByTurn().addCardToGraveyard(mapElement.getValue());
                    phase.getPlayerByTurn().removeCardFromCardsInZone(mapElement.getValue(), phase.getPlayerByTurn().getLocationOfThisSpellOrTrapCardInZone(mapElement.getValue()));
                }
                return status;
            }
        }
        view.printError("there is no way you could ritual summon a monster");
        return false;
    }

    private boolean ritualSummonProcedure(MonsterCard card) {
        ArrayList<Integer> locations = new ArrayList<>();
        HashMap<Integer, MonsterCard> cards = phase.getPlayerByTurn().getMonsterCardsInZone();
        int location = 0;
        int levelSum = 0;
        view.printError("enter location of cards that you want to tribute for the ritual summon (sum of levels of selected cards must either be equal or greater than the target card level)");
        view.printError("to end the selection enter 0");
        while (true) {
            try {
                location = view.intScanner();
            } catch (Exception e) {
                view.printError("you should ritual summon right now");
            }
            if (location < 1 || location > 5) {
                view.printError("invalid location");
            } else if (cards.get(location) == null) {
                view.printError("no card in selected location");
            } else if (locations.contains(location)) {
                view.printError("repeated location");
            } else if (cards.get(location).getSpecialPower() != MonsterPower.NONE){
                view.printError("This card is not a normal monster");
            } else {
                locations.add(location);
                levelSum += cards.get(location).getLevel();
            }
            if (location == 0)
                return false;

            view.printError("selected monsters levels don’t match with ritual monster");

            if (levelSum >= card.getLevel()) {
                for (Integer integer : locations) {
                    phase.getPlayerByTurn().addCardToGraveyard(cards.get(integer));
                    phase.getPlayerByTurn().removeCardFromCardsInZone(phase.getPlayerByTurn().getMonsterCardByLocationFromZone(integer), integer);
                }
                return true;
            }
        }
    }
}

