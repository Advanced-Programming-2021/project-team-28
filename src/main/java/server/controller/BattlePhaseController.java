package server.controller;

import org.model.BattlePhase;
import org.model.Card;
import org.model.MonsterCard;
import org.model.Player;
import org.model.enums.MonsterCardPosition;
import org.model.enums.RecentActionsInGame;
import org.view.BattlePhaseView;
import org.view.GameView;

public class BattlePhaseController extends PhaseController {
    GameView gameView;

    private BattlePhaseView battleView = new BattlePhaseView(this);

    public BattlePhaseController(BattlePhase battlePhase) {
        super(battlePhase);
    }

    public BattlePhaseController(BattlePhase battlePhase, GameView view){
        super(battlePhase);
        this.gameView = view;
    }

    public GameView getGameView() {
        return gameView;
    }



    protected void controlSummonCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromHand() || !(player.getSelectedCard() instanceof MonsterCard)) {
            gameView.canNotSummonCard();
        } else {
            gameView.actionNotAllowedInThisPhase();
        }
    }

    protected void controlSetCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromHand()) {
            gameView.canNotSetCard();
        } else {
            gameView.canNotDoThisActionInThisPhase();
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
            gameView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            gameView.printString("Can not change this card's position");
        } else {
            gameView.canNotDoThisActionInThisPhase();
        }
    }

    protected void controlAttackDirectCommand() {
        Player player = phase.getPlayerByTurn();
        Player rivalPlayer = phase.getRivalPlayerByTurn();
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            gameView.canNotAttackWithThisCard();
        } else if (player.getSelectedCard() instanceof MonsterCard && ((MonsterCard) player.getSelectedCard()).getPosition() != MonsterCardPosition.OFFENSIVE_OCCUPIED) {
            gameView.canNotAttackWithThisCard();
        } else if (player.getSelectedCard() instanceof MonsterCard && ((MonsterCard) player.getSelectedCard()).hasBattledInBattlePhase()) {
            gameView.thisCardAlreadyAttacked();
        } else if (rivalPlayer.getMonsterCardsInZone().size() > 0) {
            gameView.canNotAttackDirectly();
        } else if (phase.getTurnsPlayed() < 1) {
            gameView.youCanNotAttackInYourFirstTurn();
        } else if (phase instanceof BattlePhase) {
            Card attackerCard = player.getSelectedCard();
            checkForPossibleSpellOrTrapEffect(attackerCard, null, RecentActionsInGame.RIVAL_DECLARED_A_BATTLE);
            if (((MonsterCard) attackerCard).isCardActionCanceledByAnEffect()) {
                ((MonsterCard) attackerCard).setCardActionCanceledByAnEffect(false);
                return;
            }
            int damage = ((BattlePhase) phase).attackDirect((MonsterCard) attackerCard);
            runAllMonsterPowersInZone(player);
            gameView.attackDirectResult(damage);
            player.setSelectedCard(null);
        }
    }

    protected void controlActivateEffectCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
        } else if (player.getSelectedCard() instanceof MonsterCard) {
            gameView.printString("Activation is only for spell/trap cards");
        } else {
            gameView.canNotActivateOnThisTurn();
        }
    }

    protected void controlAttackToCardCommand(int location) {
        Player player = phase.getPlayerByTurn();
        Player rivalPlayer = phase.getRivalPlayerByTurn();
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            gameView.canNotAttackWithThisCard();
        } else if (player.getSelectedCard() instanceof MonsterCard && ((MonsterCard) player.getSelectedCard()).hasBattledInBattlePhase()) {
            gameView.thisCardAlreadyAttacked();
        } else if (!rivalPlayer.doesHaveMonsterCardInThisLocation(location)) {
            gameView.thereIsNoCardToAttackHere();
        } else if (phase.getTurnsPlayed() < 1) {
            gameView.youCanNotAttackInYourFirstTurn();
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
            gameView.printString(((BattlePhase) phase).attackToCardAndReturnAttackReport(location, monsterPowers,(MonsterCard) attackerCard));
            runAllMonsterPowersInZone(player);
            runAllMonsterPowersInZone(rivalPlayer);
            player.setSelectedCard(null);
        }
    }

}
