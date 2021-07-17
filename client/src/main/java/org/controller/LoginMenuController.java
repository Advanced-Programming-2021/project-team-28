package org.controller;

import org.MainClient;
import org.model.*;
import org.view.LoginMenuView;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {
    LoginMenuView loginMenuView;


    public LoginMenuController(LoginMenuView view) {
        this.loginMenuView = view;
    }

    public void run() throws Exception {

        this.loginMenuView.run();
        CreateNewCard.serialize();
        MonsterCard.serialize();
        SpellCard.serialize();
        TrapCard.serialize();
        Deck.serialize();
        User.serialize();
    }

    private void controlLoginUserCommand(String username, String password) throws Exception {
        if (User.isUsernameAvailable(username) || !User.getUserByUsername(username).getPassword().equals(password)) {
            loginMenuView.usernameAndPasswordDidNotMatch();
        } else {
            new MainMenuController(User.getUserByUsername(username)).run();
        }
    }

    public void controlCreateUserCommand(String username, String password, String nickname) {
        try {
            String result = (String) sendAndReceive("user create -u " + username + " -p " + password + " -n " + nickname);
            System.out.println(result);


            switch (result) {
                case "usernameExists":
                    loginMenuView.usernameExists(username);
                    break;
                case "nicknameExists":
                    loginMenuView.nicknameExists(nickname);
                    break;
                case "userCreated":
                    new User(username, password, nickname);
                    loginMenuView.userCreated();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Object sendAndReceive(String command) throws IOException {
        MainClient.getDataOutputStream().writeUTF(command);
        MainClient.getDataOutputStream().flush();
        try {
            return MainClient.getObjectInputStream().readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Matcher[] getCommandMatchers(String command) {
        Pattern patternForExit = Pattern.compile("^menu exit$");
        Pattern patternForShowCurrentMenu = Pattern.compile("^menu show-current$");
        Pattern patternForCreateUser1 = Pattern.compile("^user create -u (?<username>.+?) -p (?<password>.+?) -n (?<nickname>.+?)$");
        Pattern patternForCreateUser2 = Pattern.compile("^user create -u (?<username>.+?) -n (?<nickname>.+?) -p (?<password>.+?)$");
        Pattern patternForCreateUser3 = Pattern.compile("^user create -p (?<password>.+?) -u (?<username>.+?) -n (?<nickname>.+?)$");
        Pattern patternForCreateUser4 = Pattern.compile("^user create -p (?<password>.+?) -n (?<nickname>.+?) -u (?<username>.+?)$");
        Pattern patternForCreateUser5 = Pattern.compile("^user create -n (?<nickname>.+?) -p (?<password>.+?) -u (?<username>.+?)$");
        Pattern patternForCreateUser6 = Pattern.compile("^user create -n (?<nickname>.+?) -u (?<username>.+?) -p (?<password>.+?)$");
        Pattern patternForCreateUser7 = Pattern.compile("^user create --username (?<username>.+?) --password (?<password>.+?) --nickname (?<nickname>.+?)$");
        Pattern patternForCreateUser8 = Pattern.compile("^user create --username (?<username>.+?) --nickname (?<nickname>.+?) --password (?<password>.+?)$");
        Pattern patternForCreateUser9 = Pattern.compile("^user create --password (?<password>.+?) --username (?<username>.+?) --nickname (?<nickname>.+?)$");
        Pattern patternForCreateUser10 = Pattern.compile("^user create --password (?<password>.+?) --nickname (?<nickname>.+?) --username (?<username>.+?)$");
        Pattern patternForCreateUser11 = Pattern.compile("^user create --nickname (?<nickname>.+?) --password (?<password>.+?) --username (?<username>.+?)$");
        Pattern patternForCreateUser12 = Pattern.compile("^user create --nickname (?<nickname>.+?) --username (?<username>.+?) --password (?<password>.+?)$");
        Pattern patternForLoginUser1 = Pattern.compile("^user login --username (?<username>.+?) --password (?<password>.+?)$");
        Pattern patternForLoginUser2 = Pattern.compile("^user login --password (?<password>.+?) --username (?<username>.+?)$");
        Pattern patternForLoginUser3 = Pattern.compile("^user login -u (?<username>.+?) -p (?<password>.+?)$");
        Pattern patternForLoginUser4 = Pattern.compile("^user login -p (?<password>.+?) -u (?<username>.+?)$");
        Pattern patternForEnterAnotherMenu = Pattern.compile("^menu enter (Duel|Scoreboard|Deck|Import/Export|Shop|Profile)$");
        Matcher[] commandMatchers = new Matcher[19];
        commandMatchers[0] = patternForExit.matcher(command);
        commandMatchers[1] = patternForShowCurrentMenu.matcher(command);
        commandMatchers[2] = patternForCreateUser1.matcher(command);
        commandMatchers[3] = patternForCreateUser2.matcher(command);
        commandMatchers[4] = patternForCreateUser3.matcher(command);
        commandMatchers[5] = patternForCreateUser4.matcher(command);
        commandMatchers[6] = patternForCreateUser5.matcher(command);
        commandMatchers[7] = patternForCreateUser6.matcher(command);
        commandMatchers[8] = patternForCreateUser7.matcher(command);
        commandMatchers[9] = patternForCreateUser8.matcher(command);
        commandMatchers[10] = patternForCreateUser9.matcher(command);
        commandMatchers[11] = patternForCreateUser10.matcher(command);
        commandMatchers[12] = patternForCreateUser11.matcher(command);
        commandMatchers[13] = patternForCreateUser12.matcher(command);
        commandMatchers[14] = patternForLoginUser1.matcher(command);
        commandMatchers[15] = patternForLoginUser2.matcher(command);
        commandMatchers[16] = patternForLoginUser3.matcher(command);
        commandMatchers[17] = patternForLoginUser4.matcher(command);
        commandMatchers[18] = patternForEnterAnotherMenu.matcher(command);
        return commandMatchers;
    }

    public void saveDatabase() {
        CreateNewCard.serialize();
        MonsterCard.serialize();
        SpellCard.serialize();
        TrapCard.serialize();
        Deck.serialize();
        User.serialize();
    }
}
