package org.controller;

import com.google.gson.Gson;
import org.model.*;
import org.view.ImportExportView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportExportController {
    User user;
    ImportExportView view = new ImportExportView(this);


    public ImportExportController(User user) {
        this.user = user;
    }

    public void run() {
        view.run();
    }

    public MenuEnum processCommand(String command) {
        Matcher[] commandMatchers = getMatchers(command);
        if (command.equals("menu show-current")) {
            view.menuShowCurrent();
        } else if (command.equals("menu exit")) {
            return MenuEnum.BACK;
        } else if (commandMatchers[0].find()) {
            controlImportCardCommand(commandMatchers[0].group(1));
        } else if (commandMatchers[1].find()) {
            controlExportCardCommand(commandMatchers[1].group(1));
        } else if (commandMatchers[2].find()) {
            view.impossibleMenuNavigation();
        } else {
            view.invalidCommand();
        }
        return MenuEnum.CONTINUE;
    }

    private void controlExportCardCommand(String cardName) {
        try {
            if (!Card.isThisCardNameValid(cardName)) {
                view.cardWithThisNameDoesNotExist();
            } else if(Card.getCardByName(user.getAllCards(), cardName) == null){
                view.youDoNotHaveThisCard();
            } else {
                Card cardToExport = Card.getCardByName(user.getAllCards(), cardName);
                File cardsFolder = new File("src/ExportedCards");
                cardsFolder.mkdir();
                File cardToExportFile = new File("src/ExportedCards/" + cardName +"_"+ cardToExport.getNumber()+ ".json");
                cardToExportFile.createNewFile();
                FileWriter writer = new FileWriter(cardToExportFile);
                writer.write(new Gson().toJson(cardToExport));
                writer.close();
                view.exportedSuccessfully();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void controlImportCardCommand(String cardName) {
        try {
            if (!Card.isThisCardNameValid(cardName)) {
                view.cardWithThisNameDoesNotExist();
                return;
            }
            File importCardsFolder = new File("src/CardsToImport");
            if (!importCardsFolder.exists()){
                view.cardFolderNotFound();
                return;
            }
            File[] cardsForImport = importCardsFolder.listFiles();
            if(cardsForImport == null){
                view.youDoNotHaveThisCardForImport();
                return;
            }
            ArrayList<File> cardsWithThisNameForImport = new ArrayList<>();
            for (File file : cardsForImport) {
                if(file.getName().startsWith(cardName)){
                    cardsWithThisNameForImport.add(file);
                }
            }
            if(cardsWithThisNameForImport.size() == 0){
                view.youDoNotHaveThisCardForImport();
                return;
            }
            if(Card.getCardByName(Card.getAllCards(), cardName) instanceof MonsterCard){
                importMonster(cardsWithThisNameForImport);
            } else if (Card.getCardByName(Card.getAllCards(), cardName) instanceof TrapCard){
                importTrap(cardsWithThisNameForImport);
            } else {
                importSpell(cardsWithThisNameForImport);
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private void importSpell(ArrayList<File> cardsWithThisNameForImport) throws IOException {
        for (File file : cardsWithThisNameForImport) {
            String json = new String(Files.readAllBytes(file.toPath()));
            SpellCard card = new Gson().fromJson(json, SpellCard.class);
            if(!user.doesHaveCardWithThisNumber(card.getNumber())){
                user.addToCards(card);
                view.cardImportedAndAddedToYourCards();
                return;
            }
        }
        view.youAlreadyHaveThisCard();
    }

    private void importTrap(ArrayList<File> cardsWithThisNameForImport) throws IOException {
        for (File file : cardsWithThisNameForImport) {
            String json = new String(Files.readAllBytes(file.toPath()));
            TrapCard card = new Gson().fromJson(json, TrapCard.class);
            if(!user.doesHaveCardWithThisNumber(card.getNumber())){
                user.addToCards(card);
                view.cardImportedAndAddedToYourCards();
                return;
            }
        }
        view.youAlreadyHaveThisCard();
    }

    private void importMonster(ArrayList<File> cardsWithThisNameForImport) throws IOException {
        for (File file : cardsWithThisNameForImport) {
            String json = new String(Files.readAllBytes(file.toPath()));
            MonsterCard card = new Gson().fromJson(json, MonsterCard.class);
            if(!user.doesHaveCardWithThisNumber(card.getNumber())){
                user.addToCards(card);
                view.cardImportedAndAddedToYourCards();
                return;
            }
        }
        view.youAlreadyHaveThisCard();
    }


    private Matcher[] getMatchers(String command) {
        Pattern patternForImport = Pattern.compile("^import card (.+?)$");
        Pattern patternForExport = Pattern.compile("^export card (.+?)$");
        Pattern patternForImpossibleMenuNavigation = Pattern.compile("^menu enter (Duel|Deck|Scoreboard|Shop|Profile)$");
        Matcher[] matchers = new Matcher[3];
        matchers[0] = patternForImport.matcher(command);
        matchers[1] = patternForExport.matcher(command);
        matchers[2] = patternForImpossibleMenuNavigation.matcher(command);
        return matchers;
    }

}
