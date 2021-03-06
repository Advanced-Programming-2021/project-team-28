package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.controller.MainMenuController;
import org.controller.ScoreboardController;
import org.model.User;


import java.util.ArrayList;

public class ScoreboardView extends Application {


    ScoreboardController controller;

    @FXML
    private Label player1;
    @FXML
    private Label player2;
    @FXML
    private Label player3;
    @FXML
    private Label player4;
    @FXML
    private Label player5;
    @FXML
    private Label player6;
    @FXML
    private Label player7;
    @FXML
    private Label player8;
    @FXML
    private Label player9;
    @FXML
    private Label player10;
    @FXML
    private Label player11;
    @FXML
    private Label player12;
    @FXML
    private Label player13;
    @FXML
    private Label player14;
    @FXML
    private Label player15;
    @FXML
    private Label player16;
    @FXML
    private Label player17;
    @FXML
    private Label player18;
    @FXML
    private Label player19;
    @FXML
    private Label player20;
    @FXML
    public Button back;
    @FXML
    public Button refresh;
    @FXML
    private ArrayList<Label> labels;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/scoreboard.fxml"));
        Scene scene = new Scene(loader.load());
        fillLabels();
        ShopView.setMusic(back);
        ShopView.setMusic(refresh);
        setUpScoreboard();
        stage.setScene(scene);
        stage.show();
    }



    private void setUpScoreboard() {
        ArrayList<User> users = controller.getSortedUsers();
        int rank = 1;
        int upperTeamScore = -1;
        for (int i = 1; i <= Math.min(20 , users.size()); i++) {
            if(users.get(i-1) != null) {
                User user = users.get(i-1);
                if(user.getScore() != upperTeamScore){
                    rank = i;
                }
                if(!controller.getUser().getUsername().equals(users.get(i-1).getUsername())) {
                    labels.get(i-1).setText((rank) + " - " + user.getUsername() + "   Score : " + user.getScore());
                } else {
                    labels.get(i-1).setText((rank) + " - " + user.getUsername() + "   Score : " + user.getScore());
                    labels.get(i-1).setTextFill(Color.web("#1D7127"));
                }
                upperTeamScore = user.getScore();
            }
        }

    }

    public ScoreboardView (ScoreboardController controller){
        this.controller = controller;
    }

    public void run () throws Exception {
        start(LoginMenuView.getPrimaryStage());
    }

    public void printScoreboard(String scoreboard){
        System.out.println(scoreboard);
    }

    public void impossibleMenuNavigation (){
        System.out.println("menu navigation is not possible");
    }

    public void invalidCommand(){
        System.out.println("invalid command");
    }

    public void showCurrentMenu(){
        System.out.println("Scoreboard Menu");
    }


    private void fillLabels() {
        labels = new ArrayList<>();
        labels.add(player1);
        labels.add(player2);
        labels.add(player3);
        labels.add(player4);
        labels.add(player5);
        labels.add(player6);
        labels.add(player7);
        labels.add(player8);
        labels.add(player9);
        labels.add(player10);
        labels.add(player11);
        labels.add(player12);
        labels.add(player13);
        labels.add(player14);
        labels.add(player15);
        labels.add(player16);
        labels.add(player17);
        labels.add(player18);
        labels.add(player19);
        labels.add(player20);

    }

    public void back ()  {
        try {
            new MainMenuController(controller.getUser()).run();
        }catch (Exception e){}
    }

    public void refresh(){
        setUpScoreboard();

    }
}
