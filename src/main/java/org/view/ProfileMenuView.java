package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controller.ProfileMenuController;


import java.util.Scanner;

public class ProfileMenuView extends Application {

    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private ImageView profilePicture;


    ProfileMenuController controller;
    Scanner scanner = ScannerInstance.getInstance().getScanner();
    private String command ;


    public ProfileMenuView(ProfileMenuController controller){
        this.controller = controller;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/profile.fxml"));
        Scene scene = new Scene(loader.load());
        fillProfileMenu();
        stage.setScene(scene);
        stage.show();
    }

    private void fillProfileMenu() {
        username.setText("Username : " + controller.getUser().getUsername());
        password.setText("Password : " + controller.getUser().getPassword());
        profilePicture.setImage(new Image(getClass().getResource(controller.getUser().getProfilePicturePath()).toExternalForm()));
    }

    public void run()  {
        try {
            start(LoginMenuView.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        while (true){
//            command = scanner.nextLine();
//            command = command.trim();
//            if (command.equals("menu exit"))
//                break;
//            controller.processCommand(command);
//
//        }
    }

    public void nicknameExists(String nickname) {
        System.out.println("user with nickname " + nickname + " already exists");
    }

    public void nicknameChanged(){
        System.out.println("nickname Changed successfully!");
    }

    public void usernameExists(String username){
        System.out.println("user with username " + username + " already exists");
    }

    public void usernameChanged(){
        System.out.println("username Changed successfully!");
    }

    public void passwordChanged(){
        System.out.println("password Changed successfully!");
    }

    public void wrongPassword(){
        System.out.println("current password is invalid");
    }

    public void passwordIsTheSame(){
        System.out.println("please enter a new password");
    }

    public void showMenu(){
        System.out.println("Profile Menu");
    }

    public void impossibleMenuNavigation(){
        System.out.println("menu navigation is not possible");
    }

    public void invalidCommand(){
        System.out.println("invalid command");
    }


}
