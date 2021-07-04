package org.controller;

import org.model.Scoreboard;
import org.model.User;
import org.view.ScoreboardView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreboardController {

    User user ;

    ScoreboardView view = new ScoreboardView(this);

    public void run() throws Exception {
        this.view.run();
    }

    public ScoreboardController(User user) {
        this.user = user;
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

    public ArrayList<User> getSortedUsers() {
        ArrayList<User> sortedUsers = User.getUsers();
        sortedUsers.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getScore() , o2.getScore());
            }
        });
        return sortedUsers;
    }

    public User getUser() {
        return user;
    }
}
