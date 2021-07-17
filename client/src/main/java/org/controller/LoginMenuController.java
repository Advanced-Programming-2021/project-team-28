package org.controller;

import org.MainClient;
import org.model.*;
import org.view.LoginMenuView;

import java.io.IOException;
import java.util.Objects;
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
            switch (Objects.requireNonNull(result)) {
                case "usernameExists":
                    loginMenuView.usernameExists(username);
                    break;
                case "nicknameExists":
                    loginMenuView.nicknameExists(nickname);
                    break;
                default:
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

    public void saveDatabase() {
        CreateNewCard.serialize();
        MonsterCard.serialize();
        SpellCard.serialize();
        TrapCard.serialize();
        Deck.serialize();
        User.serialize();
    }
}
