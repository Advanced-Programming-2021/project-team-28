package model;

import java.util.*;

public class Deck {
    //attributes
    private User user;
    private String deckName;
    private boolean validity;
    private int numberOfMainDeckCards;
    private int numberOfSideDeckCards;
    private ArrayList<Card> allCardsInMainDeck;
    private ArrayList<Card> allCardsInSideDeck;

    public Deck(User userWhoCreateTheDeck, String deckName) {
        this.user = userWhoCreateTheDeck;
        this.deckName = deckName;
        this.validity = false;
        this.user.addToDecks(this);
    }

//    public boolean doesUserHaveThisDeck(User user, String deckName) {
//
//    }

//    public void createDeck(User user, String deckName) {
//
//    }

//    public String decksArrayListToString(ArrayList<Deck> decks) {
//
//    }
//    public void sortDeck() {
//
//    }


    public void addDeckToUserDecks() {

        this.user.addToDecks(this);

    }


    public Deck getDeckByDeckName(String deckName) {
        for (Deck deck : this.user.getDecks()) {
            if (deck.deckName.equals(deckName)) return deck;
        }

        return null;
    }



    public void removeDeck() {
        this.user.getDecks().remove(this);
    }

    public void activateDeck() {
        this.user.setActiveDeck(this);
    }

    public void addCardToDeck(Card card, String cardName, boolean isForSideDeck) {
        if (isForSideDeck) this.allCardsInSideDeck.add(card);
    }

    public void removeCardFromDeck(String cardName, boolean isFromSideDeck) {
        if (isFromSideDeck) {
            for (Card card : this.allCardsInSideDeck) {
                if (card.getName().equals(cardName)) this.allCardsInSideDeck.remove(card);
            }
        } else {
            for (Card card: this.allCardsInMainDeck) {
                if (card.getName().equals(cardName)) this.allCardsInMainDeck.remove(card);
            }
        }
    }

//    public String showDeck(String deckName, boolean isSideDeck) {
//        String deckInfo = "Deck: " + this.deckName +"\n" +
//                "Side/Main deck:\n" +
//                "Monsters:\n" +
//                "<card name>: <card description>\n" +
//                "Spell and Traps:\n" +
//                "<card name>: <card description>";
//
//        return deckInfo;
//    }

    public boolean isMainDeckFull() {
        if (this.numberOfMainDeckCards == 60) return true;
        else return false;
    }

    public boolean isSideDeckFull() {
        if (this.numberOfSideDeckCards == 15) return true;
        else return false;
    }

    public boolean doesDeckHaveThreeCardsFromThisTypeOfCard(String cardName) {
        int numberOfThisCard = 0;

        for (Card card : this.allCardsInMainDeck) {
            if (card.getName().equals(cardName)) ++numberOfThisCard;
        }

        for (Card card : this.allCardsInSideDeck) {
            if (card.getName().equals(cardName)) ++numberOfThisCard;
        }

        if (numberOfThisCard == 3) return true;
        else return false;
    }

    public String toString() {
        String deckInfo;
        if (validity) {

            deckInfo = this.deckName + ": main deck " + this.numberOfMainDeckCards + ", side deck\n" +
                    this.numberOfSideDeckCards + ", " + "valid";
        } else {
            deckInfo = this.deckName + ": main deck " + this.numberOfMainDeckCards + ", side deck\n" +
                    this.numberOfSideDeckCards + ", " + "invalid";
        }
        return deckInfo;
    }

    public String getDeckName() {
        return this.deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getNumOfMainCards() {
        return numberOfMainDeckCards;
    }

    public void increaseNumOfMainCards(int numOfAddedCards) {
        this.numberOfMainDeckCards += numOfAddedCards;
    }

    public void decreaseNumOfMainCards(int numOfRemovedCards) {
        this.numberOfMainDeckCards -= numOfRemovedCards;
    }

    public int getNumOfSideCards() {
        return this.numberOfSideDeckCards;
    }

    public void increaseNumOfSideCards(int numOfAddedCards) {
        this.numberOfSideDeckCards += numOfAddedCards;
    }

    public void decreaseNumOfSideCards(int numOfRemovedCards) {
        this.numberOfSideDeckCards -= numOfRemovedCards;
    }

    public boolean isDeckValid() {
        return this.validity;
    }
}
