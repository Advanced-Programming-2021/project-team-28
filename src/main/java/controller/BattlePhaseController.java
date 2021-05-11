package controller;

import enums.MonsterCardPosition;
import model.BattlePhase;
import model.MonsterCard;
import model.Player;
import model.SpellCard;
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
        } else {
            battleView.actionNotAllowedInThisPhase();
        }
    }

    protected void controlSetCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            battleView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromHand()) {
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
        if (!player.hasSelectedCard()) {
            battleView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            battleView.canNotAttackWithThisCard();
        } else if (player.getSelectedCard() instanceof MonsterCard && ((MonsterCard) player.getSelectedCard()).getPosition() != MonsterCardPosition.OFFENSIVE_OCCUPIED) {
            battleView.canNotAttackWithThisCard();
        } else if (player.getSelectedCard() instanceof MonsterCard && ((MonsterCard) player.getSelectedCard()).hasBattledInBattlePhase()) {
            battleView.thisCardAlreadyAttacked();
        } else if (rivalPlayer.getMonsterCardsInZone().size() > 0) {
            battleView.canNotAttackDirectly();
        } else if (phase.getTurnsPlayed() < 2) {
            battleView.youCanNotAttackInYourFirstTurn();
        } else if (phase instanceof BattlePhase) {
            int damage = ((BattlePhase) phase).attackDirect();
            battleView.attackDirectResult(damage);
            battleView.printString(phase.getMapToString());
            player.setSelectedCard(null);
        }
    }

    protected void controlActivateEffectCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            battleView.noCardSelectedYet();
        } else if (!(player.getSelectedCard() instanceof SpellCard)) {
            battleView.activateEffectIsForSpellCards();
        } else {
            battleView.canNotActivateOnThisTurn();
        }
    }

    protected void controlAttackToCardCommand(int location) {
        Player player = phase.getPlayerByTurn();
        Player rivalPlayer = phase.getRivalPlayerByTurn();
        if (!player.hasSelectedCard()) {
            battleView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            battleView.canNotAttackWithThisCard();
        } else if (player.getSelectedCard() instanceof MonsterCard && ((MonsterCard) player.getSelectedCard()).hasBattledInBattlePhase()) {
            battleView.thisCardAlreadyAttacked();
        } else if (!rivalPlayer.doesHaveMonsterCardInThisLocation(location)) {
            battleView.thereIsNoCardToAttackHere();
        } else if (phase.getTurnsPlayed() < 2) {
            battleView.youCanNotAttackInYourFirstTurn();
        } else {
            battleView.printString(((BattlePhase) phase).attackToCardAndReturnAttackReport(location));
            battleView.printString(phase.getMapToString());
            player.setSelectedCard(null);
        }
    }
}
