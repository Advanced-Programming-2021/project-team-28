package model;

import enums.MonsterCardPosition;
import enums.SpellOrTrapCardPosition;

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
    private boolean isSelectedCardVisible = false;
    private boolean isAbleToActivateTrapCard = true;
    private boolean isAbleToAddCardInDrawPhase = true;
    private boolean isSurrenderedOrLostByCheat = false;

    private Random random = new Random();

    public Player(User user) throws CloneNotSupportedException {
        setUser(user);
        setMainAndSideDuelDeckAndRemainingCards();
        shuffleRemainingCards();
        setCardsInHand();
    }

    public ArrayList<Card> getMainDuelDeck() {
        return mainDuelDeck;
    }

    public ArrayList<Card> getSideDuelDeck() {
        return sideDuelDeck;
    }

    public boolean isAbleToActivateTrapCard() {
        return isAbleToActivateTrapCard;
    }

    public void setAbleToActivateTrapCard(boolean ableToActivateTrapCard) {
        isAbleToActivateTrapCard = ableToActivateTrapCard;
    }

    public boolean isAbleToAddCardInDrawPhase() {
        return isAbleToAddCardInDrawPhase;
    }

    public void setAbleToAddCardInDrawPhase(boolean ableToAddCardInDrawPhase) {
        this.isAbleToAddCardInDrawPhase = ableToAddCardInDrawPhase;
    }

    public boolean isSurrenderedOrLostByCheat() {
        return isSurrenderedOrLostByCheat;
    }

    public void setSurrenderedOrLostByCheat(boolean surrenderedOrLostByCheat) {
        isSurrenderedOrLostByCheat = surrenderedOrLostByCheat;
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

    public void increaseNumberOfRoundsWon() {
        numberOfRoundsWon++;
    }

    public void increaseLifePoint(int lifePoint) {
        this.lifePoint += lifePoint;
    }

    public void decreaseLifePoint(int lifePoint) {
        this.lifePoint -= lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    private void setUser(User user) {
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

    public boolean hasSelectedCard() {
        return selectedCard != null;
    }

    private void setMainAndSideDuelDeckAndRemainingCards() throws CloneNotSupportedException {
        fillArraylistWithCardClones(user.getActiveDeck().getAllCardsInMainDeck(), mainDuelDeck);

        fillArraylistWithCardClones(user.getActiveDeck().getAllCardsInSideDeck(), sideDuelDeck);

        fillArraylistWithCardClones(mainDuelDeck, remainingPlayerCardsInGame);
    }

    public void fillArraylistWithCardClones(ArrayList<Card> mainArrayList, ArrayList<Card> cloneArrayList) throws CloneNotSupportedException {
        for (Card card : mainArrayList) {
            if (card instanceof MonsterCard) {
                cloneArrayList.add((MonsterCard) card.clone());
            } else if (card instanceof TrapCard) {
                cloneArrayList.add((TrapCard) card.clone());
            } else if (card instanceof SpellCard) {
                cloneArrayList.add((SpellCard) card.clone());
            }
        }
    }

    public void addCardToCardsInZone(Card card) {
        int location;
        if (card instanceof TrapCard || card instanceof SpellCard) {
            location = getProperLocationForCard(false);
            spellOrTrapCardsInZone.put(location, card);
        } else if (card instanceof MonsterCard) {
            location = getProperLocationForCard(true);
            monsterCardsInZone.put(location, (MonsterCard) card);
        }
    }

    private int getProperLocationForCard(boolean isCardMonster) {
        if (isCardMonster) {
            for (int i = 1; i <= 5; i++) {
                if (!monsterCardsInZone.containsKey(i)) {
                    return i;
                }
            }
        } else {
            for (int i = 1; i <= 5; i++) {
                if (!spellOrTrapCardsInZone.containsKey(i)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean isMonsterCardZoneFull() {
        return monsterCardsInZone.size() >= 5;
    }

    public boolean isMonsterCardZoneEmpty() {
        return monsterCardsInZone.isEmpty();
    }

    public boolean isSpellCardZoneFull() {
        return spellOrTrapCardsInZone.size() >= 5;
    }

    public void removeCardFromCardsInZone(Card card, int location) {
        if (card instanceof TrapCard || card instanceof SpellCard) {
            spellOrTrapCardsInZone.remove(location, card);
        } else if (card instanceof MonsterCard) {
            monsterCardsInZone.remove(location, card);
        }
    }

    public void setCardsInHand() {
        for (int i = 0; i < 5; i++) {
            cardsInHand.add(remainingPlayerCardsInGame.get(i));
        }
        remainingPlayerCardsInGame.subList(0, 5).clear();
    }

    public void shuffleRemainingCards() {
        Collections.shuffle(remainingPlayerCardsInGame);
    }

    public void addCardToHand(Card card) {
        cardsInHand.add(card);
    }

    public void removeCardFromHand(Card card) {
        cardsInHand.remove(card);
    }

    public void addCardToGraveyard(Card card) {
        try {
            if(card instanceof MonsterCard){
                resetMonsterCardDataAfterGoingToGraveyard((MonsterCard) card);
            } else if (card instanceof SpellCard){
                ((SpellCard) card).setPosition(SpellOrTrapCardPosition.NOT_IN_PLAY_ZONE);
            } else if (card instanceof TrapCard){
                ((TrapCard) card).setPosition(SpellOrTrapCardPosition.NOT_IN_PLAY_ZONE);
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
        card.setGoingToGraveyard(false);
        cardsInGraveyard.add(card);
    }

    private void resetMonsterCardDataAfterGoingToGraveyard(MonsterCard card) throws CloneNotSupportedException {
        card.setPosition(MonsterCardPosition.NOT_IN_PLAY_ZONE);
        card.setAttackPoint(((MonsterCard) Card.getCardByName(Card.getAllCards(), card.getName())).getAttackPoint());
        card.setDefencePoint(((MonsterCard) Card.getCardByName(Card.getAllCards(), card.getName())).getDefencePoint());
        card.setEffectedByFieldSpell(false);
        equipReset(card);
    }

    private void equipReset(MonsterCard card) {
        if(card.getEquipCard() == null){
            return;
        }
        addCardToGraveyard(card.getEquipCard());
        removeCardFromHand(card.getEquipCard());
        card.setEquipCard(null);
    }


    public void removeCardFromGraveyard(Card card) {
        cardsInGraveyard.remove(card);
    }

    public Card getFieldZoneCard() {
        return fieldZoneCard;
    }

    public void setFieldZoneCard(Card fieldZoneCard) {
        this.fieldZoneCard = fieldZoneCard;
    }

    public boolean hasFieldSpellCardInZone() {
        return !(fieldZoneCard == null);
    }

    public String graveyardToString() {
        if(cardsInGraveyard.size() == 0){
            return "graveyard empty";
        }
        StringBuilder graveyardToStringBuilder = new StringBuilder();
        for (int i = 1; i <= cardsInGraveyard.size(); i++) {
            graveyardToStringBuilder.append(i);
            graveyardToStringBuilder.append(". ");
            graveyardToStringBuilder.append(cardsInGraveyard.get(i - 1).getName());
            graveyardToStringBuilder.append(" : ");
            graveyardToStringBuilder.append(cardsInGraveyard.get(i - 1).getDescription());
            graveyardToStringBuilder.append("\n");
        }
        return graveyardToStringBuilder.toString();
    }

    public boolean isSelectedCardFromHand() {
        if (!hasSelectedCard()) {
            return false;
        }
        for (Card card : cardsInHand) {
            if (card.equals(selectedCard)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSelectedCardFromMonsterCardZone() {
        if (!hasSelectedCard()) {
            return false;
        }
        for (Map.Entry<Integer, MonsterCard> locationAndMonsterCard : monsterCardsInZone.entrySet()) {
            if (locationAndMonsterCard.getValue().equals(selectedCard)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSelectedCardFromSpellAndTrapZone() {
        if (!hasSelectedCard()) {
            return false;
        }
        for (Map.Entry<Integer, Card> locationCard : spellOrTrapCardsInZone.entrySet()) {
            if (locationCard.getValue().equals(selectedCard)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSelectedCardFromFieldZone(){
        if(!hasSelectedCard()){
            return false;
        } else if (!hasFieldSpellCardInZone()){
            return false;
        } else return getSelectedCard().equals(getFieldZoneCard());
    }

    public boolean doesHaveMonsterCardInThisLocation(int location) {
        for (Map.Entry<Integer, MonsterCard> locationAndMonsterCard : monsterCardsInZone.entrySet()) {
            if (locationAndMonsterCard.getKey() == location) {
                return true;
            }
        }
        return false;
    }

    public boolean doesHaveSpellOrTrapCardInThisPosition(int location) {
        for (Map.Entry<Integer, Card> locationAndCard : spellOrTrapCardsInZone.entrySet()) {
            if (locationAndCard.getKey() == location) {
                return true;
            }
        }
        return false;
    }

    public boolean doesHaveThisCardNameInThisPlace(String cardName, ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }

    public Card getACardWithThisNameInThisPlace(String cardName, ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    public int getLocationOfThisMonsterCardInZone(MonsterCard monsterCard) {
        for (Map.Entry<Integer, MonsterCard> locationAndCard : monsterCardsInZone.entrySet()) {
            if (locationAndCard.getValue().equals(monsterCard)) {
                return locationAndCard.getKey();
            }
        }
        return -1;
    }

    public int getLocationOfThisSpellOrTrapCardInZone(Card card) {
        for (Map.Entry<Integer, Card> locationAndCard : spellOrTrapCardsInZone.entrySet()) {
            if (locationAndCard.getValue().equals(card)) {
                return locationAndCard.getKey();
            }
        }
        return -1;
    }

    public void addAllCardsWithThisNameToGraveyard(String cardName) {
        addCardsWithThisNameToGraveyardFromArrayList(cardName, cardsInHand);
        addCardsWithThisNameToGraveyardFromArrayList(cardName, remainingPlayerCardsInGame);
        for (Map.Entry<Integer, MonsterCard> locationCard : monsterCardsInZone.entrySet()){
            if(locationCard.getValue().getName().equals(cardName)){
                addCardToGraveyard(locationCard.getValue());
            }
        }
        for (Map.Entry<Integer, Card> locationCard : spellOrTrapCardsInZone.entrySet()){
            if(locationCard.getValue().getName().equals(cardName)){
                addCardToGraveyard(locationCard.getValue());
            }
        }
        cardsInHand.removeIf(card -> card.getName().equals(cardName));
        remainingPlayerCardsInGame.removeIf(card -> card.getName().equals(cardName));
        Set<Map.Entry<Integer, MonsterCard>> entrySet = getMonsterCardsInZone().entrySet();
        entrySet.removeIf(entry -> entry.getValue().getName().equals(cardName));
        Set<Map.Entry<Integer, Card>> entrySet2 = getSpellOrTrapCardsInZone().entrySet();
        entrySet2.removeIf(entry2 -> entry2.getValue().getName().equals(cardName));
        if(hasFieldSpellCardInZone() && cardName.equals(fieldZoneCard.getName())) {
            addCardToGraveyard(fieldZoneCard);
            setFieldZoneCard(null);
        }
    }

    private void addCardsWithThisNameToGraveyardFromArrayList(String cardName, ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getName().equals(cardName)) {
                addCardToGraveyard(card);
            }
        }
    }

    public MonsterCard getMonsterCardByLocationFromZone(int location) {
        return monsterCardsInZone.get(location);
    }

    public void addAllCardsOfMonsterZoneToGraveyard(){
        for (Map.Entry<Integer, MonsterCard> mapElement : getMonsterCardsInZone().entrySet()){
            addCardToGraveyard(mapElement.getValue());
        }
    }

    public MonsterCard findEquipCardOwner(SpellCard card){
        for (Map.Entry<Integer, MonsterCard> mapElement : getMonsterCardsInZone().entrySet()){
            if(mapElement.getValue().getEquipCard() == card){
                return mapElement.getValue();
            }
        }
        return null;
    }
}

