package enums;

public enum MonsterCardPosition {
    DEFENSIVE_HIDDEN("\tDH"),
    DEFENSIVE_OCCUPIED("\tDO"),
    OFFENSIVE_OCCUPIED("\tOO"),
    NOT_IN_PLAY_ZONE("\tE");

    private String positionInMap;

    MonsterCardPosition(String positionInMap){
        this.positionInMap = positionInMap;
    }

    public String getPositionInMap(){
        return positionInMap;
    }
}
