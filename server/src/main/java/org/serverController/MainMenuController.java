package org.serverController;

import org.MainServer;

public class MainMenuController {
    public String logout (String token) {
        if(MainServer.getTokens().containsKey(token)) {
            MainServer.getTokens().remove(token);
            return "success";
        } else {
            return "error";
        }
    }
}
