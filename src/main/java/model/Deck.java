package model;

import java.util.*;

public class Deck {

    private User user;
    private String deckName;
    private ArrayList<Card> allCardsInMainDeck = new ArrayList<>();
    private ArrayList<Card> allCardsInSideDeck = new ArrayList<>();

    public Deck(User userWhoCreateTheDeck, String deckName) {
        this.user = userWhoCreateTheDeck;
        setDeckName(deckName);
        this.user.addToDecks(this);
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
        for (int i = 0; i < user.getAllCards().size(); i++) {
            if (user.getAllCards().get(i).getName().equals(cardName) &&
                    !isThisCardInDeck(user.getAllCards().get(i))) {
                if (isToSideDeck) {
                    allCardsInSideDeck.add(user.getAllCards().get(i));
                } else {
                    allCardsInMainDeck.add(user.getAllCards().get(i));
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
                    return;
                }
            }
        } else {
            for (Card card : allCardsInMainDeck) {
                if (card.getName().equals(cardName)) {
                    allCardsInMainDeck.remove(card);
                    return;
                }
            }
        }
    }

    public void removeDeck() {
        this.user.getDecks().remove(this);
    }

    public void activateDeck() {
        this.user.setActiveDeck(this);
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

    public boolean isThisCardInSideOrMainDeck (String cardName, boolean isInSideDeck){
        if(isInSideDeck){
            for (Card card : allCardsInSideDeck){
                if(card.getName().equals(cardName)){
                    return true;
                }
            }
        } else {
            for (Card card : allCardsInMainDeck){
                if(card.getName().equals(cardName)){
                    return true;
                }
            }
        }
        return false;
    }
}
