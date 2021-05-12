package model;

import java.util.*;

public class Player {
    private User user;
    private int lifePoint = 8000;
    private int numberOfRoundsWon = 0;
    private ArrayList<Card> mainDuelDeck = new ArrayList<>();
    private ArrayList<Card> sideDuelDeck = new ArrayList<>();
    private ArrayList<Card> remainingPlayerCardsInGame = new ArrayList<>();
    private HashMap<Integer, MonsterCard> monsterCardsInZone = new HashMap<>();
    private HashMap<Integer, Card> spellOrTrapCardsInZone = new HashMap<>();
    private ArrayList<Card> cardsInHand = new ArrayList<>();
    private ArrayList<Card> cardsInGraveyard = new ArrayList<>();
    private Card fieldZoneCard;
    private Card selectedCard;
    private boolean isSelectedCardVisible;
    private boolean isAbleToActivateTrapCard = true;

    public boolean isAbleToActivateTrapCard() {
        return isAbleToActivateTrapCard;
    }

    public void setAbleToActivateTrapCard(boolean ableToActivateTrapCard) {
        isAbleToActivateTrapCard = ableToActivateTrapCard;
    }

    private Random random = new Random();

    public Player(User user) throws CloneNotSupportedException {
        setUser(user);
        setMainAndSideDuelDeckAndRemainingCards();
        shuffleRemainingCards();
        setCardsInHand();
        setSelectedCardVisible(false);
    }

    public User getUser() {
        return user;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public boolean isSelectedCardVisible() {
        return isSelectedCardVisible;
    }

    public HashMap<Integer, MonsterCard>
    getMonsterCardsInZone() {
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

    public ArrayList<Card> getRemainingPlayerCardsInGame() {
        return remainingPlayerCardsInGame;
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

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    private void setUser(User user){
        this.user = user;
    }

    public void setSelectedCardVisible(boolean selectedCardVisible) {
        isSelectedCardVisible = selectedCardVisible;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public boolean hasSelectedCard(){
        return selectedCard != null;
    }

    private void setMainAndSideDuelDeckAndRemainingCards() throws CloneNotSupportedException {
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
                remainingPlayerCardsInGame.add((MonsterCard) card.clone());
            } else if(card instanceof TrapCard){
                remainingPlayerCardsInGame.add((TrapCard) card.clone());
            } else if(card instanceof SpellCard){
                remainingPlayerCardsInGame.add((SpellCard) card.clone());
            }
        }
    }

    public void addCardToCardsInZone (Card card){
        int location;
        if(card instanceof TrapCard || card instanceof SpellCard){
            location = getProperLocationForCard(false);
            spellOrTrapCardsInZone.put(location, card);
        } else if(card instanceof MonsterCard){
            location = getProperLocationForCard(true);
            monsterCardsInZone.put(location,(MonsterCard) card);
        }
    }

    private int getProperLocationForCard(boolean isCardMonster) {
        if(isCardMonster){
            for(int i=1; i<=5; i++){
                if(!monsterCardsInZone.containsKey(i)){
                    return i;
                }
            }
        } else {
            for (int i=1; i<=5; i++){
                if(!spellOrTrapCardsInZone.containsKey(i)){
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean isMonsterCardZoneFull (){
        return monsterCardsInZone.size() >= 5;
    }

    public boolean isSpellCardZoneFull (){
        return spellOrTrapCardsInZone.size() >= 5;
    }

    public void removeCardFromCardsInZone (Card card, int location){
        if(card instanceof TrapCard || card instanceof SpellCard){
            spellOrTrapCardsInZone.remove(location, card);
        } else if(card instanceof MonsterCard){
            monsterCardsInZone.remove(location, card);
        }
    }

    public void setCardsInHand(){
        for (int i=0; i<5; i++){
            cardsInHand.add(remainingPlayerCardsInGame.get(i));
        }
        remainingPlayerCardsInGame.subList(0, 5).clear();
    }

    public void shuffleRemainingCards () {
        for (int i=0; i<200; i++){
            int firstRandomInt = Math.abs(random.nextInt()%(remainingPlayerCardsInGame.size()));
            int secondRandomInt = Math.abs(random.nextInt()%(remainingPlayerCardsInGame.size()-1));
            Collections.swap(remainingPlayerCardsInGame, firstRandomInt, secondRandomInt);
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

    public boolean hasFieldSpellCardInZone (){
        return !(fieldZoneCard == null);
    }

    public String graveyardToString (){
        StringBuilder graveyardToStringBuilder = new StringBuilder();
        for(int i=1; i<=cardsInGraveyard.size(); i++){
            graveyardToStringBuilder.append(i);
            graveyardToStringBuilder.append(". ");
            graveyardToStringBuilder.append(cardsInGraveyard.get(i-1).getName());
            graveyardToStringBuilder.append(" : ");
            graveyardToStringBuilder.append(cardsInGraveyard.get(i-1).getDescription());
            graveyardToStringBuilder.append("\n");
        }
        return graveyardToStringBuilder.toString();
    }

    public boolean isSelectedCardFromHand(){
        if(!hasSelectedCard()){
            return false;
        }
        for(Card card : cardsInHand){
            if(card.equals(selectedCard)){
                return true;
            }
        }
        return false;
    }

    public boolean isSelectedCardFromMonsterCardZone(){
        if(!hasSelectedCard()){
            return false;
        }
        for (Map.Entry<Integer, MonsterCard> locationAndMonsterCard : monsterCardsInZone.entrySet()){
            if(locationAndMonsterCard.getValue().equals(selectedCard)){
                return true;
            }
        }
        return false;
    }

    public boolean doesHaveMonsterCardInThisLocation(int location){
        for (Map.Entry<Integer, MonsterCard> locationAndMonsterCard : monsterCardsInZone.entrySet()){
            if(locationAndMonsterCard.getKey() == location){
                return true;
            }
        }
        return false;
    }

    public boolean doesHaveSpellOrTrapCardInThisPosition(int location){
        for (Map.Entry<Integer, Card> locationAndCard : spellOrTrapCardsInZone.entrySet()){
            if(locationAndCard.getKey() == location){
                return true;
            }
        }
        return false;
    }

    public int getLocationOfThisMonsterCardInZone (MonsterCard monsterCard){
        for (Map.Entry<Integer, MonsterCard> locationAndCard : monsterCardsInZone.entrySet()){
            if(locationAndCard.getValue().equals(monsterCard)){
                return locationAndCard.getKey();
            }
        }
        return -1;
    }

}

