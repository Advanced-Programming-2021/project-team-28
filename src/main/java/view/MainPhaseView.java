package view;

import controller.MainPhaseController;
import controller.PhaseController;

public class MainPhaseView extends PhaseView {

    public MainPhaseView(MainPhaseController mainPhaseController) {
        super(mainPhaseController);
    }

    public void canNotActivateCardInThisTurn(){
        System.out.println("This card can't be activated in the turn it was set");
    }

    public void chooseMonsterLocationForTribute(int numOfLocations) {
        System.out.println("Please choose " + numOfLocations + " location(s) to tribute");
    }

    public void pleaseChooseTwoDifferentMonsters() {
        System.out.println("Please choose two different locations");
    }
}
