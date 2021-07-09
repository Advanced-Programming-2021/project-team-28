package org;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.model.*;
import org.view.LoginMenuView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        restoreDatabase();
        new LoginMenuView().run();
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