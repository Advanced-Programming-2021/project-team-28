package org.controller;

import org.model.enums.*;
import org.model.*;
import org.view.GameView;
import org.view.MainPhaseView;

import static org.model.enums.MonsterCardPosition.*;

public class MainPhaseController extends PhaseController {
    GameView gameView;

    protected boolean isSummonOrSetMonsterCard;

    SpellEffects spellEffects = new SpellEffects(phase.getRound(), this);


    public MainPhaseController(MainPhase mainPhase) {
        super(mainPhase);
        this.isSummonOrSetMonsterCard = false;
        for (MonsterCard card : phase.getPlayerByTurn().getMonsterCardsInZone().values()) {
            card.setPositionChangedInThisTurn(false);
            card.setSummonedInThisTurn(false);
        }
    }

    public MainPhaseController(MainPhase mainPhase, GameView view){
        super(mainPhase);
        this.isSummonOrSetMonsterCard = false;
        for (MonsterCard card : phase.getPlayerByTurn().getMonsterCardsInZone().values()){
            card.setPositionChangedInThisTurn(false);
            card.setSummonedInThisTurn(false);
        }
        this.gameView = view;
    }

    private MainPhaseView mainPhaseView = new MainPhaseView(this);

    public GameView getGameView() {
        return gameView;
    }

    public void setSummonOrSetMonsterCard(boolean summonOrSetMonsterCard) {
        isSummonOrSetMonsterCard = summonOrSetMonsterCard;
    }

