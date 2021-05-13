package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Deck {

    @Expose
    private String creatorUsername;
    @Expose
    private String deckName;

    private ArrayList<Card> allCardsInMainDeck = new ArrayList<>();

    private ArrayList<Card> allCardsInSideDeck = new ArrayList<>();

    @Expose
    private ArrayList<String> allCardsNameInMainDeck = new ArrayList<>();
    @Expose
    private ArrayList<String> allCardsNameInSideDeck = new ArrayList<>();
    @Expose
    public static ArrayList<Deck> allDecks = new ArrayList<>();


    public Deck(String deckName, String creatorUsername) {
        setCreatorUsername(creatorUsername);
        setDeckName(deckName);
        User.getUserByUsername(creatorUsername).addToDecks(this);
    }

    public String showDeck(boolean isSideDeck) {
        StringBuilder deckToStringBuilder = new StringBuilder("Deck: ");
        deckToStringBuilder.append(deckName);
        deckToStringBuilder.append("\n");
        if (isSideDeck) {
            deckToStringBuilder.append("Side deck:\n");
            deckToStringBuilder.append(Card.cardsArrayListToString(allCardsInSideDeck));
        } else {
            deckToStringBuilder.append("Main deck:\n");
            deckToStringBuilder.append(Card.cardsArrayListToString(allCardsInMainDeck));
        }
        return deckToStringBuilder.toString();
    }

    public void addCardToDeck(String cardName, boolean isToSideDeck) {
        User user = User.getUserByUsername(creatorUsername);
        for (int i = 0; i < user.getAllCards().size(); i++) {
            if (user.getAllCards().get(i).getName().equals(cardName) &&
                    !isThisCardInDeck(user.getAllCards().get(i))) {
                if (isToSideDeck) {
                    allCardsInSideDeck.add(user.getAllCards().get(i));
                    allCardsNameInSideDeck.add(user.getAllCards().get(i).getName());
                } else {
                    allCardsInMainDeck.add(user.getAllCards().get(i));
                    allCardsNameInMainDeck.add(user.getAllCards().get(i).getName());
                }
                return;
            }
        }
    }

    private boolean isThisCardInDeck(Card card) {
        for (Card cardsInDeck : allCardsInSideDeck) {
            if (cardsInDeck.equals(card)) {
                return true;
            }
        }
        for (Card cardsInDeck : allCardsInMainDeck) {
            if (cardsInDeck.equals(card)) {
                return true;
            }
        }
        return false;

    }

    public void removeCardFromDeck(String cardName, boolean isFromSideDeck) {
        if (isFromSideDeck) {
            for (Card card : allCardsInSideDeck) {
                if (card.getName().equals(cardName)) {
                    allCardsInSideDeck.remove(card);
                    allCardsNameInSideDeck.remove(card.getName());
                    return;
                }
            }
        } else {
            for (Card card : allCardsInMainDeck) {
                if (card.getName().equals(cardName)) {
                    allCardsInMainDeck.remove(card);
                    allCardsNameInSideDeck.remove(card.getName());
                    return;
                }
            }
        }
    }

    public static Deck getDeckByOwnerAndName(String ownerName, String deckName) {
        for (Deck deck: allDecks){
            if (ownerName.equals(deck.creatorUsername)
                    && deckName.equals(deck.getDeckName())) return deck;
        }
        return null;
    }

    public void removeDeck() {
        User.getUserByUsername(creatorUsername).getDecks().remove(this);
    }

    public void activateDeck() {
        User.getUserByUsername(creatorUsername).setActiveDeck(this);
    }

    public boolean isMainDeckFull() {
        return this.allCardsInMainDeck.size() == 60;
    }

    public boolean isSideDeckFull() {
        return this.allCardsInSideDeck.size() == 15;
    }

    public boolean doesDeckHaveThreeCardsFromThisTypeOfCard(String cardName) {
        int numberOfThisCard = 0;

        for (Card card : this.allCardsInMainDeck) {
            if (card.getName().equals(cardName)) ++numberOfThisCard;
        }

        for (Card card : this.allCardsInSideDeck) {
            if (card.getName().equals(cardName)) ++numberOfThisCard;
        }

        return numberOfThisCard >= 3;
    }

    public String toString() {
        String deckInfo;
        if (isDeckValid()) {
            deckInfo = this.deckName + ": main deck " + this.allCardsInMainDeck.size() + ", side deck " +
                    this.allCardsInSideDeck.size() + ", " + "valid\n";
        } else {
            deckInfo = this.deckName + ": main deck " + this.allCardsInMainDeck.size() + ", side deck " +
                    this.allCardsInSideDeck.size() + ", " + "invalid\n";
        }
        return deckInfo;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getDeckName() {
        return this.deckName;
    }

    private void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getNumOfMainCards() {
        return allCardsInMainDeck.size();
    }

    public int getNumOfSideCards() {
        return this.allCardsInSideDeck.size();
    }

    public ArrayList<Card> getAllCardsInMainDeck() {
        return allCardsInMainDeck;
    }

    public ArrayList<Card> getAllCardsInSideDeck() {
        return allCardsInSideDeck;
    }

    public boolean isDeckValid() {
        return (allCardsInMainDeck.size() >= 40 && allCardsInMainDeck.size() <= 60 && allCardsInSideDeck.size() <= 15);
    }

    public boolean isThisCardInSideOrMainDeck(String cardName, boolean isInSideDeck) {
        if (isInSideDeck) {
            for (Card card : allCardsInSideDeck) {
                if (card.getName().equals(cardName)) {
                    return true;
                }
            }
        } else {
            for (Card card : allCardsInMainDeck) {
                if (card.getName().equals(cardName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void serialize() {
        try (Writer writer = new FileWriter("DecksOutput.json")) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            gson.toJson(Deck.allDecks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src/DecksOutput.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Deck[] decks = gson.fromJson(reader, Deck[].class);
        for (Deck deck : decks) {
            for (String cardName : deck.allCardsNameInMainDeck) {
                deck.allCardsInMainDeck.add(Card.getCardByName(Card.allCards, cardName));
            }
            for (String cardName : deck.allCardsNameInSideDeck) {
                deck.allCardsInSideDeck.add(Card.getCardByName(Card.allCards, cardName));
            }
            Deck.allDecks.add(deck);
        }
    }
}
