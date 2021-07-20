package org.model;

import org.model.enums.*;
import org.serverController.ShopMenuController;

import java.util.HashMap;

public class AllCardsInitiator {
    private static HashMap<String, Integer> prices = Card.getPrices();

    public static void fillAllCards() {
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Command Knight", "", MonstersDescriptions.commandKnight, 1000, 1000,
                MonsterPower.COMMAND_KNIGHT, 4, Attribute.FIRE));
        Card.addToAllCards(new MonsterCard(MonsterType.BEAST_WARRIOR, null, "Battle Ox", "", MonstersDescriptions.battleOX, 1700, 1000,
                MonsterPower.NONE, 4, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Axe Raider", "", MonstersDescriptions.axeRaider, 1700, 1150,
                MonsterPower.NONE, 4, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.FIEND, null, "Horn Imp", "", MonstersDescriptions.hornImp, 1300, 1000,
                MonsterPower.NONE, 4, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.BEAST, null, "Silver Fang", "", MonstersDescriptions.silverFang, 1200, 800,
                MonsterPower.NONE, 3, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.AQUA, null, "Yomi Ship", "", MonstersDescriptions.yomiShip, 800, 1400,
                MonsterPower.YOMI_SHIP, 3, Attribute.WATER));
        Card.addToAllCards(new MonsterCard(MonsterType.AQUA, null, "Suijin", "", MonstersDescriptions.suijin, 2500, 2400,
                MonsterPower.SUIJIN, 7, Attribute.WATER));
        Card.addToAllCards(new MonsterCard(MonsterType.PYRO, null, "Fireyarou", "", MonstersDescriptions.fireYarou, 1300, 1000,
                MonsterPower.NONE, 4, Attribute.FIRE));
        Card.addToAllCards(new MonsterCard(MonsterType.SPELL_CASTER, null, "Curtain Of The Dark Ones", "", MonstersDescriptions.curtainOfTheDarkOnes, 600, 500,
                MonsterPower.NONE, 2, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.FIEND, null, "Feral Imp", "", MonstersDescriptions.feralImp, 1300, 1400,
                MonsterPower.NONE, 4, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.SPELL_CASTER, null, "Dark Magician", "", MonstersDescriptions.darkMagician, 2500, 2100,
                MonsterPower.NONE, 7, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.THUNDER, null, "Wattkid", "", MonstersDescriptions.wattkid, 1000, 500,
                MonsterPower.NONE, 3, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.DRAGON, null, "Baby Dragon", "", MonstersDescriptions.babyDragon, 1200, 700,
                MonsterPower.NONE, 3, Attribute.WIND));
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Hero Of The East", "", MonstersDescriptions.heroOfTheEast, 1100, 1000,
                MonsterPower.NONE, 3, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Battle Warrior", "", MonstersDescriptions.battleWarrior, 700, 1000,
                MonsterPower.NONE, 3, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.DRAGON, null, "Crawling Dragon", "", MonstersDescriptions.crawlingDragon, 1600, 1400,
                MonsterPower.NONE, 5, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.SPELL_CASTER, null, "Flame Manipulator", "", MonstersDescriptions.flameManipulator, 900, 100,
                MonsterPower.NONE, 3, Attribute.FIRE));
        Card.addToAllCards(new MonsterCard(MonsterType.DRAGON, null, "Blue-Eyes White Dragon", "", MonstersDescriptions.blueEyesDragon, 3000, 2500,
                MonsterPower.NONE, 8, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.AQUA, null, "Crab Turtle", "", MonstersDescriptions.crabTurtle, 2550, 2500,
                MonsterPower.RITUAL, 7, Attribute.WATER));
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Skull Guardian", "", MonstersDescriptions.skullGuardian, 2050, 2500,
                MonsterPower.RITUAL, 7, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.SUT, null, "The Master", "", MonstersDescriptions.TheMaster, 9000, 9000,
                MonsterPower.RITUAL, 12, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.DRAGON, null, "The Angry Cobbler", "", MonstersDescriptions.theAngry, 9999, 9999,
                MonsterPower.NONE, 12, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.SUT, null, "Team-28 Cerberus", "", MonstersDescriptions.cerberus, 9000, 9000,
                MonsterPower.RITUAL, 10, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.WITCHER, null, "Geralt Of Rivia", "", MonstersDescriptions.Geralt, 9000, 9000,
                MonsterPower.RITUAL, 12, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.SUT, null, "SPN The Sage", "", MonstersDescriptions.SPN, 5000, 3000,
                MonsterPower.NONE, 7, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Abbas Boua'zar", "", MonstersDescriptions.abbas, 5000, 3000,
                MonsterPower.NONE, 10, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.SUT, null, "The Graphic Lord", "", MonstersDescriptions.graphicLord, 5000, 3000,
                MonsterPower.NONE, 7, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.MACHINE, null, "Slot Machine", "", MonstersDescriptions.slotMachine, 2000, 2300,
                MonsterPower.NONE, 7, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.ROCK, null, "Haniwa", "", MonstersDescriptions.haniwa, 500, 500,
                MonsterPower.NONE, 2, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.INSECT, null, "Man-Eater Bug", "", MonstersDescriptions.manEaterBug, 450, 600,
                MonsterPower.MAN_EATER_BUG, 2, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Gate Guardian", "", MonstersDescriptions.gateGuardian, 3750, 3400,
                MonsterPower.GATE_GUARDIAN, 11, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.MACHINE, null, "Scanner", "", MonstersDescriptions.scanner, 0, 0,
                MonsterPower.SCANNER, 1, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.CYBERSE, null, "Bitron", "", MonstersDescriptions.bitron, 200, 2000,
                MonsterPower.NONE, 2, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.FAIRY, null, "Marshmallon", "", MonstersDescriptions.marshmallon, 300, 500,
                MonsterPower.MARSHMALLON, 3, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.BEAST_WARRIOR, null, "Beast King Barbaros", "", MonstersDescriptions.beastKing, 3000, 1200,
                MonsterPower.BEAST_KING_BARBAROS, 8, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.CYBERSE, null, "Texchanger", "", MonstersDescriptions.texChanger, 100, 100,
                MonsterPower.TEXCHANGER, 1, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.CYBERSE, null, "Leotron", "", MonstersDescriptions.leotron, 2000, 0,
                MonsterPower.NONE, 4, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.THUNDER, null, "The Calculator", "", MonstersDescriptions.theCalculator, 0, 0,
                MonsterPower.THE_CALCULATOR, 2, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.DRAGON, null, "Alexandrite Dragon", "", MonstersDescriptions.alexandriteDragon, 2000, 100,
                MonsterPower.NONE, 4, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.DRAGON, null, "Mirage Dragon", "", MonstersDescriptions.mirageDragon, 1600, 600,
                MonsterPower.MIRAGE_DRAGON, 4, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.SPELL_CASTER, null, "Herald Of Creation", "", MonstersDescriptions.heraldOfTheCreation, 1800, 600,
                MonsterPower.HERALD_OF_CREATION, 4, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.DRAGON, null, "Exploder Dragon", "", MonstersDescriptions.exploderDragon, 1000, 0,
                MonsterPower.EXPLODER_DRAGON, 3, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Warrior Dai Grepher", "", MonstersDescriptions.warriorDaiGrepher, 1700, 1600,
                MonsterPower.NONE, 4, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Dark Blade", "", MonstersDescriptions.darkBlade, 1800, 1500,
                MonsterPower.NONE, 4, Attribute.DARK));
        Card.addToAllCards(new MonsterCard(MonsterType.DRAGON, null, "Wattaildragon", "", MonstersDescriptions.wattailDragon, 2500, 1700,
                MonsterPower.NONE, 6, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Terratiger, the Empowered Warrior", "", MonstersDescriptions.terraTigerTheEmpoweredWarrior, 1800, 1200,
                MonsterPower.TERRATIGER_THE_EMPOWERED_WARRIOR, 4, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.SPELL_CASTER, null, "The Tricky", "", MonstersDescriptions.theTricky, 2000, 1200,
                MonsterPower.THE_TRICKY, 5, Attribute.WIND));
        Card.addToAllCards(new MonsterCard(MonsterType.SEA_SERPENT, null, "Spiral Serpent", "", MonstersDescriptions.spiralSerpent, 2900, 2900,
                MonsterPower.NONE, 8, Attribute.WATER));
        Card.addToAllCards(new SpellCard(null, "Monster Reborn", "", SpellsDescription.monsterReborn, SpellIcon.NORMAL, SpellEffect.MONSTER_REBORN));
        Card.addToAllCards(new SpellCard(null, "Terraforming", "", SpellsDescription.terrafoming, SpellIcon.NORMAL, SpellEffect.TERRAFORMING));
        Card.addToAllCards(new SpellCard(null, "Pot of Greed", "", SpellsDescription.potOfGreed, SpellIcon.NORMAL, SpellEffect.POT_OF_GREED));
        Card.addToAllCards(new SpellCard(null, "Raigeki", "", SpellsDescription.raigeki, SpellIcon.NORMAL, SpellEffect.RAIGEKI));
        Card.addToAllCards(new SpellCard(null, "change of Heart", "", SpellsDescription.changeOfHearts, SpellIcon.NORMAL, SpellEffect.CHANGEOFHEART));
        Card.addToAllCards(new SpellCard(null, "Sword of Revealing Light", "", SpellsDescription.swordOfRevealingLight, SpellIcon.NORMAL, SpellEffect.SWORDS_OF_REVEALING_LIGHT));
        Card.addToAllCards(new SpellCard(null, "Harpie's Feather Duster", "", SpellsDescription.harpies, SpellIcon.NORMAL, SpellEffect.HARPIES_FEATHER_DUSTER));
        Card.addToAllCards(new SpellCard(null, "Dark Hole", "", SpellsDescription.darkHole, SpellIcon.NORMAL, SpellEffect.DARK_HOLE));
        Card.addToAllCards(new SpellCard(null, "Supply Squad", "", SpellsDescription.supplySquad, SpellIcon.CONTINUOUS, SpellEffect.SUPPLY_SQUAD));
        Card.addToAllCards(new SpellCard(null, "Spell Absorption", "", SpellsDescription.spellAbsorption, SpellIcon.CONTINUOUS, SpellEffect.SPELL_ABSORPTION));
        Card.addToAllCards(new SpellCard(null, "Messenger of Peace", "", SpellsDescription.messengerOfPeace, SpellIcon.CONTINUOUS, SpellEffect.MESSENGER_OF_PEACE));
        Card.addToAllCards(new SpellCard(null, "Twin Twister", "", SpellsDescription.twinTwister, SpellIcon.QUICK_PLAY, SpellEffect.TWIN_TWISTERS));
        Card.addToAllCards(new SpellCard(null, "Mystical Space Typhoon", "", SpellsDescription.mysticalSpaceTyphoon, SpellIcon.QUICK_PLAY, SpellEffect.MYSTICAL_SPACE_TYPHOON));
        Card.addToAllCards(new SpellCard(null, "Ring of Defence", "", SpellsDescription.ringOfDefence, SpellIcon.QUICK_PLAY, SpellEffect.RING_OF_DEFENCE));
        Card.addToAllCards(new SpellCard(null, "Yami", "", SpellsDescription.yami, SpellIcon.FIELD, SpellEffect.YAMI));
        Card.addToAllCards(new SpellCard(null, "Forest", "", SpellsDescription.forest, SpellIcon.FIELD, SpellEffect.FOREST));
        Card.addToAllCards(new SpellCard(null, "Closed Forest", "", SpellsDescription.closedForest, SpellIcon.FIELD, SpellEffect.CLOSED_FOREST));
        Card.addToAllCards(new SpellCard(null, "Umiiruka", "", SpellsDescription.umiiruka, SpellIcon.FIELD, SpellEffect.UMIIRUKA));
        Card.addToAllCards(new SpellCard(null, "Sword of Dark Destruction", "", SpellsDescription.swordOfDestruction, SpellIcon.EQUIP, SpellEffect.SWORD_OF_DARK_DESTRUCTION));
        Card.addToAllCards(new SpellCard(null, "Black Pendant", "", SpellsDescription.blackPendant, SpellIcon.EQUIP, SpellEffect.BLACK_PENDANT));
        Card.addToAllCards(new SpellCard(null, "United We Stand", "", SpellsDescription.unitedWeStand, SpellIcon.EQUIP, SpellEffect.UNITED_WE_STAND));
        Card.addToAllCards(new SpellCard(null, "Magnum Shield", "", SpellsDescription.magnumShield, SpellIcon.EQUIP, SpellEffect.MAGNUM_SHIELD));
        Card.addToAllCards(new SpellCard(null, "Advanced Ritual Art", "", SpellsDescription.advancedRitualArt, SpellIcon.RITUAL, SpellEffect.ADVANCED_RITUAL_ART));
        Card.addToAllCards(new TrapCard(null, "Trap Hole", "", TrapsDescription.trapHole, TrapIcon.NORMAL, TrapEffect.TRAP_HOLE));
        Card.addToAllCards(new TrapCard(null, "Mirror Force", "", TrapsDescription.mirrorForce, TrapIcon.NORMAL, TrapEffect.MIRROR_FORCE));
        Card.addToAllCards(new TrapCard(null, "Magic Cylinder", "", TrapsDescription.magicCylinder, TrapIcon.NORMAL, TrapEffect.MAGIC_CYLINDER));
        Card.addToAllCards(new TrapCard(null, "Mind Crush", "", TrapsDescription.mindCrush, TrapIcon.NORMAL, TrapEffect.MIND_CRUSH));
        Card.addToAllCards(new TrapCard(null, "Torrential Tribute", "", TrapsDescription.torrentialTribute, TrapIcon.NORMAL, TrapEffect.TORRENTIAL_TRIBUTE));
        Card.addToAllCards(new TrapCard(null, "Time Seal", "", TrapsDescription.timeSeal, TrapIcon.NORMAL, TrapEffect.TIME_SEAL));
        Card.addToAllCards(new TrapCard(null, "Negate Attack", "", TrapsDescription.negateAttack, TrapIcon.COUNTER, TrapEffect.NEGATE_ATTACK));
        Card.addToAllCards(new TrapCard(null, "Solemn Warning", "", TrapsDescription.solemnWarning, TrapIcon.COUNTER, TrapEffect.SOLEMN_WARNING));
        Card.addToAllCards(new TrapCard(null, "Magic Jammer", "", TrapsDescription.magicJammer, TrapIcon.COUNTER, TrapEffect.MAGIC_JAMMER));
        Card.addToAllCards(new TrapCard(null, "Call of The Haunted", "", TrapsDescription.callOfTheHaunted, TrapIcon.CONTINUOUS, TrapEffect.CALL_OF_THE_HAUNTED));
    }

    public static void setPrices() {
        prices.put("Abbas Boua'zar", 12345);
        prices.put("The Angry Cobbler", 12345);
        prices.put("Geralt Of Rivia", 20000);
        prices.put("Team-28 Cerberus", 20000);
        prices.put("The Graphic Lord", 12000);
        prices.put("SPN The Sage", 12000);
        prices.put("The Master", 20000);
        prices.put("Command Knight", 2100);
        prices.put("Battle Ox", 2900);
        prices.put("Axe Raider", 3100);
        prices.put("Horn Imp", 2500);
        prices.put("Silver Fang", 1700);
        prices.put("Yomi Ship", 1700);
        prices.put("Suijin", 8700);
        prices.put("Fireyarou", 2500);
        prices.put("Curtain Of The Dark Ones", 700);
        prices.put("Feral Imp", 2800);
        prices.put("Dark Magician", 8300);
        prices.put("Wattkid", 1300);
        prices.put("Baby Dragon", 1600);
        prices.put("Hero Of The East", 1700);
        prices.put("Battle Warrior", 1300);
        prices.put("Crawling Dragon", 3900);
        prices.put("Flame Manipulator", 1500);
        prices.put("Blue-Eyes White Dragon", 11300);
        prices.put("Crab Turtle", 10200);
        prices.put("Skull Guardian", 7900);
        prices.put("Slot Machine", 7500);
        prices.put("Haniwa", 600);
        prices.put("Man-Eater Bug", 600);
        prices.put("Gate Guardian", 20000);
        prices.put("Scanner", 8000);
        prices.put("Bitron", 1000);
        prices.put("Marshmallon", 700);
        prices.put("Beast King Barbaros", 9200);
        prices.put("Texchanger", 200);
        prices.put("Leotron", 2500);
        prices.put("The Calculator", 8000);
        prices.put("Alexandrite Dragon", 2600);
        prices.put("Mirage Dragon", 2500);
        prices.put("Herald Of Creation", 2700);
        prices.put("Exploder Dragon", 1000);
        prices.put("Warrior Dai Grepher", 3400);
        prices.put("Dark Blade", 3500);
        prices.put("Wattaildragon", 5800);
        prices.put("Terratiger, the Empowered Warrior", 3200);
        prices.put("The Tricky", 4300);
        prices.put("Spiral Serpent", 11700);
        prices.put("Monster Reborn", 2500);
        prices.put("Terraforming", 2500);
        prices.put("Pot of Greed", 2500);
        prices.put("Raigeki", 2500);
        prices.put("change of Heart", 2500);
        prices.put("Sword of Revealing Light", 2500);
        prices.put("Harpie's Feather Duster", 2500);
        prices.put("Dark Hole", 2500);
        prices.put("Supply Squad", 4000);
        prices.put("Spell Absorption", 4000);
        prices.put("Messenger of Peace", 4000);
        prices.put("Twin Twister", 3500);
        prices.put("Mystical Space Typhoon", 3500);
        prices.put("Ring of Defence", 3500);
        prices.put("Yami", 4300);
        prices.put("Forest", 4300);
        prices.put("Closed Forest", 4300);
        prices.put("Umiiruka", 4300);
        prices.put("Sword of Dark Destruction", 4300);
        prices.put("Black Pendant", 4300);
        prices.put("United We Stand", 4300);
        prices.put("Magnum Shield", 4300);
        prices.put("Advanced Ritual Art", 3000);
        prices.put("Trap Hole", 2000);
        prices.put("Mirror Force", 2000);
        prices.put("Magic Cylinder", 2000);
        prices.put("Mind Crush", 2000);
        prices.put("Torrential Tribute", 2000);
        prices.put("Time Seal", 2000);
        prices.put("Negate Attack", 3000);
        prices.put("Solemn Warning", 3000);
        prices.put("Magic Jammer", 3000);
        prices.put("Call of The Haunted", 3500);
    }

    public static void setCardAmountsAndAvailability() {
        for (Card card : Card.getAllCards()) {
            ShopMenuController.getCardsAdminFields().put(card.getName(), new AdminCardFields());
        }
    }
}

