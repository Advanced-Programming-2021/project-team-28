package model;

import java.util.ArrayList;
import java.util.Collections;


public class User {
    private String username;
    private String password;
    private String nickname;


    private int score;
    private int balance;


    private ArrayList <Deck> decks = new ArrayList<>();
    private Deck activeDeck;
    private ArrayList <Card> allCards = new ArrayList<>();

    private static ArrayList <User> users = new ArrayList<>();

    public User (String username , String password , String nickname){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        users.add(this);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setActiveDeck(Deck activeDeck) {
        this.activeDeck = activeDeck;
    }

    public void changeScore(Integer score) {
        this.score += score;
    }

    public void changeBalance(Integer balance) {
        this.balance += balance;
    }

    public void addToDecks (Deck deck){
        decks.add(deck);
    }

    public void addToCards (Card card){
        allCards.add(card);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getBalance() {
        return balance;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public static User getUserByUsername (String username) {
        for (int i = 0; i < users.size(); i++) {
            if ( users.get(i).getUsername().equals(username)) return users.get(i);
        }
        return null;
    }

    public static User getUserByNickName (String nickname) {
        for (int i = 0; i < users.size(); i++) {
            if ( users.get(i).getNickname().equals(nickname)) return users.get(i);
        }
        return null;
    }

    public static boolean isPasswordCorrect ( String username , String password){
        return getUserByUsername(username).getPassword().equals( password );
    }

    public static boolean isNicknameAvailable(String nickname){
       if(getUserByNickName(nickname) == null ) return true;
       else
           return false;
    }

    public static boolean isUsernameAvailable(String username) {
        if (getUserByUsername(username) == null) return true;
        else
            return false;
    }

    public Deck getDeckByDeckName (String deckName){
        for (Deck deck : decks){
            if(deck.getDeckName().equals(deckName)){
                return deck;
            }
        }
        return null;
    }

    public boolean doesUserHaveThisDeck (String deckName){
        for (Deck deck : decks){
            if(deck.getDeckName().equals(deckName)){
                return true;
            }
        }
        return false;
    }

    public String decksArrayListToString (){
        StringBuilder decksArrayListToStringBuilder = new StringBuilder();
        decksArrayListToStringBuilder.append("Decks:\nActive Deck:\n");
        if(hasActiveDeck()){
            decksArrayListToStringBuilder.append(activeDeck.toString());
        }
        sortDecksArrayList();
        decksArrayListToStringBuilder.append("Other decks:\n");
        for (Deck deck : decks) {
            if (!hasActiveDeck() || !deck.getDeckName().equals(activeDeck.getDeckName())) {
                decksArrayListToStringBuilder.append(deck.toString());
            }
        }
        return decksArrayListToStringBuilder.toString();
    }

    private void sortDecksArrayList (){
        for (int i=0; i<decks.size(); i++){
            for (int j=0; j<decks.size() - 1; j++){
                if(decks.get(j).getDeckName().compareTo(decks.get(j+1).getDeckName()) > 0){
                    Collections.swap(decks, j, j+1);
                }
            }
        }
    }

    private boolean hasActiveDeck(){
        return !(activeDeck == null);
    }

    public int numOfCardsWithThisName (String cardName){
        int numOfCardsWithThisName = 0;
        for (Card card : allCards){
            if(card.getName().equals(cardName)){
                numOfCardsWithThisName++;
            }
        }
        return numOfCardsWithThisName;
    }

    public boolean isAmountOfThisCardEnough (Deck deck, String cardName){
        int numOfThisTypeOfCardUserHave = 0;
        int numOfThisTypeOfCardInDeck = 0;
        for (Card card : allCards){
            if(card.getName().equals(cardName)){
                numOfThisTypeOfCardUserHave++;
            }
        }
        for (Card card : deck.getAllCardsInMainDeck()){
            if(card.getName().equals(cardName)){
                numOfThisTypeOfCardInDeck++;
            }
        }
        for (Card card : deck.getAllCardsInSideDeck()){
            if(card.getName().equals(cardName)){
                numOfThisTypeOfCardInDeck++;
            }
        }
        return (numOfThisTypeOfCardUserHave > numOfThisTypeOfCardInDeck);
    }
}
