package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controller.MainMenuController;

import java.util.Scanner;

public class MainMenuView extends Application {
    private static Stage primaryStage;
    MainMenuController controller;
    public Text userText;

    public MainMenuView(MainMenuController controller) {
        this.controller = controller;
    }


    private Scanner scanner = ScannerInstance.getInstance().getScanner();

    public void run() throws Exception {
        start(LoginMenuView.getPrimaryStage());
    }


    public void showMenu() {
        System.out.println("Main Menu");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void cheatActivated() {
        System.out.println("Cheat activated");
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/mainmenu.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        setUserText();
        stage.setScene(scene);
        stage.show();
    }

    public void logout() {

        LoginMenuView loginMenuView = new LoginMenuView();
        try {
            loginMenuView.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newDuel() {

    }

    public void shop() {

    }

    public void deck() {
        try {
            controller.processCommand("menu enter Deck");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scoreBoard() {
        try {
            controller.processCommand("menu enter Scoreboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profile() {

    }

    public void importExport() {
    }

    public void setUserText() {
        userText.setText("User logged in: " + controller.getUser().getUsername() + "\nknown as: " + controller.getUser().getNickname());

    }
}
