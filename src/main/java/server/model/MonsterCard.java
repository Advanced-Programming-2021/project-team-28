package server.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import server.model.enums.Attribute;
import server.model.enums.MonsterCardPosition;
import server.model.enums.MonsterPower;
import server.model.enums.MonsterType;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MonsterCard extends Card {
    @Expose
    public static ArrayList<MonsterCard> allMonsterCards = new ArrayList<>();
    @Expose
    private int attackPoint;
    @Expose
    private int defencePoint;
    @Expose
    private int level;
    @Expose
    private Attribute attribute;
    @Expose
    private MonsterPower specialPower;
    @Expose
    private MonsterCardPosition position;
    @Expose
    private MonsterType type;

    private SpellCard EquipCard = null;
    private boolean isSummoned = false;
    private boolean isFlipped = false;
    private boolean hasBattledInBattlePhase = false;
    private boolean isPositionChangedInThisTurn = false;
    private boolean isSummonedInThisTurn = false;
    private boolean isEffectedByFieldSpell = false;
    private boolean isCardActionCanceledByAnEffect = false;
    private boolean isSpecialSummoned = false;
    private boolean isGoingToGraveyardWithItsOwnAttack = false;

    public boolean isGoingToGraveyardWithItsOwnAttack() {
        return isGoingToGraveyardWithItsOwnAttack;
    }

    public void setGoingToGraveyardWithItsOwnAttack(boolean goingToGraveyardWithItsOwnAttack) {
        isGoingToGraveyardWithItsOwnAttack = goingToGraveyardWithItsOwnAttack;
    }

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

    public MonsterCard(MonsterType type , String ownerUsername , String name, String number, String description , int attackPoint , int defencePoint , MonsterPower specialPower , int level , Attribute attribute) {
        super(ownerUsername ,name, number, description);
        setAttackPoint(attackPoint);
        setDefencePoint(defencePoint);
        setSpecialPower(specialPower);
        setLevel(level);
        setAttribute(attribute);
        setPosition(MonsterCardPosition.NOT_IN_PLAY_ZONE);
        setType(type);
        MonsterCard.allMonsterCards.add(this);
        classID = 2;
    }
    public MonsterCard(){
        classID = 2;
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

    public boolean isSpecialSummoned() {
        return isSpecialSummoned;
    }

    public void setSpecialSummoned(boolean specialSummoned) {
        isSpecialSummoned = specialSummoned;
    }

    public void setHasBattledInBattlePhase(boolean hasBattledInBattlePhase) {
        this.hasBattledInBattlePhase = hasBattledInBattlePhase;
    }

    public boolean isCardActionCanceledByAnEffect() {
        return isCardActionCanceledByAnEffect;
    }

    public void setCardActionCanceledByAnEffect(boolean cardActionCanceledByAnEffect) {
        isCardActionCanceledByAnEffect = cardActionCanceledByAnEffect;
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

    public boolean isEffectedByFieldSpell() {
        return isEffectedByFieldSpell;
    }

    public void setEffectedByFieldSpell(boolean effectedByFieldSpell) {
        isEffectedByFieldSpell = effectedByFieldSpell;
    }

    @Override
    public Object clone() {
        MonsterCard cloneMonsterCard = new MonsterCard();
        cloneMonsterCard.number = this.number;
        cloneMonsterCard.description = this.description;
        cloneMonsterCard.name = this.name;
        cloneMonsterCard.ownerUsername = this.ownerUsername;
        cloneMonsterCard.attackPoint = this.attackPoint;
        cloneMonsterCard.defencePoint = this.defencePoint;
        cloneMonsterCard.attribute = this.attribute;
        cloneMonsterCard.level = this.level;
        cloneMonsterCard.specialPower = this.specialPower;
        cloneMonsterCard.position = this.position;
        cloneMonsterCard.type = this.type;
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
        MonsterCard[] monsterCards = gson.fromJson(reader, MonsterCard[].class);


        for (MonsterCard monsterCard: monsterCards){
            MonsterCard.allMonsterCards.add(monsterCard);
            Card.getAllCards().add(monsterCard);
//            User.getUserByUsername(monsterCard.getOwnerUsername()).addToCards(monsterCard);
        }


    }

    public SpellCard getEquipCard() {
        return EquipCard;
    }

    public void setEquipCard(SpellCard equipCard) {
        EquipCard = equipCard;
    }
}
