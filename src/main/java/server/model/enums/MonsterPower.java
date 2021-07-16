package server.model.enums;

import server.model.MonstersDescriptions;

public enum MonsterPower {
    NONE,
    COMMAND_KNIGHT,
    YOMI_SHIP,
    SUIJIN,
    MAN_EATER_BUG,
    GATE_GUARDIAN,
    SCANNER,
    CRAB_TURTLE,
    SKULL_GUARDIAN,
    MARSHMALLON,
    BEAST_KING_BARBAROS,
    TEXCHANGER,
    THE_CALCULATOR,
    MIRAGE_DRAGON,
    HERALD_OF_CREATION,
    EXPLODER_DRAGON,
    TERRATIGER_THE_EMPOWERED_WARRIOR,
    THE_TRICKY,
    RITUAL;

    public String description;

    static {

        COMMAND_KNIGHT.description = MonstersDescriptions.commandKnight;
        YOMI_SHIP.description = MonstersDescriptions.yomiShip;
        SUIJIN.description = MonstersDescriptions.suijin;
        MAN_EATER_BUG.description = MonstersDescriptions.manEaterBug;
        GATE_GUARDIAN.description = MonstersDescriptions.gateGuardian;
        SCANNER.description = MonstersDescriptions.scanner;
        CRAB_TURTLE.description = MonstersDescriptions.crabTurtle;
        SKULL_GUARDIAN.description = MonstersDescriptions.skullGuardian;
        MARSHMALLON.description = MonstersDescriptions.marshmallon;
        BEAST_KING_BARBAROS.description = MonstersDescriptions.beastKing;
        TEXCHANGER.description = MonstersDescriptions.texChanger;
        THE_CALCULATOR.description = MonstersDescriptions.theCalculator;
        MIRAGE_DRAGON.description = MonstersDescriptions.mirageDragon;
        HERALD_OF_CREATION.description = MonstersDescriptions.heraldOfTheCreation;
        EXPLODER_DRAGON.description = MonstersDescriptions.exploderDragon;
        TERRATIGER_THE_EMPOWERED_WARRIOR.description = MonstersDescriptions.terraTigerTheEmpoweredWarrior;
        THE_TRICKY.description = MonstersDescriptions.theTricky;

    }


}
