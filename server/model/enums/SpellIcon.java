package server.model.enums;

public enum SpellIcon {
    NORMAL("Normal"),
    CONTINUOUS("Continuous"),
    QUICK_PLAY("Quick-play"),
    FIELD("Field"),
    EQUIP("Equip"),
    RITUAL("Ritual");

    private String name;

    SpellIcon (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
