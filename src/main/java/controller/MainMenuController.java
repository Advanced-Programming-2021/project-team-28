package controller;

import model.Game;
import Enums.NumberOfRounds;
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

        if (command.equals("menu show-current")) {
            view.showMenu();
            return;
        } else if (command.equals("scoreboard show")) {
            //TODO : complete the functions

        } else if (command.startsWith("menu enter ")) {
            Matcher matcher = getCommandMatcher(command, "^menu enter (.+)");
            if (matcher.group(1).equals("Deck")) {
                new DeckMenuController(user).run();
            }
            if (matcher.group(1).equals("Profile")) {
                ProfileMenuController profile = new ProfileMenuController(this.user);
                profile.run();
            }
            if (matcher.group(1).equals("Shop")) {
                new ShopController(user).run();
            }
            if (matcher.group(1).equals("Import/Export")) {

            }
        }
        else if(command.startsWith("duel")){
            Matcher matcher = getCommandMatcher(command , "^duel --new --second-player (.+) --rounds (.+)$");
            if(validateMatch(matcher) != NumberOfRounds.OTHERS){
                Game game = new Game(user , User.getUserByUsername(matcher.group(2)) , validateMatch(matcher));
                game.run();
            }
        }
    }


    private static Matcher getCommandMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        if (matcher.find())
            return matcher;
        else
            return null;
    }

    private NumberOfRounds validateMatch(Matcher matcher){
        String username = matcher.group(1);
        Integer numberOfRounds = Integer.parseInt(matcher.group(2));
        if(User.getUserByUsername(username) == null) {
            view.showError("there is no player with this username");
            return NumberOfRounds.OTHERS;
        }
        else if(!user.hasActiveDeck()){
            view.showError(user.getUsername() + " has no active deck");
            return NumberOfRounds.OTHERS;
        }
        else if(!User.getUserByUsername(username).hasActiveDeck()){
            view.showError(username + " has no active deck");
            return NumberOfRounds.OTHERS;
        }
        else if(!user.getActiveDeck().isDeckValid()){
            view.showError(user.getUsername() + "'s deck is invalid");
            return NumberOfRounds.OTHERS;
        }
        else if(!User.getUserByUsername(username).getActiveDeck().isDeckValid()){
            view.showError(username + "'s deck is invalid");
            return NumberOfRounds.OTHERS;
        }
        else if(numberOfRounds != 1 && numberOfRounds != 3){
            view.showError("number of rounds not supported");
            return NumberOfRounds.OTHERS;
        }
        else if(numberOfRounds == 3)
            return NumberOfRounds.THREE_ROUND_MATCH;

        else return NumberOfRounds.ONE_ROUND_MATCH;
    }
}
