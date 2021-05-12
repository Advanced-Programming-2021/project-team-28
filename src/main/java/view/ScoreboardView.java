package view;

import enums.MenuEnum;
import controller.ScoreboardController;

import java.util.Scanner;

public class ScoreboardView {

    ScoreboardController controller;

    public ScoreboardView (ScoreboardController controller){
        this.controller = controller;
    }

    public void run (){
        Scanner scanner = ScannerInstance.getInstance().getScanner();
        String command;
        while(true){
            command = scanner.nextLine();
            if(controller.processCommand(command).equals(MenuEnum.BACK)){
                return;
            }
        }
    }

    public void printScoreboard(String scoreboard){
        System.out.println(scoreboard);
    }

    public void impossibleMenuNavigation (){
        System.out.println("menu navigation is not possible");
    }

    public void invalidCommand(){
        System.out.println("invalid command");
    }

    public void showCurrentMenu(){
        System.out.println("Scoreboard Menu");
    }
}
