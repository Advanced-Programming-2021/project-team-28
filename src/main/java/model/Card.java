package model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Card {
    @Expose
    protected String name;
    @Expose
    protected String number;
    @Expose
    protected String description;
    @Expose(serialize = false, deserialize = false)
    public static ArrayList<Card> cards = new ArrayList<>();

    public Card(String name, String number, String description) {
        setName(name);
        setDescription(description);
        setNumber(number);
        cards.add(this);
    }

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
                if (Utilities.compareAlphabetical(cards.get(j).getName(), cards.get(j+1).getName()) > 0) {
                    Collections.swap(cards, j, j + 1);
                }
            }
        }
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
}
