package view;

import controller.ImportExportController;
import enums.MenuEnum;

import java.util.Scanner;

public class ImportExportView {
    ImportExportController controller;

    public ImportExportView (ImportExportController controller){
        this.controller = controller;
    }

    public void run(){
        Scanner scanner = ScannerInstance.getInstance().getScanner();
        String command;
        while(true){
            command = scanner.nextLine();
            if(controller.processCommand(command) == MenuEnum.BACK){
                return;
            }
        }
    }

    public void menuShowCurrent(){
        System.out.println("Import/Export Menu");
    }

    public void invalidCommand(){
        System.out.println("invalid command");
    }

    public void impossibleMenuNavigation(){
        System.out.println("impossible menu navigation");
    }

    public void cardWithThisNameDoesNotExist() {
        System.out.println("Card with this name does not exist");
    }

    public void youDoNotHaveThisCard(){
        System.out.println("You don't have this card");
    }

    public void exportedSuccessfully(){
        System.out.println("Card exported successfully");
    }

    public void youDoNotHaveThisCardForImport(){
        System.out.println("You don't have this card for import");
    }

    public void cardImportedAndAddedToYourCards(){
        System.out.println("Card imported and added to your cards");
    }

    public void youAlreadyHaveThisCard(){
        System.out.println("You already have this card");
    }
}
