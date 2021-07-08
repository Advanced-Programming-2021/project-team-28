package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controller.DeckMenuController;
import org.controller.MainMenuController;
import org.model.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.view.ScrollPaneEnum.*;

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
    private VBox availableCardsVBox;
    @FXML
    private VBox mainDeckVBox;
    @FXML
    private VBox sideDeckVBox;
    @FXML
    private ScrollPane mainDeck;
    @FXML
    private ScrollPane sideDeck;
    @FXML
    private ScrollPane availableCards;
    @FXML
    private Text errorText;
    @FXML
    private Button create;
    @FXML
    private Button back;
    @FXML
    private Button back2;

    private boolean isInEditMode = false;
    private Deck selectedDeck;

    public DeckMenuView(DeckMenuController controller) {
        this.controller = controller;
    }

    public void run() throws Exception {
        start(LoginMenuView.getPrimaryStage());
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/deckmenu.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        setButtons();
        stage.setScene(scene);
        stage.show();
        refreshDecksList();
        showActiveDeck();
    }

    private void setButtons() {
        back.setOnMouseEntered(mouseEvent -> back.setEffect(new Glow()));
        back.setOnMouseExited(mouseEvent -> back.setEffect(null));
        ShopView.setMusic(back);
        create.setOnMouseEntered(mouseEvent -> create.setEffect(new Glow()));
        create.setOnMouseExited(mouseEvent -> create.setEffect(null));
        ShopView.setMusic(create);
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
        errorText.setText("Error, " + mainOrSide + " deck is full");
    }

    public void threeSimilarCardInADeck(String cardName, String deckName) {
        errorText.setText("Error, there are already three cards with name " + cardName + " in deck " + deckName);
    }

    public void cardAddedToDeck() {
        errorText.setText("");
    }

    public void cardDoesNotExistInSideOrMainDeck(String cardName, boolean isInSideDeck) {
        String mainOrSide = isInSideDeck ? "Side" : "Main";
        System.out.println("card with name " + cardName + " does not exist in " + mainOrSide + " deck");
    }

    public void cardRemovedFromDeck() {
        errorText.setText("");
    }

    public void impossibleMenuNavigation() {
        System.out.println("menu navigation is not possible");
    }

    public void invalidCommand() {
        System.out.println("invalid command");
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
            Rectangle rectangle = getRectangle(40, 100, Color.web("#888ECA"));
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
        Rectangle rectangle = getRectangle(40, 660, Color.web("#D1C2A9"));
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
        setStyleForButton(activateButton);
        activateButton.setOnMouseClicked(mouseEvent -> {
            controller.processCommand("deck set-activate " + ((Text) hBox.getChildren().get(0)).getText());
            showActiveDeck();
        });
        activateButton.setPrefSize(80, 30);
        hBox.getChildren().add(activateButton);
        Button editDeckButton = new Button("Details/Edit");
        editDeckButton.setOnMouseClicked(mouseEvent -> {
            isInEditMode = true;
            selectedDeck = deck;
            loadEditMenu();
        });
        editDeckButton.setPrefSize(120, 30);
        setStyleForButton(editDeckButton);
        hBox.getChildren().add(editDeckButton);
        Button deleteDeckButton = new Button("Delete");
        deleteDeckButton.setOnMouseClicked(mouseEvent -> {
            controller.processCommand("deck delete " + ((Text) hBox.getChildren().get(0)).getText());
            refreshDecksList();
        });
        deleteDeckButton.setPrefSize(80, 30);
        setStyleForButton(deleteDeckButton);
        hBox.getChildren().add(deleteDeckButton);

    }

    private void setStyleForButton(Button button) {
        button.setStyle("-fx-background-radius: 30;\n" +
                "    -fx-background-color: #edd9ce;\n" +
                "    -fx-text-fill: #313335;" +
                "-fx-font-family: thewitcher");
    }

    private void loadEditMenu() {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/deckEdit.fxml"));

        try {
            LoginMenuView.getPrimaryStage().getScene().setRoot(loader.load());
            selectedDeckText.setText("Deck name: " + selectedDeck.getDeckName());
            selectedCardImageView.setImage(Card.getCardImageByName("Unknown"));
            addAllCardImages();
            setDragAndDropMethodsForScrollPanes();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void addAllCardImages() {
        fillVBox(mainDeckVBox, 9, 90, 60, selectedDeck.getAllCardsInMainDeck(), MAIN_DECK);
        fillVBox(sideDeckVBox, 9, 90, 60, selectedDeck.getAllCardsInSideDeck(), SIDE_DECK);
        fillVBox(availableCardsVBox, 2, 180, 120, controller.getUser().getAllCardsOutOfThisDeck(selectedDeck), AVAILABLE_CARDS);
    }

    private void fillVBox(VBox vbox, int size, int imageHeight, int imageWidth, ArrayList<Card> cardsToAdd, ScrollPaneEnum scrollPaneEnum) {
        vbox.getChildren().clear();
        HBox row = new HBox();
        int i = 0;
        Card.sortCardsWithImage(cardsToAdd);
        for (Card card : cardsToAdd) {
            if (i % size == 0) {
                row = new HBox();
                row.setSpacing(7);
                vbox.getChildren().add(row);
            }
            ImageView view = new ImageView(Card.getCardImageByName(card.getName()));
            view.setFitHeight(imageHeight);
            view.setFitWidth(imageWidth);
            setDragAndDropForImageViews(view, scrollPaneEnum, card.getName());
            view.setOnMouseClicked(mouseEvent -> showPictureAndDescription(view));
            row.getChildren().add(view);
            i++;
        }
    }

    private void showPictureAndDescription(ImageView view) {
        ImportExportView.showCardDetails(view, selectedCardImageView, selectedCardToString);
    }

    private void setDragAndDropMethodsForScrollPanes() {
        mainDeck.setOnDragOver(this::setDragOverForScrollPanes);
        sideDeck.setOnDragOver(this::setDragOverForScrollPanes);
        availableCards.setOnDragOver(this::setDragOverForScrollPanes);
        mainDeck.setOnDragDropped(dragEvent -> dragDropped(dragEvent, MAIN_DECK));
        sideDeck.setOnDragDropped(dragEvent -> dragDropped(dragEvent, SIDE_DECK));
        availableCards.setOnDragDropped(dragEvent -> dragDropped(dragEvent, AVAILABLE_CARDS));
    }

    private void dragDropped(DragEvent dragEvent, ScrollPaneEnum scrollPaneEnum) {
        Dragboard db = dragEvent.getDragboard();
        if (db.hasUrl() && db.hasString()) {
            String whereIsThisCard = db.getString();
            String cardName = db.getUrl();
            String deckName = selectedDeck.getDeckName();
            if (scrollPaneEnum == MAIN_DECK) {
                moveDraggedInMainCard(whereIsThisCard, cardName, deckName);
            } else if (scrollPaneEnum == SIDE_DECK) {
                moveDraggedInSideCard(whereIsThisCard, cardName, deckName);
            } else {
                moveDraggedInAvailableCards(whereIsThisCard, cardName, deckName);
            }
            dragEvent.setDropCompleted(true);
        } else {
            dragEvent.setDropCompleted(false);
        }
        dragEvent.consume();
    }

    private void moveDraggedInAvailableCards(String whereIsThisCard, String cardName, String deckName) {
        if (whereIsThisCard.equals("0")) {
            controller.controlRemoveCardCommand(cardName, deckName, false);
        } else if (whereIsThisCard.equals("1")) {
            controller.controlRemoveCardCommand(cardName, deckName, true);
        }
        addAllCardImages();
    }

    private void moveDraggedInSideCard(String whereIsThisCard, String cardName, String deckName) {
        if (whereIsThisCard.equals("0")) {
            controller.controlAddCardFromMainToSide(cardName, deckName);
        } else if (whereIsThisCard.equals("2")) {
            controller.controlAddCardCommand(cardName, deckName, true);
        }
        addAllCardImages();
    }

    private void moveDraggedInMainCard(String whereIsThisCard, String cardName, String deckName) {
        if (whereIsThisCard.equals("1")) {
            controller.controlAddCardFromSideToMain(cardName, deckName);
        } else if (whereIsThisCard.equals("2")) {
            controller.controlAddCardCommand(cardName, deckName, false);
        }
        addAllCardImages();
    }

    private void setDragOverForScrollPanes(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        if (db.hasUrl() && db.hasString()) {
            dragEvent.acceptTransferModes(TransferMode.MOVE);
        }
        dragEvent.consume();
    }

    private void setDragAndDropForImageViews(ImageView source, ScrollPaneEnum scrollPaneEnum, String cardName) {
        source.setOnDragDetected(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            Image sourceImage = source.getImage();
            content.putUrl(cardName);
            content.putImage(sourceImage);
            content.putString(scrollPaneEnum.getName());
            db.setContent(content);
            event.consume();
        });
    }

    public void back() {
        try {
            if (isInEditMode) {
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
        text1.setStyle("-fx-font-family: thewitcher; -fx-text-fill: #e1c5c5 ; -fx-font-size: 35 ");
        text1.setFill(Color.BLACK);
        return text1;
    }
}
