package view;

import java.util.Scanner;

public class TrapEffectsView {
    Scanner scanner = ScannerInstance.getInstance().getScanner();

    public String scanCardName (){
        return scanner.nextLine();
    }

    public void enterCardName (){
        System.out.print("Enter card name: ");
    }

    public void invalidCardName(){
        System.out.println("Invalid card name. enter again");
    }
}
