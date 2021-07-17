package org;

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
            socket = new Socket("localhost", 7877);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        initializeNetwork();
        new LoginMenuView().run();
//        while(true){
//            Scanner scanner = new Scanner(System.in);
//            String string = scanner.nextLine();
//            dataOutputStream.writeUTF(string);
//            dataOutputStream.flush();
//            String result = dataInputStream.readUTF();
//            System.out.println(result);
//        }

//        restoreDatabase();

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

    public static Socket getSocket() {
        return socket;
    }

    public static DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public static DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }
}