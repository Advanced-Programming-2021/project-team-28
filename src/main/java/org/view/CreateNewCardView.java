package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controller.CreateNewCardController;
import org.controller.ShopController;


public class CreateNewCardView extends Application {
    private CreateNewCardController controller;
    public static Stage primaryStage;
    @FXML
    private ChoiceBox cardType;
    @FXML
    private ChoiceBox level;
    @FXML
    private TextField cardName;
    @FXML
    private TextField atkText;
    @FXML
    private TextField defText;
    @FXML
    private Button create;
    @FXML
    private Button getPrice;
    @FXML
    private TextArea description;
    private String type;

    public CreateNewCardView(CreateNewCardController controller) {
        this.controller = controller;
    }

    public void run() {
        try {
            start(LoginMenuView.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/CreateNewCard.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.show();
        setChoices();

    }


    public void back() {
        ShopController shopController = new ShopController(controller.getUser());
        try {
            shopController.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setChoices() {
        cardType.getItems().add("Monster");
        cardType.getItems().add("Trap");
        cardType.getItems().add("Spell");
        cardType.getItems().add("Student");
        cardType.getItems().add("Master");
        cardType.getItems().add("TA");

        level.getItems().add(1);
        level.getItems().add(2);
        level.getItems().add(3);
        level.getItems().add(4);
        level.getItems().add(5);
        level.getItems().add(6);
        level.getItems().add(7);
        level.getItems().add(8);
        level.getItems().add(9);
        level.getItems().add(10);
        level.getItems().add(11);
        level.getItems().add(12);


    }
    public void calculatePrice(){

    }

    public void create(){

    }

}
