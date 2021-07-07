package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controller.ImportExportController;
import org.controller.MenuEnum;

import java.util.Scanner;

public class ImportExportView extends Application {
    ImportExportController controller;

    public ImportExportView (ImportExportController controller){
        this.controller = controller;
    }

    public void run(){
        try {
            start(LoginMenuView.getPrimaryStage());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/importExportMenu.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        stage.setScene(scene);
        stage.show();
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

    public void cardFolderNotFound(){
        System.out.println("Cards folder not found");
    }
}
