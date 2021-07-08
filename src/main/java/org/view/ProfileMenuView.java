package org.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controller.MainMenuController;
import org.controller.ProfileMenuController;
import org.model.enums.Status;


import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class ProfileMenuView extends Application {

    @FXML
    private Label username;
    @FXML
    private Label nickname;
    @FXML
    private Label passwordChangeNotifyLabel;
    @FXML
    private Label nicknameChangeNotifyLabel;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Button back;
    @FXML
    private Button changePassword;
    @FXML
    private Button change;
    @FXML
    private Button changeNickname;
    @FXML
    private TextField currentPasswordField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private TextField nicknameField;
    @FXML
    private Label changeProfile;


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
        loader.setLocation(getClass().getResource("/mainclass/FXML/profile.fxml"));
        Scene scene = new Scene(loader.load());
        fillProfileMenu();
        setButtonsAnimation();
        stage.setScene(scene);
        stage.show();
    }

    private void setButtonsAnimation() {
        back.setOnMouseEntered(mouseEvent -> back.setEffect(new Glow()));
        back.setOnMouseExited(mouseEvent -> back.setEffect(null));
        change.setOnMouseEntered(mouseEvent -> change.setEffect(new Glow()));
        change.setOnMouseExited(mouseEvent -> change.setEffect(null));
        changePassword.setOnMouseEntered(mouseEvent -> changePassword.setEffect(new Glow()));
        changePassword.setOnMouseExited(mouseEvent -> changePassword.setEffect(null));
        changeNickname.setOnMouseEntered(mouseEvent -> changeNickname.setEffect(new Glow()));
        changeNickname.setOnMouseExited(mouseEvent -> changeNickname.setEffect(null));
        ShopView.setMusic(back);
        ShopView.setMusic(change);
        ShopView.setMusic(changeNickname);
        ShopView.setMusic(changePassword);
    }

    private void fillProfileMenu() {
        username.setText("Username : " + controller.getUser().getUsername());
        nickname.setText("Nickname : " + controller.getUser().getNickname());
        try {
            if(controller.getUser().hasChangedProfilePicture()){
                profilePicture.setImage(new Image(controller.getUser().getProfilePicturePath()));
            } else {
                profilePicture.setImage(new Image(getClass().getResource(controller.getUser().getProfilePicturePath()).toExternalForm()));
            }
        } catch (Exception e){}
    }

    public void run()  {
        try {
            start(LoginMenuView.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePassword(){
        changeProfile.setText("");
        Status result = controller.controlChangePassword(currentPasswordField.getText() , newPasswordField.getText());
        switch (result){
            case WRONG_PASSWORD:{
                passwordChangeNotifyLabel.setText("Wrong password please retry");
                currentPasswordField.setText("");
                newPasswordField.setText("");
                return;
            }
            case SUCCESS:{
                passwordChangeNotifyLabel.setText("Password was Changed successfully");
                currentPasswordField.setText("");
                newPasswordField.setText("");
                return;
            }
            case REPEATED_PASSWORD:{
                passwordChangeNotifyLabel.setText("You must Enter a new password");
                currentPasswordField.setText("");
                newPasswordField.setText("");
                return;
            }
        }
    }

    public void changeNickname(){
        changeProfile.setText("");
        Status result = controller.controlChangeNickName(nicknameField.getText());
        switch (result){
            case SUCCESS:{
                nicknameChangeNotifyLabel.setText("Nickname changed successfully");
                nickname.setText("Nickname : " + controller.getUser().getNickname());
                return;
            }
            case PLEASE_ENTER_DATA_FIRST:{
                nicknameChangeNotifyLabel.setText("Please enter a valid nickname first");
                return;
            }
            case REPEATED_NICKNAME:{
                nicknameChangeNotifyLabel.setText("This nickname is already taken please try again");
                return;
            }
        }
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

    public void back(){
        try {
            new MainMenuController(controller.getUser()).run();
        }catch (Exception e){}
    }

    public void changeProfilePicture(){
        changeProfile.setText("");
        FileChooser fileChooser =  new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG Files", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(LoginMenuView.getPrimaryStage());
        profilePicture.setImage(new Image(selectedFile.toURI().toString()));
        changeProfile.setText("Profile changed successfully");
        controller.changeProfilePic(selectedFile);
    }
}
