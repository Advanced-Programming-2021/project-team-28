package model;

import java.util.ArrayList;


public class User {
    private String username;
    private String password;
    private String nickname;


    private Integer score;
    private Integer balance;


    private ArrayList <Deck> decks;
    private Deck activeDeck;
    private ArrayList <Card> nonUsedCards;


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

    public void addToDecks( Deck deck){
        decks.add(deck);
    }

    public void addToCards (Card card){
        nonUsedCards.add(card);
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

    public ArrayList<Card> getNonUsedCards() {
        return nonUsedCards;
    }

    public static User getUserByUsername (String username) {
        for (int i = 0; i < users.size(); i++) {
            if ( users.get(i).getUsername().equals(username)) return users.get(i);
        }
        return null;
    }

    public static User getUserByNickName (String nickname) {
        for (int i = 0; i < users.size(); i++) {
            if ( users.get(i).getUsername().equals(nickname)) return users.get(i);
        }
        return null;
    }

    public static boolean isPasswordCorrect ( String username , String password){
        return getUserByUsername(username).getPassword().equals( password );
    }

    public static boolean checkNicknameValidity ( String nickname){
       if(getUserByNickName(nickname) == null ) return true;
       else
           return false;
    }

    public static boolean checkUsernameValidity ( String username) {
        if (getUserByUsername(username) == null) return true;
        else
            return false;
    }
    //useless comment
}
