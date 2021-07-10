package org.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controller.MiniGameController;
import org.model.enums.MiniGame;
import org.model.enums.Status;

public class MiniGameView extends Application {

    @FXML
    private Rectangle P1R;
    @FXML
    private Rectangle P1S;
    @FXML
    private Rectangle P1P;
    @FXML
    private Rectangle P2P;
    @FXML
    private Rectangle P2S;
    @FXML
    private Rectangle P2R;
    @FXML
    private Rectangle P2res;
    @FXML
    private Rectangle P1res;
    @FXML
    private Text text;
    @FXML
    private Button start;

    MiniGameController controller;

    private Boolean hasP1Selected = Boolean.FALSE;
    private Boolean hasP2Selected = Boolean.FALSE;

    private boolean isFinished = false;

    private MiniGame P1Selection = null;
    private MiniGame P2Selection = null;

    private Status winner = null;

    public MiniGameView(MiniGameController controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/miniGame.fxml"));
        Scene scene = new Scene(loader.load());
        fillBoard();
        stage.setScene(scene);
        stage.show();
    }


    public void run() throws Exception {
        start(LoginMenuView.getPrimaryStage());
    }

    private void fillBoard() {
        setOptions();
        start.setDisable(true);
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO : add the code to start the match
            }
        });

    }

    private void setOptions() {
        P1P.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/paper.bmp").toExternalForm())));
        P1S.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/scissors.bmp").toExternalForm())));
        P1R.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/rock.bmp").toExternalForm())));
        P2P.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/paper2.bmp").toExternalForm())));
        P2S.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/scissors2.bmp").toExternalForm())));
        P2R.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/rock2.bmp").toExternalForm())));
        giveP1ButtonsEffect(P1P , MiniGame.PAPER );
        giveP1ButtonsEffect(P1R , MiniGame.ROCK );
        giveP1ButtonsEffect(P1S , MiniGame.SCISSORS );
        giveP2ButtonsEffect(P2P , MiniGame.PAPER );
        giveP2ButtonsEffect(P2R , MiniGame.ROCK );
        giveP2ButtonsEffect(P2S , MiniGame.SCISSORS );

    }

    private void giveP2ButtonsEffect(Rectangle rectangle, MiniGame value) {
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setEffect(new Glow());
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setEffect(null);
            }
        });
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                P2Selection = value;
                hasP2Selected = true;
                defineWinner(P1Selection , P2Selection);
            }

        });
    }

    private void giveP1ButtonsEffect(Rectangle rectangle , MiniGame value ) {
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setEffect(new Glow());
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setEffect(null);
            }
        });
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                P1Selection = value;
                hasP1Selected = true;
                defineWinner(P1Selection , P2Selection);
            }
        });
    }

    private void defineWinner(MiniGame p1, MiniGame p2) {
        if(p1 != null && p2 != null && !isFinished){

            Status result = controller.winnerSelection(p1, p2);
            switch (result){

                case P1:{
                    isFinished = true ;
                    text.setText("Player ONE Won");
                    winner = Status.P1;
                    start.setDisable(false);
                    showChoices();
                    return;
                }
                case P2:{
                    isFinished = true ;
                    text.setText("Player TWO Won");
                    winner = Status.P2;
                    start.setDisable(false);
                    showChoices();
                    return;
                }
                case DRAW:{
                    text.setText("Draw! (try again)");
                    showChoices();
                    P1Selection = null;
                    P2Selection = null;
                }
            }

        }
    }

    private void showChoices() {
        switch (P1Selection){
            case ROCK:{
                P1res.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/Rresault.bmp").toExternalForm())));
                break;
            }
            case SCISSORS:{
                P1res.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/Sresault.bmp").toExternalForm())));
                break;
            }
            case PAPER:{
                P1res.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/Presault.bmp").toExternalForm())));

            }
        }

        switch (P2Selection){
            case ROCK:{
                P2res.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/Rresault2.bmp").toExternalForm())));
                break;
            }
            case SCISSORS:{
                P2res.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/Sresault2.bmp").toExternalForm())));
                break;
            }
            case PAPER:{
                P2res.setFill(new ImagePattern(new Image(getClass().getResource("/miniGame/Presault2.bmp").toExternalForm())));

            }
        }
    }
}
