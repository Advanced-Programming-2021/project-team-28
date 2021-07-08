package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controller.ImportExportController;
import org.controller.MainMenuController;
import org.model.Card;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ImportExportView extends Application {
    ImportExportController controller;

    @FXML
    private VBox playerCards;
    @FXML
    private ImageView selectedCardImageView;
    @FXML
    private Text selectedCardToString;
    @FXML
    private Text successAndErrorText;
    @FXML
    private Button exportButton;
    @FXML
    private Button importBtn;
    @FXML
    private Button back;


    private Card cardToExport;

    public ImportExportView (ImportExportController controller){
        this.controller = controller;
    }

    public void run(){
        try {
            start(LoginMenuView.getPrimaryStage());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/importExportMenu.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        stage.setScene(scene);
        stage.show();
        ShopView.setMusic(back);
        ShopView.setMusic(importBtn);
        ShopView.setMusic(exportButton);
        selectedCardImageView.setImage(Card.getCardImageByName("Unknown"));
        setCardImages();
    }

    private void setCardImages() {
        playerCards.getChildren().clear();
        HBox row = new HBox();
        int i = 0;
        Card.sortCardsWithImage(controller.getUser().getAllCards());
        for (Card card : controller.getUser().getAllCards()) {
            if (i % 4 == 0) {
                row = new HBox();
                row.setSpacing(7);
                playerCards.getChildren().add(row);
            }
            ImageView view = new ImageView(Card.getCardImageByName(card.getName()));
            view.setFitHeight(180);
            view.setFitWidth(120);
            view.setOnMouseClicked(mouseEvent -> {
                showPictureAndDescription(view);
                exportButton.setDisable(false);
                cardToExport = card;
            });
            row.getChildren().add(view);
            i++;
        }
    }

    private void showPictureAndDescription(ImageView view) {
        showCardDetails(view, selectedCardImageView, selectedCardToString);
    }

    static void showCardDetails(ImageView view, ImageView selectedCardImageView, Text selectedCardToString) {
        selectedCardImageView.setImage(view.getImage());
        String description = null;
        try {
            Card card1 = Card.getCardByName(Card.getAllCards(), Card.getCardNameByImage(view.getImage()));
            description = card1.getName() + ": " + card1.getDescription();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        selectedCardToString.setText(description);
    }

    public void export() {
        if(exportButton.isDisabled() || cardToExport == null){
            return;
        }
        try {
            controller.exportThisCard(cardToExport);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void importACard(){
        FileChooser fileChooser =  new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );
        File selectedFile = fileChooser.showOpenDialog(LoginMenuView.getPrimaryStage());
        controller.importThisJsonFile(selectedFile);
    }

    public void menuShowCurrent(){
        System.out.println("Import/Export Menu");
    }

    public void invalidCommand(){
        System.out.println("invalid command");
    }

    public void impossibleMenuNavigation(){
        System.out.println("impossible menu navigation");
    }

    public void cardWithThisNameDoesNotExist() {
        System.out.println("Card with this name does not exist");
    }

    public void youDoNotHaveThisCard(){
        System.out.println("You don't have this card");
    }

    public void exportedSuccessfully(){
        successAndErrorText.setText("Card exported successfully");
        selectedCardImageView.setImage(Card.getCardImageByName("Unknown"));
        exportButton.setDisable(true);
        selectedCardToString.setText("");
    }

    public void youDoNotHaveThisCardForImport(){
        System.out.println("You don't have this card for import");
    }

    public void cardImportedAndAddedToYourCards(){
        System.out.println("Card imported and added to your cards");
    }

    public void youAlreadyHaveThisCard(){
        System.out.println("You already have this card");
    }

    public void cardFolderNotFound(){
        System.out.println("Cards folder not found");
    }

    public void setError(String error) {
        successAndErrorText.setText(error);
    }

    public void importedSuccessfully(Card card) {
        successAndErrorText.setText("Imported successfully");
        exportButton.setDisable(true);
        selectedCardImageView.setImage(Card.getCardImageByName(card.getName()));
        selectedCardToString.setText(card.getName() + ": " + card.getDescription());
        setCardImages();
    }

    public void back(){
        try {
            new MainMenuController(controller.getUser()).run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
