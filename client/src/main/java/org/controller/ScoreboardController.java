package org.controller;

import org.MainClient;
import org.model.Scoreboard;
import org.model.User;
import org.model.Utilities;
import org.view.ScoreboardView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
        try {
            return  (ArrayList<User>) LoginMenuController.sendAndReceive("get sorted users " + MainClient.getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser() {
        return user;
    }
}
