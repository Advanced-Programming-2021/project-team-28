package controller;

import enums.MenuEnum;
import model.*;
import view.LoginMenuView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {
    LoginMenuView loginMenuView = new LoginMenuView(this);

    public void run() throws CloneNotSupportedException, IOException {
        createResourceFileIfNeeded();

        MonsterCard.deserialize();
        SpellCard.deserialize();
        TrapCard.deserialize();
        Deck.deserialize();
        User.deserialize();

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


        for (File file : files)
        if(!file.exists()){
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("[]");
            writer.close();
        }

    }

    public MenuEnum processCommand(String command) throws CloneNotSupportedException {
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

    private void controlLoginUserCommand(String username, String password) throws CloneNotSupportedException {
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
        Pattern patternForCreateUser1 = Pattern.compile("^user create --username (.+?) --password (.+?) --nickname (.+?)$");
        Pattern patternForCreateUser2 = Pattern.compile("^user create --username (.+?) --nickname (.+?) --password (.+?)$");
        Pattern patternForCreateUser3 = Pattern.compile("^user create --password (.+?) --username (.+?) --nickname (.+?)$");
        Pattern patternForCreateUser4 = Pattern.compile("^user create --password (.+?) --nickname (.+?) --username (.+?)$");
        Pattern patternForCreateUser5 = Pattern.compile("^user create --nickname (.+?) --password (.+?) --username (.+?)$");
        Pattern patternForCreateUser6 = Pattern.compile("^user create --nickname (.+?) --username (.+?) --password (.+?)$");
        Pattern patternForLoginUser1 = Pattern.compile("^user login --username (.+?) --password (.+?)$");
        Pattern patternForLoginUser2 = Pattern.compile("^user login --password (.+?) --username (.+?)$");
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
