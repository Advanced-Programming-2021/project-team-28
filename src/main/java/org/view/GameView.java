package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controller.GameController;
import org.model.Card;
import org.model.DrawPhase;
import org.model.Player;
import org.model.User;
import org.model.enums.PhaseName;

import javax.swing.*;

import static org.model.enums.PhaseName.*;


public class GameView extends Application {
    private static MediaPlayer backgroundMusic = new MediaPlayer(new Media(GameView.class.getResource("/sound/2 - Riot.mp3").toExternalForm()));
    private GameController game;
    private PhaseName phase = MAIN_PHASE_1;
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
    @FXML
    private Text rivalNumberOfCardsRemaining;
    @FXML
    private Text playerNumberOfCardsRemaining;
    @FXML
    private Rectangle playerLifePointBar;
    @FXML
    private Rectangle rivalLifePointBar;
    @FXML
    private ImageView playerGraveyard;
    @FXML
    private ImageView rivalGraveyard;
    @FXML
    private VBox graveyardVBox;

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
        game.getDrawPhase().run();
        setInfo();
        backgroundMusic.setCycleCount(-1);
        backgroundMusic.play();
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
        setCardsInHand(player, playerCardsInHand, false);
        setCardsInHand(rivalPlayer, rivalCardsInHand, true);
        playerNumberOfCardsRemaining.setText(String.valueOf(player.getRemainingPlayerCardsInGame().size()));
        rivalNumberOfCardsRemaining.setText(String.valueOf(rivalPlayer.getRemainingPlayerCardsInGame().size()));
        playerLifePointBar.setWidth(((double)player.getLifePoint() / 8000) * 272.0);
        rivalLifePointBar.setWidth(((double)rivalPlayer.getLifePoint() / 8000) * 272.0);
        setGraveyard(player, playerGraveyard);
        setGraveyard(rivalPlayer, rivalGraveyard);
    }

    private void setGraveyard(Player player, ImageView playerGraveyard) {
        graveyardVBox.getChildren().clear();
        if(player.getCardsInGraveyard().size() > 0){
            int size = player.getCardsInGraveyard().size();
            Card lastCard = player.getCardsInGraveyard().get(size - 1);
            playerGraveyard.setImage(Card.getCardImageByName(lastCard.getName()));
            playerGraveyard.setOnMouseClicked(mouseEvent -> {
                selectedCardImageView.setImage(Card.getCardImageByName(lastCard.getName()));
                for (Card card : player.getCardsInGraveyard()){
                    ImageView view = new ImageView(Card.getCardImageByName(card.getName()));
                    graveyardVBox.getChildren().add(view);
                    view.setOnMouseClicked(mouseEvent2 -> selectedCardImageView.setImage(Card.getCardImageByName(card.getName())));
                }
            });
        }
    }

    private void setCardsInHand(Player player, VBox cardsInHand, boolean isRival) {
        cardsInHand.getChildren().clear();
        HBox row = new HBox();
        int i = 0;
        for (Card card : player.getCardsInHand()) {
            if (i % 6 == 0) {
                row = new HBox();
                row.setSpacing(7);
                cardsInHand.getChildren().add(row);
            }
            ImageView view;
            if(isRival){
                view = new ImageView(Card.getCardImageByName("Unknown"));
            } else {
                view = new ImageView(Card.getCardImageByName(card.getName()));
            }
            view.setFitHeight(100);
            view.setFitWidth(75);
            if(!isRival){
                view.setOnMouseClicked(mouseEvent -> {
                selectedCardImageView.setImage(Card.getCardImageByName(card.getName()));
                selectedCardDescription.setText(card.getName() + ": " + card.getDescription());
            });
            }
            row.getChildren().add(view);
            i++;
        }
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

    public void nextPhase(){
        if(phase == MAIN_PHASE_1){
            phase = BATTLE_PHASE;
            phaseName.setText("Phase: " + phase.getPhaseName());
        } else if (phase == BATTLE_PHASE){
            phase = MAIN_PHASE_2;
            phaseName.setText("Phase: " + phase.getPhaseName());
        } else if (phase == MAIN_PHASE_2){
            phase = END_PHASE;
            phaseName.setText("Phase: " + phase.getPhaseName());
            nextPhase();
        } else if (phase == END_PHASE){
            JOptionPane.showMessageDialog(null, "It's now "
                    + game.getRound().getPlayerByTurn().getUser().getNickname() + " 's turn");
            changeTurnForAllControllers();
            phase = DRAW_PHASE;
            phaseName.setText("Phase: " + phase.getPhaseName());
            nextPhase();
        } else if(phase == DRAW_PHASE){
            game.getDrawPhase().run();
            phase = STANDBY_PHASE;
            phaseName.setText("Phase: " + phase.getPhaseName());
            nextPhase();
        } else if (phase == STANDBY_PHASE){
            phase = MAIN_PHASE_1;
            setInfo();
        }

    }

    private void changeTurnForAllControllers() {
        game.getMainController().getPhase().changeTurn();
        System.out.println();
        game.getBattleController().getPhase().changeTurn();
        game.getRound().changeTurn();
        game.getDrawPhase().changeTurn();
    }


    public void showMatchWinner(User winner , int winnerScore , int loserScore){
        System.out.println(winner.getUsername() + " won the whole match with score: " + winnerScore + "-" + loserScore);
    }

    public void showRoundWinner(User winner , int winnerScore , int loserScore){
        System.out.println(winner.getUsername() + " won the game and the score is: " + winnerScore + "-" + loserScore);
    }
}
