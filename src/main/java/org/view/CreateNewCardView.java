package org.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controller.CreateNewCardController;
import org.controller.ShopController;
import org.model.enums.MonsterPower;
import org.model.enums.MonsterType;
import org.model.enums.SpellEffect;
import org.model.enums.TrapEffect;

import javax.swing.*;


public class CreateNewCardView extends Application {
    private CreateNewCardController controller;
    public static Stage primaryStage;
    @FXML
    private ChoiceBox cardType;
    @FXML
    private ChoiceBox levelChoices;
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
    @FXML
    private ChoiceBox monsterTypeChoices;
    @FXML
    private Button clearPower;
    @FXML
    private Text powerDescription;
    @FXML
    private ChoiceBox monsterPowerChoices;
    @FXML
    private ChoiceBox spellEffectChoices;
    @FXML
    private ChoiceBox trapEffectChoices;
    @FXML
    private Text priceText;


    private String type = null;
    private String name = null;
    private int level = 0;
    private MonsterType monsterType = null;
    private MonsterPower monsterPower = null;
    private SpellEffect spellEffect = null;
    private TrapEffect trapEffect = null;
    private int price = 0;
    private int monsterDef = 0;
    private int monsterAtk = 0;

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
        cardType.setDisable(true);
        atkText.setDisable(true);
        defText.setDisable(true);
        monsterTypeChoices.setDisable(true);
        monsterPowerChoices.setDisable(true);
        spellEffectChoices.setDisable(true);
        trapEffectChoices.setDisable(true);


        cardType.setOnAction((event) -> {
            type = (String) cardType.getValue();
            if (!type.equals("Spell") && !type.equals("Trap")) {
                atkText.setDisable(false);
                defText.setDisable(false);
                monsterTypeChoices.setDisable(false);
                monsterPowerChoices.setDisable(false);
                spellEffectChoices.setDisable(true);
                spellEffectChoices.getSelectionModel().clearSelection();
                trapEffectChoices.setDisable(true);
                trapEffectChoices.getSelectionModel().clearSelection();
            } else {
                atkText.setDisable(true);
                atkText.clear();
                defText.setDisable(true);
                defText.clear();
                monsterTypeChoices.setDisable(true);
                monsterTypeChoices.getSelectionModel().clearSelection();
                monsterPowerChoices.setDisable(true);
                monsterPowerChoices.getSelectionModel().clearSelection();
                if (type.equals("Spell")) {
                    spellEffectChoices.setDisable(false);
                }
                if (type.equals("Trap")) {
                    trapEffectChoices.setDisable(false);
                }
            }

        });
        levelChoices.setOnAction((event) -> {
            cardType.setDisable(false);
            level = (int) levelChoices.getValue();

        });
        monsterTypeChoices.setOnAction((event) -> {
            monsterType = (MonsterType) monsterTypeChoices.getValue();
        });
        monsterPowerChoices.setOnAction((event) -> {
            monsterPower = (MonsterPower) monsterPowerChoices.getValue();
            powerDescription.setText(monsterPower.description);
        });
        trapEffectChoices.setOnAction((event) -> {
            trapEffect = (TrapEffect) trapEffectChoices.getValue();
            powerDescription.setText(trapEffect.description);
        });
        spellEffectChoices.setOnAction((event) -> {
            spellEffect = (SpellEffect) spellEffectChoices.getValue();
            powerDescription.setText(spellEffect.description);
        });


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

        for (int i = 1; i <= 12; i++) {
            levelChoices.getItems().add(i);
        }

        for (MonsterType type : MonsterType.values()) {
            monsterTypeChoices.getItems().add(type);
        }
        for (MonsterPower power : MonsterPower.values()) {
            if (power.equals(MonsterPower.NONE) || power.equals(MonsterPower.RITUAL))
                continue;
            monsterPowerChoices.getItems().add(power);
        }
        for (TrapEffect effect : TrapEffect.values()) {
            trapEffectChoices.getItems().add(effect);
        }
        for (SpellEffect effect : SpellEffect.values()) {
            spellEffectChoices.getItems().add(effect);
        }

    }

    public void calculatePrice() {
        if (level == 0) {
            JOptionPane.showMessageDialog(null, "please select a level first");
        } else if (type == null) {
            JOptionPane.showMessageDialog(null, "you hae to select a type for your card");
        } else if (type.equals("Trap") || type.equals("Spell")) {
            if (spellEffect == null || trapEffect == null) {
                price = 2000;
            } else {
                price = 3000;
                priceText.setText("its price will be: " + price);
                return;
            }
        } else {
            if (atkText.getText().equals("") || defText.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "please inter to integers for atk & def");
            } else {
                try {
                    monsterAtk = Integer.parseInt(atkText.getText());
                    monsterDef = Integer.parseInt(defText.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "enter two valid integer number for ATK & DEF");
                    return;
                }

                if (monsterType == null) {
                    JOptionPane.showMessageDialog(null, "please select your Monster type");
                } else {
                    if (monsterPower == null) {
                        price = monsterAtk + monsterDef;
                    } else {
                        price = monsterAtk + monsterDef + 500;
                    }
                    priceText.setText("its price will be: " + price);
                    return;
                }
            }
        }

    }

    public void create() {
        if (cardName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please enter card name to crate a card");
        } else if (description.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please enter a description for your card");
        } else if (level == 0) {
            JOptionPane.showMessageDialog(null, "please select a level first");
        } else if (type == null) {
            JOptionPane.showMessageDialog(null, "you hae to select a type for your card");
        } else {
            type = (String) cardType.getValue();
            switch (type) {
                case "Spell": {
                    createSpell();
                    break;
                }

                case "Trap": {
                    createTrap();
                    break;
                }

                default: {
                    createMonster();
                    break;
                }
            }
        }
    }

    public void createMonster() {
        System.out.println(type);

    }

    public void createSpell() {
        System.out.println(type);
    }

    public void createTrap() {
        System.out.println(type);
    }

    public void clearPower() {
        monsterPowerChoices.getSelectionModel().clearSelection();
        spellEffectChoices.getSelectionModel().clearSelection();
        trapEffectChoices.getSelectionModel().clearSelection();
        powerDescription.setText("");
    }
}
