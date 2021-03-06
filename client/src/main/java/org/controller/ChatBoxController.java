package org.controller;

import org.MainClient;
import org.model.User;
import org.view.ChatBoxView;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ChatBoxController {
    private  Socket socket;

    private User user;
    private ChatBoxView view;
    private ObjectInputStream objectInputStream;

    public ChatBoxController(User user) {
        this.user = user;
        initializeNetwork();
        view = new ChatBoxView(this);
        view.run();

        try {
            LoginMenuController.sendAndReceive("enter chatBox --token " + MainClient.getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeNetwork() {
        try {
            socket = new Socket("localhost", 8888);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            newThread(objectInputStream);

        } catch (IOException x) {
            x.printStackTrace();
        }

    }

    public User getUser() {
        return user;
    }

    public void sendMessage(String message) {
        try {
            LoginMenuController.sendAndReceive("new message --token " + MainClient.getToken() + " --message " + message);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "SERVER ERROR");
        }
    }

    public void newThread(ObjectInputStream objectInputStream){
        new Thread(() -> {
            while (true) {
                try {
                    ArrayList<String> messages = (ArrayList<String>) objectInputStream.readObject();
                    if(messages != null) {
                        view.showChat(messages);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NegativeArraySizeException e) {
                    System.out.println("");
                }

            }


        }).start();
    }


}
