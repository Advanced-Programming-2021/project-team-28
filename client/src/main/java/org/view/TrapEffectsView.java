package org.view;

import java.util.Scanner;

public class TrapEffectsView {
    Scanner scanner = ScannerInstance.getInstance().getScanner();

    public String scanString(){
        return scanner.nextLine();
    }

    public void enterCardName (){
        System.out.print("Enter card name: ");
    }

    public void invalidCardName(){
        System.out.println("Invalid card name. enter again");
    }

    public void youDoNotHaveThisCardInGraveYard(){
        System.out.println("You don't have this card in graveyard");
    }

    public void thisCardIsNotAMonster(){
        System.out.println("This card is not a monster");
    }

    public void yourMonsterCardZoneIsFull(){
        System.out.println("You can't activate this card. because your monster card zone is full");
    }

    public void yourCardsInGraveyard(){
        System.out.println("Your cards in graveyard: ");
    }

    public void thereIsNoCardInThisLocation() {
        System.out.println("There is no card in this location");
    }

    public void invalidLocation() {
        System.out.println("Invalid location");
    }

    public void enterCardLocationInHand() {
        System.out.println("Enter the location number of card you want to send to graveyard");
    }
}
