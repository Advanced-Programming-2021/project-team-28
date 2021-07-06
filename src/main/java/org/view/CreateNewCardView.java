package org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.controller.CreateNewCardController;

public class CreateNewCardView extends Application {
    private CreateNewCardController controller;
    public static Stage primaryStage;


    public CreateNewCardView(CreateNewCardController controller) {
        this.controller = controller;
    }

    public void run(){};

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("src/main/resources/mainclass/FXML/CreateNewCard.fxml"));
        stage.show();
    }
}
