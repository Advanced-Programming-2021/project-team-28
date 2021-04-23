package model;

public class MonsterCard extends Card {
    private int attackPoint;
    private int defencePoint;
    private boolean isSummoned;
    private boolean isOnSetPosition;
    private boolean isFlipped;
    private int level;
    private Attribute attribute;
    private boolean hasBattledInBattlePhase;

    public MonsterCard(String name, String number, String description) {
        super(name, number, description);
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
}
