package controller;

import model.Game;
import enums.NumberOfRounds;
import model.User;
import view.MainMenuView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuController {

    User user;
    MainMenuView view = new MainMenuView(this);

    MainMenuController(User user) {
        this.user = user;
    }


    public void run() throws CloneNotSupportedException {
        this.view.run();
    }


    public void processCommand(String command) throws CloneNotSupportedException {
        Matcher[] duelCommandMatchers = getDuelCommandMatchers(command);
        if (command.equals("menu show-current")) {
            view.showMenu();
        } else if (command.equals("menu enter Deck")) {
            new DeckMenuController(user).run();
        } else if (command.equals("menu enter Profile")) {
            new ProfileMenuController(user).run();
        } else if (command.equals("menu enter Shop")) {
            new ShopController(user).run();
        } else if (command.equals("menu enter Import/Export")) {

        } else if (command.equals("menu enter Scoreboard")) {
            new ScoreboardController().run();
        } else if (duelCommandMatchers[0].find()) {
            if(validateMatch(duelCommandMatchers[0]) != NumberOfRounds.OTHERS){
                new Game(user, User.getUserByUsername(duelCommandMatchers[0].group("secondPlayer")), validateMatch(duelCommandMatchers[0])).run();
            }
        } else if (duelCommandMatchers[1].find()) {
            if(validateMatch(duelCommandMatchers[1]) != NumberOfRounds.OTHERS){
                new Game(user, User.getUserByUsername(duelCommandMatchers[1].group("secondPlayer")), validateMatch(duelCommandMatchers[1])).run();
            }
        } else if (duelCommandMatchers[2].find()) {
            if(validateMatch(duelCommandMatchers[2]) != NumberOfRounds.OTHERS){
                new Game(user, User.getUserByUsername(duelCommandMatchers[2].group("secondPlayer")), validateMatch(duelCommandMatchers[2])).run();
            }
        } else if (duelCommandMatchers[3].find()) {
            if(validateMatch(duelCommandMatchers[3]) != NumberOfRounds.OTHERS){
                new Game(user, User.getUserByUsername(duelCommandMatchers[3].group("secondPlayer")), validateMatch(duelCommandMatchers[3])).run();
            }
        } else if (duelCommandMatchers[4].find()) {
            if(validateMatch(duelCommandMatchers[4]) != NumberOfRounds.OTHERS){
                new Game(user, User.getUserByUsername(duelCommandMatchers[4].group("secondPlayer")), validateMatch(duelCommandMatchers[4])).run();
            }
        } else if (duelCommandMatchers[5].find()) {
            if(validateMatch(duelCommandMatchers[5]) != NumberOfRounds.OTHERS){
                new Game(user, User.getUserByUsername(duelCommandMatchers[5].group("secondPlayer")), validateMatch(duelCommandMatchers[5])).run();
            }
        } else {
            view.showError("invalid command");
        }
    }


    private static Matcher[] getDuelCommandMatchers(String command) {
        Pattern pattern0 = Pattern.compile("^duel --new --second-player (?<secondPlayer>.+) --rounds (?<numberOfRounds>\\d+)$");
        Pattern pattern1 = Pattern.compile("^duel --new --rounds (?<numberOfRounds>\\d+) --second-player (?<secondPlayer>.+)$");
        Pattern pattern2 = Pattern.compile("^duel --second-player (?<secondPlayer>.+) --new --rounds (?<numberOfRounds>\\d+)$");
        Pattern pattern3 = Pattern.compile("^duel --second-player (?<secondPlayer>.+) --rounds (?<numberOfRounds>\\d+) --new$");
        Pattern pattern4 = Pattern.compile("^duel --rounds (?<numberOfRounds>\\d+) --new --second-player (?<secondPlayer>.+)$");
        Pattern pattern5 = Pattern.compile("^duel --rounds (?<numberOfRounds>\\d+) --second-player (?<secondPlayer>.+) --new$");
        Matcher[] matchers = new Matcher[6];
        matchers[0] = pattern0.matcher(command);
        matchers[1] = pattern1.matcher(command);
        matchers[2] = pattern2.matcher(command);
        matchers[3] = pattern3.matcher(command);
        matchers[4] = pattern4.matcher(command);
        matchers[5] = pattern5.matcher(command);
        return matchers;
    }

    private NumberOfRounds validateMatch(Matcher matcher) {
        String username = matcher.group("secondPlayer");
        int numberOfRounds = Integer.parseInt(matcher.group("numberOfRounds"));
        if (User.getUserByUsername(username) == null) {
            view.showError("there is no player with this username");
            return NumberOfRounds.OTHERS;
        } else if (!user.hasActiveDeck()) {
            view.showError(user.getUsername() + " has no active deck");
            return NumberOfRounds.OTHERS;
        } else if (!User.getUserByUsername(username).hasActiveDeck()) {
            view.showError(username + " has no active deck");
            return NumberOfRounds.OTHERS;
        } else if (!user.getActiveDeck().isDeckValid()) {
            view.showError(user.getUsername() + "'s deck is invalid");
            return NumberOfRounds.OTHERS;
        } else if (!User.getUserByUsername(username).getActiveDeck().isDeckValid()) {
            view.showError(username + "'s deck is invalid");
            return NumberOfRounds.OTHERS;
        } else if (numberOfRounds != 1 && numberOfRounds != 3) {
            view.showError("number of rounds not supported");
            return NumberOfRounds.OTHERS;
        } else if (numberOfRounds == 3)
            return NumberOfRounds.THREE_ROUND_MATCH;

        else return NumberOfRounds.ONE_ROUND_MATCH;
    }
}
