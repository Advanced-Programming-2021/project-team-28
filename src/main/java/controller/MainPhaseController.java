package controller;

import enums.MonsterCardPosition;
import model.*;
import view.MainPhaseView;

import java.util.Scanner;

import static enums.MonsterCardPosition.*;

public class MainPhaseController extends PhaseController {

    protected boolean isSummonOrSetMonsterCard;

    public MainPhaseController(MainPhase mainPhase) {
        super(mainPhase);
        this.isSummonOrSetMonsterCard = false;
        for (MonsterCard card: phase.getPlayerByTurn().getMonsterCardsInZone().values()){
            card.setPositionChangedInThisTurn(false);
            card.setSummonedInThisTurn(false);
        }
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
        } else if (!isSummonOrSetMonsterCard) {
            mainPhaseView.printString("you already summoned/set on this turn");
        } else if (((MonsterCard) player.getSelectedCard()).getLevel() <= 4) {
            summonMonsterCard(player, (MonsterCard) player.getSelectedCard());

        } else controlTributeSummon(player);
    }

    protected void controlTributeSummon(Player player) {
        int tributeNeeded;

        if (((MonsterCard) player.getSelectedCard()).getLevel() <= 6) {
            tributeNeeded = 1;
        } else tributeNeeded = 2;
        if (player.getMonsterCardsInZone().size() < tributeNeeded) {
            mainPhaseView.printString("there are not enough cards for tribute");
        } else {
            Scanner scanner = new Scanner(System.in);
            if (tributeNeeded == 1) {
                int tributeAddress = scanner.nextInt();
                if (!player.doesHaveMonsterCardInThisLocation(tributeAddress)) {
                    mainPhaseView.printString("there is no monster on one of these addresses");
                } else {
                    player.addCardToGraveyard(player.getMonsterCardsInZone().get(tributeAddress));
                    player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(tributeAddress), tributeAddress);
                    summonMonsterCard(player, (MonsterCard) player.getSelectedCard());

                }
            } else {
                int firstTributeAddress = scanner.nextInt();
                int secondTributeAddress = scanner.nextInt();
                if (!player.doesHaveMonsterCardInThisLocation(firstTributeAddress)
                        || !player.doesHaveMonsterCardInThisLocation(secondTributeAddress)) {
                    mainPhaseView.printString("there is no monster on one of these addresses");
                } else {
                    player.addCardToGraveyard(player.getMonsterCardsInZone().get(firstTributeAddress));
                    player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(firstTributeAddress), firstTributeAddress);
                    player.addCardToGraveyard(player.getMonsterCardsInZone().get(secondTributeAddress));
                    player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(secondTributeAddress), secondTributeAddress);
                    summonMonsterCard(player, (MonsterCard) player.getSelectedCard());
                }

            }
        }

    }

    protected void summonMonsterCard(Player player, MonsterCard card) {
        card.setSummoned(true);
        card.setPositionChangedInThisTurn(true);
        card.setPosition(OFFENSIVE_OCCUPIED);
        player.addCardToCardsInZone(card);
        player.removeCardFromHand(card);
        player.setSelectedCard(null);
        this.isSummonOrSetMonsterCard = true;

        mainPhaseView.printString("summoned successfully");
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
        } else controlTributeSet(player, card);
    }

    protected void controlTributeSet(Player player, MonsterCard card) {
        int tributeNeeded;

        if (((MonsterCard) player.getSelectedCard()).getLevel() <= 6) {
            tributeNeeded = 1;
        } else tributeNeeded = 2;
        if (player.getMonsterCardsInZone().size() < tributeNeeded) {
            mainPhaseView.printString("there are not enough cards for tribute");
        } else {
            Scanner scanner = new Scanner(System.in);
            if (tributeNeeded == 1) {
                int tributeAddress = scanner.nextInt();
                if (!player.doesHaveMonsterCardInThisLocation(tributeAddress)) {
                    mainPhaseView.printString("there is no monster on one of these addresses");
                } else {
                    player.addCardToGraveyard(player.getMonsterCardsInZone().get(tributeAddress));
                    player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(tributeAddress), tributeAddress);
                    setMonsterCard(player, (MonsterCard) player.getSelectedCard());

                }
            } else {
                int firstTributeAddress = scanner.nextInt();
                int secondTributeAddress = scanner.nextInt();
                if (!player.doesHaveMonsterCardInThisLocation(firstTributeAddress)
                        || !player.doesHaveMonsterCardInThisLocation(secondTributeAddress)) {
                    mainPhaseView.printString("there is no monster on one of these addresses");
                } else {
                    player.addCardToGraveyard(player.getMonsterCardsInZone().get(firstTributeAddress));
                    player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(firstTributeAddress), firstTributeAddress);
                    player.addCardToGraveyard(player.getMonsterCardsInZone().get(secondTributeAddress));
                    player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(secondTributeAddress), secondTributeAddress);
                    setMonsterCard(player, (MonsterCard) player.getSelectedCard());
                }

            }
        }
    }


    protected void setMonsterCard(Player player, MonsterCard card) {
        //nothing to save this card has been set?!
        card.setPosition(DEFENSIVE_HIDDEN);
        player.addCardToCardsInZone(card);
        player.removeCardFromHand(card);
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
        } else if (((MonsterCard)player.getSelectedCard()).isSummonedInThisTurn()
                || ((MonsterCard)player.getSelectedCard()).getPosition() != DEFENSIVE_HIDDEN){
            mainPhaseView.printString("you can’t flip summon this card");
        } else {
            ((MonsterCard) player.getSelectedCard()).setPosition(OFFENSIVE_OCCUPIED);
            ((MonsterCard) player.getSelectedCard()).setPositionChangedInThisTurn(true);
            player.setSelectedCard(null);
            mainPhaseView.printString("flip summoned successfully");
        }
    }

    @Override
    protected void controlAttackDirectCommand() {

    }

    @Override
    protected void controlActivateEffectCommand() {

    }

    @Override
    protected void controlAttackToCardCommand(int location) {

    }
}
