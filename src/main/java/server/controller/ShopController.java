package server.controller;

import org.model.*;
import org.model.enums.*;
import org.view.ShopView;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopController {

    public User getUser() {
        return user;
    }

    User user;
    ShopView view = new ShopView(this);

    public ShopController(User user) {
        this.user = user;
    }

    public ShopView getView() {
        return view;
    }

    public void run() throws Exception {
        this.view.run();
    }

    public void processCommand(String command) throws Exception {
        Pattern patternForImpossibleMenuNavigation = Pattern.compile("^menu enter (Duel|Scoreboard|Import/Export|Deck|Profile)$");
        Matcher matcherForImpossibleMenuNavigation = patternForImpossibleMenuNavigation.matcher(command);
        if (matcherForImpossibleMenuNavigation.find()) {
            view.impossibleMenuNavigation();
        } else if (command.equals("menu show-current")) {
            view.menuShowCurrent();
        } else if (command.startsWith("shop buy ")) {
            Matcher matcher = getCommandMatcher(command, "^shop buy (.+)$");
            sellCard(matcher.group(1));
        } else if (command.equals("shop show all")) {
            showAllCards();
        } else if (command.startsWith("card show ")) {
            Matcher matcher = getCommandMatcher(command, "^card show (.*)");
            showCard(matcher.group(1));
        } else {
            Matcher[] cheatMatchers = getCheatMatchers(command);
            for (int i=0; i<2; i++){
                if(cheatMatchers[i].find()){
                    user.changeBalance(Integer.parseInt(cheatMatchers[i].group(1)));
                    view.cheatActivated();
                    return;
                }
            }
            view.invalidCommand();
        }
    }

    public static Matcher getCommandMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        if (matcher.find())
            return matcher;
        else
            return null;
    }

    public void showCard(String name) throws Exception {
        if (Card.getCardByName(Card.getAllCards(), name) != null)
            view.showCard(Card.getCardByName(Card.getAllCards(), name));
        else view.cardNotFound();
    }


    public void sellCard(String cardName){
        String cardNumber = UUID.randomUUID().toString();
        if(cardName.equals("Command Knight")){
            if(receiveMoneyFromCustomer(2100)) {
                MonsterCard card = new MonsterCard(MonsterType.WARRIOR , user.getUsername() ,"Command Knight", cardNumber, MonstersDescriptions.commandKnight, 1000, 1000,
                        MonsterPower.COMMAND_KNIGHT, 4, Attribute.FIRE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Battle Ox")){
            if(receiveMoneyFromCustomer(2900)) {
                MonsterCard card = new MonsterCard(MonsterType.BEAST_WARRIOR , user.getUsername(),"Battle Ox", cardNumber, MonstersDescriptions.battleOX, 1700, 1000,
                        MonsterPower.NONE, 4, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Axe Raider")){
            if(receiveMoneyFromCustomer(3100)) {
                MonsterCard card = new MonsterCard(MonsterType.WARRIOR , user.getUsername() , cardName, cardNumber, MonstersDescriptions.axeRaider, 1700, 1150,
                        MonsterPower.NONE, 4, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Horn Imp")){
            if(receiveMoneyFromCustomer(2500)) {
                MonsterCard card = new MonsterCard(MonsterType.FIEND , user.getUsername() , cardName, cardNumber, MonstersDescriptions.hornImp, 1300, 1000,
                        MonsterPower.NONE, 4, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Silver Fang")){
            if(receiveMoneyFromCustomer(1700)) {
                MonsterCard card = new MonsterCard(MonsterType.BEAST , user.getUsername() , cardName, cardNumber, MonstersDescriptions.silverFang, 1200, 800,
                        MonsterPower.NONE, 3, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Yomi Ship")){
            if(receiveMoneyFromCustomer(1700)) {
                MonsterCard card = new MonsterCard(MonsterType.AQUA , user.getUsername() , cardName, cardNumber, MonstersDescriptions.yomiShip, 800, 1400,
                        MonsterPower.YOMI_SHIP, 3, Attribute.WATER);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Suijin")){
            if(receiveMoneyFromCustomer(8700)) {
                MonsterCard card = new MonsterCard(MonsterType.AQUA , user.getUsername() , cardName, cardNumber, MonstersDescriptions.suijin, 2500, 2400,
                        MonsterPower.SUIJIN, 7, Attribute.WATER);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Fireyarou")){
            if(receiveMoneyFromCustomer(2500)) {
                MonsterCard card = new MonsterCard(MonsterType.PYRO , user.getUsername() , cardName, cardNumber, MonstersDescriptions.fireYarou, 1300, 1000,
                        MonsterPower.NONE, 4, Attribute.FIRE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Abbas Boua'zar")){
            if(receiveMoneyFromCustomer(2500)) {
                MonsterCard card = new MonsterCard(MonsterType.WARRIOR, user.getUsername(), "Abbas Boua'zar", "", MonstersDescriptions.abbas, 5000, 3000,
                        MonsterPower.NONE, 10, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("SPN The Sage")){
            if(receiveMoneyFromCustomer(12000)) {
                MonsterCard card = new MonsterCard(MonsterType.SUT, user.getUsername(), "SPN The Sage", cardNumber, MonstersDescriptions.SPN, 5000, 3000,
                        MonsterPower.NONE, 7, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Team-28 Cerberus")){
            if(receiveMoneyFromCustomer(20000)) {
                MonsterCard card = new MonsterCard(MonsterType.SUT, user.getUsername(), "Team-28 Cerberus", cardNumber, MonstersDescriptions.cerberus, 9000, 9000,
                        MonsterPower.RITUAL, 10, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("The Angry Cobbler")){
            if(receiveMoneyFromCustomer(12345)) {
                MonsterCard card = new MonsterCard(MonsterType.DRAGON, user.getUsername(), "The Angry Cobbler", "", MonstersDescriptions.theAngry, 9999, 9999,
                        MonsterPower.NONE, 12, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("The Graphic Lord")){
            if(receiveMoneyFromCustomer(12000)) {
                MonsterCard card = new MonsterCard(MonsterType.SUT, user.getUsername(), "The Graphic Lord", cardNumber, MonstersDescriptions.graphicLord, 5000, 3000,
                        MonsterPower.NONE, 7, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Curtain Of The Dark Ones")){
            if(receiveMoneyFromCustomer(700)) {
                MonsterCard card = new MonsterCard(MonsterType.SPELL_CASTER , user.getUsername() , cardName, cardNumber, MonstersDescriptions.curtainOfTheDarkOnes, 600, 500,
                        MonsterPower.NONE, 2, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Feral Imp")){
            if(receiveMoneyFromCustomer(2800)) {
                MonsterCard card = new MonsterCard(MonsterType.FIEND , user.getUsername() , cardName, cardNumber, MonstersDescriptions.feralImp, 1300, 1400,
                        MonsterPower.NONE, 4, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Dark Magician")){
            if(receiveMoneyFromCustomer(8300)) {
                MonsterCard card = new MonsterCard(MonsterType.SPELL_CASTER , user.getUsername() , cardName, cardNumber, MonstersDescriptions.darkMagician, 2500, 2100,
                        MonsterPower.NONE, 7, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Wattkid")){
            if(receiveMoneyFromCustomer(1300)) {
                MonsterCard card = new MonsterCard(MonsterType.THUNDER , user.getUsername() , cardName, cardNumber, MonstersDescriptions.wattkid, 1000, 500,
                        MonsterPower.NONE, 3, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Baby Dragon")){
            if(receiveMoneyFromCustomer(1600)) {
                MonsterCard card = new MonsterCard(MonsterType.DRAGON , user.getUsername() , cardName, cardNumber, MonstersDescriptions.babyDragon, 1200, 700,
                        MonsterPower.NONE, 3, Attribute.WIND);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Hero Of The East")){
            if(receiveMoneyFromCustomer(1700)) {
                MonsterCard card = new MonsterCard(MonsterType.WARRIOR , user.getUsername() , cardName, cardNumber, MonstersDescriptions.heroOfTheEast, 1100, 1000,
                        MonsterPower.NONE, 3, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Battle Warrior")){
            if(receiveMoneyFromCustomer(1300)) {
                MonsterCard card = new MonsterCard(MonsterType.WARRIOR , user.getUsername() , cardName, cardNumber, MonstersDescriptions.battleWarrior, 700, 1000,
                        MonsterPower.NONE, 3, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Crawling Dragon")){
            if(receiveMoneyFromCustomer(3900)) {
                MonsterCard card = new MonsterCard(MonsterType.DRAGON , user.getUsername() , cardName, cardNumber, MonstersDescriptions.crawlingDragon, 1600, 1400,
                        MonsterPower.NONE, 5, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Flame Manipulator")){
            if(receiveMoneyFromCustomer(1500)) {
                MonsterCard card = new MonsterCard(MonsterType.SPELL_CASTER , user.getUsername() , cardName, cardNumber, MonstersDescriptions.flameManipulator, 900, 100,
                        MonsterPower.NONE, 3, Attribute.FIRE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("The Master")){
            if(receiveMoneyFromCustomer(20000)) {
                MonsterCard card = new MonsterCard(MonsterType.SUT, user.getUsername(), "The Master", cardNumber, MonstersDescriptions.TheMaster, 9000, 9000,
                        MonsterPower.RITUAL, 12, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Geralt Of Rivia")){
            if(receiveMoneyFromCustomer(20000)) {
                MonsterCard card = new MonsterCard(MonsterType.WITCHER, user.getUsername(), "Geralt Of Rivia", cardNumber, MonstersDescriptions.Geralt, 9000, 9000,
                        MonsterPower.RITUAL, 12, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Blue-Eyes White Dragon")){
            if(receiveMoneyFromCustomer(11300)) {
                MonsterCard card = new MonsterCard(MonsterType.DRAGON , user.getUsername() , cardName, cardNumber, MonstersDescriptions.blueEyesDragon, 3000, 2500,
                        MonsterPower.NONE, 8, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Crab Turtle")){
            if(receiveMoneyFromCustomer(10200)) {
                MonsterCard card = new MonsterCard(MonsterType.AQUA , user.getUsername() , cardName, cardNumber, MonstersDescriptions.crabTurtle, 2550, 2500,
                        MonsterPower.RITUAL, 7, Attribute.WATER);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Skull Guardian")){
            if(receiveMoneyFromCustomer(7900)) {
                MonsterCard card = new MonsterCard(MonsterType.WARRIOR , user.getUsername() , cardName, cardNumber, MonstersDescriptions.skullGuardian, 2050, 2500,
                        MonsterPower.RITUAL, 7, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Slot Machine")){
            if(receiveMoneyFromCustomer(7500)) {
                MonsterCard card = new MonsterCard(MonsterType.MACHINE , user.getUsername() , cardName, cardNumber, MonstersDescriptions.slotMachine, 2000, 2300,
                        MonsterPower.NONE, 7, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Haniwa")){
            if(receiveMoneyFromCustomer(600)) {
                MonsterCard card = new MonsterCard(MonsterType.ROCK , user.getUsername() , cardName, cardNumber, MonstersDescriptions.haniwa, 500, 500,
                        MonsterPower.NONE, 2, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Man-Eater Bug")){
            if(receiveMoneyFromCustomer(600)) {
                MonsterCard card = new MonsterCard(MonsterType.INSECT , user.getUsername() , cardName, cardNumber, MonstersDescriptions.manEaterBug, 450, 600,
                        MonsterPower.MAN_EATER_BUG, 2, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Gate Guardian")){
            if(receiveMoneyFromCustomer(20000)) {
                MonsterCard card = new MonsterCard(MonsterType.WARRIOR , user.getUsername() , cardName, cardNumber, MonstersDescriptions.gateGuardian, 3750, 3400,
                        MonsterPower.GATE_GUARDIAN, 11, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Scanner")){
            if(receiveMoneyFromCustomer(8000)) {
                MonsterCard card = new MonsterCard(MonsterType.MACHINE , user.getUsername() , cardName, cardNumber, MonstersDescriptions.scanner, 0, 0,
                        MonsterPower.SCANNER, 1, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Bitron")){
            if(receiveMoneyFromCustomer(1000)) {
                MonsterCard card = new MonsterCard(MonsterType.CYBERSE , user.getUsername() , cardName, cardNumber, MonstersDescriptions.bitron, 200, 2000,
                        MonsterPower.NONE, 2, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Marshmallon")){
            if(receiveMoneyFromCustomer(700)) {
                MonsterCard card = new MonsterCard(MonsterType.FAIRY , user.getUsername() , cardName, cardNumber, MonstersDescriptions.marshmallon, 300, 500,
                        MonsterPower.MARSHMALLON, 3, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Beast King Barbaros")){
            if(receiveMoneyFromCustomer(9200)) {
                MonsterCard card = new MonsterCard(MonsterType.BEAST_WARRIOR , user.getUsername() , cardName, cardNumber, MonstersDescriptions.beastKing, 3000, 1200,
                        MonsterPower.BEAST_KING_BARBAROS, 8, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Texchanger")){
            if(receiveMoneyFromCustomer(200)) {
                MonsterCard card = new MonsterCard(MonsterType.CYBERSE , user.getUsername() , cardName, cardNumber, MonstersDescriptions.texChanger, 100, 100,
                        MonsterPower.TEXCHANGER, 1, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Leotron")){
            if(receiveMoneyFromCustomer(2500)) {
                MonsterCard card = new MonsterCard(MonsterType.CYBERSE , user.getUsername() , cardName, cardNumber, MonstersDescriptions.leotron, 2000, 0,
                        MonsterPower.NONE, 4, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("The Calculator")){
            if(receiveMoneyFromCustomer(8000)) {
                MonsterCard card = new MonsterCard(MonsterType.THUNDER , user.getUsername() , cardName, cardNumber, MonstersDescriptions.theCalculator ,0, 0,
                        MonsterPower.THE_CALCULATOR, 2, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Alexandrite Dragon")){
            if(receiveMoneyFromCustomer(2600)) {
                MonsterCard card = new MonsterCard(MonsterType.DRAGON , user.getUsername() , cardName, cardNumber, MonstersDescriptions.alexandriteDragon, 2000, 100,
                        MonsterPower.NONE, 4, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Mirage Dragon")){
            if(receiveMoneyFromCustomer(2500)) {
                MonsterCard card = new MonsterCard(MonsterType.DRAGON , user.getUsername() , cardName, cardNumber, MonstersDescriptions.mirageDragon, 1600, 600,
                        MonsterPower.MIRAGE_DRAGON, 4, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Herald Of Creation")){
            if(receiveMoneyFromCustomer(2700)) {
                MonsterCard card = new MonsterCard(MonsterType.SPELL_CASTER , user.getUsername() , cardName, cardNumber, MonstersDescriptions.heraldOfTheCreation, 1800, 600,
                        MonsterPower.HERALD_OF_CREATION, 4, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Exploder Dragon")){
            if(receiveMoneyFromCustomer(1000)) {
                MonsterCard card = new MonsterCard(MonsterType.DRAGON , user.getUsername() , cardName, cardNumber, MonstersDescriptions.exploderDragon, 1000, 0,
                        MonsterPower.EXPLODER_DRAGON, 3, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Warrior Dai Grepher")){
            if(receiveMoneyFromCustomer(3400)) {
                MonsterCard card = new MonsterCard(MonsterType.WARRIOR , user.getUsername() , cardName, cardNumber, MonstersDescriptions.warriorDaiGrepher, 1700, 1600,
                        MonsterPower.NONE, 4, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Dark Blade")){
            if(receiveMoneyFromCustomer(3500)) {
                MonsterCard card = new MonsterCard(MonsterType.WARRIOR , user.getUsername(), cardName, cardNumber, MonstersDescriptions.darkBlade, 1800, 1500,
                        MonsterPower.NONE, 4, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Wattaildragon")){
            if(receiveMoneyFromCustomer(5800)) {
                MonsterCard card = new MonsterCard(MonsterType.DRAGON , user.getUsername() , cardName, cardNumber, MonstersDescriptions.wattailDragon, 2500, 1700,
                        MonsterPower.NONE, 6, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Terratiger, the Empowered Warrior")){
            if(receiveMoneyFromCustomer(3200)) {
                MonsterCard card = new MonsterCard(MonsterType.WARRIOR , user.getUsername() , cardName, cardNumber, MonstersDescriptions.terraTigerTheEmpoweredWarrior, 1800, 1200,
                        MonsterPower.TERRATIGER_THE_EMPOWERED_WARRIOR, 4, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("The Tricky")){
            if(receiveMoneyFromCustomer(4300)) {
                MonsterCard card = new MonsterCard(MonsterType.SPELL_CASTER , user.getUsername() , cardName, cardNumber, MonstersDescriptions.theTricky, 2000, 1200,
                        MonsterPower.THE_TRICKY, 5, Attribute.WIND);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Spiral Serpent")){
            if(receiveMoneyFromCustomer(11700)) {
                MonsterCard card = new MonsterCard(MonsterType.SEA_SERPENT , user.getUsername() , cardName, cardNumber, MonstersDescriptions.spiralSerpent, 2900, 2900,
                        MonsterPower.NONE, 8, Attribute.WATER);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Monster Reborn")){
            if(receiveMoneyFromCustomer(2500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.monsterReborn , SpellIcon.NORMAL , SpellEffect.MONSTER_REBORN);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Terraforming")){
            if(receiveMoneyFromCustomer(2500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.terrafoming , SpellIcon.NORMAL , SpellEffect.TERRAFORMING);
                user.addToCards(card);
            }

        }
        else if(cardName.equals("Pot of Greed")){
            if(receiveMoneyFromCustomer(2500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.potOfGreed , SpellIcon.NORMAL, SpellEffect.POT_OF_GREED);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Raigeki")){
            if(receiveMoneyFromCustomer(2500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.raigeki , SpellIcon.NORMAL , SpellEffect.RAIGEKI);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("change of Heart")){
            if(receiveMoneyFromCustomer(2500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.changeOfHearts , SpellIcon.NORMAL , SpellEffect.CHANGEOFHEART);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Sword of Revealing Light")){
            if(receiveMoneyFromCustomer(2500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.swordOfRevealingLight , SpellIcon.NORMAL , SpellEffect.SWORDS_OF_REVEALING_LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Harpie's Feather Duster")){
            if(receiveMoneyFromCustomer(2500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.harpies , SpellIcon.NORMAL , SpellEffect.HARPIES_FEATHER_DUSTER);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Dark Hole")){
            if(receiveMoneyFromCustomer(2500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.darkHole , SpellIcon.NORMAL , SpellEffect.DARK_HOLE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Supply Squad")){
            if(receiveMoneyFromCustomer(4000)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.supplySquad , SpellIcon.CONTINUOUS , SpellEffect.SUPPLY_SQUAD);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Spell Absorption")){
            if(receiveMoneyFromCustomer(4000)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.spellAbsorption , SpellIcon.CONTINUOUS , SpellEffect.SPELL_ABSORPTION);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Messenger of Peace")){
            if(receiveMoneyFromCustomer(4000)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.messengerOfPeace , SpellIcon.CONTINUOUS , SpellEffect.MESSENGER_OF_PEACE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Twin Twister")){
            if(receiveMoneyFromCustomer(3500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.twinTwister , SpellIcon.QUICK_PLAY , SpellEffect.TWIN_TWISTERS);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Mystical Space Typhoon")){
            if(receiveMoneyFromCustomer(3500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.mysticalSpaceTyphoon , SpellIcon.QUICK_PLAY , SpellEffect.MYSTICAL_SPACE_TYPHOON);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Ring of Defence")){
            if(receiveMoneyFromCustomer(3500)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.ringOfDefence , SpellIcon.QUICK_PLAY , SpellEffect.RING_OF_DEFENCE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Yami")){
            if(receiveMoneyFromCustomer(4300)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.yami , SpellIcon.FIELD , SpellEffect.YAMI);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Forest")){
            if(receiveMoneyFromCustomer(4300)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.forest , SpellIcon.FIELD , SpellEffect.FOREST);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Closed Forest")){
            if(receiveMoneyFromCustomer(4300)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.closedForest , SpellIcon.FIELD , SpellEffect.CLOSED_FOREST);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Umiiruka")){
            if(receiveMoneyFromCustomer(4300)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.umiiruka , SpellIcon.FIELD , SpellEffect.UMIIRUKA);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Sword of Dark Destruction")){
            if(receiveMoneyFromCustomer(4300)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.swordOfDestruction , SpellIcon.EQUIP , SpellEffect.SWORD_OF_DARK_DESTRUCTION);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Black Pendant")){
            if(receiveMoneyFromCustomer(4300)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.blackPendant , SpellIcon.EQUIP , SpellEffect.BLACK_PENDANT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("United We Stand")){
            if(receiveMoneyFromCustomer(4300)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.unitedWeStand , SpellIcon.EQUIP , SpellEffect.UNITED_WE_STAND);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Magnum Shield")){
            if(receiveMoneyFromCustomer(4300)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.magnumShield , SpellIcon.EQUIP , SpellEffect.MAGNUM_SHIELD);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Advanced Ritual Art")){
            if(receiveMoneyFromCustomer(3000)) {
                SpellCard card = new SpellCard(user.getUsername() , cardName, cardNumber, SpellsDescription.advancedRitualArt , SpellIcon.RITUAL , SpellEffect.ADVANCED_RITUAL_ART);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Trap Hole")){
            if(receiveMoneyFromCustomer(2000)) {
                TrapCard card = new TrapCard(user.getUsername() , cardName, cardNumber, TrapsDescription.trapHole , TrapIcon.NORMAL , TrapEffect.TRAP_HOLE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Mirror Force")){
            if(receiveMoneyFromCustomer(2000)) {
                TrapCard card = new TrapCard(user.getUsername() , cardName, cardNumber, TrapsDescription.mirrorForce , TrapIcon.NORMAL , TrapEffect.MIRROR_FORCE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Magic Cylinder")){
            if(receiveMoneyFromCustomer(2000)) {
                TrapCard card = new TrapCard(user.getUsername() , cardName, cardNumber, TrapsDescription.magicCylinder , TrapIcon.NORMAL , TrapEffect.MAGIC_CYLINDER);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Mind Crush")){
            if(receiveMoneyFromCustomer(2000)) {
                TrapCard card = new TrapCard(user.getUsername() , cardName, cardNumber, TrapsDescription.mindCrush , TrapIcon.NORMAL , TrapEffect.MIND_CRUSH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Torrential Tribute")){
            if(receiveMoneyFromCustomer(2000)) {
                TrapCard card = new TrapCard(user.getUsername() , cardName, cardNumber, TrapsDescription.torrentialTribute , TrapIcon.NORMAL , TrapEffect.TORRENTIAL_TRIBUTE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Time Seal")){
            if(receiveMoneyFromCustomer(2000)) {
                TrapCard card = new TrapCard(user.getUsername() , cardName, cardNumber, TrapsDescription.timeSeal , TrapIcon.NORMAL , TrapEffect.TIME_SEAL);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Negate Attack")){
            if(receiveMoneyFromCustomer(3000)) {
                TrapCard card = new TrapCard(user.getUsername() , cardName, cardNumber, TrapsDescription.negateAttack , TrapIcon.COUNTER , TrapEffect.NEGATE_ATTACK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Solemn Warning")){
            if(receiveMoneyFromCustomer(3000)) {
                TrapCard card = new TrapCard(user.getUsername() , cardName, cardNumber, TrapsDescription.solemnWarning , TrapIcon.COUNTER , TrapEffect.SOLEMN_WARNING);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Magic Jammer")){
            if(receiveMoneyFromCustomer(3000)) {
                TrapCard card = new TrapCard(user.getUsername() , cardName, cardNumber, TrapsDescription.magicJammer , TrapIcon.COUNTER , TrapEffect.MAGIC_JAMMER);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Call of The Haunted")){
            if(receiveMoneyFromCustomer(3500)) {
                TrapCard card = new TrapCard(user.getUsername() , cardName, cardNumber, TrapsDescription.callOfTheHaunted , TrapIcon.CONTINUOUS , TrapEffect.CALL_OF_THE_HAUNTED);
                user.addToCards(card);
            }
        } else if(Card.isThisCardNameValid(cardName)){
            try{
                for (MonsterCard monsterCard : CreateNewCard.newMonsters){
                    if(monsterCard.getName().equals(cardName)){
                        user.addToCards(Card.getCardByName(Card.getAllCards(), cardName));
                        user.changeBalance(-monsterCard.getPrice());
                    }
                }
                for (SpellCard spellCard : CreateNewCard.newSpells){
                    if(spellCard.getName().equals(cardName)){
                        user.addToCards(Card.getCardByName(Card.getAllCards(), cardName));
                        user.changeBalance(-spellCard.getPrice());
                    }
                }
                for (TrapCard trapCard : CreateNewCard.newTraps){
                    if(trapCard.getName().equals(cardName)){
                        user.addToCards(Card.getCardByName(Card.getAllCards(), cardName));
                        user.changeBalance(-trapCard.getPrice());
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        } else {
            view.cardNotFound();
        }
    }

    public boolean checkUserMoney(int price) {
        if (user.getBalance() < price) {
            view.notEnoughMoney();
            return false;
        } else return true;
    }

    private boolean receiveMoneyFromCustomer(int price) {
        if (checkUserMoney(price)) {
            user.changeBalance(-1 * price);
            return true;
        } else return false;
    }

    public void showAllCards() {
        view.showAllCards();
    }

    private Matcher[] getCheatMatchers(String command){
        Pattern patternForIncreaseMoney = Pattern.compile("^increase --money (\\d+)$");
        Pattern patternForIncreaseMoney2 = Pattern.compile("^increase -m (\\d+)$");
        Matcher[] cheatMatchers = new Matcher[2];
        cheatMatchers[0] = patternForIncreaseMoney.matcher(command);
        cheatMatchers[1] = patternForIncreaseMoney2.matcher(command);
        return cheatMatchers;
    }
}
