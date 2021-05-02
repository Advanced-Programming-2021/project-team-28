package view;

import controller.MainMenuController;

import java.util.Scanner;

public class MainMenuView {

    MainMenuController controller ;

    public MainMenuView(MainMenuController controller){
        this.controller = controller;
    }


    private  Scanner scanner = new Scanner(System.in);

    private  String command;




    public  void run () {
        while (true) {
            command = scanner.nextLine();
            command = command.trim();
            if (command.equals("user logout"))
            {
                System.out.println("user logged out successfully");
                break;
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

}
