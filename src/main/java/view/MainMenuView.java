package view;

import controller.MainMenuController;

import java.util.Scanner;

public class MainMenuView {

    MainMenuController controller ;

    public MainMenuView(MainMenuController controller){
        this.controller = controller;
    }


    private Scanner scanner = ScannerInstance.getInstance().getScanner();

    private String command;

    public  void run () throws Exception {
        while (true) {
            command = scanner.nextLine();
            command = command.trim();
            if (command.equals("user logout"))
            {
                System.out.println("user logged out successfully");
                return;
            }
            controller.processCommand(command);
        }
    }



    public void showMenu (){
        System.out.println("Main Menu");
    }

    public void showError(String error){
        System.out.println(error);
    }

    public void cheatActivated(){
        System.out.println("Cheat activated");
    }

}
