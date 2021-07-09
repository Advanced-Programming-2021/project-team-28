package org.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controller.CreateNewCardController;
import org.controller.MainMenuController;
import org.controller.ShopController;
import org.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ShopView extends Application {

    @FXML
    private Button buyButton;
    @FXML
    private Button backButton;
    @FXML
    private Label ownedNumber;
    @FXML
    private Label priceBar;
    @FXML
    private Label money;
    @FXML
    private Label resultOfPurchase;
    @FXML
    private ImageView cardImage;
    @FXML
    private VBox vBox;
    @FXML
    private AnchorPane parent;
    @FXML
    private Button createCard;
    @FXML
    private Text description;
    Scanner scanner = ScannerInstance.getInstance().getScanner();
    ShopController controller;
    String selectedCardName;

    public ShopView(ShopController controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(getClass().getResource("/mainclass/FXML/shopMenu.fxml"));
        Scene scene = new Scene(loader.load());
        vBox.getChildren().clear();
        setMusic(buyButton);
        setMusic(backButton);
        fillShopCards();
        stage.setScene(scene);
        stage.show();
    }

    private void fillShopCards() {
        vBox.getChildren().clear();
        buyButton.setDisable(true);
        ArrayList<CardAndImage> cardAndImages = Card.getCardsAndImages();
        money.setText("Your current balance is : " + controller.getUser().getBalance());
        cardImage.setImage(Card.getCardImageByName("Unknown"));
        setCardsInShop(cardAndImages);
    }

    private void setCardsInShop(ArrayList<CardAndImage> cardAndImages) {
        for (CardAndImage cardAndImage : cardAndImages) {
            if (!cardAndImage.getCardName().equals("Unknown")) {
                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(168.4);
                rectangle.setHeight(245.6);
                rectangle.setFill(new ImagePattern(cardAndImage.getImage()));
                rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        rectangle.setEffect(new DropShadow());
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
                        cardImage.setImage(cardAndImage.getImage());
                        ownedNumber.setText("You have : " + controller.getUser().numOfCardsWithThisName(cardAndImage.getCardName()) + " card of this type");
                        selectedCardName = cardAndImage.getCardName();
                        priceBar.setText("Price : " + Card.getPrices().get(cardAndImage.getCardName()));
                        if (Card.getPrices().get(cardAndImage.getCardName()) > controller.getUser().getBalance()) {
                            buyButton.setDisable(true);
                        } else buyButton.setDisable(false);

                        try {
                            description.setText("Description :\n" + Card.getCardByName(Card.getAllCards() , cardAndImage.getCardName()).getDescription());
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        if(cardAndImage.getCardName().equals("The Angry Cobbler")){
                            MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("/sound/angryy.mp3").toExternalForm()));
                            player.play();
                        }
                        if(cardAndImage.getCardName().equals("Abbas Boua'zar")){
                            MediaPlayer player = new MediaPlayer(new Media(getClass().getResource(abbasInitiator()).toExternalForm()));
                            player.setAutoPlay(true);
                            player.play();
                        }
                    }
                });
                vBox.getChildren().add(rectangle);
            }
        }
    }

    private String abbasInitiator() {
        int random = (int)Math.floor(Math.random()*(4)+2);
        switch (random){
            case 2 : {
                return "/sound/abas2.mp3";
            }
            case 3 : {
                return "/sound/abas3.mp3";
            }
            case 4 : {
                return "/sound/abas4.mp3";
            }
            case 5 : {
                return "/sound/abas5.mp3";
            }
        }
        return "/sound/abas2.mp3";
    }

    public static void setMusic(Button button){
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("/sound/gga.mp3").toExternalForm()));
                player.play();
            }
        });
    }

    public void buyCard() {
        try {
            Card targetCard = Card.getCardByName(Card.getAllCards(), selectedCardName);
            if (targetCard == null) {
                resultOfPurchase.setText("Please select a card first");
            } else {
                controller.sellCard(selectedCardName);
                money.setText("Your current balance is : " + controller.getUser().getBalance());
                ownedNumber.setText("You have : " + controller.getUser().numOfCardsWithThisName(selectedCardName) + " card of this type");
                if (Card.getPrices().get(selectedCardName) > controller.getUser().getBalance()) {
                    buyButton.setDisable(true);
                } else buyButton.setDisable(false);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        start(LoginMenuView.getPrimaryStage());
    }

    public void notEnoughMoney() {
        resultOfPurchase.setText("you dont have enough money");
        System.out.println("not enough money");

    }

    public void back() {
        try {
            new MainMenuController(controller.getUser()).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cardNotFound() {
        System.out.println("there is no card with this name");
    }

    public void invalidCommand() {
        System.out.println("invalid command");
    }

    public void menuShowCurrent() {
        System.out.println("Shop Menu");
    }

    public void impossibleMenuNavigation() {
        System.out.println("menu navigation is not possible");
    }

    public void showCard(Card card) {
        System.out.println(card);
    }

    public void showAllCards() {
        System.out.println("Advanced Ritual Art " + ": " + SpellsDescription.advancedRitualArt);
        System.out.println("Alexandrite Dragon " + ": " + MonstersDescriptions.alexandriteDragon);
        System.out.println("Axe Raider " + ": " + MonstersDescriptions.axeRaider);
        System.out.println("Baby dragon " + ": " + MonstersDescriptions.babyDragon);
        System.out.println("Battle OX " + ": " + MonstersDescriptions.battleOX);
        System.out.println("Battle warrior " + ": " + MonstersDescriptions.battleWarrior);
        System.out.println("Beast King Barbaros " + ": " + MonstersDescriptions.beastKing);
        System.out.println("Bitron " + ": " + MonstersDescriptions.bitron);
        System.out.println("Black Pendant " + ": " + SpellsDescription.blackPendant);
        System.out.println("Blue-Eyes white dragon " + ": " + MonstersDescriptions.blueEyesDragon);
        System.out.println("Call of The Haunted " + ": " + TrapsDescription.callOfTheHaunted);
        System.out.println("Change of Hearts " + ": " + SpellsDescription.changeOfHearts);
        System.out.println("Closed Forest " + ": " + SpellsDescription.closedForest);
        System.out.println("Command Knight " + ": " + MonstersDescriptions.commandKnight);
        System.out.println("Crab Turtle " + ": " + MonstersDescriptions.crabTurtle);
        System.out.println("Crawling dragon " + ": " + MonstersDescriptions.crawlingDragon);
        System.out.println("Curtain of the dark ones " + ": " + MonstersDescriptions.curtainOfTheDarkOnes);
        System.out.println("Dark Blade " + ": " + MonstersDescriptions.darkBlade);
        System.out.println("Dark Hole " + ": " + SpellsDescription.darkHole);
        System.out.println("Dark magician " + ": " + MonstersDescriptions.darkMagician);
        System.out.println("Exploder Dragon " + ": " + MonstersDescriptions.exploderDragon);
        System.out.println("Feral Imp " + ": " + MonstersDescriptions.feralImp);
        System.out.println("Fireyarou " + ": " + MonstersDescriptions.fireYarou);
        System.out.println("Flame manipulator " + ": " + MonstersDescriptions.flameManipulator);
        System.out.println("Forest " + ": " + SpellsDescription.forest);
        System.out.println("Gate Guardian " + ": " + MonstersDescriptions.gateGuardian);
        System.out.println("Haniwa " + ": " + MonstersDescriptions.haniwa);
        System.out.println("Harpie's Feather Duster " + ": " + SpellsDescription.harpies);
        System.out.println("Herald of Creation " + ": " + MonstersDescriptions.heraldOfTheCreation);
        System.out.println("Hero of the east " + ": " + MonstersDescriptions.heroOfTheEast);
        System.out.println("Horn Imp " + ": " + MonstersDescriptions.hornImp);
        System.out.println("Leotron " + ": " + MonstersDescriptions.leotron);
        System.out.println("Magic Cylinder " + ": " + TrapsDescription.magicCylinder);
        System.out.println("Magic jammer " + ": " + TrapsDescription.magicJammer);
        System.out.println("Magnum Shield " + ": " + SpellsDescription.magnumShield);
        System.out.println("Man-Eater Bug " + ": " + MonstersDescriptions.manEaterBug);
        System.out.println("Marshmallon " + ": " + MonstersDescriptions.marshmallon);
        System.out.println("Messenger of Peace " + ": " + SpellsDescription.messengerOfPeace);
        System.out.println("Mind Crush " + ": " + TrapsDescription.mindCrush);
        System.out.println("Mirage Dragon " + ": " + MonstersDescriptions.mirageDragon);
        System.out.println("Mirror Force " + ": " + TrapsDescription.mirrorForce);
        System.out.println("Monster Reborn " + ": " + SpellsDescription.monsterReborn);
        System.out.println("Mystical Space Typhoon " + ": " + SpellsDescription.mysticalSpaceTyphoon);
        System.out.println("Negate Attack " + ": " + TrapsDescription.negateAttack);
        System.out.println("Pot of Greed " + ": " + SpellsDescription.potOfGreed);
        System.out.println("Raigeki " + ": " + SpellsDescription.raigeki);
        System.out.println("Ring of Defence " + ": " + SpellsDescription.ringOfDefence);
        System.out.println("Scanner " + ": " + MonstersDescriptions.scanner);
        System.out.println("Silver Fang " + ": " + MonstersDescriptions.silverFang);
        System.out.println("Skull Guardian " + ": " + MonstersDescriptions.skullGuardian);
        System.out.println("Slot Machine " + ": " + MonstersDescriptions.slotMachine);
        System.out.println("Solemn Warning " + ": " + TrapsDescription.solemnWarning);
        System.out.println("Spell Absorption " + ": " + SpellsDescription.spellAbsorption);
        System.out.println("Spiral Serpent " + ": " + MonstersDescriptions.spiralSerpent);
        System.out.println("Suijin " + ": " + MonstersDescriptions.suijin);
        System.out.println("Supply Squad " + ": " + SpellsDescription.supplySquad);
        System.out.println("Sword of dark Destruction " + ": " + SpellsDescription.swordOfDestruction);
        System.out.println("Sword of Revealing Light " + ": " + SpellsDescription.swordOfRevealingLight);
        System.out.println("Terraforming " + ": " + SpellsDescription.terrafoming);
        System.out.println("Terratiger, the Empowered Warrior " + ": " + MonstersDescriptions.terraTigerTheEmpoweredWarrior);
        System.out.println("Texchanger " + ": " + MonstersDescriptions.texChanger);
        System.out.println("The Calculator " + ": " + MonstersDescriptions.theCalculator);
        System.out.println("The Tricky " + ": " + MonstersDescriptions.theTricky);
        System.out.println("Time Seal " + ": " + TrapsDescription.timeSeal);
        System.out.println("Torrential Tribute " + ": " + TrapsDescription.torrentialTribute);
        System.out.println("Trap Hole " + ": " + TrapsDescription.trapHole);
        System.out.println("Twin Twister " + ": " + SpellsDescription.twinTwister);
        System.out.println("Umiiruka " + ": " + SpellsDescription.umiiruka);
        System.out.println("United We Stand " + ": " + SpellsDescription.unitedWeStand);
        System.out.println("Vanity's Emptiness " + ": " + TrapsDescription.vanitiesEmptiness);
        System.out.println("Wall of Revealing Light " + ": " + TrapsDescription.wallOfRevealingLight);
        System.out.println("Warrior Dai Grepher " + ": " + MonstersDescriptions.warriorDaiGrepher);
        System.out.println("Wattaildragon " + ": " + MonstersDescriptions.wattailDragon);
        System.out.println("Wattkid " + ": " + MonstersDescriptions.wattkid);
        System.out.println("Yami " + ": " + SpellsDescription.yami);
        System.out.println("Yomi Ship " + ": " + MonstersDescriptions.yomiShip);
    }

    public void cheatActivated() {
        System.out.println("Cheat activated");
    }

    public void createCard() {
        CreateNewCardController createNewCardController = new CreateNewCardController(controller.getUser());
        createNewCardController.run();
    }

}
