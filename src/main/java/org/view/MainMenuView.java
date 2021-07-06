package org.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controller.DeckMenuController;
import org.controller.MainMenuController;

import java.util.Scanner;

public class MainMenuView extends Application {

    @FXML
    private Button start;
    @FXML
    private Button shop;
    @FXML
    private Button deck;
    @FXML
    private Button profile;
    @FXML
    private Button logout;
    @FXML
    private Button score;
    @FXML
    private Button export;


    private static Stage primaryStage;
    MainMenuController controller;
    public Text userText;

    public MainMenuView(MainMenuController controller) {
        this.controller = controller;
    }


    private Scanner scanner = ScannerInstance.getInstance().getScanner();

    public void run() throws Exception {
        start(LoginMenuView.getPrimaryStage());
    }


    public void showMenu() {
        System.out.println("Main Menu");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void cheatActivated() {
        System.out.println("Cheat activated");
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/mainmenu.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("Main Menu");
        buttonSet();
        setUserText();
        stage.setScene(scene);
        stage.show();
    }

    private void buttonSet() {
        logout.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logout.setEffect(new Lighting());
            }
        });

        logout.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logout.setEffect(null);
            }
        });

        export.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                export.setEffect(new Lighting());
            }
        });

        export.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                export.setEffect(null);
            }
        });

        start.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                start.setEffect(new Lighting());
            }
        });

        start.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                start.setEffect(null);
            }
        });

        score.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                score.setEffect(new Lighting());
            }
        });

        score.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                score.setEffect(null);
            }
        });

        profile.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                profile.setEffect(new Lighting());
            }
        });

        profile.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                profile.setEffect(null);
            }
        });

        deck.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deck.setEffect(new Lighting());
            }
        });

        deck.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deck.setEffect(null);
            }
        });

        shop.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                shop.setEffect(new Lighting());
            }
        });

        shop.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                shop.setEffect(null);
            }
        });

    }

    public void logout() {

        LoginMenuView loginMenuView = new LoginMenuView();
        try {
            loginMenuView.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newDuel() {

    }

    public void shop() {
        try {
            controller.processCommand("menu enter Shop");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deck() throws Exception {
        new DeckMenuController(controller.getUser()).run();
        try {
            controller.processCommand("menu enter Deck");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scoreBoard() {
        try {
            controller.processCommand("menu enter Scoreboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profile() {
        try {
            controller.processCommand("menu enter Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importExport() {
    }

    public void setUserText() {
        userText.setText("User Logged In: " + "\n" +controller.getUser().getUsername() + "\nKnown as: " + "\n" + controller.getUser().getNickname());

    }
}
