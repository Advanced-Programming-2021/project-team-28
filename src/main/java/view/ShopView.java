package view;

import controller.ShopController;

import java.util.Scanner;

public class ShopView {
    Scanner scanner = new Scanner(System.in);
    ShopController controller;

    public ShopView (ShopController controller){
        this.controller = controller;
    }

    public void run(){
        String command;
        while (true){

            command = scanner.nextLine().trim();
            if(command.equals("menu exit")) break;
            controller.processCommand(command);
        }
    }


}
