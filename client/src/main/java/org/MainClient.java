package org;

import org.model.*;
import org.view.LoginMenuView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.OptionalDouble;


public class MainClient {
    private static String token;
    private static User user;
    private static Socket socket;
    private static ObjectInputStream objectInputStream;
    private static DataOutputStream dataOutputStream;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MainClient.user = user;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MainClient.token = token;
    }


    public static void initializeNetwork() {
        try {
            socket = new Socket("localhost", 7877);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        initializeNetwork();
        new LoginMenuView().run();
//        restoreDatabase();
    }

    private static void restoreDatabase() throws Exception {
        createResourceFileIfNeeded();
        //think about it later:
        AllCardsInitiator.fillAllCards();
        AllCardsInitiator.setPrices();

    }

    private static void createResourceFileIfNeeded() throws IOException {
        ArrayList<File> files= new ArrayList<>();
        File cardsFolder = new File("src/ExportedCards");
        cardsFolder.mkdir();
        for (File file : files)
            if(!file.exists()){
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write("[]");
                writer.close();
            }

    }

    public static Socket getSocket() {
        return socket;
    }

    public static ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public static DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }
}