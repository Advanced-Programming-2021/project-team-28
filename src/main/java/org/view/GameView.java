package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controller.GameController;
import org.model.*;
import org.model.enums.MonsterCardPosition;
import org.model.enums.PhaseName;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.model.enums.PhaseName.*;
import static org.model.enums.MonsterCardPosition.*;
import static org.model.enums.SpellOrTrapCardPosition.*;


public class GameView extends Application {
    private static MediaPlayer backgroundMusic = new MediaPlayer(new Media(GameView.class.getResource("/sound/2 - Riot.mp3").toExternalForm()));
    private GameController game;
    private PhaseName phase = MAIN_PHASE_1;
    private ArrayList<ImageView> cardsInZone = new ArrayList<>();
    private boolean isPaused = false;
    private Stage popStage;
    public static Stage primaryStage;
    private boolean isMuted = false;
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
    @FXML
    private Text errorBox;
    @FXML
    private VBox buttonBar;
    @FXML
    private ImageView rivalSpell1;
    @FXML
    private ImageView rivalSpell2;
    @FXML
    private ImageView rivalSpell3;
    @FXML
    private ImageView rivalSpell4;
    @FXML
    private ImageView rivalSpell5;
    @FXML
    private ImageView rivalMonster1;
    @FXML
    private ImageView rivalMonster2;
    @FXML
    private ImageView rivalMonster3;
    @FXML
    private ImageView rivalMonster4;
    @FXML
    private ImageView rivalMonster5;
    @FXML
    private ImageView playerSpell1;
    @FXML
    private ImageView playerSpell2;
    @FXML
    private ImageView playerSpell3;
    @FXML
    private ImageView playerSpell4;
    @FXML
    private ImageView playerSpell5;
    @FXML
    private ImageView playerMonster1;
    @FXML
    private ImageView playerMonster2;
    @FXML
    private ImageView playerMonster3;
    @FXML
    private ImageView playerMonster4;
    @FXML
    private ImageView playerMonster5;
    @FXML
    private ImageView settingButton;
    @FXML
    private Button resume;
    @FXML
    private ImageView surrenderButton;
    @FXML
    private ImageView muteButton;

    public GameView(GameController game) {
        this.game = game;
    }

    public Text getErrorBox() {
        return errorBox;
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
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mainclass/FXML/game.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
        game.getDrawPhase().run();
        fillCardsInZoneImageViewArrayList();
        setInfo();
        backgroundMusic.setCycleCount(-1);
        backgroundMusic.play();
    }

    private void fillCardsInZoneImageViewArrayList() {
        cardsInZone.clear();
        cardsInZone.add(rivalSpell1);
        cardsInZone.add(rivalSpell2);
        cardsInZone.add(rivalSpell3);
        cardsInZone.add(rivalSpell4);
        cardsInZone.add(rivalSpell5);
        cardsInZone.add(rivalMonster1);
        cardsInZone.add(rivalMonster2);
        cardsInZone.add(rivalMonster3);
        cardsInZone.add(rivalMonster4);
        cardsInZone.add(rivalMonster5);
        cardsInZone.add(playerSpell1);
        cardsInZone.add(playerSpell2);
        cardsInZone.add(playerSpell3);
        cardsInZone.add(playerSpell4);
        cardsInZone.add(playerSpell5);
        cardsInZone.add(playerMonster1);
        cardsInZone.add(playerMonster2);
        cardsInZone.add(playerMonster3);
        cardsInZone.add(playerMonster4);
        cardsInZone.add(playerMonster5);
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
        playerLifePointBar.setWidth(((double) player.getLifePoint() / 8000) * 272.0);
        rivalLifePointBar.setWidth(((double) rivalPlayer.getLifePoint() / 8000) * 272.0);
        setGraveyard(player, playerGraveyard);
        setGraveyard(rivalPlayer, rivalGraveyard);
        setMonsterZone(player, false);
        setMonsterZone(rivalPlayer, true);
    }

    private void setMonsterZone(Player player, boolean isRival) {
        int firstLoop = isRival ? 4 : 14;
        int secondLoop = isRival ? -1 : 9;
        for (int i = 1; i <= 5; i++) {
            if (player.getMonsterCardsInZone().containsKey(i)) {
                MonsterCard card = player.getMonsterCardByLocationFromZone(i);
                if (card.getPosition() == DEFENSIVE_HIDDEN) {
                    cardsInZone.get(i + firstLoop).setImage(Card.getCardImageByName("Unknown"));
                } else {
                    cardsInZone.get(i + firstLoop).setImage(Card.getCardImageByName(card.getName()));
                }
                if (card.getPosition() == DEFENSIVE_HIDDEN || card.getPosition() == DEFENSIVE_OCCUPIED) {
                    cardsInZone.get(i + firstLoop).setRotate(90);
                }
            } else {
                cardsInZone.get(i + firstLoop).setImage(null);
            }
        }
        for (int i = 1; i <= 5; i++) {
            if (player.getSpellOrTrapCardsInZone().containsKey(i)) {
                Card card = player.getSpellOrTrapCardsInZone().get(i);
                if ((card instanceof SpellCard && ((SpellCard) card).getPosition() == HIDDEN) || (card instanceof TrapCard && ((TrapCard) card).getPosition() == HIDDEN)) {
                    cardsInZone.get(i + secondLoop).setImage(Card.getCardImageByName("Unknown"));
                } else {
                    cardsInZone.get(i + secondLoop).setImage(Card.getCardImageByName(card.getName()));
                }
            } else {
                cardsInZone.get(i + secondLoop).setImage(null);
            }
        }

    }


