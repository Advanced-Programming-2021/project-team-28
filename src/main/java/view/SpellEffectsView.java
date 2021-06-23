package view;

import model.MonsterPowers;
import model.SpellEffects;

import java.util.Scanner;

public class SpellEffectsView {
    private SpellEffects controller;

    private Scanner scanner = ScannerInstance.getInstance().getScanner();

    public SpellEffectsView(SpellEffects controller) {
        this.controller = controller;
    }

    public String scanString() {
        return scanner.nextLine();
    }

    public int intScanner(){
        return Integer.parseInt(scanner.nextLine());
    }

    public void selectSpellZoneYouWantToSelectCardFrom() {
        System.out.println("Select spell zone you want to select card from\n1. Your Spell zone   2. Opponent's Spell zone");
    }

    public void selectLocationOfCardYouWantToDestroy() {
        System.out.println("Select location of card you want to destroy");
    }

    public void cardSentToGraveyardSuccessfully() {
        System.out.println("Card sent to the graveyard successfully");
    }

    public void invalidChoice() {
        System.out.println("invalid choice");
    }

    public void invalidLocation() {
        System.out.println("invalid location");
    }

    public void thereIsNoCardInThisLocation() {
        System.out.println("There is no card in this location");
    }

    public void whichGraveyard() {
        System.out.println("which graveyard do you want to use? (for yours enter \"y\" for opponent's enter \"o\")\n" + "You also can use this commands:\n" +
                "Cancel activating: \"cancel\"\n" +
                "Show your graveyard: \"show graveyard\"\n" +
                "Show opponent graveyard: \"show opponent graveyard\"");
    }

    public void selectCardFromGraveyard() {
        System.out.println("which Card do you want to select (enter the number)");
    }

    public int scanNumber() {
        try {
            return (Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException exception){
            return 0;
        }
    }

    public void thisIsNotMonsterCard() {
        System.out.println("this is not a monster card");
    }

    public void printString(String string) {
        System.out.println(string);
    }

    public void youShouldSpecialSummonRightNow() {
        System.out.println("you should special summon right now");
    }

    public void thisGraveyardIsEmpty(){
        System.out.println("This graveyard is empty. Choose another graveyard");
    }

    public void activationCancelled(){
        System.out.println("Activation cancelled");
    }

    public void enterMonsterPosition(){
        System.out.println("Choose monster position, Enter OO for Offensive Occupied OR DO for Defensive Occupied");
    }

    public void enterTargetPosition(){
        System.out.println("Enter the position of the monster you want to equip with this spell (enter \"0\" to terminate process):");
    }

    public void thisCardAlreadyHasAnEquipSpell(){
        System.out.println("this Card Already Has An Equip Spell");
    }

    public void theSelectedCardMustBeFiendOrSpellCaster() {
        System.out.println("you must select a fiend or spellCaster card in order to equip it with this spell");
    }

    public void youMustChooseAnOccupiedCard() {
        System.out.println("you must choose an occupied card (either defensive or offensive) in order to equip it with this card");
    }

    public void theSelectedCardMustBeWarrior() {
        System.out.println("you must select a warrior card in order to equip it with this spell");
    }
}
