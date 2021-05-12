package controller;

import enums.MenuEnum;
import enums.MonsterCardPosition;
import enums.SpellOrTrapCardPosition;
import enums.Turn;
import model.*;
import view.PhaseView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PhaseController {

    protected Phase phase;
    protected PhaseView view = new PhaseView(this);

    public PhaseController(Phase phase) {
        this.phase = phase;
    }

    public void run() {
        view.printString(phase.getMapToString());
        this.view.run();
    }

    public MenuEnum processCommand(String command) {
        Matcher matcherForAttackToCard = Pattern.compile("^attack (\\d+)$").matcher(command);
        if (command.equals("next phase")) {
            return MenuEnum.BACK;
        } else if (command.equals("surrender")) {
            phase.getPlayerByTurn().setLifePoint(0);
            return MenuEnum.BACK;
        } else if (command.equals("menu show-current")) {
            view.menuShowCurrent();
        } else if (command.equals("summon")) {
            controlSummonCommand();
        } else if (command.equals("set")) {
            controlSetCommand();
        } else if (command.equals("set --position attack")) {
            controlSetPositionAttackCommand();
        } else if (command.equals("set --position defense")) {
            controlSetPositionDefenseCommand();
        } else if (command.equals("flip-summon")) {
            controlFlipSummonCommand();
        } else if (command.equals("attack direct")) {
            controlAttackDirectCommand();
        } else if (command.equals("activate effect")) {
            controlActivateEffectCommand();
        } else if (command.equals("show graveyard")) {
            controlShowGraveyardCommand();
        } else if (command.equals("card show --selected")) {
            controlShowCardSelectedCommand();
        } else if (matcherForAttackToCard.find()) {
            controlAttackToCardCommand(Integer.parseInt(matcherForAttackToCard.group(1)));
        } else if (command.startsWith("select ")) {
            Matcher[] selectCommandMatchers = getSelectCommandMatchers(command);
            if (selectCommandMatchers[0].find()) {
                controlSelectOwnMonsterCommand(Integer.parseInt(selectCommandMatchers[0].group(1)));
            } else if (selectCommandMatchers[1].find()) {
                controlSelectOwnSpellCommand(Integer.parseInt(selectCommandMatchers[1].group(1)));
            } else if (selectCommandMatchers[2].find()) {
                controlSelectRivalMonsterCommand(Integer.parseInt(selectCommandMatchers[2].group(1)));
            } else if (selectCommandMatchers[3].find()) {
                controlSelectRivalMonsterCommand(Integer.parseInt(selectCommandMatchers[3].group(1)));
            } else if (selectCommandMatchers[4].find()) {
                controlSelectRivalSpellCommand(Integer.parseInt(selectCommandMatchers[4].group(1)));
            } else if (selectCommandMatchers[5].find()) {
                controlSelectRivalSpellCommand(Integer.parseInt(selectCommandMatchers[5].group(1)));
            } else if (selectCommandMatchers[6].find()) {
                controlSelectOwnFieldZoneSpellCommand();
            } else if (selectCommandMatchers[7].find() || selectCommandMatchers[8].find()) {
                controlSelectRivalFieldZoneSpellCommand();
            } else if (selectCommandMatchers[9].find()) {
                controlSelectFromOwnHand(Integer.parseInt(selectCommandMatchers[9].group(1)));
            } else if (selectCommandMatchers[10].find()) {
                controlDeselectCommand();
            } else {
                view.invalidSelection();
            }
        } else {
            view.invalidCommand();
        }
        if (phase.getFirstPlayer().getLifePoint() <= 0 || phase.getSecondPlayer().getLifePoint() <= 0) {
            view.printString(phase.getMapToString());
            return MenuEnum.BACK;
        }
        view.printString(phase.getMapToString());
        return MenuEnum.CONTINUE;
    }

    protected abstract void controlSummonCommand();

    protected abstract void controlSetCommand();

    protected abstract void controlSetPositionAttackCommand();

    protected abstract void controlSetPositionDefenseCommand();

    protected abstract void controlFlipSummonCommand();

    protected abstract void controlAttackDirectCommand();

    protected abstract void controlActivateEffectCommand();

    protected abstract void controlAttackToCardCommand(int location);

    protected void controlSelectOwnMonsterCommand(int location) {
        if (location < 1 || location > 5) {
            view.invalidSelection();
        } else if (phase.getPlayerByTurn().doesHaveMonsterCardInThisLocation(location)) {
            phase.getPlayerByTurn().setSelectedCard(phase.getPlayerByTurn().getMonsterCardsInZone().get(location));
            phase.getPlayerByTurn().setSelectedCardVisible(true);
            view.cardSelected();
        } else {
            view.noCardFoundInPosition();
        }
    }

    protected void controlSelectOwnSpellCommand(int location) {
        if (location < 1 || location > 5) {
            view.invalidSelection();
        } else if (phase.getPlayerByTurn().doesHaveSpellOrTrapCardInThisPosition(location)) {
            phase.getPlayerByTurn().setSelectedCard(phase.getPlayerByTurn().getSpellOrTrapCardsInZone().get(location));
            phase.getPlayerByTurn().setSelectedCardVisible(true);
            view.cardSelected();
        } else {
            view.noCardFoundInPosition();
        }
    }

    protected void controlSelectRivalMonsterCommand(int location) {
        if (location < 1 || location > 5) {
            view.invalidSelection();
        } else if (phase.getRivalPlayerByTurn().doesHaveMonsterCardInThisLocation(location)) {
            MonsterCard cardToSelect = phase.getRivalPlayerByTurn().getMonsterCardsInZone().get(location);
            phase.getPlayerByTurn().setSelectedCard(cardToSelect);
            phase.getPlayerByTurn().setSelectedCardVisible(cardToSelect.getPosition() != MonsterCardPosition.DEFENSIVE_HIDDEN);
            view.cardSelected();
        } else {
            view.noCardFoundInPosition();
        }
    }

    protected void controlSelectRivalSpellCommand(int location) {
        if (location < 1 || location > 5) {
            view.invalidSelection();
        } else if (phase.getRivalPlayerByTurn().doesHaveSpellOrTrapCardInThisPosition(location)) {
            Card cardToSelect = phase.getRivalPlayerByTurn().getSpellOrTrapCardsInZone().get(location);
            phase.getPlayerByTurn().setSelectedCard(cardToSelect);
            if (cardToSelect instanceof TrapCard && ((TrapCard) cardToSelect).getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
                phase.getPlayerByTurn().setSelectedCardVisible(true);
            } else if (cardToSelect instanceof SpellCard && ((SpellCard) cardToSelect).getPosition() == SpellOrTrapCardPosition.OCCUPIED) {
                phase.getPlayerByTurn().setSelectedCardVisible(true);
            } else {
                phase.getPlayerByTurn().setSelectedCardVisible(false);
            }
            view.cardSelected();
        } else {
            view.noCardFoundInPosition();
        }
    }

    protected void controlSelectOwnFieldZoneSpellCommand() {
        if (!phase.getPlayerByTurn().hasFieldSpellCardInZone()) {
            view.noCardFoundInPosition();
        } else {
            phase.getPlayerByTurn().setSelectedCard(phase.getPlayerByTurn().getFieldZoneCard());
            phase.getPlayerByTurn().setSelectedCardVisible(true);
            view.cardSelected();
        }
    }

    protected void controlSelectRivalFieldZoneSpellCommand() {
        if (!phase.getRivalPlayerByTurn().hasFieldSpellCardInZone()) {
            view.noCardFoundInPosition();
        } else {
            phase.getPlayerByTurn().setSelectedCard(phase.getRivalPlayerByTurn().getFieldZoneCard());
            phase.getPlayerByTurn().setSelectedCardVisible(true);
            view.cardSelected();
        }
    }

    protected void controlSelectFromOwnHand(int location) {
        if (location > phase.getPlayerByTurn().getCardsInHand().size()) {
            view.invalidSelection();
        } else {
            phase.getPlayerByTurn().setSelectedCard(phase.getPlayerByTurn().getCardsInHand().get(location - 1));
            phase.getPlayerByTurn().setSelectedCardVisible(true);
            view.cardSelected();
        }
    }

    protected void controlDeselectCommand() {
        if (phase.getPlayerByTurn().hasSelectedCard()) {
            phase.getPlayerByTurn().setSelectedCard(null);
            phase.getPlayerByTurn().setSelectedCardVisible(false);
            view.cardDeselected();
        } else {
            view.noCardSelectedYet();
        }
    }

    protected void controlShowCardSelectedCommand() {
        Player player = phase.getPlayerByTurn();
        if (!player.hasSelectedCard()) {
            view.noCardSelectedYet();
        } else if (!player.isSelectedCardVisible()) {
            view.cardIsNotVisible();
        } else {
            view.printString(player.getSelectedCard().toString());
        }
    }

    protected void controlShowGraveyardCommand() {
        if (phase.getPlayerByTurn().getCardsInGraveyard().size() > 0) {
            view.printString(phase.getPlayerByTurn().graveyardToString());
        } else {
            view.graveyardEmpty();
        }
    }

    public Matcher[] getSelectCommandMatchers(String command) {
        Pattern patternForSelectOwnMonsterInZone = Pattern.compile("^select --monster (\\d+)$");
        Pattern patternForSelectOwnSpellInZone = Pattern.compile("^select --spell (\\d+)$");
        Pattern patternForSelectRivalMonsterInZone1 = Pattern.compile("^select --monster (\\d+) --opponent$");
        Pattern patternForSelectRivalMonsterInZone2 = Pattern.compile("^select --opponent --monster (\\d+)$");
        Pattern patternForSelectRivalSpellInZone1 = Pattern.compile("^select --spell (\\d+) --opponent$");
        Pattern patternForSelectRivalSpellInZone2 = Pattern.compile("^select --opponent --spell (\\d+)$");
        Pattern patternForSelectOwnFieldZoneSpell = Pattern.compile("^select --field$");
        Pattern patternForSelectRivalFieldZoneSpell1 = Pattern.compile("^select --field --opponent$");
        Pattern patternForSelectRivalFieldZoneSpell2 = Pattern.compile("^select --opponent --field$");
        Pattern patternForSelectFromOwnHand = Pattern.compile("^select --hand (\\d+)$");
        Pattern patternForDeselect = Pattern.compile("^select -d$");
        Matcher[] selectCommandMatchers = new Matcher[11];
        selectCommandMatchers[0] = patternForSelectOwnMonsterInZone.matcher(command);
        selectCommandMatchers[1] = patternForSelectOwnSpellInZone.matcher(command);
        selectCommandMatchers[2] = patternForSelectRivalMonsterInZone1.matcher(command);
        selectCommandMatchers[3] = patternForSelectRivalMonsterInZone2.matcher(command);
        selectCommandMatchers[4] = patternForSelectRivalSpellInZone1.matcher(command);
        selectCommandMatchers[5] = patternForSelectRivalSpellInZone2.matcher(command);
        selectCommandMatchers[6] = patternForSelectOwnFieldZoneSpell.matcher(command);
        selectCommandMatchers[7] = patternForSelectRivalFieldZoneSpell1.matcher(command);
        selectCommandMatchers[8] = patternForSelectRivalFieldZoneSpell2.matcher(command);
        selectCommandMatchers[9] = patternForSelectFromOwnHand.matcher(command);
        selectCommandMatchers[10] = patternForDeselect.matcher(command);
        return selectCommandMatchers;
    }
}

