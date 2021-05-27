package controller;

import enums.*;
import model.*;
import view.PhaseView;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PhaseController {

    protected Phase phase;
    protected PhaseView view = new PhaseView(this);

    public PhaseController(Phase phase) {
        this.phase = phase;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
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
        } else if (command.equals("set --position attack") || command.equals("set -p attack")) {
            controlSetPositionAttackCommand();
        } else if (command.equals("set --position defense") || command.equals("set -p defense")) {
            controlSetPositionDefenseCommand();
        } else if (command.equals("flip-summon")) {
            controlFlipSummonCommand();
        } else if (command.equals("attack direct")) {
            controlAttackDirectCommand();
        } else if (command.equals("activate effect")) {
            controlActivateEffectCommand();
        } else if (command.equals("show graveyard")) {
            controlShowGraveyardCommand();
        } else if (command.equals("card show --selected") || command.equals("card show -s")) {
            controlShowCardSelectedCommand();
        } else if (matcherForAttackToCard.find()) {
            controlAttackToCardCommand(Integer.parseInt(matcherForAttackToCard.group(1)));
        } else if (command.startsWith("select ")) {
            controlSelectionCommands(command);
        } else {
            view.invalidCommand();
        }
        if (phase.getFirstPlayer().getLifePoint() <= 0 || phase.getSecondPlayer().getLifePoint() <= 0) {
            view.printString(phase.getMapToString());
            return MenuEnum.BACK;
        } else if (phase.isEndedByATrapCard()) {
            return MenuEnum.BACK;
        }
        view.printString(phase.getMapToString());
        return MenuEnum.CONTINUE;
    }

    private void controlSelectionCommands(String command) {
        Matcher[] selectCommandMatchers = getSelectCommandMatchers(command);
        if (selectCommandMatchers[0].find()) {
            controlSelectOwnMonsterCommand(Integer.parseInt(selectCommandMatchers[0].group(1)));
        } else if (selectCommandMatchers[1].find()) {
            controlSelectOwnMonsterCommand(Integer.parseInt(selectCommandMatchers[1].group(1)));
        } else if (selectCommandMatchers[2].find()) {
            controlSelectOwnSpellCommand(Integer.parseInt(selectCommandMatchers[2].group(1)), false, RecentActionsInGame.IN_OUR_MAIN_PHASE);
        } else if (selectCommandMatchers[3].find()) {
            controlSelectOwnSpellCommand(Integer.parseInt(selectCommandMatchers[3].group(1)), false, RecentActionsInGame.IN_OUR_MAIN_PHASE);
        } else if (selectCommandMatchers[4].find()) {
            controlSelectRivalMonsterCommand(Integer.parseInt(selectCommandMatchers[4].group(1)));
        } else if (selectCommandMatchers[5].find()) {
            controlSelectRivalMonsterCommand(Integer.parseInt(selectCommandMatchers[5].group(1)));
        } else if (selectCommandMatchers[6].find()) {
            controlSelectRivalMonsterCommand(Integer.parseInt(selectCommandMatchers[6].group(1)));
        } else if (selectCommandMatchers[7].find()) {
            controlSelectRivalMonsterCommand(Integer.parseInt(selectCommandMatchers[7].group(1)));
        } else if (selectCommandMatchers[8].find()) {
            controlSelectRivalSpellCommand(Integer.parseInt(selectCommandMatchers[8].group(1)));
        } else if (selectCommandMatchers[9].find()) {
            controlSelectRivalSpellCommand(Integer.parseInt(selectCommandMatchers[9].group(1)));
        } else if (selectCommandMatchers[10].find()) {
            controlSelectRivalSpellCommand(Integer.parseInt(selectCommandMatchers[10].group(1)));
        } else if (selectCommandMatchers[11].find()) {
            controlSelectRivalSpellCommand(Integer.parseInt(selectCommandMatchers[11].group(1)));
        } else if (selectCommandMatchers[12].find()) {
            controlSelectFromOwnHand(Integer.parseInt(selectCommandMatchers[12].group(1)));
        } else if (selectCommandMatchers[13].find()) {
            controlSelectFromOwnHand(Integer.parseInt(selectCommandMatchers[13].group(1)));
        } else if (selectCommandMatchers[14].find()) {
            controlSelectOwnFieldZoneSpellCommand();
        } else if (selectCommandMatchers[15].find()) {
            controlSelectOwnFieldZoneSpellCommand();
        } else if (selectCommandMatchers[16].find()) {
            controlSelectRivalFieldZoneSpellCommand();
        } else if (selectCommandMatchers[17].find()) {
            controlSelectRivalFieldZoneSpellCommand();
        } else if (selectCommandMatchers[18].find()) {
            controlSelectRivalFieldZoneSpellCommand();
        } else if (selectCommandMatchers[19].find()) {
            controlSelectRivalFieldZoneSpellCommand();
        } else if (selectCommandMatchers[20].find()) {
            controlDeselectCommand();
        } else {
            view.invalidSelection();
        }
    }

    protected MenuEnum processActivatingEffectInRivalTurnCommand(Card rivalCard, Card ourCard, String selection, RecentActionsInGame recentAction) {
        Matcher[] selectCommandMatchers = getSelectCommandMatchers(selection);
        if (selection.equals("activate effect")) {
            if (!phase.getPlayerByTurn().hasSelectedCard()) {
                view.noCardSelectedYet();
            } else {
                if (phase.getPlayerByTurn().getSelectedCard() instanceof SpellCard) {

                } else if (phase.getPlayerByTurn().getSelectedCard() instanceof TrapCard) {
                    TrapEffectController.searchForThisEffect(phase, rivalCard, (TrapCard) phase.getPlayerByTurn().getSelectedCard());
                }
                view.spellOrTrapActivated(phase.getPlayerByTurn().getSelectedCard() instanceof SpellCard ? "Spell" : "Trap");
                return MenuEnum.BACK;
            }
        } else if (selectCommandMatchers[2].find()) {
            controlSelectOwnSpellCommand(Integer.parseInt(selectCommandMatchers[2].group(1)), true, recentAction);
        } else if (selectCommandMatchers[3].find()) {
            controlSelectOwnSpellCommand(Integer.parseInt(selectCommandMatchers[3].group(1)), true, recentAction);
        } else if (selection.equals("cancel")) {
            return MenuEnum.BACK;
        } else {
            view.canNotPlayThisKindOfMoves();
        }
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

    protected void controlSelectOwnSpellCommand(int location, boolean isForActivatingInRivalTurn, RecentActionsInGame recentAction) {
        if (location < 1 || location > 5) {
            view.invalidSelection();
        } else if (phase.getPlayerByTurn().doesHaveSpellOrTrapCardInThisPosition(location)) {
            if (isForActivatingInRivalTurn && !canCardBeActivatedAfterThisAction(recentAction, phase.getPlayerByTurn().getSpellOrTrapCardsInZone().get(location))) {
                view.canNotPlayThisKindOfMoves();
                return;
            }
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

    public boolean canRivalActivateEffect(RecentActionsInGame recentAction) {
        for (Map.Entry<Integer, Card> locationCard : phase.getRivalPlayerByTurn().getSpellOrTrapCardsInZone().entrySet()) {
            if (canCardBeActivatedAfterThisAction(recentAction, locationCard.getValue())) {
                return true;
            }
        }
        return false;
    }

    public boolean canCardBeActivatedAfterThisAction(RecentActionsInGame recentAction, Card card) {
        if (card instanceof MonsterCard) {
            return false;
        }
        if (recentAction == RecentActionsInGame.DECLARED_A_BATTLE) {
            if (card instanceof TrapCard) {
                return ((TrapCard) card).getEffect() == TrapEffect.MIRROR_FORCE ||
                        ((TrapCard) card).getEffect() == TrapEffect.MAGIC_CYLINDER ||
                        ((TrapCard) card).getEffect() == TrapEffect.NEGATE_ATTACK;
            } else {
                //TODO: search for spell cards can be activated
            }
        } else if (recentAction == RecentActionsInGame.SUMMONED_A_MONSTER_WITH_LESS_THAN_1000_ATTACK_POINT) {
            if (card instanceof TrapCard) {
                return ((TrapCard) card).getEffect() == TrapEffect.TORRENTIAL_TRIBUTE ||
                        ((TrapCard) card).getEffect() == TrapEffect.SOLEMN_WARNING;
            } else {
                //TODO: search for spell cards can be activated
            }
        } else if (recentAction == RecentActionsInGame.SUMMONED_A_MONSTER_WITH_1000_OR_MORE_ATTACK_POINT) {
            if (card instanceof TrapCard) {
                return ((TrapCard) card).getEffect() == TrapEffect.TORRENTIAL_TRIBUTE ||
                        ((TrapCard) card).getEffect() == TrapEffect.TRAP_HOLE ||
                        ((TrapCard) card).getEffect() == TrapEffect.SOLEMN_WARNING;
            } else {
                //TODO: search for spell cards can be activated
            }
        } else if (recentAction == RecentActionsInGame.SPECIAL_SUMMONED) {
            if (card instanceof TrapCard) {
                return ((TrapCard) card).getEffect() == TrapEffect.TORRENTIAL_TRIBUTE ||
                        ((TrapCard) card).getEffect() == TrapEffect.SOLEMN_WARNING;
            } else {
                //TODO: search for spell cards can be activated
            }
        } else if (recentAction == RecentActionsInGame.IN_OUR_MAIN_PHASE) {
            if (card instanceof TrapCard) {
                return ((TrapCard) card).getEffect() == TrapEffect.MIND_CRUSH ||
                        ((TrapCard) card).getEffect() == TrapEffect.TIME_SEAL ||
                        ((TrapCard) card).getEffect() == TrapEffect.CALL_OF_THE_HAUNTED;
            } else {
                //TODO: search for spell cards can be activated
            }
        }
        return false;
    }

    protected void checkForRivalSpellOrTrapEffect(Card rivalCard, Card ourCard, RecentActionsInGame recentAction) {
        if (canRivalActivateEffect(recentAction)) {
            phase.changeTurn();
            view.nowItWillBeRivalsTurn(phase.getPlayerByTurn().getUser().getNickname());
            view.printString(phase.getMapToString());
            view.doYouWantToActiveSpellOrTrap();
            while (true) {
                String yesOrNo = view.scanString();
                if (yesOrNo.equals("yes")) {
                    phase.getPlayerByTurn().setSelectedCard(null);
                    while (true) {
                        String selection = view.scanString();
                        if (processActivatingEffectInRivalTurnCommand(rivalCard, ourCard, selection, recentAction) == MenuEnum.BACK) {
                            break;
                        }
                    }
                    phase.getPlayerByTurn().setSelectedCard(null);
                    break;
                } else if (yesOrNo.equals("no")) {
                    break;
                } else {
                    view.invalidCommand();
                }
            }
            phase.changeTurn();
            view.nowItWillBeRivalsTurn(phase.getPlayerByTurn().getUser().getNickname());
        }
    }


    public Matcher[] getSelectCommandMatchers(String command) {
        Pattern patternForSelectOwnMonsterInZone = Pattern.compile("^select --monster (\\d+)$");
        Pattern patternForSelectOwnMonsterInZone2 = Pattern.compile("^select -m (\\d+)$");
        Pattern patternForSelectOwnSpellInZone = Pattern.compile("^select --spell (\\d+)$");
        Pattern patternForSelectOwnSpellInZone2 = Pattern.compile("^select -s (\\d+)$");
        Pattern patternForSelectRivalMonsterInZone1 = Pattern.compile("^select --monster (\\d+) --opponent$");
        Pattern patternForSelectRivalMonsterInZone2 = Pattern.compile("^select --opponent --monster (\\d+)$");
        Pattern patternForSelectRivalMonsterInZone3 = Pattern.compile("^select -m (\\d+) -o$");
        Pattern patternForSelectRivalMonsterInZone4 = Pattern.compile("^select -o -m (\\d+)$");
        Pattern patternForSelectRivalSpellInZone1 = Pattern.compile("^select --spell (\\d+) --opponent$");
        Pattern patternForSelectRivalSpellInZone2 = Pattern.compile("^select --opponent --spell (\\d+)$");
        Pattern patternForSelectRivalSpellInZone3 = Pattern.compile("^select -s (\\d+) -o$");
        Pattern patternForSelectRivalSpellInZone4 = Pattern.compile("^select -o -s (\\d+)$");
        Pattern patternForSelectFromOwnHand = Pattern.compile("^select --hand (\\d+)$");
        Pattern patternForSelectFromOwnHand2 = Pattern.compile("^select -h (\\d+)$");
        Pattern patternForSelectOwnFieldZoneSpell = Pattern.compile("^select --field$");
        Pattern patternForSelectOwnFieldZoneSpell2 = Pattern.compile("^select -f$");
        Pattern patternForSelectRivalFieldZoneSpell1 = Pattern.compile("^select --field --opponent$");
        Pattern patternForSelectRivalFieldZoneSpell2 = Pattern.compile("^select --opponent --field$");
        Pattern patternForSelectRivalFieldZoneSpell3 = Pattern.compile("^select -f -o$");
        Pattern patternForSelectRivalFieldZoneSpell4 = Pattern.compile("^select -o -f$");
        Pattern patternForDeselect = Pattern.compile("^select -d$");
        Matcher[] selectCommandMatchers = new Matcher[21];
        selectCommandMatchers[0] = patternForSelectOwnMonsterInZone.matcher(command);
        selectCommandMatchers[1] = patternForSelectOwnMonsterInZone2.matcher(command);
        selectCommandMatchers[2] = patternForSelectOwnSpellInZone.matcher(command);
        selectCommandMatchers[3] = patternForSelectOwnSpellInZone2.matcher(command);
        selectCommandMatchers[4] = patternForSelectRivalMonsterInZone1.matcher(command);
        selectCommandMatchers[5] = patternForSelectRivalMonsterInZone2.matcher(command);
        selectCommandMatchers[6] = patternForSelectRivalMonsterInZone3.matcher(command);
        selectCommandMatchers[7] = patternForSelectRivalMonsterInZone4.matcher(command);
        selectCommandMatchers[8] = patternForSelectRivalSpellInZone1.matcher(command);
        selectCommandMatchers[9] = patternForSelectRivalSpellInZone2.matcher(command);
        selectCommandMatchers[10] = patternForSelectRivalSpellInZone3.matcher(command);
        selectCommandMatchers[11] = patternForSelectRivalSpellInZone4.matcher(command);
        selectCommandMatchers[12] = patternForSelectFromOwnHand.matcher(command);
        selectCommandMatchers[13] = patternForSelectFromOwnHand2.matcher(command);
        selectCommandMatchers[14] = patternForSelectOwnFieldZoneSpell.matcher(command);
        selectCommandMatchers[15] = patternForSelectOwnFieldZoneSpell2.matcher(command);
        selectCommandMatchers[16] = patternForSelectRivalFieldZoneSpell1.matcher(command);
        selectCommandMatchers[17] = patternForSelectRivalFieldZoneSpell2.matcher(command);
        selectCommandMatchers[18] = patternForSelectRivalFieldZoneSpell3.matcher(command);
        selectCommandMatchers[19] = patternForSelectRivalFieldZoneSpell4.matcher(command);
        selectCommandMatchers[20] = patternForDeselect.matcher(command);
        return selectCommandMatchers;
    }
}

