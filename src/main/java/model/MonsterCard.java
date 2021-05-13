package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import enums.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class MonsterCard extends Card {
    @Expose
    public static  ArrayList<MonsterCard> allMonsterCards = new ArrayList<>();
    @Expose
    private int attackPoint;
    @Expose
    private int defencePoint;
    @Expose
    private boolean isSummoned;
    @Expose
    private boolean isFlipped;
    @Expose
    private int level;
    @Expose
    private Attribute attribute;
    @Expose
    private boolean hasBattledInBattlePhase;
    @Expose
    private MonsterPower specialPower;
    @Expose
    private MonsterCardPosition position;
    @Expose
    private boolean isPositionChangedInThisTurn;
    @Expose
    private boolean isSummonedInThisTurn;
    private MonsterType type;

    public boolean isSummonedInThisTurn() {
        return isSummonedInThisTurn;
    }

    public void setSummonedInThisTurn(boolean summonedInThisTurn) {
        isSummonedInThisTurn = summonedInThisTurn;
    }

    public boolean isPositionChangedInThisTurn() {
        return isPositionChangedInThisTurn;
    }

    public void setPositionChangedInThisTurn(boolean positionChangedInThisTurn) {
        isPositionChangedInThisTurn = positionChangedInThisTurn;
    }

    public MonsterCard(MonsterType type , String ownerUsername ,String name, String number, String description , int attackPoint , int defencePoint , MonsterPower specialPower , int level , Attribute attribute) {
        super(ownerUsername ,name, number, description);
        setAttackPoint(attackPoint);
        setDefencePoint(defencePoint);
        setSpecialPower(specialPower);
        setLevel(level);
        setAttribute(attribute);
        setPosition(MonsterCardPosition.NOT_IN_PLAY_ZONE);
        setType(type);
        MonsterCard.allMonsterCards.add(this);
    }
    public MonsterCard(){
        MonsterCard.allMonsterCards.add(this);
    }

    @Override
    public String toString() {
        return "Name : " + this.name + "\n"
                + "Level : " + this.level + "\n"
                + "ATK : " + this.attackPoint + "\n"
                + "DEF : " + this.defencePoint + "\n"
                + "Description : " + this.description;
    }

    public MonsterPower getSpecialPower() {
        return specialPower;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void changeAttackPoint(int attackPoint) {
        this.attackPoint += attackPoint;
    }

    public int getDefencePoint() {
        return defencePoint;
    }

    public void changeDefencePoint(int defencePoint) {
        this.defencePoint += defencePoint;
    }

    public boolean isSummoned() {
        return isSummoned;
    }

    public void setSummoned(boolean summoned) {
        isSummoned = summoned;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public boolean hasBattledInBattlePhase() {
        return hasBattledInBattlePhase;
    }

    public void setHasBattledInBattlePhase(boolean hasBattledInBattlePhase) {
        this.hasBattledInBattlePhase = hasBattledInBattlePhase;
    }


    public MonsterCardPosition getPosition() {
        return position;
    }

    public void setPosition(MonsterCardPosition position) {
        this.position = position;
    }


    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public void setSpecialPower(MonsterPower specialPower) {
        this.specialPower = specialPower;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public void setDefencePoint(int defencePoint) {
        this.defencePoint = defencePoint;
    }

    public MonsterType getType() {
        return type;
    }

    public void setType(MonsterType type) {
        this.type = type;
    }

    @Override
    public Object clone() {
        MonsterCard cloneMonsterCard = new MonsterCard();
        cloneMonsterCard.number = this.number;
        cloneMonsterCard.description = this.description;
        cloneMonsterCard.name = this.name;
        cloneMonsterCard.attackPoint = this.attackPoint;
        cloneMonsterCard.defencePoint = this.defencePoint;
        cloneMonsterCard.attribute = this.attribute;
        cloneMonsterCard.hasBattledInBattlePhase = this.hasBattledInBattlePhase;
        cloneMonsterCard.isFlipped = this.isFlipped;
        cloneMonsterCard.isSummoned = this.isSummoned;
        cloneMonsterCard.level = this.level;
        cloneMonsterCard.specialPower = this.specialPower;
        cloneMonsterCard.position = this.position;
        return cloneMonsterCard;
    }

    public static void serialize(){
        try (Writer writer = new FileWriter("src/MonsterCardsOutput.json")) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            gson.toJson(MonsterCard.allMonsterCards, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize(){
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src/MonsterCardsOutput.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MonsterCard[] monsterCards = gson.fromJson(reader,MonsterCard[].class);


        for (MonsterCard monsterCard: monsterCards){
            MonsterCard.allMonsterCards.add(monsterCard);
            Card.allCards.add(monsterCard);
//            User.getUserByUsername(monsterCard.getOwnerUsername()).addToCards(monsterCard);
        }

    }
}
