package serverController;


import model.*;

public class LoginMenuController {


    public Object[] controlLoginUserCommand(String username, String password) throws Exception {
        if (User.isUsernameAvailable(username) || !User.getUserByUsername(username).getPassword().equals(password)) {
//            loginMenuView.usernameAndPasswordDidNotMatch();
        } else {
//            new MainMenuController(User.getUserByUsername(username)).run();
        }
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

