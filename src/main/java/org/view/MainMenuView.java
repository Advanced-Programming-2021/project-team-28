package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controller.MainMenuController;

import java.util.Scanner;

public class MainMenuView extends Application {

    MainMenuController controller ;

    public MainMenuView(MainMenuController controller){
        this.controller = controller;
    }


    private Scanner scanner = ScannerInstance.getInstance().getScanner();

    public  void run () throws Exception {
        start(LoginMenuView.getPrimaryStage());
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

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/mainmenu.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

}