    @Override
    protected void controlSummonCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromHand() || !(player.getSelectedCard() instanceof MonsterCard)) {
            gameView.canNotSummonCard();
        } else if ((((MonsterCard) player.getSelectedCard()).getLevel() <= 4 || player.getSelectedCard().getName().equals("The Tricky"))
                && player.isMonsterCardZoneFull()) {
            gameView.monsterZoneIsFull();
        } else if (isCardSpecial(player.getSelectedCard())) {
            specialSummon((MonsterCard) player.getSelectedCard());
        } else if (isSummonOrSetMonsterCard) {
            gameView.printString("you already summoned/set on this turn");
        } else if (((MonsterCard) player.getSelectedCard()).getSpecialPower() == MonsterPower.RITUAL) {
            ritualSummon((MonsterCard) player.getSelectedCard());
        } else if (((MonsterCard) player.getSelectedCard()).getLevel() <= 4) {
            summonMonsterCard(player, (MonsterCard) player.getSelectedCard(), OFFENSIVE_OCCUPIED);
        } else controlTributeSummonOrSet(player, true);
    }

    private void specialSummon(MonsterCard selectedCard) {
        if (selectedCard.getName().equals("The Tricky")) {
            int cardAddress;
            String cardLocationStr;
            while (true) {

                view.printString("for special summon this card you have to remove a card from your hand\n" +
                        "select that card, or enter \"cancel\"");
                cardLocationStr = view.scanString();
                if (cardLocationStr.equals("cancel"))
                    return;
                try {
                    cardAddress = Integer.parseInt(cardLocationStr);
                    if (cardAddress < 1 || cardAddress > phase.getPlayerByTurn().getCardsInHand().size() ||
                            phase.getPlayerByTurn().getCardsInHand().get(cardAddress - 1).equals(phase.getPlayerByTurn().getSelectedCard())) {
                        view.invalidLocation();
                        continue;
                    }
                } catch (NumberFormatException exception) {
                    mainPhaseView.invalidLocation();
                    continue;
                }
                summonWithTributeOneCardFromHand(cardAddress, selectedCard);
                return;
            }

        } else if (selectedCard.getName().equals("Gate Guardian")) {
            if (phase.getPlayerByTurn().getMonsterCardsInZone().size() < 3) {
                view.printString("You don't have enough Monster Cards in zone to tribute");
            } else {
                String position;
                while (true) {
                    view.printString("Enter OO for Offensive Occupied OR DO for Defensive Occupied");
                    position = view.scanString();
                    if (position.equals("OO")) {
                        payThreeTributes(phase.getPlayerByTurn(), true, selectedCard);
                        break;
                    } else if (position.equals("DO")) {
                        payThreeTributes(phase.getPlayerByTurn(), false, selectedCard);
                        break;
                    } else if (position.equals("cancel")){
                        return;
                    }
                    view.printString("invalid input");
                }
            }
        }

    }

    private void summonWithTributeOneCardFromHand(int tributeAddress, Card selectedCard) {
        String position;
        Player player = phase.getPlayerByTurn();
        boolean isIsSummonOrSetMonsterCardWasTrue = this.isSummonOrSetMonsterCard;
        while (true) {
            view.printString("Enter OO for Offensive Occupied OR DO for Defensive Occupied");
            position = view.scanString();
            if (position.equals("OO")) {
                player.addCardToGraveyard(player.getCardsInHand().get(tributeAddress - 1));
                player.removeCardFromHand(player.getCardsInHand().get(tributeAddress - 1));
                summonMonsterCard(phase.getPlayerByTurn(), (MonsterCard) selectedCard, OFFENSIVE_OCCUPIED);
                break;
            } else if (position.equals("DO")) {
                player.addCardToGraveyard(player.getCardsInHand().get(tributeAddress - 1));
                player.removeCardFromHand(player.getCardsInHand().get(tributeAddress - 1));
                summonMonsterCard(phase.getPlayerByTurn(), (MonsterCard) selectedCard, DEFENSIVE_OCCUPIED);
                break;
            }
            view.printString("invalid input");
        }
        if (!isIsSummonOrSetMonsterCardWasTrue) {
            this.isSummonOrSetMonsterCard = false;
        }
    }

    private boolean isCardSpecial(Card selectedCard) {
        return selectedCard.getName().equals("The Tricky") || selectedCard.getName().equals("Gate Guardian");
    }


    protected void controlTributeSummonOrSet(Player player, boolean isForSummon) {
        int tributeNeeded;
        if (((MonsterCard) player.getSelectedCard()).getLevel() <= 6) {
            tributeNeeded = 1;
        } else tributeNeeded = 2;
        if (player.getMonsterCardsInZone().size() < tributeNeeded) {
            mainPhaseView.printString("there are not enough cards for tribute");
        } else {
            if (tributeNeeded == 1) {
                payOneTribute(player, isForSummon);
            } else {
                payTwoTributes(player, isForSummon);
            }
        }

    }

    private void payThreeTributes(Player player, boolean isForOffensive, Card selectedCard) {
        int firstTributeAddress;
        int secondTributeAddress;
        int thirdTributeAddress;
        mainPhaseView.chooseMonsterLocationForTribute(3);
        while (true) {
            String firstTributeAddressToString = mainPhaseView.scanString();
            if (firstTributeAddressToString.equals("cancel")) return;
            String secondTributeAddressToString = mainPhaseView.scanString();
            if (secondTributeAddressToString.equals("cancel")) return;
            String thirdTributeAddressToString = mainPhaseView.scanString();
            if (thirdTributeAddressToString.equals("cancel")) return;

            try {
                firstTributeAddress = Integer.parseInt(firstTributeAddressToString);
                secondTributeAddress = Integer.parseInt(secondTributeAddressToString);
                thirdTributeAddress = Integer.parseInt(thirdTributeAddressToString);
            } catch (NumberFormatException exception) {
                view.invalidLocation();
                continue;
            }
            if (firstTributeAddress != secondTributeAddress && thirdTributeAddress != secondTributeAddress &&
                    thirdTributeAddress != firstTributeAddress) {
                break;
            }
            mainPhaseView.printString("Please enter 3 different address");
        }
        if (!player.doesHaveMonsterCardInThisLocation(firstTributeAddress)
                || !player.doesHaveMonsterCardInThisLocation(secondTributeAddress)
                || !player.doesHaveMonsterCardInThisLocation(thirdTributeAddress)) {
            mainPhaseView.printString("there is no monster on one of these addresses");
        } else {
            player.addCardToGraveyard(player.getMonsterCardsInZone().get(firstTributeAddress));
            player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(firstTributeAddress), firstTributeAddress);
            player.addCardToGraveyard(player.getMonsterCardsInZone().get(secondTributeAddress));
            player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(secondTributeAddress), secondTributeAddress);
            player.addCardToGraveyard(player.getMonsterCardsInZone().get(thirdTributeAddress));
            player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(thirdTributeAddress), thirdTributeAddress);
            boolean isIsSummonOrSetMonsterCardWasTrue = this.isSummonOrSetMonsterCard;
            ((MonsterCard) selectedCard).setSpecialSummoned(true);
            if (isForOffensive) {
                summonMonsterCard(player, (MonsterCard) selectedCard, OFFENSIVE_OCCUPIED);
            } else {
                summonMonsterCard(player, (MonsterCard) selectedCard, DEFENSIVE_OCCUPIED);
            }
            if (!isIsSummonOrSetMonsterCardWasTrue) {
                this.isSummonOrSetMonsterCard = false;
            }
        }
    }

    private void payTwoTributes(Player player, boolean isForSummon) {
        int firstTributeAddress;
        int secondTributeAddress;
        mainPhaseView.chooseMonsterLocationForTribute(2);
        while (true) {
            String firstTributeAddressToString = mainPhaseView.scanString();
            if (firstTributeAddressToString.equals("cancel")) return;
            String secondTributeAddressToString = mainPhaseView.scanString();
            if (secondTributeAddressToString.equals("cancel")) return;
            try {
                firstTributeAddress = Integer.parseInt(firstTributeAddressToString);
                secondTributeAddress = Integer.parseInt(secondTributeAddressToString);
            } catch (NumberFormatException exception) {
                view.invalidLocation();
                continue;
            }
            if (firstTributeAddress != secondTributeAddress) {
                break;
            }
            mainPhaseView.pleaseChooseTwoDifferentMonsters();
        }
        if (!player.doesHaveMonsterCardInThisLocation(firstTributeAddress)
                || !player.doesHaveMonsterCardInThisLocation(secondTributeAddress)) {
            mainPhaseView.printString("there is no monster on one of these addresses");
        } else {
            player.addCardToGraveyard(player.getMonsterCardsInZone().get(firstTributeAddress));
            player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(firstTributeAddress), firstTributeAddress);
            player.addCardToGraveyard(player.getMonsterCardsInZone().get(secondTributeAddress));
            player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(secondTributeAddress), secondTributeAddress);
            if (isForSummon) {
                summonMonsterCard(player, (MonsterCard) player.getSelectedCard(), OFFENSIVE_OCCUPIED);
            } else {
                setMonsterCard(player, (MonsterCard) player.getSelectedCard());
            }
        }
    }

    private void payOneTribute(Player player, boolean isForSummon) {
        mainPhaseView.chooseMonsterLocationForTribute(1);
        while (true) {
            String tributeAddressToString = mainPhaseView.scanString();
            if (tributeAddressToString.equals("cancel")) return;
            int tributeAddress;
            try {
                tributeAddress = Integer.parseInt(tributeAddressToString);
            } catch (NumberFormatException exception) {
                mainPhaseView.invalidLocation();
                continue;
            }
            if (!player.doesHaveMonsterCardInThisLocation(tributeAddress)) {
                mainPhaseView.printString("there is no monster on one of these addresses");
            } else {
                player.addCardToGraveyard(player.getMonsterCardsInZone().get(tributeAddress));
                player.removeCardFromCardsInZone(player.getMonsterCardsInZone().get(tributeAddress), tributeAddress);
                if (isForSummon) {
                    summonMonsterCard(player, (MonsterCard) player.getSelectedCard(), OFFENSIVE_OCCUPIED);
                } else {
                    setMonsterCard(player, (MonsterCard) player.getSelectedCard());
                }
                return;
            }
        }


    }

    public void summonMonsterCard(Player player, MonsterCard card, MonsterCardPosition position) {
        card.setSummoned(true);
        card.setPositionChangedInThisTurn(true);
        card.setPosition(position);
        player.addCardToCardsInZone(card);
        player.removeCardFromHand(card);
        checkTrapActionsAfterSummon(card);
        if (card.isCardActionCanceledByAnEffect()) {
            card.setCardActionCanceledByAnEffect(false);
            player.setSelectedCard(null);
            return;
        }
        player.setSelectedCard(null);
        this.isSummonOrSetMonsterCard = true;
        runFieldZoneSpells(player);
        runAllMonsterPowersInZone(player);
        gameView.summonedSuccessfully();
    }

    public void runFieldZoneSpells(Player player) {
        if (player.getFieldZoneCard() != null) {
            spellEffects.run((SpellCard) player.getFieldZoneCard());
        }
        if (phase.getRivalPlayerByTurn().getFieldZoneCard() != null) {
            spellEffects.run((SpellCard) phase.getRivalPlayerByTurn().getFieldZoneCard());
        }
    }

    public void checkTrapActionsAfterSummon(MonsterCard card) {
        if (card.isSpecialSummoned()) {
            checkForPossibleSpellOrTrapEffect(card, null,
                    RecentActionsInGame.RIVAL_SPECIAL_SUMMONED);
            card.setSpecialSummoned(false);
        } else if (card.getAttackPoint() < 1000) {
            checkForPossibleSpellOrTrapEffect(card, null,
                    RecentActionsInGame.RIVAL_SUMMONED_A_MONSTER_WITH_LESS_THAN_1000_ATTACK_POINT);
        } else {
            checkForPossibleSpellOrTrapEffect(card, null,
                    RecentActionsInGame.RIVAL_SUMMONED_A_MONSTER_WITH_1000_OR_MORE_ATTACK_POINT);
        }
    }

    @Override
    protected void controlSetCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromHand()) {
            gameView.canNotSetCard();
        }
        if (player.getSelectedCard() instanceof MonsterCard) {
            controlSetMonsterCard(player, (MonsterCard) player.getSelectedCard());
        } else setTrapOrSpellCard(player, player.getSelectedCard());
    }

    protected void controlSetMonsterCard(Player player, MonsterCard card) {
        if (player.isMonsterCardZoneFull()) {
            gameView.monsterZoneIsFull();
        } else if (isSummonOrSetMonsterCard) {
            gameView.printString("you already summoned/set on this turn");
        } else if (((MonsterCard) player.getSelectedCard()).getLevel() <= 4) {
            setMonsterCard(player, ((MonsterCard) player.getSelectedCard()));
        } else controlTributeSummonOrSet(player, false);
    }

    protected void setMonsterCard(Player player, MonsterCard card) {
        //nothing to save this card has been set?!
        card.setPosition(DEFENSIVE_HIDDEN);
        player.addCardToCardsInZone(card);
        player.removeCardFromHand(card);
        card.setSummonedInThisTurn(true);
        player.setSelectedCard(null);
        this.isSummonOrSetMonsterCard = true;
        runFieldZoneSpells(player);
        runAllMonsterPowersInZone(player);
        gameView.setSuccessfully();
    }

    protected void setTrapOrSpellCard(Player player, Card card) {
        if (player.isSpellCardZoneFull()) {
            gameView.printString("spell card zone is full");
        } else {
            if (card instanceof TrapCard) {
                ((TrapCard) card).setPosition(SpellOrTrapCardPosition.HIDDEN);
                ((TrapCard) card).setHasSetInThisTurn(true);
            } else if (card instanceof SpellCard) {
                if (((SpellCard) card).getIcon() == SpellIcon.FIELD || ((SpellCard) card).getIcon() == SpellIcon.EQUIP) {
                    gameView.canNotSetCard();
                    return;
                }
                ((SpellCard) card).setPosition(SpellOrTrapCardPosition.HIDDEN);
            }
            player.addCardToCardsInZone(card);
            player.removeCardFromHand(card);
            player.setSelectedCard(null);
            gameView.setSuccessfully();
        }
    }

    @Override
    protected void controlSetPositionAttackCommand() {
        changePositionHandler(OFFENSIVE_OCCUPIED);
    }

    @Override
    protected void controlSetPositionDefenseCommand() {
        changePositionHandler(DEFENSIVE_OCCUPIED);
    }

    private void changePositionHandler(MonsterCardPosition position) {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            gameView.printString("Can not change card position");
        } else if (((MonsterCard) player.getSelectedCard()).getPosition() == position) {
            gameView.printString("this card is already in the wanted position");
        } else if (((MonsterCard) player.getSelectedCard()).isPositionChangedInThisTurn()) {
            gameView.printString("you already changed this card position in this turn");
        } else if (((MonsterCard) player.getSelectedCard()).getPosition() == DEFENSIVE_HIDDEN) {
            gameView.printString("You can not change defensive hidden position");
        } else {
            ((MonsterCard) player.getSelectedCard()).setPosition(position);
            if (((MonsterCard) player.getSelectedCard()).getEquipCard() != null) {
                if (((MonsterCard) player.getSelectedCard()).getEquipCard().getEffect() == SpellEffect.MAGNUM_SHIELD) {
                    spellEffects.magnumShieldEffectMidGame((MonsterCard) player.getSelectedCard());
                }
            }
            ((MonsterCard) player.getSelectedCard()).setPositionChangedInThisTurn(true);
            if (position == OFFENSIVE_OCCUPIED) {
                ((MonsterCard) player.getSelectedCard()).setFlipped(true);
            }
            runAllMonsterPowersInZone(phase.getPlayerByTurn());
            gameView.changedPositionSuccessfully();
        }
    }

    @Override
    protected void controlFlipSummonCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            gameView.printString("Can not flip summon this card");
        } else if (((MonsterCard) player.getSelectedCard()).isSummonedInThisTurn()
                || ((MonsterCard) player.getSelectedCard()).getPosition() != DEFENSIVE_HIDDEN) {
            gameView.printString("you can’t flip summon this card");
        } else {
            MonsterCard flipSummonedCard = (MonsterCard) player.getSelectedCard();
            flipSummonedCard.setPosition(OFFENSIVE_OCCUPIED);
            flipSummonedCard.setPositionChangedInThisTurn(true);
            flipSummonedCard.setFlipped(true);
            flipSummonedCard.setSummoned(true);
            runAllMonsterPowersInZone(player);
            flipSummonedCard.setSpecialSummoned(false);
            checkTrapActionsAfterSummon(flipSummonedCard);
            player.setSelectedCard(null);
            gameView.flipSummonedSuccessfully();
            flipSummonedCard.setSummoned(false);
        }
    }

    @Override
    protected void controlAttackDirectCommand() {
        controlAttackCommand();
    }

    @Override
    protected void controlAttackToCardCommand(int location) {
        controlAttackCommand();
    }

    @Override
    protected void controlActivateEffectCommand() {
        Player player = phase.getPlayerByTurn();
        player.setAbleToActivateTrapCard(false);
        runAllMonsterPowersInZone(player);
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
            return;
        } else if (!player.isSelectedCardFromSpellAndTrapZone() && !player.isSelectedCardFromHand() && !player.isSelectedCardFromFieldZone()) {
            gameView.thisCardCanNotBeActivated();
            return;
        } else if (player.getSelectedCard() instanceof MonsterCard) {
            gameView.thisCardCanNotBeActivated();
        } else if (player.getSelectedCard() instanceof TrapCard) {
            controlActivateTrapEffect(player);
        } else if (player.getSelectedCard() instanceof SpellCard) {
            controlActivateSpellEffect(player);
        }
        runAllMonsterPowersInZone(player);
    }

    private void controlActivateSpellEffect(Player player) {
        SpellCard selectedSpell = (SpellCard) player.getSelectedCard();
        if (selectedSpell.getIcon() == SpellIcon.FIELD) {
            activateFieldZoneSpell(player);
        } else if (selectedSpell.getIcon() == SpellIcon.RITUAL) {
            activateRitualSpell(player);
        } else if (selectedSpell.getIcon() == SpellIcon.NORMAL || selectedSpell.getIcon() == SpellIcon.QUICK_PLAY) {
            activateNormalAndQuickPlaySpell(player);
        } else if (selectedSpell.getIcon() == SpellIcon.EQUIP) {
            activateEquipSpell(player);
        }
    }

    private void activateEquipSpell(Player player) {
        if (player.isSpellCardZoneFull()) {
            gameView.printString("spell card zone is full");
            player.setSelectedCard(null);
            return;
        }
        if (((SpellCard) player.getSelectedCard()).getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            gameView.printString("Effect is already activated");
            return;
        }
        checkForPossibleSpellOrTrapEffect(player.getSelectedCard(), null, RecentActionsInGame.RIVAL_ACTIVATED_A_SPELL_CARD);
        if (((SpellCard) player.getSelectedCard()).isActivationCancelled()) {
            view.activationCancelled();
            ((SpellCard) player.getSelectedCard()).setActivationCancelled(false);
            return;
        }
        spellEffects.run((SpellCard) player.getSelectedCard());
        if (!((SpellCard) player.getSelectedCard()).isActivationCancelled()) {
            ((SpellCard) player.getSelectedCard()).setPosition(SpellOrTrapCardPosition.OCCUPIED);
            player.addCardToCardsInZone(player.getSelectedCard());
            player.removeCardFromHand(player.getSelectedCard());
            player.setSelectedCard(null);
            gameView.printString("Spell Activated");
        } else {
            ((SpellCard) player.getSelectedCard()).setActivationCancelled(false);
        }

    }

    private void activateRitualSpell(Player player) {
        if (((SpellCard) player.getSelectedCard()).getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            gameView.printString("Effect is already activated");
        } else {
            checkForPossibleSpellOrTrapEffect(player.getSelectedCard(), null, RecentActionsInGame.RIVAL_ACTIVATED_A_SPELL_CARD);
            if (((SpellCard) player.getSelectedCard()).isActivationCancelled()) {
                view.activationCancelled();
                ((SpellCard) player.getSelectedCard()).setActivationCancelled(false);
                return;
            }
            if (player.isSelectedCardFromHand()) {
                player.addCardToCardsInZone(player.getSelectedCard());
                player.removeCardFromHand(player.getSelectedCard());
            }
            ((SpellCard) player.getSelectedCard()).setPosition(SpellOrTrapCardPosition.OCCUPIED);
           gameView.printString("Spell activated");
        }
    }

    private void activateNormalAndQuickPlaySpell(Player player) {
        if (((SpellCard) player.getSelectedCard()).getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
            gameView.printString("Effect is already activated");
        } else {
            if (player.getSelectedCard().getName().equals("Monster Reborn")) {
                if (phase.getFirstPlayer().getCardsInGraveyard().isEmpty() && phase.getSecondPlayer().getCardsInGraveyard().isEmpty()) {
                    gameView.printString("There is no way you could special summon a monster");
                    return;
                }
            }
            if (((SpellCard) player.getSelectedCard()).getPosition() == SpellOrTrapCardPosition.NOT_IN_PLAY_ZONE
                    && phase.getPlayerByTurn().isSpellCardZoneFull()) {
                gameView.printString("spell card zone is full");
                phase.getPlayerByTurn().setSelectedCard(null);
                return;
            }
            checkForPossibleSpellOrTrapEffect(player.getSelectedCard(), null, RecentActionsInGame.RIVAL_ACTIVATED_A_SPELL_CARD);
            if (((SpellCard) player.getSelectedCard()).isActivationCancelled()) {
                view.activationCancelled();
                ((SpellCard) player.getSelectedCard()).setActivationCancelled(false);
                return;
            }
            SpellOrTrapCardPosition firstPosition = ((SpellCard) player.getSelectedCard()).getPosition();
            ((SpellCard) player.getSelectedCard()).setPosition(SpellOrTrapCardPosition.OCCUPIED);
            spellEffects.run((SpellCard) player.getSelectedCard());
            if (((SpellCard) player.getSelectedCard()).isActivationCancelled()) {
                ((SpellCard) player.getSelectedCard()).setActivationCancelled(false);
                ((SpellCard) player.getSelectedCard()).setPosition(firstPosition);
                return;
            }
            if (firstPosition == SpellOrTrapCardPosition.NOT_IN_PLAY_ZONE) {
                player.addCardToCardsInZone(player.getSelectedCard());
                player.removeCardFromHand(player.getSelectedCard());
            }

            gameView.printString("Spell activated");
            phase.getPlayerByTurn().addCardToGraveyard(phase.getPlayerByTurn().getSelectedCard());
            phase.getPlayerByTurn().removeCardFromCardsInZone(phase.getPlayerByTurn().getSelectedCard()
                    , phase.getPlayerByTurn().getLocationOfThisSpellOrTrapCardInZone(phase.getPlayerByTurn().getSelectedCard()));
            phase.getPlayerByTurn().setSelectedCard(null);
        }
    }

    private void activateFieldZoneSpell(Player player) {
        if (player.getFieldZoneCard() == player.getSelectedCard()) {
            gameView.printString("Effect is already activated");
        } else if (phase.getRivalPlayerByTurn().getFieldZoneCard() == player.getSelectedCard()) {
            gameView.opponentFieldSpellSelected();
        } else {
            checkForPossibleSpellOrTrapEffect(player.getSelectedCard(), null, RecentActionsInGame.RIVAL_ACTIVATED_A_SPELL_CARD);
            if (((SpellCard) player.getSelectedCard()).isActivationCancelled()) {
                view.activationCancelled();
                ((SpellCard) player.getSelectedCard()).setActivationCancelled(false);
                return;
            }
            if (player.getFieldZoneCard() != null) {
                player.getFieldZoneCard().setGoingToGraveyard(true);
                spellEffects.run((SpellCard) player.getFieldZoneCard());
                player.getFieldZoneCard().setGoingToGraveyard(false);
                player.addCardToGraveyard(player.getFieldZoneCard());
            }

            if (phase.getRivalPlayerByTurn().getFieldZoneCard() != null) {
                phase.getRivalPlayerByTurn().getFieldZoneCard().setGoingToGraveyard(true);
                spellEffects.run((SpellCard) phase.getRivalPlayerByTurn().getFieldZoneCard());
                phase.getRivalPlayerByTurn().getFieldZoneCard().setGoingToGraveyard(false);
                phase.getRivalPlayerByTurn().addCardToGraveyard(phase.getRivalPlayerByTurn().getFieldZoneCard());
            }
            player.removeCardFromHand(player.getSelectedCard());
            player.setFieldZoneCard(player.getSelectedCard());
            spellEffects.run((SpellCard) player.getSelectedCard());
            gameView.printString("Spell activated");
        }
    }

    private void controlActivateTrapEffect(Player player) {
        if (player.isSelectedCardFromSpellAndTrapZone()) {
            if (((TrapCard) player.getSelectedCard()).hasSetInThisTurn()) {
                gameView.canNotActivateCardInThisTurn();
            } else {
                player.setAbleToActivateTrapCard(true);
                runAllMonsterPowersInZone(player);
                if (canCardBeActivatedAfterThisAction(player, RecentActionsInGame.IN_OUR_MAIN_PHASE, player.getSelectedCard())) {
                    TrapEffectController.searchForThisEffect(this, phase, null, (TrapCard) player.getSelectedCard());
                    if (((TrapCard) player.getSelectedCard()).isActivationCancelled()) {
                        ((TrapCard) player.getSelectedCard()).setActivationCancelled(false);
                        mainPhaseView.activationCancelled();
                    } else {
                        gameView.printString("Trap activated");
                    }
                } else {
                    gameView.preparationsOfSpellHaveNotBeenDoneYet();
                }
            }
        } else {
            gameView.thisCardCanNotBeActivated();
        }
    }

    private void controlAttackCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            gameView.noCardSelectedYet();
        } else if (!player.isSelectedCardFromMonsterCardZone()) {
            gameView.canNotAttackWithThisCard();
        } else {
            gameView.canNotDoThisActionInThisPhase();
        }
    }

    private void ritualSummon(MonsterCard card) {
        String position;
        if (!monsterPowers.ritualSummoned(card))
            return;
        while (true) {
            view.printString("Enter OO for Offensive Occupied OR DO for Defensive Occupied");
            position = view.scanString();
            if (position.equals("OO")) {
                summonMonsterCard(phase.getPlayerByTurn(), card, OFFENSIVE_OCCUPIED);
                break;
            } else if (position.equals("DO")) {
                summonMonsterCard(phase.getPlayerByTurn(), card, DEFENSIVE_OCCUPIED);
                break;
            }
            view.printString("invalid input");
        }

    }
}
