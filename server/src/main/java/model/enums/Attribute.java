package model.enums;

public enum Attribute {
    DARK,
    LIGHT,
    EARTH,
    WATER,
    FIRE,
    WIND;

    public String name;

    static {
        DARK.name = "Dark";
        LIGHT.name = "Light";
        EARTH.name = "Earth";
        WATER.name = "Water";
        FIRE.name = "Fire";
        WIND.name = "Wind";
    }
}
