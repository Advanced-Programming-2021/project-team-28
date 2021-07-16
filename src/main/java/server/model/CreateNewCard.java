package server.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.controller.CreateNewCardController;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateNewCard {
    private CreateNewCardController controller;

    public CreateNewCard(CreateNewCardController controller) {
        this.controller = controller;
    }


    public static ArrayList<MonsterCard> newMonsters = new ArrayList<>();
    public static ArrayList<SpellCard> newSpells = new ArrayList<>();
    public static ArrayList<TrapCard> newTraps = new ArrayList<>();
    public static ArrayList<Card> newCards = new ArrayList<>();

    public static void serialize() {
        try (Writer writer = new FileWriter("src/NewMonsterOutput.json")) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            gson.toJson(CreateNewCard.newMonsters, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Writer writer = new FileWriter("src/NewTrapOutput.json")) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            gson.toJson(CreateNewCard.newTraps, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Writer writer = new FileWriter("src/NewSpellOutput.json")) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            gson.toJson(CreateNewCard.newSpells, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        Gson gson = new Gson();
        Reader reader = null;

        try {
            reader = Files.newBufferedReader(Paths.get("src/NewMonsterOutput.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MonsterCard[] monsterCards = gson.fromJson(reader, MonsterCard[].class);


        try {
            reader = Files.newBufferedReader(Paths.get("src/NewTrapOutput.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        TrapCard[] trapCards = gson.fromJson(reader, TrapCard[].class);

        try {
            reader = Files.newBufferedReader(Paths.get("src/NewSpellOutput.json"));
        } catch (IOException e){
            e.printStackTrace();
        }
        SpellCard[] spellCards = gson.fromJson(reader, SpellCard[].class);

        CreateNewCard.newMonsters = new ArrayList<>();
        CreateNewCard.newMonsters.addAll(Arrays.asList(monsterCards));

        CreateNewCard.newTraps = new ArrayList<>();
        CreateNewCard.newTraps.addAll(Arrays.asList(trapCards));

        CreateNewCard.newSpells = new ArrayList<>();
        CreateNewCard.newSpells.addAll(Arrays.asList(spellCards));

        newCards.addAll(newTraps);
        newCards.addAll(newMonsters);
        newCards.addAll(newSpells);
    }
}
