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
}
