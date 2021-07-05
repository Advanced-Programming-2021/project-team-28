package org.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jfxtras.labs.util.event.MouseControlUtil;
import org.controller.DeckMenuController;
import org.controller.MainMenuController;
import org.model.Card;
import org.model.Deck;
import jfxtras.labs.util.*;

import javax.swing.*;
import java.io.IOException;

public class DeckMenuView extends Application {
    DeckMenuController controller;
    @FXML
    private VBox vBox;
    @FXML
    private Text activeDeckText;
    @FXML
    private Text selectedDeckText;
    @FXML
    private ImageView selectedCardImageView;
    @FXML
    private Text selectedCardToString;
    @FXML
    private VBox playerCardsVBox;
    private boolean isInEditMode = false;
    private Deck selectedDeck;

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
        showActiveDeck();
    }

    private void showActiveDeck() {
        String activeDeck = controller.getUser().hasActiveDeck() ?
                "Active Deck: " + controller.getUser().getActiveDeck().getDeckName() :
                "You don't have any active deck";
        activeDeckText.setText(activeDeck);
    }

    private void refreshDecksList() {
        vBox.getChildren().clear();
        controller.getUser().sortDecksArrayList();
        for (Deck deck : controller.getUser().getDecks()) {
            HBox row = new HBox();
            row.setSpacing(8);
            addNameAndButtonsToRow(deck, row);
            StackPane stackPane = new StackPane();
            Rectangle rectangle = getRectangle(40, 100, Color.KHAKI);
            stackPane.getChildren().add(rectangle);
            HBox mainAndSideDeckSizeTexts = new HBox();
            mainAndSideDeckSizeTexts.setSpacing(10);
            mainAndSideDeckSizeTexts.getChildren().add(getText(String.valueOf(deck.getAllCardsInMainDeck().size())));
            addRegionToHBox(mainAndSideDeckSizeTexts);
            mainAndSideDeckSizeTexts.getChildren().add(getText(String.valueOf(deck.getAllCardsInSideDeck().size())));
            stackPane.getChildren().add(mainAndSideDeckSizeTexts);
            row.getChildren().add(stackPane);
            vBox.getChildren().add(row);
        }
    }

    private void addRegionToHBox(HBox mainAndSideDeckSizeTexts) {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        mainAndSideDeckSizeTexts.getChildren().add(region);
    }

    private void addNameAndButtonsToRow(Deck deck, HBox row) {
        Rectangle rectangle = getRectangle(40, 660, Color.MOCCASIN);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(rectangle);
        HBox nameAndButtons = new HBox();
        nameAndButtons.setSpacing(10);
        nameAndButtons.getChildren().add(getText(deck.getDeckName()));
        addRegionToHBox(nameAndButtons);
        stackPane.getChildren().add(nameAndButtons);
        stackPane.setOnMouseEntered(mouseEvent -> addButtons(nameAndButtons, deck));
        stackPane.setOnMouseExited(mouseEvent -> removeButtons(nameAndButtons));
        row.getChildren().add(stackPane);
    }

    private void removeButtons(HBox hBox) {
        Text text1 = (Text) hBox.getChildren().get(0);
        hBox.getChildren().clear();
        hBox.getChildren().add(text1);
    }

    private void addButtons(HBox hBox, Deck deck) {
        addRegionToHBox(hBox);
        Button activateButton = new Button("Activate");
        activateButton.setOnMouseClicked(mouseEvent -> {
            controller.processCommand("deck set-activate " + ((Text) hBox.getChildren().get(0)).getText());
            showActiveDeck();
        });
        hBox.getChildren().add(activateButton);
        Button editDeckButton = new Button("Show deck details and edit");
        editDeckButton.setOnMouseClicked(mouseEvent -> {
            isInEditMode = true;
            selectedDeck = deck;
            loadEditMenu();
        });
        hBox.getChildren().add(editDeckButton);
        Button deleteDeckButton = new Button("Delete");
        deleteDeckButton.setOnMouseClicked(mouseEvent -> {
            controller.processCommand("deck delete " + ((Text) hBox.getChildren().get(0)).getText());
            refreshDecksList();
        });
        hBox.getChildren().add(deleteDeckButton);

    }

    private void loadEditMenu() {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/deckEdit.fxml"));
        try {
            LoginMenuView.getPrimaryStage().getScene().setRoot(loader.load());
            selectedDeckText.setText("Deck name: " + selectedDeck.getDeckName());
            selectedCardImageView.setImage(Card.getCardImageByName("Unknown"));
            addCardsToGridPane();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void addCardsToGridPane() {
        HBox row = new HBox();
        int i=0;
        for (Card card : controller.getUser().getAllCards()){
            if(i%2==0){
                row = new HBox();
                row.setSpacing(7);
                playerCardsVBox.getChildren().add(row);
            }
            ImageView view = new ImageView(Card.getCardImageByName(card.getName()));
            view.setFitHeight(180);
            view.setFitWidth(120);
            view.setOnMouseClicked(mouseEvent -> {
                selectedCardImageView.setImage(view.getImage());
                //selectedDeckText.setText();
            });
            row.getChildren().add(view);
            i++;
        }
    }

    public void back() {
        try {
            if(isInEditMode){
                isInEditMode = false;
                new DeckMenuController(controller.getUser()).run();
            } else {
                new MainMenuController(controller.getUser()).run();
            }
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

    public Rectangle getRectangle(double height, double width, Color color) {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(color);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        return rectangle;
    }

    public Text getText(String text) {
        Text text1 = new Text(text);
        text1.setFill(Color.BLACK);
        text1.setFont(Font.font("Verdana", 30));
        return text1;
    }
}
