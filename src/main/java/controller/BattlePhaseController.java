package controller;

import model.BattlePhase;
import model.MonsterCard;
import model.Player;
import view.BattlePhaseView;

public class BattlePhaseController extends PhaseController {

    private BattlePhaseView battleView = new BattlePhaseView(this);
    public BattlePhaseController(BattlePhase battlePhase) {
        super(battlePhase);
    }

    protected void controlSummonCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            battleView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromHand() || !(player.getSelectedCard() instanceof MonsterCard)) {
            battleView.canNotSummonCard();
        } else{
            battleView.actionNotAllowedInThisPhase();
        }
    }

    protected void controlSetCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            battleView.noCardSelectedYet();
        } else if(!player.isSelectedCardFromHand()){
            battleView.canNotSetCard();
        } else {
            battleView.canNotDoThisActionInThisPhase();
        }
    }

    protected void controlSetPositionAttackCommand() {
        controlSetPositionAttackOrDefenseOrFlipSummonCommand();
    }

    protected void controlSetPositionDefenseCommand() {
        controlSetPositionAttackOrDefenseOrFlipSummonCommand();
    }

    protected void controlFlipSummonCommand() {
        controlSetPositionAttackOrDefenseOrFlipSummonCommand();
    }

    private void controlSetPositionAttackOrDefenseOrFlipSummonCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            battleView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            battleView.canNotChangeCardPosition();
        } else {
            battleView.canNotDoThisActionInThisPhase();
        }
    }

    protected void controlAttackDirectCommand() {
        Player player = phase.getPlayerByTurn();
        Player rivalPlayer = phase.getRivalPlayerByTurn();
        if(!player.hasSelectedCard()){
            battleView.noCardSelectedYet();
        } else if(!player.isSelectedCardFromMonsterCardZone()){
            battleView.canNotAttackWithThisCard();
        } else if(player.getSelectedCard() instanceof MonsterCard && ((MonsterCard) player.getSelectedCard()).hasBattledInBattlePhase()){
            battleView.thisCardAlreadyAttacked();
        } else if(rivalPlayer.getMonsterCardsInZone().size() > 0){
            battleView.canNotAttackDirectly();
        } else if(phase instanceof BattlePhase){
            int damage = ((BattlePhase) phase).attackDirect();
            battleView.attackDirectResult(damage);
        }
    }

    protected void controlActivateEffectCommand() {

    }

    protected void controlAttackToCardCommand(int location) {

    }
}
