package model;

import enums.Turn;

public class MainPhase extends Phase {
    private int whatMainPhase = 1;
    public MainPhase(Player firstPlayer, Player secondPlayer,Round round) {
        super(firstPlayer, secondPlayer,round);
    }

    public int getWhatMainPhase() {
        return whatMainPhase;
    }

    public void setWhatMainPhase(int whatMainPhase) {
        this.whatMainPhase = whatMainPhase;
    }
}
