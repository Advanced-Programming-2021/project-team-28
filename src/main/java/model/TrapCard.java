package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import enums.SpellOrTrapCardPosition;
import enums.TrapEffect;
import enums.TrapIcon;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TrapCard extends Card implements Cloneable {

    @Expose
    private TrapIcon icon;
    @Expose
    private TrapEffect effect;
    @Expose
    private SpellOrTrapCardPosition position;
    @Expose
    public static ArrayList<TrapCard> allTrapCard = new ArrayList<>();
    private boolean hasSetInThisTurn = false;
    private boolean isActivationCancelled = false;

    public TrapCard(String ownerUsername, String name, String number, String description, TrapIcon icon, TrapEffect effect) {
        super(ownerUsername, name, number, description);
        setIcon(icon);
        setEffect(effect);
        setPosition(SpellOrTrapCardPosition.NOT_IN_PLAY_ZONE);
        TrapCard.allTrapCard.add(this);
    }

    public TrapCard() {

    }

    public boolean isActivationCancelled() {
        return isActivationCancelled;
    }

    public void setActivationCancelled(boolean activationCancelled) {
        isActivationCancelled = activationCancelled;
    }

    public boolean hasSetInThisTurn() {
        return hasSetInThisTurn;
    }

    public void setHasSetInThisTurn(boolean hasSetInThisTurn) {
        this.hasSetInThisTurn = hasSetInThisTurn;
    }

    public SpellOrTrapCardPosition getPosition() {
        return position;
    }

    public void setPosition(SpellOrTrapCardPosition position) {
        this.position = position;
    }

    public TrapIcon getIcon() {
        return icon;
    }

    public void setIcon(TrapIcon icon) {
        this.icon = icon;
    }

    public TrapEffect getEffect() {
        return effect;
    }

    public void setEffect(TrapEffect effect) {
        this.effect = effect;
    }

    @Override
    public Object clone() {
        TrapCard cloneTrapCard = new TrapCard();
        cloneTrapCard.number = this.number;
        cloneTrapCard.description = this.description;
        cloneTrapCard.name = this.name;
        cloneTrapCard.ownerUsername = this.ownerUsername;
        cloneTrapCard.icon = this.icon;
        cloneTrapCard.effect = this.effect;
        cloneTrapCard.position = this.position;
        return cloneTrapCard;
    }

    public static void serialize() {
        try (Writer writer = new FileWriter("src/TrapCardsOutput.json")) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            gson.toJson(TrapCard.allTrapCard, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src/TrapCardsOutput.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        TrapCard[] trapCards = gson.fromJson(reader, TrapCard[].class);


        TrapCard.allTrapCard = new ArrayList<>();
        for (TrapCard trapCard : trapCards) {
            TrapCard.allTrapCard.add(trapCard);
            Card.getAllCards().add(trapCard);
//            User.getUserByUsername(trapCard.getOwnerUsername()).addToCards(trapCard);
        }
    }

    @Override
    public String toString() {
        return "Name : " + this.name + "\n"
                + "Trap" + "\n"
                + "Type : " + this.icon.getName() + "\n"
                + "Description : " + this.description;
    }
}
