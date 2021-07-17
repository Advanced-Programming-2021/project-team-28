package serverController;


import model.*;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {


    private void controlLoginUserCommand(String username, String password) throws Exception {
        if (User.isUsernameAvailable(username) || !User.getUserByUsername(username).getPassword().equals(password)) {
//            loginMenuView.usernameAndPasswordDidNotMatch();
        } else {
//            new MainMenuController(User.getUserByUsername(username)).run();
        }
    }

    //TODO
    public String controlCreateUserCommand(String username, String password, String nickname) {

        if (!User.isUsernameAvailable(username)) {
            return "usernameExists";
        } else if (!User.isNicknameAvailable(nickname)) {
            return "nicknameExists";
        } else {
            new User(username, password, nickname);
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

