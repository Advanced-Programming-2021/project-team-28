package org.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controller.DeckMenuController;
import org.controller.ImportExportController;
import org.controller.MainMenuController;
import org.model.Admin;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class MainMenuView extends Application {

    @FXML
    private Button start;
    @FXML
    private Button shop;
    @FXML
    private Button deck;
    @FXML
    private Button profile;
    @FXML
    private Button logout;
    @FXML
    private Button score;
    @FXML
    private Button export;
    @FXML
    private CheckBox oneRound;
    @FXML
    private CheckBox threeRound;
    @FXML
    private TextField opponentUsername;

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
        System.out.println("MainServer Menu");
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(null, error);
    }

    public void cheatActivated() {
        System.out.println("Cheat activated");
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/mainmenu2.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        buttonSet();
        setUserText();
        stage.setScene(scene);
        stage.show();
        setNumberOfRoundBoxes();
        setAdminModeIfNeeded();
    }

    private void setAdminModeIfNeeded() {
        if(controller.getUser() instanceof Admin) {
            shop.setText("Shop / Admin Panel");
        }
    }

    private void setNumberOfRoundBoxes() {
        oneRound.setSelected(true);
        oneRound.setOnMouseClicked(mouseEvent -> threeRound.setSelected(false));
        threeRound.setOnMouseClicked(mouseEvent -> oneRound.setSelected(false));
    }

    private void buttonSet() {
        setEffectForButton(logout);
        setEffectForButton(export);
        setEffectForButton(start);
        setEffectForButton(score);
        setEffectForButton(profile);
        setEffectForButton(deck);
        setEffectForButton(shop);
    }

    public void setEffectForButton(Button button){
        button.setOnMouseEntered(mouseEvent -> button.setEffect(new Lighting()));
        button.setOnMouseEntered(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("/sound/gga.mp3").toExternalForm()));
                player.play();
            }
        });
        button.setOnMouseExited(mouseEvent -> button.setEffect(null));
    }

    public void logout() {
        controller.controlLogoutCommand();
    }

    public void newDuel() {
        if(opponentUsername.getText() == null || opponentUsername.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter opponent username first");
        } else if ((!oneRound.isSelected()) && (!threeRound.isSelected())){
            JOptionPane.showMessageDialog(null, "Select number of rounds first");
        } else {
            int numberOfRounds = oneRound.isSelected() ? 1 : 3;
            try {
                controller.processCommand("duel --new --second-player " + opponentUsername.getText() + " --rounds " + numberOfRounds);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void shop() {
        try {
            controller.processCommand("menu enter Shop");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deck() throws Exception {
        new DeckMenuController(controller.getUser()).run();
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
        try {
            controller.processCommand("menu enter Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importExport() {
        new ImportExportController(controller.getUser()).run();
    }

    public void setUserText() {
        userText.setText("User Logged In: " + "\n" +controller.getUser().getUsername() + "\n\nKnown as: " + "\n" + controller.getUser().getNickname());
    }
}
