package org.model;

public class MainPhase extends Phase {
    private int whatMainPhase = 1;
    public MainPhase(Round round) {
        super(round);
    }

    public int getWhatMainPhase() {
        return whatMainPhase;
    }

    public void setWhatMainPhase(int whatMainPhase) {
        this.whatMainPhase = whatMainPhase;
    }
}
