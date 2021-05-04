package enums;

public enum TrapIcon {
    NORMAL("Normal"),
    COUNTER("Counter"),
    CONTINUOUS("Continuous");

    private String name;

    private TrapIcon(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
