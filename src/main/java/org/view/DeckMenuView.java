package org.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.controller.DeckMenuController;
import org.controller.MainMenuController;
import org.controller.MenuEnum;
import org.model.Deck;

import javax.swing.*;
import java.util.Scanner;

public class DeckMenuView extends Application {
    DeckMenuController controller;
    @FXML
    private VBox vBox;

    public DeckMenuView(DeckMenuController controller) {
        this.controller = controller;
    }

    public void run() throws Exception {
        start(LoginMenuView.getPrimaryStage());
    }

    public void thisDeckAlreadyExists(String deckName) {
        JOptionPane.showMessageDialog(null, "deck with name " + deckName + " already exists");
    }

    public void deckCreated() {
        JOptionPane.showMessageDialog(null, "deck created successfully!");
    }

    public void deckDoesNotExist(String deckName) {
        System.out.println("deck with name " + deckName + " does not exist");
    }

    public void deckDeleted() {
        JOptionPane.showMessageDialog(null, "deck deleted successfully");
    }

    public void deckActivated() {
        JOptionPane.showMessageDialog(null, "deck activated successfully");
    }

    public void print(String string) {
        System.out.println(string);
    }

    public void showCurrentMenu() {
        System.out.println("Deck Menu");
    }

    public void cardDoesNotExist(String cardName) {
        System.out.println("card with name " + cardName + " does not exist");
    }

    public void mainOrSideDeckIsFull(boolean isSideDeckFull) {
        String mainOrSide = isSideDeckFull ? "Side" : "Main";
        System.out.println(mainOrSide + " deck is full");
    }

    public void threeSimilarCardInADeck(String cardName, String deckName) {
        System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
    }

    public void cardAddedToDeck() {
        System.out.println("card added to deck successfully");
    }

    public void cardDoesNotExistInSideOrMainDeck(String cardName, boolean isInSideDeck) {
        String mainOrSide = isInSideDeck ? "Side" : "Main";
        System.out.println("card with name " + cardName + " does not exist in " + mainOrSide + " deck");
    }

    public void cardRemovedFromDeck() {
        System.out.println("card removed from deck successfully");
    }

    public void impossibleMenuNavigation() {
        System.out.println("menu navigation is not possible");
    }

    public void invalidCommand() {
        System.out.println("invalid command");
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/deckmenu.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Deck Menu");
        stage.show();
        refreshDecksList();
    }

    private void refreshDecksList() {
        vBox.getChildren().clear();
        controller.getUser().sortDecksArrayList();
        for (Deck deck : controller.getUser().getDecks()) {
            Rectangle rectangle = getRectangle(40, 768);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(rectangle);
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            Text text = new Text(deck.getDeckName());
            text.setFill(Color.BLACK);
            text.setFont(Font.font("Verdana", 30));
            hBox.getChildren().add(text);
            stackPane.getChildren().add(hBox);
            vBox.getChildren().add(stackPane);
            stackPane.setOnMouseEntered(mouseEvent -> {
                addButtons(hBox);
            });
            stackPane.setOnMouseExited(mouseEvent -> {
                removeButtons(hBox);
            });
        }
    }

    private void removeButtons(HBox hBox) {
        Text text1 = (Text) hBox.getChildren().get(0);
        hBox.getChildren().clear();
        hBox.getChildren().add(text1);
    }

    private void addButtons(HBox hBox) {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        hBox.getChildren().add(region);
        Button activateButton = new Button("Activate");
        activateButton.setOnMouseClicked(mouseEvent -> controller.processCommand("deck set-activate " + ((Text) hBox.getChildren().get(0)).getText()));
        hBox.getChildren().add(activateButton);
        Button editDeckButton = new Button("Show deck details and edit");
        hBox.getChildren().add(editDeckButton);
        Button deleteDeckButton= new Button("Delete");
        deleteDeckButton.setOnMouseClicked(mouseEvent -> {
            controller.processCommand("deck delete " + ((Text) hBox.getChildren().get(0)).getText());
            refreshDecksList();
        });
        hBox.getChildren().add(deleteDeckButton);

    }

    public void back() {
        try {
            new MainMenuController(controller.getUser()).run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void createNewDeck() {
        String deckName = JOptionPane.showInputDialog("Enter deck name: ");
        if (deckName == null || deckName.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter deck name first");
        } else {
            controller.processCommand("deck create " + deckName);
        }
        refreshDecksList();
    }

    public Rectangle getRectangle(double height, double width) {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.MOCCASIN);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        return rectangle;
    }
}
