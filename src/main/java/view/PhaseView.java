package view;

import controller.PhaseController;
import enums.MenuEnum;

import java.util.Scanner;

public class PhaseView {

    PhaseController controller;

    public PhaseView(PhaseController controller) {
        this.controller = controller;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        String command;
        while(true){
            command = scanner.nextLine();
            if(controller.processCommand(command).equals(MenuEnum.BACK)){
                return;
            }
        }

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
}
