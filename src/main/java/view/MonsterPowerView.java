package view;

import model.MonsterPowers;

import java.util.Scanner;

public class MonsterPowerView {

    private MonsterPowers controller;

    private Scanner scanner = ScannerInstance.getInstance().getScanner();

    public MonsterPowerView(MonsterPowers controller){
        this.controller = controller;
    }

    public int manEaterBug(){
        return scanner.nextInt();
    }

    public void printError(String error){
        System.out.println(error);
    }

    public int intScanner(){
        return Integer.parseInt(scanner.nextLine());
    }

    public void nowItIsRivalTurn(String rivalNickname){
        System.out.println("Now It's " + rivalNickname + "'s turn");
    }
}
