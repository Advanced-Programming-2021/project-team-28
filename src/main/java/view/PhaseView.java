package view;

import controller.PhaseController;
import enums.MenuEnum;

import java.util.Scanner;

public class PhaseView {

    PhaseController controller;

    public PhaseView(PhaseController controller) {
        this.controller = controller;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        String command;
        while(true){
            command = scanner.nextLine();
            if(controller.processCommand(command).equals(MenuEnum.BACK)){
                return;
            }
        }

    }

    public void menuShowCurrent() {
        System.out.println("Duel Menu");
    }

    public void invalidCommand() {
        System.out.println("invalid command");
    }

    public void invalidSelection(){
        System.out.println("invalid selection");
    }

    public void print (String string){
        System.out.println(string);
    }
}
