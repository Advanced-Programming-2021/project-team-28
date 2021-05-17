package view;

import model.MonsterPowers;
import model.SpellEffects;

import java.util.Scanner;

public class SpellEffectsView {
    private SpellEffects controller;

    private Scanner scanner = ScannerInstance.getInstance().getScanner();

    public SpellEffectsView(SpellEffects controller){
        this.controller = controller;
    }

    public String scanString(){
        return scanner.nextLine();
    }

    public void selectSpellZoneYouWantToSelectCardFrom(){
        System.out.println("Select spell zone you want to select card from\n1. Your Spell zone   2. Opponent's Spell zone");
    }

    public void selectLocationOfCardYouWantToDestroy(){
        System.out.println("Select location of card you want to destroy");
    }

    public void cardSentToGraveyardSuccessfully(){
        System.out.println("Card sent to the graveyard successfully");
    }

    public void invalidChoice(){
        System.out.println("invalid choice");
    }

    public void invalidLocation(){
        System.out.println("invalid location");
    }

    public void thereIsNoCardInThisLocation(){
        System.out.println("There is no card in this location");
    }

    public void whichGraveyard(){
        System.out.println("which graveyard do you want to use (for yours enter \"y\" for opponent's enter \"o\")");
    }

    public void selectCardFromGraveyard(){
        System.out.println("which Card do you want to select (enter the number)");
    }

    public int scanNumber(){
        return scanner.nextInt();
    }

    public void thisIsNotMonsterCard(){
        System.out.println("this is not a monster card");
    }
}
