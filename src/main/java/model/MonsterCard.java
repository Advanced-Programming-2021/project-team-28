package model;

import Enums.Attribute;
import Enums.MonsterPower;

public class MonsterCard extends Card {
    private int attackPoint;
    private int defencePoint;
    private boolean isSummoned;
    private boolean isOnSetPosition;
    private boolean isFlipped;
    private int level;
    private Attribute attribute;
    private boolean hasBattledInBattlePhase;
    private MonsterPower specialPower;

    public MonsterCard(String name, String number, String description , int attackPoint , int defencePoint , MonsterPower specialPower , int level , Attribute attribute) {
        super(name, number, description);
        setAttackPoint(attackPoint);
        setDefencePoint(defencePoint);
        setSpecialPower(specialPower);
        setLevel(level);
        setAttribute(attribute);
    }
    public MonsterCard(){

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

    public boolean isOnSetPosition() {
        return isOnSetPosition;
    }

    public void setOnSetPosition(boolean onSetPosition) {
        isOnSetPosition = onSetPosition;
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
        cloneMonsterCard.isOnSetPosition = this.isOnSetPosition;
        cloneMonsterCard.isSummoned = this.isSummoned;
        cloneMonsterCard.level = this.level;
        cloneMonsterCard.specialPower = this.specialPower;
        return cloneMonsterCard;
    }
}
