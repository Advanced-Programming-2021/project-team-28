package org.model;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    private static Admin instance;

    private Admin (String username, String password, String nickname) {
        super(username, password, nickname);
    }

    public static Admin getInstance() {
        if(instance == null) {
            instance = new Admin("administrator", "administrator", "administrator");
        }
        return instance;
    }
}
