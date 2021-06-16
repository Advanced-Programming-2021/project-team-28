package controller;

import enums.MonsterCardPosition;
import enums.RecentActionsInGame;
import enums.SpellOrTrapCardPosition;
import model.*;
import view.MainPhaseView;
import view.ScannerInstance;

import java.util.Scanner;

import static enums.MonsterCardPosition.*;

public class MainPhaseController extends PhaseController {

    protected boolean isSummonOrSetMonsterCard;
    MonsterPowers monsterPowers;

    public MainPhaseController(MainPhase mainPhase) {
        super(mainPhase);
        this.isSummonOrSetMonsterCard = false;
        for (MonsterCard card : phase.getPlayerByTurn().getMonsterCardsInZone().values()) {
            card.setPositionChangedInThisTurn(false);
            card.setSummonedInThisTurn(false);
        }
        monsterPowers = new MonsterPowers(phase);
    }

    private MainPhaseView mainPhaseView = new MainPhaseView(this);

    @Override
    protected void controlSummonCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            mainPhaseView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromHand() || !(player.getSelectedCard() instanceof MonsterCard)) {
            mainPhaseView.canNotSummonCard();
        } else if (phase.getPlayerByTurn().isMonsterCardZoneFull()) {
            mainPhaseView.monsterZoneIsFull();
        } else if (isSummonOrSetMonsterCard) {
            mainPhaseView.printString("you already summoned/set on this turn");
        } else if (((MonsterCard) player.getSelectedCard()).getLevel() <= 4) {
            summonMonsterCard(player, (MonsterCard) player.getSelectedCard());
        } else controlTributeSummonOrSet(player, true);
    }

    protected void controlTributeSummonOrSet(Player player, boolean isForSummon) {
        int tributeNeeded;
        if (((MonsterCard) player.getSelectedCard()).getLevel() <= 6) {
            tributeNeeded = 1;
        } else tributeNeeded = 2;
        if (player.getMonsterCardsInZone().size() < tributeNeeded) {
            mainPhaseView.printString("there are not enough cards for tribute");
        } else {
            Scanner scanner = ScannerInstance.getInstance().getScanner();
            try{
                if (tributeNeeded == 1) {
                    payOneTribute(player, isForSummon, scanner);
                } else {
                    payTwoTributes(player, isForSummon, scanner);
                }
            } catch (NumberFormatException exception) {
                view.invalidLocation();
            }

        }

    }

    private void payTwoTributes(Player player, boolean isForSummon, Scanner scanner) {
        int firstTributeAddress;
        int secondTributeAddress;
        mainPhaseView.chooseMonsterLocationForTribute(2);
        while(true){
            firstTributeAddress = Integer.parseInt(scanner.nextLine());
            secondTributeAddress = Integer.parseInt(scanner.nextLine());
            if(firstTributeAddress != secondTributeAddress) {
                break;
            }
            mainPhaseView.pleaseChooseTwoDifferentMonsters();
        }
        if (!player.doesHaveMonsterCardInThisLocation(firstTributeAddress)
                || !player.doesHaveMonsterCardInThisLocation(secondTributeAddress)) {
            mainPhaseView.printString("there is no monster on one of these addresses");
        } else {
            player.addCardToGraveyard(player.getMonsterCardsInZone().get(firstTributeAddress));
            player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(firstTributeAddress), firstTributeAddress);
            player.addCardToGraveyard(player.getMonsterCardsInZone().get(secondTributeAddress));
            player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(secondTributeAddress), secondTributeAddress);
            if(isForSummon){
                summonMonsterCard(player, (MonsterCard) player.getSelectedCard());
            } else {
                setMonsterCard(player, (MonsterCard) player.getSelectedCard());
            }
        }
    }

    private void payOneTribute(Player player, boolean isForSummon, Scanner scanner) {
        mainPhaseView.chooseMonsterLocationForTribute(1);

        int tributeAddress = Integer.parseInt(scanner.nextLine());
        if (!player.doesHaveMonsterCardInThisLocation(tributeAddress)) {
            mainPhaseView.printString("there is no monster on one of these addresses");
        } else {
            player.addCardToGraveyard(player.getMonsterCardsInZone().get(tributeAddress));
            player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(tributeAddress), tributeAddress);
            if(isForSummon){
                summonMonsterCard(player, (MonsterCard) player.getSelectedCard());
            } else {
                setMonsterCard(player, (MonsterCard) player.getSelectedCard());
            }
        }
    }

    public void summonMonsterCard(Player player, MonsterCard card) {
        card.setSummoned(true);
        monsterPowers.run(card, null);
        card.setPositionChangedInThisTurn(true);
        card.setPosition(OFFENSIVE_OCCUPIED);
        player.addCardToCardsInZone(card);
        player.removeCardFromHand(card);
        checkRivalActionsAfterSummon(card);
        if (card.isCardActionCanceledByAnEffect()) {
            card.setCardActionCanceledByAnEffect(false);
            player.setSelectedCard(null);
            return;
        }
        player.setSelectedCard(null);
        this.isSummonOrSetMonsterCard = true;
        mainPhaseView.printString("summoned successfully");
    }

    private void checkRivalActionsAfterSummon(MonsterCard card) {
        if (card.isSpecialSummoned()) {
            checkForPossibleSpellOrTrapEffect(card, null,
                    RecentActionsInGame.SPECIAL_SUMMONED);
            card.setSpecialSummoned(false);
        } else if (card.getAttackPoint() < 1000) {
            checkForPossibleSpellOrTrapEffect(card, null,
                    RecentActionsInGame.SUMMONED_A_MONSTER_WITH_LESS_THAN_1000_ATTACK_POINT);
        } else {
            checkForPossibleSpellOrTrapEffect(card, null,
                    RecentActionsInGame.SUMMONED_A_MONSTER_WITH_1000_OR_MORE_ATTACK_POINT);
        }
    }

    @Override
    protected void controlSetCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            mainPhaseView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromHand()) {
            mainPhaseView.canNotSetCard();
        }
        if (player.getSelectedCard() instanceof MonsterCard) {
            controlSetMonsterCard(player, (MonsterCard) player.getSelectedCard());
        } else setTrapOrSpellCard(player, player.getSelectedCard());
    }

    protected void controlSetMonsterCard(Player player, MonsterCard card) {
        if (player.isMonsterCardZoneFull()) {
            mainPhaseView.monsterZoneIsFull();
        } else if (isSummonOrSetMonsterCard) {
            mainPhaseView.printString("you already summoned/set on this turn");
        } else if (((MonsterCard) player.getSelectedCard()).getLevel() <= 4) {
            setMonsterCard(player, ((MonsterCard) player.getSelectedCard()));
        } else controlTributeSummonOrSet(player, false);
    }

    protected void setMonsterCard(Player player, MonsterCard card) {
        //nothing to save this card has been set?!
        card.setPosition(DEFENSIVE_HIDDEN);
        player.addCardToCardsInZone(card);
        player.removeCardFromHand(card);
        card.setSummonedInThisTurn(true);
        player.setSelectedCard(null);
        this.isSummonOrSetMonsterCard = true;
        mainPhaseView.printString("set successfully");
    }

    protected void setTrapOrSpellCard(Player player, Card card) {
        if (!player.hasSelectedCard()) {
            mainPhaseView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromHand()) {
            mainPhaseView.canNotSetCard();
        } else if (player.isSpellCardZoneFull()) {
            mainPhaseView.printString("spell card zone is full");
        } else {
            if (card instanceof TrapCard) {
                ((TrapCard) card).setPosition(SpellOrTrapCardPosition.HIDDEN);
                ((TrapCard) card).setHasSetInThisTurn(true);
            } else if (card instanceof SpellCard) {
                ((SpellCard) card).setPosition(SpellOrTrapCardPosition.HIDDEN);
            }
            player.addCardToCardsInZone(card);
            player.removeCardFromHand(card);
            player.setSelectedCard(null);
            mainPhaseView.printString("set successfully");
        }
    }


    protected void controlChangeMonsterCardPosition() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            mainPhaseView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            mainPhaseView.canNotChangeCardPosition();
        }
    }


    @Override
    protected void controlSetPositionAttackCommand() {

        changePositionHandler(OFFENSIVE_OCCUPIED);
    }

    @Override
    protected void controlSetPositionDefenseCommand() {
        changePositionHandler(DEFENSIVE_OCCUPIED);
    }

    private void changePositionHandler(MonsterCardPosition position) {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            mainPhaseView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            mainPhaseView.canNotChangeCardPosition();
        } else if (((MonsterCard) player.getSelectedCard()).getPosition() == position) {
            mainPhaseView.printString("this card is already in the wanted position");
        } else if (((MonsterCard) player.getSelectedCard()).isPositionChangedInThisTurn()) {

            mainPhaseView.printString("you already changed this card position in this turn");
        } else {
            ((MonsterCard) player.getSelectedCard()).setPosition(position);
            ((MonsterCard) player.getSelectedCard()).setPositionChangedInThisTurn(true);
            mainPhaseView.printString("monster card position changed successfully");
        }
    }

    @Override
    protected void controlFlipSummonCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            mainPhaseView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            mainPhaseView.canNotChangeCardPosition();
        } else if (((MonsterCard) player.getSelectedCard()).isSummonedInThisTurn()
                || ((MonsterCard) player.getSelectedCard()).getPosition() != DEFENSIVE_HIDDEN) {
            mainPhaseView.printString("you can’t flip summon this card");
        } else {
            MonsterCard flipSummonedCard = (MonsterCard) player.getSelectedCard();
            flipSummonedCard.setPosition(OFFENSIVE_OCCUPIED);
            flipSummonedCard.setPositionChangedInThisTurn(true);
            flipSummonedCard.setFlipped(true);
            if (flipSummonedCard.getAttackPoint() < 1000) {
                checkForPossibleSpellOrTrapEffect(flipSummonedCard, null,
                        RecentActionsInGame.SUMMONED_A_MONSTER_WITH_LESS_THAN_1000_ATTACK_POINT);
            } else {
                checkForPossibleSpellOrTrapEffect(flipSummonedCard, null,
                        RecentActionsInGame.SUMMONED_A_MONSTER_WITH_1000_OR_MORE_ATTACK_POINT);
            }
            player.setSelectedCard(null);
            mainPhaseView.printString("flip summoned successfully");
        }
    }

    @Override
    protected void controlAttackDirectCommand() {
        controlAttackCommand();
    }

    @Override
    protected void controlAttackToCardCommand(int location) {
        controlAttackCommand();
    }

    @Override
    protected void controlActivateEffectCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            view.noCardSelectedYet();
            return;
        }
        if (player.isSelectedCardFromSpellAndTrapZone() && player.getSelectedCard() instanceof TrapCard) {
            if(((TrapCard) player.getSelectedCard()).hasSetInThisTurn()){
                mainPhaseView.canNotActivateCardInThisTurn();
            } else {
                if (canCardBeActivatedAfterThisAction(player, RecentActionsInGame.IN_OUR_MAIN_PHASE, player.getSelectedCard())) {
                    TrapEffectController.searchForThisEffect(this, phase, null, (TrapCard) player.getSelectedCard());
                    view.spellOrTrapActivated("Trap");
                } else {
                    view.preparationsOfSpellHaveNotBeenDoneYet();
                }
            }
        }
    }

    private void controlAttackCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            view.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            view.canNotAttackWithThisCard();
        } else {
            view.canNotDoThisActionInThisPhase();
        }
    }
}
