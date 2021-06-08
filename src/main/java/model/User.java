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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class User {
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String nickname;
    @Expose
    private int score;
    @Expose
    private int balance = 100000;


    @Expose
    private ArrayList<String> decksName = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();

    @Expose
    private String activeDeckName;
    private Deck activeDeck;
    @Expose
    private ArrayList<String> allCardsName = new ArrayList<>();
    private ArrayList<Card> allCards = new ArrayList<>();

    @Expose
    private static ArrayList<User> users = new ArrayList<>();

    public User(String username, String password, String nickname) {
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
        if(activeDeck == null){
            this.activeDeck = null;
            this.activeDeckName = null;
        } else {
            this.activeDeck = activeDeck;
            this.activeDeckName = activeDeck.getDeckName();
        }
    }

    public void changeScore(int score) {
        this.score += score;
    }

    public void changeBalance(int balance) {
        this.balance += balance;
    }

    public void addToDecks(Deck deck) {
        decks.add(deck);
        decksName.add(deck.getDeckName());
    }

    public void addToCards(Card card) {
        allCards.add(card);
        allCardsName.add(card.getName());
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

    public int getScore() {
        return score;
    }

    public int getBalance() {
        return balance;
    }

    public static ArrayList<User> getUsers() {
        return users;
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

    public static User getUserByUsername(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) return users.get(i);
        }
        return null;
    }

    public static User getUserByNickName(String nickname) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getNickname().equals(nickname)) return users.get(i);
        }
        return null;
    }

    public static boolean isPasswordCorrect(String username, String password) {
        return getUserByUsername(username).getPassword().equals(password);
    }

    public static boolean isNicknameAvailable(String nickname) {
        if (getUserByNickName(nickname) == null) return true;
        else
            return false;
    }

    public static boolean isUsernameAvailable(String username) {
        if (getUserByUsername(username) == null) return true;
        else
            return false;
    }

    public Deck getDeckByDeckName(String deckName) {
        for (Deck deck : decks) {
            if (deck.getDeckName().equals(deckName)) {
                return deck;
            }
        }
        return null;
    }

    public boolean doesUserHaveThisDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck.getDeckName().equals(deckName)) {
                return true;
            }
        }
        return false;
    }

    public String decksArrayListToString() {
        StringBuilder decksArrayListToStringBuilder = new StringBuilder();
        decksArrayListToStringBuilder.append("Decks:\nActive Deck:\n");
        if (hasActiveDeck()) {
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

    private void sortDecksArrayList() {
        for (int i = 0; i < decks.size(); i++) {
            for (int j = 0; j < decks.size() - 1; j++) {
                if (Utilities.compareAlphabetical(decks.get(j).getDeckName(), decks.get(j + 1).getDeckName()) > 0) {
                    Collections.swap(decks, j, j + 1);
                }
            }
        }
    }

    public boolean hasActiveDeck() {
        return !(activeDeck == null);
    }

    public int numOfCardsWithThisName(String cardName) {
        int numOfCardsWithThisName = 0;
        for (Card card : allCards) {
            if (card.getName().equals(cardName)) {
                numOfCardsWithThisName++;
            }
        }
        return numOfCardsWithThisName;
    }

    public boolean isAmountOfThisCardEnough(Deck deck, String cardName) {
        int numOfThisTypeOfCardUserHave = numOfCardsWithThisName(cardName);
        int numOfThisTypeOfCardInDeck = 0;
        for (Card card : deck.getAllCardsInMainDeck()) {
            if (card.getName().equals(cardName)) {
                numOfThisTypeOfCardInDeck++;
            }
        }
        for (Card card : deck.getAllCardsInSideDeck()) {
            if (card.getName().equals(cardName)) {
                numOfThisTypeOfCardInDeck++;
            }
        }
        return (numOfThisTypeOfCardUserHave > numOfThisTypeOfCardInDeck);
    }

    public static void serialize() {
        try (Writer writer = new FileWriter("src/UserOutput.json")) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            gson.toJson(User.users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() throws Exception {
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src/UserOutput.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        User[] users = gson.fromJson(reader, User[].class);

        for (User user : users) {
            User.users.add(user);
            user.allCards = new ArrayList<>();
            for (String cardName: user.allCardsName){
                user.allCards.add(Card.getCardByName(Card.getAllCards(), cardName));
            }
        }
        Deck.deserialize();
        for (User user : users) {
            user.decks = new ArrayList<>();
            for (String deckName: user.decksName){
                user.decks.add(Deck.getDeckByOwnerAndName(user.username, deckName));
            }
            if(user.activeDeckName != null)
            user.setActiveDeck(Deck.getDeckByOwnerAndName(user.getUsername(), user.activeDeckName));
        }
    }
}
