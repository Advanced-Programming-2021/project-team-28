package controller;

import model.Game;
import model.enums.NumberOfRounds;
import model.User;
import view.MainMenuView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuController {

    User user;
    MainMenuView view = new MainMenuView(this);

    public MainMenuController(User user) {
        this.user = user;
    }


    public void run() throws Exception {
        this.view.run();
    }


    public void processCommand(String command) throws Exception {
        Matcher[] duelCommandMatchers = getDuelCommandMatchers(command);
        if (command.equals("menu show-current")) {
            view.showMenu();
        } else if (command.equalsIgnoreCase("hesoyam")){
            user.changeBalance(250000);
            view.cheatActivated();
        } else if (command.equals("menu enter Deck")) {
            new DeckMenuController(user).run();
        } else if (command.equals("menu enter Profile")) {
            new ProfileMenuController(user).run();
        } else if (command.equals("menu enter Shop")) {
            new ShopController(user).run();
        } else if (command.equals("menu enter Import/Export")) {
            new ImportExportController(user).run();
        } else if (command.equals("menu enter Scoreboard")) {
            new ScoreboardController().run();
        } else {
            for (int i=0; i<12; i++){
                if(duelCommandMatchers[i].find()){
                    if(validateMatch(duelCommandMatchers[i]) != NumberOfRounds.OTHERS){
                        new Game(user, User.getUserByUsername(duelCommandMatchers[i].group("secondPlayer")), validateMatch(duelCommandMatchers[i])).run();
                    }
                    return;
                }
            }
            for (int i=12; i<14; i++){
                if (duelCommandMatchers[i].find()) {
                    user.changeBalance(Integer.parseInt(duelCommandMatchers[i].group(1)));
                    view.cheatActivated();
                    return;
                }
            }
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
        Pattern pattern6 = Pattern.compile("^duel -n -s-p (?<secondPlayer>.+) -r (?<numberOfRounds>\\d+)$");
        Pattern pattern7 = Pattern.compile("^duel -n -r (?<numberOfRounds>\\d+) -s-p (?<secondPlayer>.+)$");
        Pattern pattern8 = Pattern.compile("^duel -s-p (?<secondPlayer>.+) -n -r (?<numberOfRounds>\\d+)$");
        Pattern pattern9 = Pattern.compile("^duel -s-p (?<secondPlayer>.+) -r (?<numberOfRounds>\\d+) -n$");
        Pattern pattern10 = Pattern.compile("^duel -r (?<numberOfRounds>\\d+) -n -s-p (?<secondPlayer>.+)$");
        Pattern pattern11 = Pattern.compile("^duel -r (?<numberOfRounds>\\d+) -s-p (?<secondPlayer>.+) -n$");
        Pattern patternForIncreaseMoney = Pattern.compile("^increase --money (\\d+)$");
        Pattern patternForIncreaseMoney2 = Pattern.compile("^increase -m (\\d+)$");
        Matcher[] matchers = new Matcher[14];
        matchers[0] = pattern0.matcher(command);
        matchers[1] = pattern1.matcher(command);
        matchers[2] = pattern2.matcher(command);
        matchers[3] = pattern3.matcher(command);
        matchers[4] = pattern4.matcher(command);
        matchers[5] = pattern5.matcher(command);
        matchers[6] = pattern6.matcher(command);
        matchers[7] = pattern7.matcher(command);
        matchers[8] = pattern8.matcher(command);
        matchers[9] = pattern9.matcher(command);
        matchers[10] = pattern10.matcher(command);
        matchers[11] = pattern11.matcher(command);
        matchers[12] = patternForIncreaseMoney.matcher(command);
        matchers[13] = patternForIncreaseMoney2.matcher(command);
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
