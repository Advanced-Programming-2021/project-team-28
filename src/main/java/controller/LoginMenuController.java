package controller;

import model.User;
import view.LoginMenuView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {
    LoginMenuView loginMenuView = new LoginMenuView();

    public MenuEnum run(String command) {
        Matcher[] commandMatchers = getCommandMatchers(command);
        if (commandMatchers[0].find()) {
            return MenuEnum.BACK;
        } else if (commandMatchers[1].find()) {
            loginMenuView.showCurrentMenu();
        } else if (commandMatchers[2].find()) {
            controlCreateUserCommand(commandMatchers[2].group(1), commandMatchers[2].group(2), commandMatchers[2].group(3));
        } else if (commandMatchers[3].find()) {
            controlCreateUserCommand(commandMatchers[3].group(1), commandMatchers[3].group(3), commandMatchers[3].group(2));
        } else if (commandMatchers[4].find()) {
            controlCreateUserCommand(commandMatchers[4].group(2), commandMatchers[4].group(1), commandMatchers[4].group(3));
        } else if (commandMatchers[5].find()) {
            controlCreateUserCommand(commandMatchers[5].group(3), commandMatchers[5].group(1), commandMatchers[5].group(2));
        } else if (commandMatchers[6].find()) {
            controlCreateUserCommand(commandMatchers[6].group(3), commandMatchers[6].group(2), commandMatchers[6].group(1));
        } else if (commandMatchers[7].find()) {
            controlCreateUserCommand(commandMatchers[7].group(2), commandMatchers[7].group(3), commandMatchers[7].group(1));
        } else if (commandMatchers[8].find()) {
            controlLoginUserCommand(commandMatchers[8].group(1), commandMatchers[8].group(2));
        } else if (commandMatchers[9].find()) {
            controlLoginUserCommand(commandMatchers[9].group(2), commandMatchers[9].group(1));
        }else if(commandMatchers[10].find()){
            loginMenuView.pleaseLoginFirst();
        } else {
            loginMenuView.invalidCommand();
        }
        return MenuEnum.CONTINUE;
    }

    private void controlLoginUserCommand(String username, String password) {
        if(User.isUsernameAvailable(username) || !User.getUserByUsername(username).getPassword().equals(password)){
            loginMenuView.usernameAndPasswordDidNotMatch();
        } else {
            loginMenuView.userLoggedIn();
            new MainMenuController(User.getUserByUsername(username)).run();
        }
    }

    private void controlCreateUserCommand(String username, String password, String nickname) {
        if(!User.isUsernameAvailable(username)){
            loginMenuView.usernameExists(username);
        } else if(!User.isNicknameAvailable(nickname)){
            loginMenuView.nicknameExists(nickname);
        } else {
            new User(username, password, nickname);
            loginMenuView.userCreated();
        }
    }

    private Matcher[] getCommandMatchers(String command) {
        Pattern patternForExit = Pattern.compile("^menu exit$");
        Pattern patternForShowCurrentMenu = Pattern.compile("^menu show-current$");
        Pattern patternForCreateUser1 = Pattern.compile("^user create --username (\\S+) --password (\\S+) --nickname (\\S+)$");
        Pattern patternForCreateUser2 = Pattern.compile("^user create --username (\\S+) --nickname (\\S+) --password (\\S+)$");
        Pattern patternForCreateUser3 = Pattern.compile("^user create --password (\\S+) --username (\\S+) --nickname (\\S+)$");
        Pattern patternForCreateUser4 = Pattern.compile("^user create --password (\\S+) --nickname (\\S+) --username (\\S+)$");
        Pattern patternForCreateUser5 = Pattern.compile("^user create --nickname (\\S+) --password (\\S+) --username (\\S+)$");
        Pattern patternForCreateUser6 = Pattern.compile("^user create --nickname (\\S+) --nickname (\\S+) --password (\\S+)$");
        Pattern patternForLoginUser1 = Pattern.compile("^user login --username (\\S+) --password (\\S+)$");
        Pattern patternForLoginUser2 = Pattern.compile("^user login --password (\\S+) --username (\\S+)$");
        Pattern patternForEnterAnotherMenu = Pattern.compile("^menu enter (Duel|Scoreboard|Deck|Import/Export|Shop|Profile)$");
        Matcher[] commandMatchers = new Matcher[11];
        commandMatchers[0] = patternForExit.matcher(command);
        commandMatchers[1] = patternForShowCurrentMenu.matcher(command);
        commandMatchers[2] = patternForCreateUser1.matcher(command);
        commandMatchers[3] = patternForCreateUser2.matcher(command);
        commandMatchers[4] = patternForCreateUser3.matcher(command);
        commandMatchers[5] = patternForCreateUser4.matcher(command);
        commandMatchers[6] = patternForCreateUser5.matcher(command);
        commandMatchers[7] = patternForCreateUser6.matcher(command);
        commandMatchers[8] = patternForLoginUser1.matcher(command);
        commandMatchers[9] = patternForLoginUser2.matcher(command);
        commandMatchers[10] = patternForEnterAnotherMenu.matcher(command);
        return commandMatchers;
    }
}
