package org.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.controller.CreateNewCardController;
import org.model.enums.Attribute;
import org.model.enums.MonsterPower;
import org.model.enums.MonsterType;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
        for (MonsterCard monsterCard: monsterCards){
            CreateNewCard.newMonsters.add(monsterCard);
        }

        CreateNewCard.newTraps = new ArrayList<>();
        for (TrapCard trapCard: trapCards){
            CreateNewCard.newTraps.add(trapCard);
        }

        CreateNewCard.newSpells = new ArrayList<>();
        for (SpellCard spellCard: spellCards){
            CreateNewCard.newSpells.add(spellCard);
        }

        newCards.addAll(newTraps);
        newCards.addAll(newMonsters);
        newCards.addAll(newSpells);
    }
}
