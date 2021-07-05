package org.controller;

import org.model.enums.MonsterCardPosition;
import org.model.enums.RecentActionsInGame;
import org.model.*;
import org.view.BattlePhaseView;

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
        } else if (phase.getTurnsPlayed() < 1) {
            battleView.youCanNotAttackInYourFirstTurn();
        } else if (phase instanceof BattlePhase) {
            Card attackerCard = player.getSelectedCard();
            checkForPossibleSpellOrTrapEffect(attackerCard, null, RecentActionsInGame.RIVAL_DECLARED_A_BATTLE);
            if (((MonsterCard) attackerCard).isCardActionCanceledByAnEffect()) {
                ((MonsterCard) attackerCard).setCardActionCanceledByAnEffect(false);
                return;
            }
            int damage = ((BattlePhase) phase).attackDirect((MonsterCard) attackerCard);
            runAllMonsterPowersInZone(player);
            battleView.attackDirectResult(damage);
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
        } else if (phase.getTurnsPlayed() < 1) {
            battleView.youCanNotAttackInYourFirstTurn();
        } else {
            Card attackerCard = player.getSelectedCard();
            MonsterCard defenderCard = rivalPlayer.getMonsterCardsInZone().get(location);
            if(defenderCard.getPosition() == MonsterCardPosition.DEFENSIVE_HIDDEN){
                defenderCard.setPosition(MonsterCardPosition.DEFENSIVE_OCCUPIED);
                defenderCard.setFlipped(true);
            }
            monsterPowers.run(defenderCard, (MonsterCard) attackerCard, 0);
            defenderCard.setFlipped(false);
            checkForPossibleSpellOrTrapEffect(attackerCard, defenderCard, RecentActionsInGame.RIVAL_DECLARED_A_BATTLE);
            if (((MonsterCard) attackerCard).isCardActionCanceledByAnEffect()) {
                ((MonsterCard) attackerCard).setCardActionCanceledByAnEffect(false);
                return;
            }
            battleView.printString(((BattlePhase) phase).attackToCardAndReturnAttackReport(location, monsterPowers,(MonsterCard) attackerCard));
            runAllMonsterPowersInZone(player);
            runAllMonsterPowersInZone(rivalPlayer);
            player.setSelectedCard(null);
        }
    }

}