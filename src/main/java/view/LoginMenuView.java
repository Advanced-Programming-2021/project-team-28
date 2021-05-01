package view;

import controller.LoginMenuController;
import controller.MenuEnum;

import java.util.Scanner;

public class LoginMenuView {


    public void run(){
        LoginMenuController controller = new LoginMenuController();
        Scanner scanner = new Scanner(System.in);
        String command;
        while(true){
            command = scanner.nextLine();
            if(controller.run(command).equals(MenuEnum.BACK)){
                return;
            }
        }
    }
    public void showCurrentMenu(){
        System.out.println("Login Menu");
    }

    public void invalidCommand(){
        System.out.println("invalid command");
    }

    public void pleaseLoginFirst(){
        System.out.println("please login first");
    }

    public void usernameExists (String username){
        System.out.println("user with username " + username + " already exists");
    }

    public void nicknameExists (String nickname){
        System.out.println("user with nickname " + nickname + " already exists");
    }

    public void userCreated (){
        System.out.println("user created successfully!");
    }

    public void usernameAndPasswordDidNotMatch(){
        System.out.println("Username and password didn’t match!");
    }

    public void userLoggedIn(){
        System.out.println("user logged in successfully!");
    }
}
