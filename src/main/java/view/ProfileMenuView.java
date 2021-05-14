package view;

import controller.ProfileMenuController;

import java.util.Scanner;

public class ProfileMenuView {

    ProfileMenuController controller;
    Scanner scanner = ScannerInstance.getInstance().getScanner();
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

    public void nicknameChanged(){
        System.out.println("nickname Changed successfully!");
    }

    public void passwordChanged(){
        System.out.println("password Changed successfully!");
    }

    public void wrongPassword(){
        System.out.println("current password is invalid");
    }

    public void passwordIsTheSame(){
        System.out.println("please enter a new password");
    }

    public void showMenu(){
        System.out.println("Profile Menu");
    }

    public void impossibleMenuNavigation(){
        System.out.println("menu navigation is not possible");
    }

    public void invalidCommand(){
        System.out.println("invalid command");
    }
}
