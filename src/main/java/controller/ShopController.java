package controller;

import model.*;
import view.ShopView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopController {

    User user;
    ShopView view = new ShopView(this);

    public void processCommand(String command){
        if(command.startsWith("shop buy")){
            Matcher matcher = getCommandMatcher(command , "^shop buy (.+)$");
            sellCard(matcher.group(1));
        }
        else if(command.equals("shop show all")){
            showAllCards();
        }
    }

    public static Matcher getCommandMatcher(String command , String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        if (matcher.find())
            return matcher;
        else
            return null;
    }

    private void sellCard(String cardName){
        if(cardName.equals("Command knight")){
            if(receiveMoneyFromCustomer(2100)) {
                MonsterCard card = new MonsterCard("Command knight", "", MonstersDescriptions.commandKnight, 1000, 1000,
                        MonsterPower.COMMAND_KNIGHT, 4, Attribute.FIRE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Battle Ox")){
            if(receiveMoneyFromCustomer(2900)) {
                MonsterCard card = new MonsterCard("Battle Ox", "", MonstersDescriptions.battleOX, 1700, 1000,
                        MonsterPower.NONE, 4, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Axe Raider")){
            if(receiveMoneyFromCustomer(3100)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.axeRaider, 1700, 1150,
                        MonsterPower.NONE, 4, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Horn Imp")){
            if(receiveMoneyFromCustomer(2500)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.hornImp, 1300, 1000,
                        MonsterPower.NONE, 4, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Silver Fang")){
            if(receiveMoneyFromCustomer(1700)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.silverFang, 1200, 800,
                        MonsterPower.NONE, 3, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Yomi Ship")){
            if(receiveMoneyFromCustomer(1700)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.yomiShip, 800, 1400,
                        MonsterPower.YOMI_SHIP, 3, Attribute.WATER);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Suijin")){
            if(receiveMoneyFromCustomer(8700)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.suijin, 2500, 2400,
                        MonsterPower.SUIJIN, 7, Attribute.WATER);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Fireyarou")){
            if(receiveMoneyFromCustomer(2500)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.fireYarou, 1300, 1000,
                        MonsterPower.NONE, 4, Attribute.FIRE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Curtain of Dark Ones")){
            if(receiveMoneyFromCustomer(700)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.curtainOfTheDarkOnes, 600, 500,
                        MonsterPower.NONE, 2, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Feral Imp")){
            if(receiveMoneyFromCustomer(2800)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.feralImp, 1400, 1300,
                        MonsterPower.NONE, 4, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Dark Magician")){
            if(receiveMoneyFromCustomer(8300)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.darkMagician, 2500, 2100,
                        MonsterPower.NONE, 7, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Wattkid")){
            if(receiveMoneyFromCustomer(1300)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.wattkid, 1000, 500,
                        MonsterPower.NONE, 3, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Baby Dragon")){
            if(receiveMoneyFromCustomer(1600)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.babyDragon, 1200, 700,
                        MonsterPower.NONE, 3, Attribute.WIND);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Hero of the East")){
            if(receiveMoneyFromCustomer(1700)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.heroOfTheEast, 1100, 1000,
                        MonsterPower.NONE, 3, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Battle Warrior")){
            if(receiveMoneyFromCustomer(1300)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.battleWarrior, 700, 1000,
                        MonsterPower.NONE, 3, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Crawling dragon")){
            if(receiveMoneyFromCustomer(3900)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.crawlingDragon, 1600, 1400,
                        MonsterPower.NONE, 5, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Flame Manipulator")){
            if(receiveMoneyFromCustomer(1500)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.flameManipulator, 900, 100,
                        MonsterPower.NONE, 3, Attribute.FIRE);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Blue-Eyes White Dragon")){
            if(receiveMoneyFromCustomer(11300)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.blueEyesDragon, 3000, 2500,
                        MonsterPower.NONE, 8, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Crab Turtle")){
            if(receiveMoneyFromCustomer(10200)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.crabTurtle, 2550, 2500,
                        MonsterPower.CRAB_TURTLE, 7, Attribute.WATER);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Skull Guardian")){
            if(receiveMoneyFromCustomer(7900)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.skullGuardian, 2050, 2500,
                        MonsterPower.SKULL_GUARDIAN, 7, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Slot Machine")){
            if(receiveMoneyFromCustomer(7500)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.slotMachine, 2000, 2300,
                        MonsterPower.NONE, 7, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Haniwa")){
            if(receiveMoneyFromCustomer(600)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.haniwa, 500, 500,
                        MonsterPower.NONE, 2, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Man-Eater Bug")){
            if(receiveMoneyFromCustomer(600)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.manEaterBug, 450, 600,
                        MonsterPower.MAN_EATER_BUG, 2, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Gate Guardian")){
            if(receiveMoneyFromCustomer(20000)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.gateGuardian, 3750, 3400,
                        MonsterPower.GATE_GUARDIAN, 11, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Scanner")){
            if(receiveMoneyFromCustomer(8000)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.scanner, 0, 0,
                        MonsterPower.SCANNER, 1, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Bitron")){
            if(receiveMoneyFromCustomer(1000)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.bitron, 200, 2000,
                        MonsterPower.NONE, 2, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Marshmallon")){
            if(receiveMoneyFromCustomer(700)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.marshmallon, 300, 500,
                        MonsterPower.MARSHMALLON, 3, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Beast King Barbaros")){
            if(receiveMoneyFromCustomer(9200)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.beastKing, 3000, 1200,
                        MonsterPower.BEAST_KING_BARBAROS, 8, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Texchanger")){
            if(receiveMoneyFromCustomer(200)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.texChanger, 100, 100,
                        MonsterPower.TEXCHANGER, 1, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Leotron")){
            if(receiveMoneyFromCustomer(2500)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.leotron, 2000, 0,
                        MonsterPower.NONE, 4, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("The Calculator")){
            if(receiveMoneyFromCustomer(8000)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.theCalculator ,0, 0,
                        MonsterPower.THE_CALCULATOR, 2, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Alexandrite Dragon")){
            if(receiveMoneyFromCustomer(2600)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.alexandriteDragon, 2000, 100,
                        MonsterPower.NONE, 4, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Mirage Dragon")){
            if(receiveMoneyFromCustomer(2500)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.mirageDragon, 1600, 600,
                        MonsterPower.MIRAGE_DRAGON, 4, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Herald of Creation")){
            if(receiveMoneyFromCustomer(2700)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.heraldOfTheCreation, 1800, 600,
                        MonsterPower.HERALD_OF_CREATION, 4, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Exploder Dragon")){
            if(receiveMoneyFromCustomer(1000)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.exploderDragon, 1000, 0,
                        MonsterPower.EXPLODER_DRAGON, 3, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Warrior Dai Grepher")){
            if(receiveMoneyFromCustomer(3400)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.warriorDaiGrepher, 1700, 1600,
                        MonsterPower.NONE, 4, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Dark Blade")){
            if(receiveMoneyFromCustomer(3500)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.darkBlade, 1800, 1500,
                        MonsterPower.NONE, 4, Attribute.DARK);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Wattaildragon")){
            if(receiveMoneyFromCustomer(5800)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.wattailDragon, 2500, 1700,
                        MonsterPower.NONE, 6, Attribute.LIGHT);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Terratiger, the Empowered Warrior")){
            if(receiveMoneyFromCustomer(3200)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.terraTigerTheEmpoweredWarrior, 1800, 1200,
                        MonsterPower.TERRATIGER_THE_EMPOWERED_WARRIOR, 4, Attribute.EARTH);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("The Tricky")){
            if(receiveMoneyFromCustomer(4300)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.theTricky, 2000, 1200,
                        MonsterPower.THE_TRICKY, 5, Attribute.WIND);
                user.addToCards(card);
            }
        }
        else if(cardName.equals("Spiral Serpent")){
            if(receiveMoneyFromCustomer(11700)) {
                MonsterCard card = new MonsterCard(cardName, "", MonstersDescriptions.spiralSerpent, 2900, 2900,
                        MonsterPower.NONE, 8, Attribute.WATER);
                user.addToCards(card);
            }
        }
        else view.cardNotFound();
    }

    public boolean checkUserMoney (int price){
        if(user.getBalance() < price) {
            view.notEnoughMoney();
            return false;
        }
        else return true;
    }

    private boolean receiveMoneyFromCustomer(int price){
        if(checkUserMoney(price)){
            user.changeBalance(-1 * price);
            return true;
        }
        else return false;
    }

    public void showAllCards(){
        view.showAllCards();
    }
}
