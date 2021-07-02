package org.view;

import org.controller.DeckMenuController;
import org.controller.MenuEnum;

import java.util.Scanner;

public class DeckMenuView {
    DeckMenuController controller;

    public DeckMenuView (DeckMenuController controller){
        this.controller = controller;
    }
    public void run(){
        String command;
        Scanner scanner = ScannerInstance.getInstance().getScanner();
        while(true){
            command = scanner.nextLine();
            if(controller.processCommand(command).equals(MenuEnum.BACK)){
                return;
            }
        }
    }

    public void thisDeckAlreadyExists (String deckName){
        System.out.println("deck with name " + deckName + " already exists");
    }

    public void deckCreated (){
        System.out.println("deck created successfully!");
    }

    public void deckDoesNotExist(String deckName){
        System.out.println("deck with name " + deckName + " does not exist");
    }

    public void deckDeleted(){
        System.out.println("deck deleted successfully");
    }

    public void deckActivated(){
        System.out.println("deck activated successfully");
    }

    public void print(String string){
        System.out.println(string);
    }

    public void showCurrentMenu(){
        System.out.println("Deck Menu");
    }

    public void cardDoesNotExist (String cardName){
        System.out.println("card with name " + cardName + " does not exist");
    }

    public void mainOrSideDeckIsFull(boolean isSideDeckFull){
        String mainOrSide = isSideDeckFull ? "Side" : "Main";
        System.out.println(mainOrSide + " deck is full");
    }

    public void threeSimilarCardInADeck(String cardName, String deckName){
        System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
    }

    public void cardAddedToDeck (){
        System.out.println("card added to deck successfully");
    }

    public void cardDoesNotExistInSideOrMainDeck(String cardName, boolean isInSideDeck){
        String mainOrSide = isInSideDeck ? "Side" : "Main";
        System.out.println("card with name " + cardName + " does not exist in " + mainOrSide + " deck");
    }

    public void cardRemovedFromDeck (){
        System.out.println("card removed from deck successfully");
    }

    public void impossibleMenuNavigation() {
        System.out.println("menu navigation is not possible");
    }

    public void invalidCommand(){
        System.out.println("invalid command");
    }
}
