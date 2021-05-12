package model;

import enums.MonsterCardPosition;
import enums.MonsterPower;
import enums.Turn;
import view.MonsterPowerView;

import java.util.Map;

public class MonsterPowers {

    private MonsterPowerView view = new MonsterPowerView(this);

    Round round;

    MonsterPowers(Round round){
        this.round = round;
    }

    public void  run( MonsterCard activeCard , MonsterCard opponentCard){
        switch (activeCard.getSpecialPower()) {
            case NONE: return;
            case MAN_EATER_BUG:{
                manEaterBug(activeCard);
                return;
            }
            case MARSHMALLON:
            case SUIJIN:
            case SCANNER:
            case YOMI_SHIP:{
                yomiShip(activeCard , opponentCard);
                return;
            }
            case TEXCHANGER:
            case THE_TRICKY:
            case CRAB_TURTLE:
            case GATE_GUARDIAN:
            case MIRAGE_DRAGON:{
                mirageDragon(activeCard);
                return;
            }
            case COMMAND_KNIGHT:
            case SKULL_GUARDIAN:
            case THE_CALCULATOR:{
                theCalculator(activeCard);
                return;
            }
            case EXPLODER_DRAGON:
            case HERALD_OF_CREATION:
            case BEAST_KING_BARBAROS:
            case TERRATIGER_THE_EMPOWERED_WARRIOR:
        }
    }
    public void manEaterBug(MonsterCard manEaterBug){
        int opponentCardLocation;
        if(manEaterBug.isFlipped()){
            if(!round.getRivalPlayerByTurn().isMonsterCardZoneEmpty()){
                view.printError("please enter a location to destroy enemy card : ");
                opponentCardLocation = view.manEaterBug();
               if(opponentCardLocation < 1 || opponentCardLocation > 5){
                   view.printError("there is no place with this number");
                   return;
               }
               if(!round.getRivalPlayerByTurn().doesHaveMonsterCardInThisLocation(opponentCardLocation)){
                   view.printError("there is no card in selected zone");
                   return;
               }
               MonsterCard opponentCard = round.getRivalPlayerByTurn().getMonsterCardByLocationFromZone(opponentCardLocation);
               round.getRivalPlayerByTurn().addCardToGraveyard(opponentCard);
               round.getRivalPlayerByTurn().removeCardFromCardsInZone(opponentCard , opponentCardLocation);
            }
        }
        manEaterBug.setFlipped(false);
    }
    public void theCalculator(MonsterCard activeCard){
        int levelSum = 0;
        if(User.getUserByUsername(activeCard.getOwnerUsername()) == round.getFirstPlayer().getUser()){
          for(Map.Entry<Integer, MonsterCard> monsterZone  : round.getFirstPlayer().getMonsterCardsInZone().entrySet()){
            if(monsterZone.getValue().getPosition() == MonsterCardPosition.DEFENSIVE_OCCUPIED || monsterZone.getValue().getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED){
                levelSum += monsterZone.getValue().getLevel();
            }
          }
            activeCard.setAttackPoint(300 * levelSum);
        }
        else{
            for(Map.Entry<Integer, MonsterCard> monsterZone  : round.getSecondPlayer().getMonsterCardsInZone().entrySet()){
                if(monsterZone.getValue().getPosition() == MonsterCardPosition.DEFENSIVE_OCCUPIED || monsterZone.getValue().getPosition() == MonsterCardPosition.OFFENSIVE_OCCUPIED){
                    levelSum += monsterZone.getValue().getLevel();
                }
            }
            activeCard.setAttackPoint(300 * levelSum);
        }
    }

    public void mirageDragon(MonsterCard activeCard){
        if(!activeCard.isGoingToGraveyard) {
            if (User.getUserByUsername(activeCard.getOwnerUsername()) == round.getFirstPlayer().getUser()) {
                round.getSecondPlayer().setAbleToActivateTrapCard(false);
                return;
            } else {
                round.getFirstPlayer().setAbleToActivateTrapCard(false);
                return;
            }
        }
        else{
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

    public void yomiShip(MonsterCard yomiShip , MonsterCard attacker){
        if(yomiShip.isGoingToGraveyard()){
            Player attackerPlayer = round.getPlayerByTurn();
            attackerPlayer.addCardToGraveyard(attacker);
            attackerPlayer.removeCardFromCardsInZone(attacker , attackerPlayer.getLocationOfThisMonsterCardInZone(attacker) );
            }
        }
    }
