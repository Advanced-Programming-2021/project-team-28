package org;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.model.*;
import org.view.LoginMenuView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class MainClient {

    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    public static void initializeNetwork() {
        try {
            socket = new Socket("localhost", 7677);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        initializeNetwork();
        dataOutputStream.writeUTF("2");
        dataOutputStream.flush();
        String result = dataInputStream.readUTF();
        System.out.println("4");
//        restoreDatabase();
//        new LoginMenuView().run();
    }

    private static void restoreDatabase() throws Exception {
        createResourceFileIfNeeded();
        CreateNewCard.deserialize();
        AllCardsInitiator.fillAllCards();
        AllCardsInitiator.setPrices();
        MonsterCard.deserialize();
        SpellCard.deserialize();
        TrapCard.deserialize();
        User.deserialize();
    }

    private static void createResourceFileIfNeeded() throws IOException {
        ArrayList<File> files= new ArrayList<>();
        files.add(new File("src/UserOutput.json"));
        files.add(new File("src/MonsterCardsOutput.json"));
        files.add(new File("src/SpellCardsOutput.json"));
        files.add(new File("src/TrapCardsOutput.json"));
        files.add(new File("src/DecksOutput.json"));
        files.add(new File("src/NewMonsterOutput.json"));
        files.add(new File("src/NewTrapOutput.json"));
        files.add(new File("src/NewSpellOutput.json"));
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
}