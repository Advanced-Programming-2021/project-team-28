package org.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.model.enums.SpellEffect;
import org.model.enums.SpellIcon;
import org.model.enums.SpellOrTrapCardPosition;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SpellCard extends Card implements Serializable {

    @Expose
    private SpellEffect effect;
    @Expose
    private SpellIcon icon;
    @Expose
    public static ArrayList<SpellCard> allSpellCard = new ArrayList<>();
    @Expose
    private SpellOrTrapCardPosition position;

    private boolean isActivationCancelled = false;



    public SpellCard (String ownerUsername , String name , String number , String description , SpellIcon icon , SpellEffect effect){
        super(ownerUsername ,name, number, description);
        setEffect(effect);
        setIcon(icon);
        setPosition(SpellOrTrapCardPosition.NOT_IN_PLAY_ZONE);
        SpellCard.allSpellCard.add(this);
        classID = 1;
    }

    public SpellCard(){
        classID = 1;
    }

    @Override
    public String toString() {
        return "Name : " + this.name + "\n"
                + "Spell" + "\n"
                + "Type : " + this.icon.getName() + "\n"
                + "Description : " + this.description ;
    }

    public SpellOrTrapCardPosition getPosition() {
        return position;
    }

    public void setPosition(SpellOrTrapCardPosition position) {
        this.position = position;
    }

    public SpellEffect getEffect() {
        return effect;
    }

    public void setEffect(SpellEffect effect) {
        this.effect = effect;
    }

    public SpellIcon getIcon() {
        return icon;
    }

    public void setIcon(SpellIcon icon) {
        this.icon = icon;
    }

    public boolean isActivationCancelled() {
        return isActivationCancelled;
    }

    public void setActivationCancelled(boolean activationCancelled) {
        isActivationCancelled = activationCancelled;
    }

    @Override
    public Object clone() {
        SpellCard cloneSpellCard = new SpellCard();
        cloneSpellCard.name = this.name;
        cloneSpellCard.description = this.description;
        cloneSpellCard.number = this.number;
        cloneSpellCard.ownerUsername = this.ownerUsername;
        cloneSpellCard.icon = this.icon;
        cloneSpellCard.effect = this.effect;
        cloneSpellCard.position = this.position;
        return cloneSpellCard;
    }

    public static void serialize(){
        try (Writer writer = new FileWriter("src/SpellCardsOutput.json")) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            gson.toJson(SpellCard.allSpellCard, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize(){
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src/SpellCardsOutput.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpellCard[] spellCards = gson.fromJson(reader,SpellCard[].class);


        for (SpellCard spellCard: spellCards){
            SpellCard.allSpellCard.add(spellCard);
            Card.getAllCards().add(spellCard);
//            User.getUserByUsername(spellCard.getOwnerUsername()).addToCards(spellCard);
        }

    }
}

