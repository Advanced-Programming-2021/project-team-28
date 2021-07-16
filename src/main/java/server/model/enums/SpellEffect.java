package server.model.enums;

import server.model.SpellsDescription;

public enum SpellEffect {
    MONSTER_REBORN,
    TERRAFORMING,
    POT_OF_GREED,
    RAIGEKI,
    CHANGEOFHEART,
    SWORDS_OF_REVEALING_LIGHT,
    HARPIES_FEATHER_DUSTER,
    DARK_HOLE,
    SUPPLY_SQUAD,
    SPELL_ABSORPTION,
    MESSENGER_OF_PEACE,
    TWIN_TWISTERS,
    MYSTICAL_SPACE_TYPHOON,
    RING_OF_DEFENCE,
    YAMI,
    FOREST,
    CLOSED_FOREST,
    UMIIRUKA,
    SWORD_OF_DARK_DESTRUCTION,
    BLACK_PENDANT,
    UNITED_WE_STAND,
    MAGNUM_SHIELD,
    ADVANCED_RITUAL_ART;
    public String description;

    static {
        MONSTER_REBORN.description = SpellsDescription.monsterReborn;
        TERRAFORMING.description = SpellsDescription.terrafoming;
        POT_OF_GREED.description = SpellsDescription.potOfGreed;
        RAIGEKI.description = SpellsDescription.raigeki;
        CHANGEOFHEART.description = SpellsDescription.changeOfHearts;
        SWORDS_OF_REVEALING_LIGHT.description = SpellsDescription.swordOfRevealingLight;
        HARPIES_FEATHER_DUSTER.description = SpellsDescription.harpies;
        DARK_HOLE.description = SpellsDescription.darkHole;
        SUPPLY_SQUAD.description = SpellsDescription.supplySquad;
        SPELL_ABSORPTION.description = SpellsDescription.spellAbsorption;
        MESSENGER_OF_PEACE.description = SpellsDescription.messengerOfPeace;
        TWIN_TWISTERS.description = SpellsDescription.twinTwister;
        MYSTICAL_SPACE_TYPHOON.description = SpellsDescription.mysticalSpaceTyphoon;
        RING_OF_DEFENCE.description = SpellsDescription.ringOfDefence;
        YAMI.description = SpellsDescription.yami;
        FOREST.description = SpellsDescription.forest;
        CLOSED_FOREST.description = SpellsDescription.closedForest;
        UMIIRUKA.description = SpellsDescription.umiiruka;
        SWORD_OF_DARK_DESTRUCTION.description = SpellsDescription.swordOfDestruction;
        BLACK_PENDANT.description = SpellsDescription.blackPendant;
        UNITED_WE_STAND.description = SpellsDescription.unitedWeStand;
        MAGNUM_SHIELD.description = SpellsDescription.magnumShield;
        ADVANCED_RITUAL_ART.description = SpellsDescription.advancedRitualArt;
    }
}
