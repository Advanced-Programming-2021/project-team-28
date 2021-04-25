package view;

import controller.ProfileMenuController;

import java.util.Scanner;

public class ProfileMenuView {

    ProfileMenuController controller;
    Scanner scanner = new Scanner(System.in);
    private String command ;

    public ProfileMenuView(ProfileMenuController controller){
        this.controller = controller;
    }

    public void run(){
        while (true){
            command = scanner.nextLine();
            command = command.trim();
            if (command.equals("menu exit"))
                break;
            controller.processCommand(command);
        }
    }

    public void nicknameExists(String nickname) {
        System.out.println("user with nickname " + nickname + " already exists");
    }
}
