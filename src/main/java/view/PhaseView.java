package view;

import controller.PhaseController;
import enums.MenuEnum;
import enums.PhaseName;
import model.BattlePhase;
import model.MainPhase;

import java.util.Scanner;

public class PhaseView {

    PhaseController controller;
    Scanner scanner = ScannerInstance.getInstance().getScanner();

    public PhaseView(PhaseController controller) {
        this.controller = controller;
    }

    public PhaseView(){

    }

    public void run(){
        printPhaseName();
        String command;
        while(true){
            command = scanner.nextLine();
            if(controller.processCommand(command).equals(MenuEnum.BACK)){
                return;
            }
        }

    }

    public void doYouWantToActiveSpellOrTrap(){
        System.out.print("do you want to activate your trap and spell?");
    }

    public String scanString(){
        return scanner.nextLine();
    }

    public void invalidLocation(){
        System.out.println("invalid location");
    }

    public void thereIsNoCardInThisLocation(){
        System.out.println("There is no card in this location");
    }

    public void invalidChoice(){
        System.out.println("invalid choice");
    }

    public void menuShowCurrent() {
        System.out.println("Duel Menu");
    }

    public void invalidCommand() {
        System.out.println("invalid command");
    }

    public void invalidSelection(){
        System.out.println("invalid selection");
    }

    public void printString(String string){
        System.out.println(string);
    }

    public void cardSelected(){
        System.out.println("card selected");
    }

    public void noCardFoundInPosition(){
        System.out.println("no card found in the given position");
    }

    public void noCardSelectedYet(){
        System.out.println("no card is selected yet");
    }

    public void cardDeselected(){
        System.out.println("card deselected");
    }

    public void canNotSummonCard(){
        System.out.println("you can’t summon this card");
    }

    public void canNotSetCard(){
        System.out.println("you can’t set this card");
    }

    public void canNotChangeCardPosition(){
        System.out.println("you can’t change this card position");
    }

    public void canNotDoThisActionInThisPhase(){
        System.out.println("you can’t do this action in this phase");
    }

    public void canNotAttackWithThisCard(){
        System.out.println("you can’t attack with this card");
    }

    public void activateEffectIsForSpellCards(){
        System.out.println("activate effect is only for spell cards.");
    }

    public void cardIsNotVisible(){
        System.out.println("card is not visible");
    }

    public void graveyardEmpty(){
        System.out.println("graveyard empty");
    }

    public void monsterZoneIsFull(){System.out.println("monster card zone is full");}

    public void thisCardCanNotBeActivated(){
        System.out.println("This card can't be activated");
    }

    public void preparationsOfSpellHaveNotBeenDoneYet(){
        System.out.println("preparations of this spell/trap are not done yet");
    }

    public void effectActivated(){
        System.out.println("Effect activated");
    }

    public void nowItWillBeRivalsTurn(String playerName){
        System.out.println("now it will be " + playerName + " 's turn");
    }

    public void canNotPlayThisKindOfMoves(){
        System.out.println("it’s not your turn to play this kind of moves");
    }

    public void spellOrTrapActivated(String spellOrTrap){
        System.out.println(spellOrTrap + " activated");
    }

    public void printPhaseName (){
        if(controller.getPhase() instanceof MainPhase){
            System.out.println("phase: Main Phase " + ((MainPhase) controller.getPhase()).getWhatMainPhase());
        } else if(controller.getPhase() instanceof BattlePhase){
            System.out.println("phase: Battle Phase");
        }
    }

    public void printPhaseName (PhaseName phaseName){
        System.out.println("phase: " + phaseName.getPhaseName());
    }

    public void cheatActivated() {
        System.out.println("Cheat activated");
    }

    public void itIsRivalTurnForEndPhase(String rivalUsername){
        System.out.println("its " + rivalUsername + " 's turn");
    }
}
