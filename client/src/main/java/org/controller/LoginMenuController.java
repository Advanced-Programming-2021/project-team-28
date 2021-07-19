package org.controller;

import org.MainClient;
import org.model.*;
import org.view.LoginMenuView;

import javax.swing.*;
import java.io.IOException;

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

    public void controlLoginUserCommand(String username, String password) throws Exception {
        try {
            String result = (String) sendAndReceive("user login -u " + username + " -p " + password);
            if(!result.equals("error")) {
                MainClient.setToken(result);
                User user = (User) sendAndReceive("get user " + username);
                new MainMenuController(user).run();
            } else {
                loginMenuView.usernameAndPasswordDidNotMatch();
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Server error");
            e.printStackTrace();
        }
    }

    public void controlCreateUserCommand(String username, String password, String nickname) {
        try {
            String result = (String) sendAndReceive("user create -u " + username + " -p " + password + " -n " + nickname);
            switch (result) {
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

    public static Object sendAndReceive(String command) throws IOException {
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
