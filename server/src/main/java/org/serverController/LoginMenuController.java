package org.serverController;


import org.MainServer;
import org.model.*;

import java.util.UUID;

public class LoginMenuController {


    public Object controlLoginUserCommand(String username, String password) {
        String result = "error";
        if (!(User.isUsernameAvailable(username) || !User.getUserByUsername(username).getPassword().equals(password))) {
            result = UUID.randomUUID().toString();
            MainServer.getTokens().put(result, username);
        }
        return result;
    }


    public synchronized String controlCreateUserCommand(String username, String password, String nickname) {
        if (!User.isUsernameAvailable(username)) {
            return "usernameExists";
        } else if (!User.isNicknameAvailable(nickname)) {
            return "nicknameExists";
        } else {
            new User(username, password, nickname);
            System.out.println("User created successfully");
            return "userCreated";
        }
    }


    //TODO
    public void saveDatabase() {
//        CreateNewCard.serialize();
        MonsterCard.serialize();
        SpellCard.serialize();
        TrapCard.serialize();
        Deck.serialize();
        User.serialize();
    }
}

