package org.view;

import org.controller.MainPhaseController;

public class MainPhaseView extends PhaseView {

    public MainPhaseView(MainPhaseController mainPhaseController) {
        super(mainPhaseController);
    }

    public void chooseMonsterLocationForTribute(int numOfLocations) {
        System.out.println("Please choose " + numOfLocations + " location(s) to tribute");
    }

    public void pleaseChooseTwoDifferentMonsters() {
        System.out.println("Please choose two different locations");
    }

    public void canNotChangeDefensiveHiddenPosition(){
        System.out.println("You can't change defensive hidden card with this command. You should flip summon it");
    }

    public void selectedCardIsMonster() {
        System.out.println("activate effect is only for spell/trap cards.");
    }

    public void effectAlreadyActivated() {
        System.out.println("you have already activated this card");
    }

    public void spellActivated() {
        System.out.println("spell activated");
    }

    public void noWayToSpecialSummon() {
        System.out.println("there is no way you could special summon a monster");
    }

    public void activationCancelled() {
        System.out.println("Activation cancelled");
    }
}
