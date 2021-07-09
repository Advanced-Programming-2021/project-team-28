package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controller.GameController;
import org.model.Card;
import org.model.Player;
import org.model.User;
import org.model.enums.PhaseName;


public class GameView extends Application {
    GameController game;
    PhaseName phase = PhaseName.MAIN_PHASE_1;
    @FXML
    private Text result;
    @FXML
    private Text phaseName;
    @FXML
    private Text rivalNickname;
    @FXML
    private Text playerNickname;
    @FXML
    private Text rivalLifePoint;
    @FXML
    private Text playerLifePoint;
    @FXML
    private ImageView selectedCardImageView;
    @FXML
    private Text selectedCardDescription;
    @FXML
    private ImageView playerDeck;
    @FXML
    private ImageView rivalDeck;
    @FXML
    private ImageView playerProfilePicture;
    @FXML
    private ImageView rivalPlayerProfilePicture;
    @FXML
    private VBox playerCardsInHand;
    @FXML
    private VBox rivalCardsInHand;

    public GameView(GameController game){
        this.game = game;
    }

    public void run() {
        try {
            start(LoginMenuView.getPrimaryStage());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mainclass/FXML/game.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
        setInfo();
    }

    private void setInfo() {
        Player firstPlayer = game.getRound().getFirstPlayer();
        Player secondPlayer = game.getRound().getSecondPlayer();
        Player player = game.getRound().getPlayerByTurn();
        Player rivalPlayer = game.getRound().getRivalPlayerByTurn();
        result.setText(firstPlayer.getUser().getNickname() + ":   " + firstPlayer.getNumberOfRoundsWon() + "\n"
                + secondPlayer.getUser().getNickname() + ":   " + secondPlayer.getNumberOfRoundsWon());
        playerNickname.setText("Nickname: " + player.getUser().getNickname() + "\nUsername: " + player.getUser().getUsername());
        rivalNickname.setText("Nickname: " + rivalPlayer.getUser().getNickname() + "\nUsername: " + rivalPlayer.getUser().getUsername());
        playerLifePoint.setText("LP: " + player.getLifePoint());
        rivalLifePoint.setText("LP: " + rivalPlayer.getLifePoint());
        setProfilePicture(player, playerProfilePicture);
        setProfilePicture(rivalPlayer, rivalPlayerProfilePicture);
        selectedCardImageView.setImage(Card.getCardImageByName("Unknown"));
        phaseName.setText("Phase: " + phase.getPhaseName());
        playerCardsInHand
    }

    private void setProfilePicture(Player player, ImageView profilePicture) {
        try {
            if(player.getUser().hasChangedProfilePicture()){
                profilePicture.setImage(new Image(player.getUser().getProfilePicturePath()));
            } else {
                profilePicture.setImage(new Image(getClass().getResource(player.getUser().getProfilePicturePath()).toExternalForm()));
            }
        } catch (Exception e){}
    }


    public void showMatchWinner(User winner , int winnerScore , int loserScore){
        System.out.println(winner.getUsername() + " won the whole match with score: " + winnerScore + "-" + loserScore);
    }

    public void showRoundWinner(User winner , int winnerScore , int loserScore){
        System.out.println(winner.getUsername() + " won the game and the score is: " + winnerScore + "-" + loserScore);
    }

}