    private void setGraveyard(Player player, ImageView playerGraveyard) {
        graveyardVBox.getChildren().clear();
        if (player.getCardsInGraveyard().size() > 0) {
            int size = player.getCardsInGraveyard().size();
            Card lastCard = player.getCardsInGraveyard().get(size - 1);
            playerGraveyard.setImage(Card.getCardImageByName(lastCard.getName()));
            playerGraveyard.setOnMouseClicked(mouseEvent -> {
                selectedCardImageView.setImage(Card.getCardImageByName(lastCard.getName()));
                for (Card card : player.getCardsInGraveyard()) {
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
            if (isRival) {
                view = new ImageView(Card.getCardImageByName("Unknown"));
            } else {
                view = new ImageView(Card.getCardImageByName(card.getName()));
            }
            view.setFitHeight(100);
            view.setFitWidth(75);
            if (!isRival) {
                view.setOnMouseClicked(mouseEvent -> {
                    game.getRound().getPlayerByTurn().setSelectedCard(card);
                    addProperButtonsForPlayerCardsInHand(card);
                    selectedCardImageView.setImage(Card.getCardImageByName(card.getName()));
                    selectedCardDescription.setText(card.getName() + ": " + card.getDescription());
                });
            } else {
                view.setOnMouseClicked(mouseEvent -> {
                    game.getRound().getPlayerByTurn().setSelectedCard(card);
                    buttonBar.getChildren().clear();
                });
            }
            row.getChildren().add(view);
            i++;
        }
    }

    private void addProperButtonsForPlayerCardsInHand(Card card) {
        buttonBar.getChildren().clear();
        if (card instanceof MonsterCard) {
            Button attackDirect = new Button("Attack direct");
            Button attackToCard = new Button("Attack to card");
            Button changePosition = new Button("Change Position");
            Button flipSummon = new Button("Flip Summon");
            Button summon = new Button("Summon");
            Button set = new Button("Set");
            setOnMouseClickedForMonsterButtons((MonsterCard) card, attackDirect, attackToCard, changePosition, flipSummon, summon, set);
            buttonBar.getChildren().add(attackDirect);
            buttonBar.getChildren().add(attackToCard);
            buttonBar.getChildren().add(changePosition);
            buttonBar.getChildren().add(flipSummon);
            buttonBar.getChildren().add(summon);
            buttonBar.getChildren().add(set);
        } else {
            Button activateEffect = new Button("Activate Effect");
            Button set = new Button("Set");
            activateEffect.setOnMouseClicked(mouseEvent -> {
                sendCommandTpProperController("activate effect");
                setInfo();
            });
            set.setOnMouseClicked(mouseEvent -> {
                sendCommandTpProperController("set");
                setInfo();
            });
            buttonBar.getChildren().add(activateEffect);
            buttonBar.getChildren().add(set);
        }
    }

    private void setOnMouseClickedForMonsterButtons(MonsterCard card, Button attackDirect, Button attackToCard, Button changePosition, Button flipSummon, Button summon, Button set) {
        summon.setOnMouseClicked(mouseEvent -> {
            sendCommandTpProperController("summon");
            setInfo();
        });
        set.setOnMouseClicked(mouseEvent -> {
            sendCommandTpProperController("set");
            setInfo();
        });
        flipSummon.setOnMouseClicked(mouseEvent -> {
            sendCommandTpProperController("flip-summon");
            setInfo();
        });
        changePosition.setOnMouseClicked(mouseEvent -> {
            if (card.getPosition() == DEFENSIVE_OCCUPIED ||
                    card.getPosition() == DEFENSIVE_HIDDEN) {
                sendCommandTpProperController("set -p attack");
            } else {
                sendCommandTpProperController("set -p defense");
            }
            setInfo();
        });
        attackToCard.setOnMouseClicked(mouseEvent -> {
        });
        attackDirect.setOnMouseClicked(mouseEvent -> {
        });
    }

    private void setProfilePicture(Player player, ImageView profilePicture) {
        try {
            if (player.getUser().hasChangedProfilePicture()) {
                profilePicture.setImage(new Image(player.getUser().getProfilePicturePath()));
            } else {
                profilePicture.setImage(new Image(getClass().getResource(player.getUser().getProfilePicturePath()).toExternalForm()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendCommandTpProperController(String command) {
        if (phase == MAIN_PHASE_1 || phase == MAIN_PHASE_2) {
            game.getMainController().processCommand(command);
        } else if (phase == BATTLE_PHASE) {
            game.getBattleController().processCommand(command);
        }
    }

    public void nextPhase() {
        errorBox.setText("");
        if (phase == MAIN_PHASE_1) {
            phase = BATTLE_PHASE;
            phaseName.setText("Phase: " + phase.getPhaseName());
        } else if (phase == BATTLE_PHASE) {
            phase = MAIN_PHASE_2;
            phaseName.setText("Phase: " + phase.getPhaseName());
        } else if (phase == MAIN_PHASE_2) {
            phase = END_PHASE;
            phaseName.setText("Phase: " + phase.getPhaseName());
            nextPhase();
        } else if (phase == END_PHASE) {
            JOptionPane.showMessageDialog(null, "It's now "
                    + game.getRound().getPlayerByTurn().getUser().getNickname() + " 's turn");
            changeTurnForAllControllers();
            phase = DRAW_PHASE;
            phaseName.setText("Phase: " + phase.getPhaseName());
            nextPhase();
        } else if (phase == DRAW_PHASE) {
            game.getDrawPhase().run();
            phase = STANDBY_PHASE;
            phaseName.setText("Phase: " + phase.getPhaseName());
            nextPhase();
        } else if (phase == STANDBY_PHASE) {
            phase = MAIN_PHASE_1;
            setInfo();
        }

    }

    private void changeTurnForAllControllers() {
        game.getMainController().setSummonOrSetMonsterCard(false);
        game.getMainController().getPhase().changeTurn();
        System.out.println();
        game.getBattleController().getPhase().changeTurn();
        game.getRound().changeTurn();
        game.getDrawPhase().changeTurn();
    }


    public void showMatchWinner(User winner, int winnerScore, int loserScore) {
        System.out.println(winner.getUsername() + " won the whole match with score: " + winnerScore + "-" + loserScore);
    }

    public void showRoundWinner(User winner, int winnerScore, int loserScore) {
        System.out.println(winner.getUsername() + " won the game and the score is: " + winnerScore + "-" + loserScore);
    }

    public void printString(String text) {
        errorBox.setText(text);
    }

    public void summonedSuccessfully() {
        errorBox.setText("Summoned Successfully");
        buttonBar.getChildren().clear();
    }

    public void noCardSelectedYet() {
        errorBox.setText("No card is selected yet");
    }

    public void canNotSummonCard() {
        errorBox.setText("You can not summon this card");
    }

    public void monsterZoneIsFull() {
        errorBox.setText("Monster card zone is full");
    }

    public void canNotSetCard() {
        errorBox.setText("Can not set this card");
    }

    public void canNotAttackWithThisCard() {
        errorBox.setText("You can not attack with this card");
    }

    public void canNotDoThisActionInThisPhase() {
        errorBox.setText("You can not do this action in this phase");
    }

    public void canNotActivateCardInThisTurn() {
        errorBox.setText("This card can't be activated in the turn it was set");
    }

    public void preparationsOfSpellHaveNotBeenDoneYet() {
        errorBox.setText("preparations of this spell/trap are not done yet");
    }

    public void thisCardCanNotBeActivated() {
        errorBox.setText("This card can't be activated");
    }


    public void opponentFieldSpellSelected() {
        errorBox.setText("you picked the wrong card fool.");
    }

    public void actionNotAllowedInThisPhase() {
        errorBox.setText("action not allowed in this phase");
    }

    public void youCanNotAttackInYourFirstTurn() {
        errorBox.setText("You can't attack in your first turn");
    }

    public void thisCardAlreadyAttacked() {
        errorBox.setText("this card already attacked");
    }

    public void canNotAttackDirectly() {
        errorBox.setText("you can’t attack the opponent directly");
    }


    public void attackDirectResult(int damage) {
        errorBox.setText("your opponent receives " + damage + " battle damage");
    }

    public void canNotActivateOnThisTurn() {
        errorBox.setText("you can’t activate an effect on this turn");
    }

    public void thereIsNoCardToAttackHere() {
        errorBox.setText("there is no card to attack here");
    }



    public void pause() {

        System.out.println("paused");
        isPaused = true;
        popStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mainclass/FXML/settingPopup.fxml"));
        loader.setController(this);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 600, 400);
        popStage.setScene(scene);
        popStage.initStyle(StageStyle.UNDECORATED);
        popStage.show();

    }

    public void hidePopup() {
        popStage.hide();
    }

    public void surrendering(){
        int isSure = JOptionPane.showConfirmDialog(null, "Are you sure you want to surrender?");
        if (isSure == JOptionPane.YES_OPTION){
            System.out.println("surrendered");
            //TODO
            //controller syncing
        } else {
            System.out.println("was unSure");
        }
    }

    public void muteUnmute(){
        if (!isMuted){
            isMuted = true;
            try {
                muteButton.setImage(new Image(this.getClass().getResource("/mainclass/icons8-mute-100.png").toExternalForm()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("now you see muted icon");
        } else {
            isMuted = false;
            try {
                muteButton.setImage(new Image(this.getClass().getResource("/mainclass/icons8-sound-100.png").toExternalForm()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("now you see sound icon");
        }

    }
}
