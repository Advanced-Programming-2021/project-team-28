package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private User user;
    private int lifePoint;
    private int numberOfRoundsWon;
    private ArrayList<Card> mainDuelDeck = new ArrayList<>();
    private ArrayList<Card> sideDuelDeck = new ArrayList<>();
    private HashMap<Integer, MonsterCard> monsterCardsInZone = new HashMap<>();
    private HashMap<Integer, Card> spellOrTrapCardsInZone = new HashMap<>();
    private ArrayList<Card> cardsInHand = new ArrayList<>();
    private ArrayList<Card> cardsInGraveyard = new ArrayList<>();
    private Card fieldZoneCard;

    public Player(User user) throws CloneNotSupportedException {
        setUser(user);
        setMainAndSideDuelDeckAndCardsInHand();
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

    private void setMainAndSideDuelDeckAndCardsInHand() throws CloneNotSupportedException {
        for (Card card : user.getActiveDeck().getAllCardsInMainDeck()){
            if(card instanceof MonsterCard){
                mainDuelDeck.add((MonsterCard) card.clone());
            } else if(card instanceof TrapCard){
                mainDuelDeck.add((TrapCard) card.clone());
            } else if(card instanceof SpellCard){
                mainDuelDeck.add((SpellCard) card.clone());
            }
        }

        for (Card card : user.getActiveDeck().getAllCardsInSideDeck()){
            if(card instanceof MonsterCard){
                sideDuelDeck.add((MonsterCard) card.clone());
            } else if(card instanceof TrapCard){
                sideDuelDeck.add((TrapCard) card.clone());
            } else if(card instanceof SpellCard){
                sideDuelDeck.add((SpellCard) card.clone());
            }
        }

        for(Card card : mainDuelDeck){
            if(card instanceof MonsterCard){
                cardsInHand.add((MonsterCard) card.clone());
            } else if(card instanceof TrapCard){
                cardsInHand.add((TrapCard) card.clone());
            } else if(card instanceof SpellCard){
                cardsInHand.add((SpellCard) card.clone());
            }
        }
    }

    public void addCardToCardsInZone (Card card, int location){
        if(card instanceof TrapCard || card instanceof SpellCard){
            spellOrTrapCardsInZone.put(location, card);
        } else if(card instanceof MonsterCard){
            monsterCardsInZone.put(location,(MonsterCard) card);
        }
    }

    public void removeCardFromCardsInZone (Card card, int location){
        if(card instanceof TrapCard || card instanceof SpellCard){
            spellOrTrapCardsInZone.remove(location, card);
        } else if(card instanceof MonsterCard){
            monsterCardsInZone.remove(location, card);
        }
    }

    public void addCardToHand (Card card){
        cardsInHand.add(card);
    }

    public void removeCardFromHand (Card card){
        cardsInHand.remove(card);
    }

    public void addCardToGraveyard (Card card){
        cardsInGraveyard.add(card);
    }

    public void removeCardFromGraveyard (Card card){
        cardsInGraveyard.remove(card);
    }

    public Card getFieldZoneCard() {
        return fieldZoneCard;
    }

    public void setFieldZoneCard(Card fieldZoneCard) {
        this.fieldZoneCard = fieldZoneCard;
    }
}

