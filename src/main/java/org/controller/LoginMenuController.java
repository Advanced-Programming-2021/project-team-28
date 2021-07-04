package org.controller;

import org.model.*;
import org.view.LoginMenuView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {
    LoginMenuView loginMenuView;

    public LoginMenuController (LoginMenuView view){
        this.loginMenuView = view;
    }

    public void run() throws Exception {

        this.loginMenuView.run();
        MonsterCard.serialize();
        SpellCard.serialize();
        TrapCard.serialize();
        Deck.serialize();
        User.serialize();
    }

    private void createResourceFileIfNeeded() throws IOException {
        ArrayList<File> files= new ArrayList<>();
        files.add(new File("src/UserOutput.json"));
        files.add(new File("src/MonsterCardsOutput.json"));
        files.add(new File("src/SpellCardsOutput.json"));
        files.add(new File("src/TrapCardsOutput.json"));
        files.add(new File("src/DecksOutput.json"));
        File cardsFolder = new File("src/ExportedCards");
        cardsFolder.mkdir();
        for (File file : files)
        if(!file.exists()){
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("[]");
            writer.close();
        }

    }

    public MenuEnum processCommand(String command) throws Exception {
        String username = "username";
        String password = "password";
        String nickname = "nickname";
        Matcher[] commandMatchers = getCommandMatchers(command);
        if (commandMatchers[0].find()) {
            return MenuEnum.BACK;
        } else if (commandMatchers[1].find()) {
            loginMenuView.showCurrentMenu();
            return MenuEnum.CONTINUE;
        } else if (commandMatchers[18].find()){
            loginMenuView.pleaseLoginFirst();
            return MenuEnum.CONTINUE;
        }
        for (int i=2 ; i<14; i++){
            if(commandMatchers[i].find()){
                controlCreateUserCommand(commandMatchers[i].group(username), commandMatchers[i].group(password), commandMatchers[i].group(nickname));
                return MenuEnum.CONTINUE;
            }
        }
        for (int i=14; i<18; i++){
            if(commandMatchers[i].find()){
                controlLoginUserCommand(commandMatchers[i].group(username), commandMatchers[i].group(password));
                return MenuEnum.CONTINUE;
            }
        }
        loginMenuView.invalidCommand();
        return MenuEnum.CONTINUE;
    }

    private void controlLoginUserCommand(String username, String password) throws Exception {
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
        MonsterCard.serialize();
        SpellCard.serialize();
        TrapCard.serialize();
        Deck.serialize();
        User.serialize();
    }
}
