package controller;

import enums.MenuEnum;
import model.Phase;
import view.PhaseView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PhaseController {

    Phase phase;
    private PhaseView view = new PhaseView(this);

    public PhaseController(Phase phase) {
        this.phase = phase;
    }

    public void run() {
        this.view.run();
    }

    public MenuEnum processCommand(String command) {
        Matcher matcherForAttackToCard = Pattern.compile("^attack (\\d+)$").matcher(command);
        if (command.equals("next phase")) {
            return MenuEnum.BACK;
        } else if (command.equals("surrender")){
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
            view.print(phase.getPlayerByTurn().graveyardToString());
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
                controlSelectFromOwnHand();
            } else if (selectCommandMatchers[10].find()) {
                controlDeselectCommand();
            } else {
                view.invalidSelection();
            }
        } else {
            view.invalidCommand();
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

    protected abstract void controlSelectOwnMonsterCommand(int location);

    protected abstract void controlSelectOwnSpellCommand(int location);

    protected abstract void controlSelectRivalMonsterCommand(int location);

    protected abstract void controlSelectRivalSpellCommand(int location);

    protected abstract void controlSelectOwnFieldZoneSpellCommand();

    protected abstract void controlSelectRivalFieldZoneSpellCommand();

    protected abstract void controlSelectFromOwnHand();

    protected abstract void controlDeselectCommand();


    public Matcher[] getSelectCommandMatchers(String command) {
        Pattern patternForSelectOwnMonsterInZone = Pattern.compile("^select --monster (\\d+)$");
        Pattern patternForSelectOwnSpellInZone = Pattern.compile("^select --spell (\\d+)$");
        Pattern patternForSelectRivalMonsterInZone1 = Pattern.compile("^select --monster --opponent (\\d+)$");
        Pattern patternForSelectRivalMonsterInZone2 = Pattern.compile("^select --opponent --monster (\\d+)$");
        Pattern patternForSelectRivalSpellInZone1 = Pattern.compile("^select --spell --opponent (\\d+)$");
        Pattern patternForSelectRivalSpellInZone2 = Pattern.compile("^select --opponent --spell (\\d+)$");
        Pattern patternForSelectOwnFieldZoneSpell = Pattern.compile("^select --field$");
        Pattern patternForSelectRivalFieldZoneSpell1 = Pattern.compile("^select --field --opponent$");
        Pattern patternForSelectRivalFieldZoneSpell2 = Pattern.compile("^select --opponent --field$");
        Pattern patternForSelectFromOwnHand = Pattern.compile("^select --hand$");
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

