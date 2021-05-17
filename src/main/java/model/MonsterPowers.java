package model;

import enums.MonsterCardPosition;
import enums.MonsterPower;
import enums.SpellEffect;
import enums.Turn;
import view.MonsterPowerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MonsterPowers {

    private MonsterPowerView view = new MonsterPowerView(this);

    Round round;

    MonsterPowers(Round round) {
        this.round = round;
    }

    public void run(MonsterCard activeCard, MonsterCard opponentCard) {
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
            case RITUAL:
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

    public void manEaterBug(MonsterCard manEaterBug) {
        int opponentCardLocation;
        if (manEaterBug.isFlipped()) {
            if (!round.getRivalPlayerByTurn().isMonsterCardZoneEmpty()) {
                view.printError("please enter a location to destroy enemy card : ");
                opponentCardLocation = view.manEaterBug();
                if (opponentCardLocation < 1 || opponentCardLocation > 5) {
                    view.printError("there is no place with this number");
                    return;
                }
                if (!round.getRivalPlayerByTurn().doesHaveMonsterCardInThisLocation(opponentCardLocation)) {
                    view.printError("there is no card in selected zone");
                    return;
                }
                MonsterCard opponentCard = round.getRivalPlayerByTurn().getMonsterCardByLocationFromZone(opponentCardLocation);
                round.getRivalPlayerByTurn().addCardToGraveyard(opponentCard);
                round.getRivalPlayerByTurn().removeCardFromCardsInZone(opponentCard, opponentCardLocation);
            }
        }
        manEaterBug.setFlipped(false);
    }

    public void theCalculator(MonsterCard activeCard) {
        int levelSum = 0;
        if (User.getUserByUsername(activeCard.getOwnerUsername()) == round.getFirstPlayer().getUser()) {
            for (Map.Entry<Integer, MonsterCard> monsterZone : round.getFirstPlayer().getMonsterCardsInZone().entrySet()) {
                if (monsterZone.getValue().getPosition() == MonsterCardPosition.DEFENSIVE_OCCUPIED || monsterZone.getValue().getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED) {
                    levelSum += monsterZone.getValue().getLevel();
                }
            }
            activeCard.setAttackPoint(300 * levelSum);
        } else {
            for (Map.Entry<Integer, MonsterCard> monsterZone : round.getSecondPlayer().getMonsterCardsInZone().entrySet()) {
                if (monsterZone.getValue().getPosition() == MonsterCardPosition.DEFENSIVE_OCCUPIED || monsterZone.getValue().getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED) {
                    levelSum += monsterZone.getValue().getLevel();
                }
            }
            activeCard.setAttackPoint(300 * levelSum);
        }
    }

    public void mirageDragon(MonsterCard activeCard) {
        if (!activeCard.isGoingToGraveyard) {
            if (User.getUserByUsername(activeCard.getOwnerUsername()) == round.getFirstPlayer().getUser()) {
                round.getSecondPlayer().setAbleToActivateTrapCard(false);
                return;
            } else {
                round.getFirstPlayer().setAbleToActivateTrapCard(false);
                return;
            }
        } else {
            if (User.getUserByUsername(activeCard.getOwnerUsername()) == round.getFirstPlayer().getUser()) {
                round.getSecondPlayer().setAbleToActivateTrapCard(true);
                activeCard.setGoingToGraveyard(false);
                return;
            } else {
                round.getFirstPlayer().setAbleToActivateTrapCard(true);
                activeCard.setGoingToGraveyard(false);
                return;
            }
        }
    }

    public void yomiShip(MonsterCard yomiShip, MonsterCard attacker) {
        if (yomiShip.isGoingToGraveyard()) {
            Player attackerPlayer = round.getPlayerByTurn();
            attackerPlayer.addCardToGraveyard(attacker);
            attackerPlayer.removeCardFromCardsInZone(attacker, attackerPlayer.getLocationOfThisMonsterCardInZone(attacker));
        }
    }

    public boolean ritualSummoned (MonsterCard card) {
        HashMap<Integer, Card> cards = round.getPlayerByTurn().getSpellOrTrapCardsInZone();
        for (Map.Entry<Integer, Card> mapElement : cards.entrySet()) {
            if (((SpellCard) mapElement.getValue()).getEffect() == SpellEffect.ADVANCED_RITUAL_ART) {
                return ritualSummonProcedure(card);
            }
        }
        view.printError("there is no Advanced Ritual Art card in field to be used for special summon");
        return false;
    }

    private boolean ritualSummonProcedure(MonsterCard card) {
        ArrayList<Integer> locations = new ArrayList<>();
        HashMap<Integer, MonsterCard> cards = round.getPlayerByTurn().getMonsterCardsInZone();
        int location = 10;
        int levelSum = 0;
        view.printError("enter location of cards that you want to tribute for the ritual summon (sum of levels of selected cards must either be equal or greater than the target card level)");
        view.printError("to end the selection enter 0");
        while (true) {
            location = view.intScanner();
            if (location < 1 || location > 5) {
                view.printError("invalid location");
            } else if (cards.get(location) == null) {
                view.printError("no card in selected location");
            } else if (locations.contains(location)) {
                view.printError("repeated location");
            } else {
                locations.add(location);
                levelSum += cards.get(location).getLevel();
            }

            if(location == 0)
                return false;

            if(levelSum >= card.getLevel()){
                for (Integer integer : locations) {
                    round.getPlayerByTurn().removeCardFromCardsInZone(round.getPlayerByTurn().getMonsterCardByLocationFromZone(integer) , integer);
                    round.getPlayerByTurn().addCardToGraveyard(cards.get(integer));
                }
            }
        }
    }
}

