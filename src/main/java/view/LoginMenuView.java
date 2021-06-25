package view;

import controller.LoginMenuController;
import controller.MenuEnum;

import java.util.Scanner;

public class LoginMenuView {

    LoginMenuController controller;

    public LoginMenuView(LoginMenuController controller) {
        this.controller = controller;
    }

    public void run() throws Exception {
        Scanner scanner = ScannerInstance.getInstance().getScanner();
        String command;
        while (true) {
            command = scanner.nextLine();
            if (controller.processCommand(command).equals(MenuEnum.BACK)) {
                return;
            }
        }
    }

    public void showCurrentMenu() {
        System.out.println("Login Menu");
    }

    public void invalidCommand() {
        System.out.println("invalid command");
    }

    public void pleaseLoginFirst() {
        System.out.println("Please login first");
    }

    public void usernameExists(String username) {
        System.out.println("user with username " + username + " already exists");
    }

    public void nicknameExists(String nickname) {
        System.out.println("user with nickname " + nickname + " already exists");
    }

    public void userCreated() {
        System.out.println("user created successfully!");
    }

    public void usernameAndPasswordDidNotMatch() {
        System.out.println("Username and password didn’t match!");
    }

    public void userLoggedIn() {
        System.out.println("user logged in successfully!");
    }
}
