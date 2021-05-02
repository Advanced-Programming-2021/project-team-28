package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private User user;
    private int lifePoint;
    private int numberOfRoundsWon;
    private HashMap<Integer, MonsterCard> monsterCardsInZone = new HashMap<>();
    private HashMap<Integer, Card> spellOrTrapCardsInZone = new HashMap<>();
    private ArrayList<Card> cardsInHand = new ArrayList<>();
    private ArrayList<Card> cardsInGraveyard = new ArrayList<>();
    private ArrayList<Card> cardsInDuelDeck = new ArrayList<>();

    public Player(User user){
        setUser(user);
    }

    public User getUser() {
        return user;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public HashMap<Integer, MonsterCard> getMonsterCardsInZone() {
        return monsterCardsInZone;
    }

    public HashMap<Integer, Card> getSpellOrTrapCardsInZone() {
        return spellOrTrapCardsInZone;
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public ArrayList<Card> getCardsInGraveyard() {
        return cardsInGraveyard;
    }

    public ArrayList<Card> getCardsInDuelDeck() {
        return cardsInDuelDeck;
    }

    public int getNumberOfRoundsWon() {
        return numberOfRoundsWon;
    }

    public void increaseNumberOfRoundsWon(){
        numberOfRoundsWon++;
    }

    public void increaseLifePoint(int lifePoint){
        this.lifePoint += lifePoint;
    }

    public void decreaseLifePoint (int lifePoint){
        this.lifePoint -= lifePoint;
    }

    private void setUser(User user){
        this.user = user;
    }
}

