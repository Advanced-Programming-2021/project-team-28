package org.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.MainClient;
import org.controller.LoginMenuController;
import org.model.AllCardsInitiator;
import org.model.Card;

import javax.swing.*;
import java.io.IOException;


public class LoginMenuView extends Application {
    private static Stage primaryStage;
    @FXML
    private PasswordField passwordForLogin;
    @FXML
    private TextField usernameForLogin;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private TextField nickname;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button exit;

    private static MediaPlayer player;

    private static boolean isMusicStarted = false;

    private final LoginMenuController CONTROLLER;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public LoginMenuView(){
        CONTROLLER = new LoginMenuController(this);
    }

    public void run() throws Exception {
        launch();
    }

    public void showCurrentMenu() {
        System.out.println("Login Menu");
    }

    public void invalidCommand() {
        System.out.println("invalid command");
    }

    public void pleaseLoginFirst() {
        System.out.println("Please login first");
    }

    public void usernameExists(String username) {
        JOptionPane.showMessageDialog(null, "user with username " + username + " already exists");
    }

    public void nicknameExists(String nickname) {
        JOptionPane.showMessageDialog(null,"user with nickname " + nickname + " already exists");
    }

    public void userCreated() {
        JOptionPane.showMessageDialog(null,"user created successfully!");
    }

    public void usernameAndPasswordDidNotMatch() {
        JOptionPane.showMessageDialog(null,"Username and password didn’t match!");
    }

    @Override
    public  void start(Stage stage) throws Exception {

        AnchorPane parent = FXMLLoader.load(getClass().getResource("/mainclass/FXML/loginmenu.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        primaryStage = stage;
        stage.setScene(scene);
        stage.setTitle("Yu-Gi-Oh");
        //musicStarter();
        stage.show();
        stage.getIcons().add(new Image(getClass().getResource("/logos/yugioh.jpg").toExternalForm()));
        Card.getCardsAndImages().clear();
        AllCardsInitiator.addMonstersToImageArrayList();
        AllCardsInitiator.addSpellTrapToImageArrayList();
    }

    private void musicStarter() {
        if(!isMusicStarted) {
            player = new MediaPlayer(new Media(getClass().getResource("/sound/SanAndreas.mp3").toExternalForm()));
            player.setOnEndOfMedia(new Runnable() {
                public void run() {
                    player.seek(Duration.ZERO);
                }
            });
            player.setVolume(0.2);
            player.play();
            isMusicStarted = true;
        }
    }

    public void initialize(){
        buttonSet();
    }

    private void buttonSet() {
        loginButton.setOnMouseEntered(mouseEvent -> loginButton.setEffect(new Lighting()));
        loginButton.setOnMouseExited(mouseEvent -> loginButton.setEffect(null));
        registerButton.setOnMouseEntered(mouseEvent -> registerButton.setEffect(new Lighting()));
        registerButton.setOnMouseExited(mouseEvent -> registerButton.setEffect(null));
        exit.setOnMouseEntered(mouseEvent -> exit.setEffect(new Lighting()));
        exit.setOnMouseExited(mouseEvent -> exit.setEffect(null));
        loginButton.setOnMouseEntered(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("/sound/gga.mp3").toExternalForm()));
                player.play();
            }
        });
        registerButton.setOnMouseEntered(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("/sound/gga.mp3").toExternalForm()));
                player.play();
            }
        });
        exit.setOnMouseEntered(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("/sound/gga.mp3").toExternalForm()));
                player.play();
            }
        });
    }

    public void signup() {
        try {
            if (password.getText().equals("") || username.getText().equals("") || nickname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter username, password and nickname first.");
            } else {
                CONTROLLER.controlCreateUserCommand(username.getText() , password.getText() , nickname.getText());
                username.clear();
                password.clear();
                nickname.clear();
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public void login() {
        try {
            if (passwordForLogin.getText().equals("") || usernameForLogin.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter username and password first.");
            } else {
                CONTROLLER.controlLoginUserCommand(usernameForLogin.getText() , passwordForLogin.getText());
                usernameForLogin.clear();
                passwordForLogin.clear();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void exitGame() {
        try {
            MainClient.getSocket().close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.exit(0);
    }



}
