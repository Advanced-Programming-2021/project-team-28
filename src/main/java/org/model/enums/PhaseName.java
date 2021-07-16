package org.model.enums;

public enum PhaseName {
    DRAW_PHASE("Draw Phase"),
    STANDBY_PHASE("Standby Phase"),
    MAIN_PHASE_1("MainServer Phase 1"),
    BATTLE_PHASE("Battle Phase"),
    MAIN_PHASE_2("MainServer Phase 2"),
    END_PHASE("End Phase");

    private String phaseName;

    PhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getPhaseName() {
        return phaseName;
    }
}
