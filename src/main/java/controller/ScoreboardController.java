package controller;

import model.Scoreboard;
import model.User;
import view.ScoreboardView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreboardController {

    ScoreboardView view = new ScoreboardView(this);
    public void run(){
        this.view.run();
    }

    public MenuEnum processCommand(String command) {
        if(command.equals("scoreboard show")){
            Scoreboard scoreboard = new Scoreboard(User.getUsers());
            view.printScoreboard(scoreboard.toString());
        } else if(command.equals("menu exit")){
            return MenuEnum.BACK;
        } else if (command.equals("menu show-current")) {
            view.showCurrentMenu();
        } else {
            Pattern patternForImpossibleNavigation = Pattern.compile("^menu enter (Duel|Deck|Import/Export|Shop|Profile)$");
            Matcher matcherForImpossibleNavigation = patternForImpossibleNavigation.matcher(command);
            if (matcherForImpossibleNavigation.find()) {
                view.impossibleMenuNavigation();
            } else {
                view.invalidCommand();
            }
        }
        return MenuEnum.CONTINUE;
    }
}
