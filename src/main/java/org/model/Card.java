package org.model;

import com.google.gson.annotations.Expose;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class Card {
    @Expose
    protected String name;
    @Expose
    protected String number;
    @Expose
    protected String description;
    @Expose
    protected boolean isGoingToGraveyard = false;
    @Expose
    protected String ownerUsername;

    private static HashMap<Image , String> cardsAndImages = new HashMap<>();

    public static HashMap<Image, String> getCardsAndImages() {
        return cardsAndImages;
    }

    private static ArrayList<Card> allCards = new ArrayList<>();
    private static HashMap<ImageView, String> cardsPicturePath = new HashMap<>();

    public Card(String ownerUsername, String name, String number, String description) {
        setOwnerUsername(ownerUsername);
        setName(name);
        setDescription(description);
        setNumber(number);
    }

    public Card() {

    }


    public abstract Object clone() throws CloneNotSupportedException;

    public abstract String toString();

    public static String cardsArrayListToString(ArrayList<Card> cards) {
        ArrayList<Card> monsterCards = new ArrayList<>();
        ArrayList<Card> spellAndTrapCards = new ArrayList<>();
        divideCardsByType(cards, monsterCards, spellAndTrapCards);
        sortCards(monsterCards);
        sortCards(spellAndTrapCards);
        StringBuilder cardsToStringBuilder = new StringBuilder("Monsters:\n");
        for (Card monsterCard : monsterCards) {
            cardsToStringBuilder.append(monsterCard.getName());
            cardsToStringBuilder.append(": ");
            cardsToStringBuilder.append(monsterCard.getDescription());
            cardsToStringBuilder.append("\n");
        }
        cardsToStringBuilder.append("Spells and Traps:\n");
        for (Card spellAndTrapCard : spellAndTrapCards) {
            cardsToStringBuilder.append(spellAndTrapCard.getName());
            cardsToStringBuilder.append(": ");
            cardsToStringBuilder.append(spellAndTrapCard.getDescription());
            cardsToStringBuilder.append("\n");
        }
        return cardsToStringBuilder.toString();
    }

    private static void divideCardsByType(ArrayList<Card> cards, ArrayList<Card> monsterCards,
                                          ArrayList<Card> spellAndTrapCards) {
        for (Card card : cards) {
            if (card instanceof MonsterCard) {
                monsterCards.add(card);
            } else if (card instanceof SpellCard || card instanceof TrapCard) {
                spellAndTrapCards.add(card);
            }
        }

    }

    private static void sortCards(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.size() - 1; j++) {
                if (Utilities.compareAlphabetical(cards.get(j).getName(), cards.get(j + 1).getName()) > 0) {
                    Collections.swap(cards, j, j + 1);
                }
            }
        }
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public static void addToAllCards(Card card) {
        Card.allCards.add(card);
    }

    public static boolean isThisCardNameValid(String cardName) {
        for (Card card : Card.getAllCards()) {
            if (card.getName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isGoingToGraveyard() {
        return isGoingToGraveyard;
    }

    public void setGoingToGraveyard(boolean goingToGraveyard) {
        isGoingToGraveyard = goingToGraveyard;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public static Card getCardByName(ArrayList<Card> cards, String name) throws CloneNotSupportedException {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                return (Card) card.clone();
            }
        }
        return null;
    }


}
