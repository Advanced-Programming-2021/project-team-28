package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controller.LoginMenuController;
import org.model.AllCardsInitiator;

import javax.swing.*;



public class LoginMenuView extends Application {
    private static Stage primaryStage;
    @FXML
    private AnchorPane anchorPane;
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
        AnchorPane parent = FXMLLoader.load(getClass().getResource("/mainclass/loginmenu.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        primaryStage = stage;
        stage.setScene(scene);
        stage.setTitle("Register Menu");
        stage.show();
        stage.getIcons().add(new Image(getClass().getResource("/logos/yugioh.jpg").toExternalForm()));
        AllCardsInitiator.addMonstersToImageArrayList();
        AllCardsInitiator.addSpellTrapToImageArrayList();
    }

    public void signup() {
        try {
            if (password.getText().equals("") || username.getText().equals("") || nickname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter username, password and nickname first.");
            } else {
                CONTROLLER.processCommand("user create -u " + username.getText() + " -p " + password.getText()
                        + " -n " + nickname.getText());
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
                CONTROLLER.processCommand("user login -u " + usernameForLogin.getText() + " -p " + passwordForLogin.getText());
                usernameForLogin.clear();
                passwordForLogin.clear();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void exitGame() {
        CONTROLLER.saveDatabase();
        System.exit(0);
    }



}
