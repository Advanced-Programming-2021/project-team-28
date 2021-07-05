package org.model;

import javafx.scene.image.Image;
import org.model.enums.*;

import java.util.ArrayList;

public class AllCardsInitiator {
    public static void fillAllCards() {
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Command knight", "", MonstersDescriptions.commandKnight, 1000, 1000,
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
        Card.addToAllCards(new MonsterCard(MonsterType.FIEND, null, "Feral Imp", "", MonstersDescriptions.feralImp, 1400, 1300,
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
        Card.addToAllCards(new MonsterCard(MonsterType.DRAGON, null, "Crawling dragon", "", MonstersDescriptions.crawlingDragon, 1600, 1400,
                MonsterPower.NONE, 5, Attribute.EARTH));
        Card.addToAllCards(new MonsterCard(MonsterType.SPELL_CASTER, null, "Flame Manipulator", "", MonstersDescriptions.flameManipulator, 900, 100,
                MonsterPower.NONE, 3, Attribute.FIRE));
        Card.addToAllCards(new MonsterCard(MonsterType.DRAGON, null, "Blue-Eyes White Dragon", "", MonstersDescriptions.blueEyesDragon, 3000, 2500,
                MonsterPower.NONE, 8, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.AQUA, null, "Crab Turtle", "", MonstersDescriptions.crabTurtle, 2550, 2500,
                MonsterPower.CRAB_TURTLE, 7, Attribute.WATER));
        Card.addToAllCards(new MonsterCard(MonsterType.WARRIOR, null, "Skull Guardian", "", MonstersDescriptions.skullGuardian, 2050, 2500,
                MonsterPower.SKULL_GUARDIAN, 7, Attribute.LIGHT));
        Card.addToAllCards(new MonsterCard(MonsterType.SUT, null, "Professor Fazli", "", MonstersDescriptions.professorFazli, 10500, 9000,
                MonsterPower.SKULL_GUARDIAN, 7, Attribute.LIGHT));
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

    public static void addMonstersToImageArrayList(){
        ArrayList<CardAndImage> cardsAndImages = Card.getCardsAndImages();
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/AlexandriteDragon.jpg").toExternalForm()) , "Alexandrite Dragon"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/AxeRaider.jpg").toExternalForm()) , "Axe Raider"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/BabyDragon.jpg").toExternalForm()) , "Baby Dragon"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/BattleOx.jpg").toExternalForm()) , "Battle Ox"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/BattleWarrior.jpg").toExternalForm()) , "Battle Warrior"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/BeastKingBarbaros.jpg").toExternalForm()) , "Beast King Barbaros"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Bitron.jpg").toExternalForm()) , "Bitron"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/BlueEyesWhiteDragon.jpg").toExternalForm()) , "Blue-Eyes White Dragon"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/CommandKnight.jpg").toExternalForm()) , "Command Knight"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/CrabTurtle.jpg").toExternalForm()) , "Crab Turtle"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/CrawlingDragon.jpg").toExternalForm()) , "Crawling Dragon"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/CurtainOfTheDarkOnes.jpg").toExternalForm()) , "Curtain Of The Dark Ones"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/DarkBlade.jpg").toExternalForm()) , "Dark Blade"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/DarkMagician.jpg").toExternalForm()) , "Dark Magician"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/ExploderDragon.jpg").toExternalForm()) , "Exploder Dragon"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/FeralImp.jpg").toExternalForm()) , "Feral Imp"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Fireyarou.jpg").toExternalForm()) , "Fireyarou"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/FlameManipulator.jpg").toExternalForm()) , "Flame Manipulator"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/GateGuardian.jpg").toExternalForm()) , "Gate Guardian"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Haniwa.jpg").toExternalForm()) , "Haniwa"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/HeraldOfCreation.jpg").toExternalForm()) , "Herald Of Creation"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/HeroOfTheEast.jpg").toExternalForm()) , "Hero Of The East"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/HornImp.jpg").toExternalForm()) , "Horn Imp"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Leotron.jpg").toExternalForm()) , "Leotron"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/ManEaterBug.jpg").toExternalForm()) , "Man-Eater Bug"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Marshmallon.jpg").toExternalForm()) , "Marshmallon"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/MirageDragon.jpg").toExternalForm()) , "Mirage Dragon"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Scanner.jpg").toExternalForm()) , "Scanner"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/SilverFang.jpg").toExternalForm()) , "Silver Fang"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/SkullGuardian.jpg").toExternalForm()) , "Skull Guardian"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/SlotMachine.jpg").toExternalForm()) , "Slot Machine"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/SpiralSerpent.jpg").toExternalForm()) , "Spiral Serpent"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Suijin.jpg").toExternalForm()) , "Suijin"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Terratiger.jpg").toExternalForm()) , "Terratiger, the Empowered Warrior"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Texchanger.jpg").toExternalForm()) , "Texchanger"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/TheCalculator.jpg").toExternalForm()) , "The Calculator"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/TheTricky.jpg").toExternalForm()) , "The Tricky"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/WarriorDaiGrepher.jpg").toExternalForm()) , "Warrior Dai Grepher"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Wattaildragon.jpg").toExternalForm()) , "Wattaildragon"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Wattkid.jpg").toExternalForm()) , "Wattkid"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/YomiShip.jpg").toExternalForm()) , "Yomi Ship"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/Monsters/Unknown.jpg").toExternalForm()) , "Unknown"));

    }

    public static void addSpellTrapToImageArrayList(){
        ArrayList<CardAndImage> cardsAndImages = Card.getCardsAndImages();
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/AdvancedRitualArt.jpg").toExternalForm()) , "Advanced Ritual Art"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/BlackPendant.jpg").toExternalForm()) , "Black Pendant"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/Call of the Hunted.jpg").toExternalForm()) , "Call of The Haunted"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/ClosedForest.jpg").toExternalForm()) , "Closed Forest"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/DarkHole.jpg").toExternalForm()) , "Dark Hole"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/Forest.jpg").toExternalForm()) , "Forest"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/HarpiesFeatherDuster.jpg").toExternalForm()) , "Harpie's Feather Duster"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/MagicCylinder.jpg").toExternalForm()) , "Magic Cylinder"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/MagicJammer.png").toExternalForm()) , "Magic Jammer"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/MagnumShield.jpg").toExternalForm()) , "Magnum Shield"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/MindCrush.jpg").toExternalForm()) , "Mind Crush"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/MirrorForce.jpg").toExternalForm()) , "Mirror Force"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/MonsterReborn.jpg").toExternalForm()) , "Monster Reborn"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/MysticalSpaceTyphoon.jpg").toExternalForm()) , "Mystical Space Typhoon"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/NegateAttack.jpg").toExternalForm()) , "Negate Attack"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/PotOfGreed.jpg").toExternalForm()) , "Pot of Greed"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/Raigeki.jpg").toExternalForm()) , "Raigeki"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/SolemnWarning.jpg").toExternalForm()) , "Solemn Warning"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/SwordOfDarkDestruction.jpg").toExternalForm()) , "Sword of Dark Destruction"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/Terraforming.jpg").toExternalForm()) , "Terraforming"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/TimeSeal.jpg").toExternalForm()) , "Time Seal"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/TorrentialTribute.jpg").toExternalForm()) , "Torrential Tribute"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/TrapHole.jpg").toExternalForm()) , "Trap Hole"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/Umiiruka.jpg").toExternalForm()) , "Umiiruka"));
        cardsAndImages.add(new CardAndImage(new Image(AllCardsInitiator.class.getResource("/cards/SpellTrap/Yami.jpg").toExternalForm()) , "Yami"));


    }

}
