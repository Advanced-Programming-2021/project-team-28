package view;

import controller.BattlePhaseController;

public class BattlePhaseView extends PhaseView {
    public BattlePhaseView(BattlePhaseController controller) {
        super(controller);
    }

    public void actionNotAllowedInThisPhase(){
        System.out.println("action not allowed in this phase");
    }

    public void thisCardAlreadyAttacked(){
        System.out.println("this card already attacked");
    }

    public void canNotAttackDirectly(){
        System.out.println("you can’t attack the opponent directly");
    }

    public void attackDirectResult(int damage){
        System.out.println("your opponent receives " + damage + " battle damage");
    }

    public void canNotActivateOnThisTurn(){
        System.out.println("you can’t activate an effect on this turn");
    }

    public void thereIsNoCardToAttackHere(){
        System.out.println("there is no card to attack here");
    }

    public void youCanNotAttackInYourFirstTurn(){
        System.out.println("You can't attack in your first turn");
    }
}