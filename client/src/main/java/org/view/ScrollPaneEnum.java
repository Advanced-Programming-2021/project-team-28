package org.view;

public enum ScrollPaneEnum {
    MAIN_DECK("0"),
    SIDE_DECK("1"),
    AVAILABLE_CARDS("2");

    private final String NAME;

    ScrollPaneEnum(String name){
        this.NAME = name;
    }

    public String getName() {
        return NAME;
    }
}
