package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.MainClient;
import org.controller.ChatBoxController;
import org.controller.LoginMenuController;
import org.controller.MainMenuController;

import javax.swing.*;
import java.util.ArrayList;

public class ChatBoxView extends Application {
    private ChatBoxController controller;
    @FXML
    private TextField message;
    @FXML
    private Text chat;

    public ChatBoxView(ChatBoxController controller) {
        this.controller = controller;
    }

    public void run() {
        try {
            start(MainMenuView.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/chatBox.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void back() {
        try {
            LoginMenuController.sendAndReceive("exit chatBox --token " + MainClient.getToken());
            new MainMenuController(controller.getUser()).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send() {
        if (message.getText() == null || message.getText() == ""){
            JOptionPane.showMessageDialog(null, "please write sth first!");
        } else {
            controller.sendMessage(message.getText());
            JOptionPane.showMessageDialog(null, "sent successfully");
            message.clear();
        }
    }

    public void showChat(ArrayList<String> messages) {
        StringBuilder chats = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            chats.append(messages.get(i));
            chats.append("\n");
        }
        chat.setText(chats.toString());
    }
}
