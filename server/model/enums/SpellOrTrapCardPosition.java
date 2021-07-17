package server.model.enums;

public enum SpellOrTrapCardPosition {
    OCCUPIED("\tO"),
    HIDDEN("\tH"),
    NOT_IN_PLAY_ZONE("\tE");

    private String positionInMap;

    SpellOrTrapCardPosition(String positionInMap){
        this.positionInMap = positionInMap;
    }

    public String getPositionInMap(){
        return positionInMap;
    }
}
