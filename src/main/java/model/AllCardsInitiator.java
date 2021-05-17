package model;

import enums.*;

public class AllCardsInitiator {
    public static void fillAllCards() {
        Card.allCards.add(new MonsterCard(MonsterType.WARRIOR , null , "Command knight", "", MonstersDescriptions.commandKnight, 1000, 1000,
                MonsterPower.COMMAND_KNIGHT, 4, Attribute.FIRE));
        Card.allCards.add(new MonsterCard(MonsterType.BEAST_WARRIOR , null , "Battle Ox", "", MonstersDescriptions.battleOX, 1700, 1000,
                MonsterPower.NONE, 4, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.WARRIOR , null , "Axe Raider", "", MonstersDescriptions.axeRaider, 1700, 1150,
                MonsterPower.NONE, 4, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.FIEND , null , "Horn Imp", "", MonstersDescriptions.hornImp, 1300, 1000,
                MonsterPower.NONE, 4, Attribute.DARK));
        Card.allCards.add(new MonsterCard(MonsterType.BEAST , null , "Silver Fang", "", MonstersDescriptions.silverFang, 1200, 800,
                MonsterPower.NONE, 3, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.AQUA , null , "Yomi Ship", "", MonstersDescriptions.yomiShip, 800, 1400,
                MonsterPower.YOMI_SHIP, 3, Attribute.WATER));
        Card.allCards.add(new MonsterCard(MonsterType.AQUA , null , "Suijin", "", MonstersDescriptions.suijin, 2500, 2400,
                MonsterPower.SUIJIN, 7, Attribute.WATER));
        Card.allCards.add(new MonsterCard(MonsterType.PYRO , null , "Fire Yarou", "", MonstersDescriptions.fireYarou, 1300, 1000,
                MonsterPower.NONE, 4, Attribute.FIRE));
        Card.allCards.add(new MonsterCard(MonsterType.SPELL_CASTER , null , "Curtain of Dark Ones", "", MonstersDescriptions.curtainOfTheDarkOnes, 600, 500,
                MonsterPower.NONE, 2, Attribute.DARK));
        Card.allCards.add(new MonsterCard(MonsterType.FIEND , null , "Feral Imp", "", MonstersDescriptions.feralImp, 1400, 1300,
                MonsterPower.NONE, 4, Attribute.DARK));
        Card.allCards.add(new MonsterCard(MonsterType.SPELL_CASTER , null , "Dark Magician", "", MonstersDescriptions.darkMagician, 2500, 2100,
                MonsterPower.NONE, 7, Attribute.DARK));
        Card.allCards.add(new MonsterCard(MonsterType.THUNDER , null , "Wattkid", "", MonstersDescriptions.wattkid, 1000, 500,
                MonsterPower.NONE, 3, Attribute.LIGHT));
        Card.allCards.add(new MonsterCard(MonsterType.DRAGON , null , "Baby Dragon", "", MonstersDescriptions.babyDragon, 1200, 700,
                MonsterPower.NONE, 3, Attribute.WIND));
        Card.allCards.add(new MonsterCard(MonsterType.WARRIOR , null , "Hero of the East", "", MonstersDescriptions.heroOfTheEast, 1100, 1000,
                MonsterPower.NONE, 3, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.WARRIOR , null , "Battle Warrior", "", MonstersDescriptions.battleWarrior, 700, 1000,
                MonsterPower.NONE, 3, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.DRAGON , null , "Crawling dragon", "", MonstersDescriptions.crawlingDragon, 1600, 1400,
                MonsterPower.NONE, 5, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.SPELL_CASTER , null , "Flame Manipulator", "", MonstersDescriptions.flameManipulator, 900, 100,
                MonsterPower.NONE, 3, Attribute.FIRE));
        Card.allCards.add(new MonsterCard(MonsterType.DRAGON , null , "Blue-Eyes White Dragon", "", MonstersDescriptions.blueEyesDragon, 3000, 2500,
                MonsterPower.NONE, 8, Attribute.LIGHT));
        Card.allCards.add(new MonsterCard(MonsterType.AQUA , null , "Crab Turtle", "", MonstersDescriptions.crabTurtle, 2550, 2500,
                MonsterPower.CRAB_TURTLE, 7, Attribute.WATER));
        Card.allCards.add(new MonsterCard(MonsterType.WARRIOR , null , "Skull Guardian", "", MonstersDescriptions.skullGuardian, 2050, 2500,
                MonsterPower.SKULL_GUARDIAN, 7, Attribute.LIGHT));
        Card.allCards.add(new MonsterCard(MonsterType.MACHINE , null , "Slot Machine", "", MonstersDescriptions.slotMachine, 2000, 2300,
                MonsterPower.NONE, 7, Attribute.DARK));
        Card.allCards.add(new MonsterCard(MonsterType.ROCK , null , "Haniwa", "", MonstersDescriptions.haniwa, 500, 500,
                MonsterPower.NONE, 2, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.INSECT , null , "Man-Eater Bug", "", MonstersDescriptions.manEaterBug, 450, 600,
                MonsterPower.MAN_EATER_BUG, 2, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.WARRIOR , null , "Gate Guardian", "", MonstersDescriptions.gateGuardian, 3750, 3400,
                MonsterPower.GATE_GUARDIAN, 11, Attribute.DARK));
        Card.allCards.add(new MonsterCard(MonsterType.MACHINE , null , "Scanner", "", MonstersDescriptions.scanner, 0, 0,
                MonsterPower.SCANNER, 1, Attribute.LIGHT));
        Card.allCards.add(new MonsterCard(MonsterType.CYBERSE , null , "Bitron", "", MonstersDescriptions.bitron, 200, 2000,
                MonsterPower.NONE, 2, Attribute.DARK));
        Card.allCards.add(new MonsterCard(MonsterType.FAIRY , null , "Marshmallon", "", MonstersDescriptions.marshmallon, 300, 500,
                MonsterPower.MARSHMALLON, 3, Attribute.LIGHT));
        Card.allCards.add( new MonsterCard(MonsterType.BEAST_WARRIOR , null , "Beast King Barbaros", "", MonstersDescriptions.beastKing, 3000, 1200,
                MonsterPower.BEAST_KING_BARBAROS, 8, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.CYBERSE , null , "Texchanger", "", MonstersDescriptions.texChanger, 100, 100,
                MonsterPower.TEXCHANGER, 1, Attribute.DARK));
        Card.allCards.add(new MonsterCard(MonsterType.CYBERSE , null , "Leotron", "", MonstersDescriptions.leotron, 2000, 0,
                MonsterPower.NONE, 4, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.THUNDER , null , "The Calculator", "", MonstersDescriptions.theCalculator ,0, 0,
                MonsterPower.THE_CALCULATOR, 2, Attribute.LIGHT));
        Card.allCards.add(new MonsterCard(MonsterType.DRAGON , null , "Alexandrite Dragon", "", MonstersDescriptions.alexandriteDragon, 2000, 100,
                MonsterPower.NONE, 4, Attribute.LIGHT));
        Card.allCards.add(new MonsterCard(MonsterType.DRAGON , null , "Mirage Dragon", "", MonstersDescriptions.mirageDragon, 1600, 600,
                MonsterPower.MIRAGE_DRAGON, 4, Attribute.LIGHT));
        Card.allCards.add(new MonsterCard(MonsterType.SPELL_CASTER , null , "Herald of Creation", "", MonstersDescriptions.heraldOfTheCreation, 1800, 600,
                MonsterPower.HERALD_OF_CREATION, 4, Attribute.LIGHT));
        Card.allCards.add(new MonsterCard(MonsterType.DRAGON , null , "Exploder Dragon", "", MonstersDescriptions.exploderDragon, 1000, 0,
                MonsterPower.EXPLODER_DRAGON, 3, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.WARRIOR , null , "Warrior Dai Grepher", "", MonstersDescriptions.warriorDaiGrepher, 1700, 1600,
                MonsterPower.NONE, 4, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.WARRIOR , null , "Dark Blade", "", MonstersDescriptions.darkBlade, 1800, 1500,
                MonsterPower.NONE, 4, Attribute.DARK));
        Card.allCards.add(new MonsterCard(MonsterType.DRAGON , null , "Wattaildragon", "", MonstersDescriptions.wattailDragon, 2500, 1700,
                MonsterPower.NONE, 6, Attribute.LIGHT));
        Card.allCards.add(new MonsterCard(MonsterType.WARRIOR , null , "Terratiger, the Empowered Warrior", "", MonstersDescriptions.terraTigerTheEmpoweredWarrior, 1800, 1200,
                MonsterPower.TERRATIGER_THE_EMPOWERED_WARRIOR, 4, Attribute.EARTH));
        Card.allCards.add(new MonsterCard(MonsterType.SPELL_CASTER , null , "The Tricky", "", MonstersDescriptions.theTricky, 2000, 1200,
                MonsterPower.THE_TRICKY, 5, Attribute.WIND));
        Card.allCards.add(new MonsterCard(MonsterType.SEA_SERPENT , null , "Spiral Serpent", "", MonstersDescriptions.spiralSerpent, 2900, 2900,
                MonsterPower.NONE, 8, Attribute.WATER));
        Card.allCards.add(new SpellCard(null , "Monster Reborn", "", SpellsDescription.monsterReborn , SpellIcon.NORMAL , SpellEffect.MONSTER_REBORN));
        Card.allCards.add(new SpellCard(null , "Terraforming", "", SpellsDescription.terrafoming , SpellIcon.NORMAL , SpellEffect.TERRAFORMING));
        Card.allCards.add(new SpellCard(null , "Pot of Greed", "", SpellsDescription.potOfGreed , SpellIcon.NORMAL, SpellEffect.POT_OF_GREED));
        Card.allCards.add(new SpellCard(null , "Raigeki", "", SpellsDescription.raigeki , SpellIcon.NORMAL , SpellEffect.RAIGEKI));
        Card.allCards.add(new SpellCard(null , "change of Heart", "", SpellsDescription.changeOfHearts , SpellIcon.NORMAL , SpellEffect.CHANGEOFHEART));
        Card.allCards.add(new SpellCard(null , "Sword of Revealing Light", "", SpellsDescription.swordOfRevealingLight , SpellIcon.NORMAL , SpellEffect.SWORDS_OF_REVEALING_LIGHT));
        Card.allCards.add(new SpellCard(null , "Harpie's Feather Duster", "", SpellsDescription.harpies , SpellIcon.NORMAL , SpellEffect.HARPIES_FEATHER_DUSTER));
        Card.allCards.add(new SpellCard(null , "Dark Hole", "", SpellsDescription.darkHole , SpellIcon.NORMAL , SpellEffect.DARK_HOLE));
        Card.allCards.add(new SpellCard(null , "Supply Squad", "", SpellsDescription.supplySquad , SpellIcon.CONTINUOUS , SpellEffect.SUPPLY_SQUAD));
        Card.allCards.add( new SpellCard(null , "Spell Absorption", "", SpellsDescription.spellAbsorption , SpellIcon.CONTINUOUS , SpellEffect.SPELL_ABSORPTION));
        Card.allCards.add(new SpellCard(null , "Messenger of Peace", "", SpellsDescription.messengerOfPeace , SpellIcon.CONTINUOUS , SpellEffect.MESSENGER_OF_PEACE));
        Card.allCards.add( new SpellCard(null , "Twin Twister", "", SpellsDescription.twinTwister , SpellIcon.QUICK_PLAY , SpellEffect.TWIN_TWISTERS));
        Card.allCards.add(new SpellCard(null , "Mystical Space Typhoon", "", SpellsDescription.mysticalSpaceTyphoon , SpellIcon.QUICK_PLAY , SpellEffect.MYSTICAL_SPACE_TYPHOON));
        Card.allCards.add(new SpellCard(null , "Ring of Defence", "", SpellsDescription.ringOfDefence , SpellIcon.QUICK_PLAY , SpellEffect.RING_OF_DEFENCE));
        Card.allCards.add(new SpellCard(null , "Yami", "", SpellsDescription.yami , SpellIcon.FIELD , SpellEffect.YAMI));
        Card.allCards.add(new SpellCard(null , "Forest", "", SpellsDescription.forest , SpellIcon.FIELD , SpellEffect.FOREST));
        Card.allCards.add(new SpellCard(null , "Closed Forest", "", SpellsDescription.closedForest , SpellIcon.FIELD , SpellEffect.CLOSED_FOREST));
        Card.allCards.add(new SpellCard(null , "Umiiruka", "", SpellsDescription.umiiruka , SpellIcon.FIELD , SpellEffect.UMIIRUKA));
        Card.allCards.add( new SpellCard(null , "Sword of Destruction", "", SpellsDescription.swordOfDestruction , SpellIcon.EQUIP , SpellEffect.SWORD_OF_DARK_DESTRUCTION));
        Card.allCards.add(new SpellCard(null , "Black Pendant", "", SpellsDescription.blackPendant , SpellIcon.EQUIP , SpellEffect.BLACK_PENDANT));
        Card.allCards.add(new SpellCard(null , "United We Stand", "", SpellsDescription.unitedWeStand , SpellIcon.EQUIP , SpellEffect.UNITED_WE_STAND));
        Card.allCards.add(new SpellCard(null , "Magnum Shield", "", SpellsDescription.magnumShield , SpellIcon.EQUIP , SpellEffect.MAGNUM_SHIELD));
        Card.allCards.add(new SpellCard(null , "Advanced Ritual Art", "", SpellsDescription.advancedRitualArt , SpellIcon.RITUAL , SpellEffect.ADVANCED_RITUAL_ART));
        Card.allCards.add(new TrapCard(null , "Trap Hole", "", TrapsDescription.trapHole , TrapIcon.NORMAL , TrapEffect.TRAP_HOLE));
        Card.allCards.add(new TrapCard(null , "Mirror Force", "", TrapsDescription.mirrorForce , TrapIcon.NORMAL , TrapEffect.MIRROR_FORCE));
        Card.allCards.add(new TrapCard(null , "Magic Cylinder", "", TrapsDescription.magicCylinder , TrapIcon.NORMAL , TrapEffect.MAGIC_CYLINDER));
        Card.allCards.add(new TrapCard(null , "Mind Crush", "", TrapsDescription.mindCrush , TrapIcon.NORMAL , TrapEffect.MIND_CRUSH));
        Card.allCards.add(new TrapCard(null , "Torrential Tribute", "", TrapsDescription.torrentialTribute , TrapIcon.NORMAL , TrapEffect.TORRENTIAL_TRIBUTE));
        Card.allCards.add(new TrapCard(null , "Time Seal", "", TrapsDescription.timeSeal , TrapIcon.NORMAL , TrapEffect.TIME_SEAL));
        Card.allCards.add(new TrapCard(null , "Negate Attack", "", TrapsDescription.negateAttack , TrapIcon.COUNTER , TrapEffect.NEGATE_ATTACK));
        Card.allCards.add( new TrapCard(null , "Solemn Warning", "", TrapsDescription.solemnWarning , TrapIcon.COUNTER , TrapEffect.SOLEMN_WARNING));
        Card.allCards.add(new TrapCard(null , "Magic Jammer", "", TrapsDescription.magicJammer , TrapIcon.COUNTER , TrapEffect.MAGIC_JAMMER));
        Card.allCards.add( new TrapCard(null , "Call of The Haunted", "", TrapsDescription.callOfTheHaunted , TrapIcon.CONTINUOUS , TrapEffect.CALL_OF_THE_HAUNTED));
        Card.allCards.add(new TrapCard(null , "Vanity's Emptiness", "", TrapsDescription.vanitiesEmptiness , TrapIcon.CONTINUOUS , TrapEffect.VANITYS_EMPTINESS));
        Card.allCards.add( new TrapCard(null , "Wall of Revealing Light", "", TrapsDescription.wallOfRevealingLight , TrapIcon.CONTINUOUS , TrapEffect.WALL_OF_REVEALING_LIGHT));

    }
}
