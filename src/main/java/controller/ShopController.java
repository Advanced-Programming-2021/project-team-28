package controller;

import model.*;
import view.ShopView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopController {

    User user;
    ShopView view = new ShopView(this);

    public ShopController(User user){
        this.user = user;
    }

    public void run() {
        this.view.run();
    }

    public void processCommand(String command){
        if(command.startsWith("shop buy ")){
            Matcher matcher = getCommandMatcher(command , "^shop buy (.+)$");
            sellCard(matcher.group(1));
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
            MonsterCard card = new MonsterCard("Command knight" , "" , "",1000 , 1000 ,
                    MonsterPower.COMMAND_KNIGHT , 4 , Attribute.FIRE);
            user.addToCards(card);
        }
        else if(cardName.equals("Battle Ox")){
            MonsterCard card = new MonsterCard("Battle Ox" , "" , "",1700 , 1000 ,
                    MonsterPower.NONE , 4 , Attribute.EARTH);
            user.addToCards(card);
        }
        else if(cardName.equals("Axe Raider")){
            MonsterCard card = new MonsterCard(cardName , "" , "",1700 , 1150 ,
                    MonsterPower.NONE , 4 , Attribute.EARTH);
            user.addToCards(card);
        }
        else if(cardName.equals("Horn Imp")){
            MonsterCard card = new MonsterCard( cardName , "" , "",1300 , 1000 ,
                    MonsterPower.NONE , 4 , Attribute.DARK);
            user.addToCards(card);
        }
        else if(cardName.equals("Silver Fang")){
            MonsterCard card = new MonsterCard(cardName , "" , "",1200 , 800 ,
                    MonsterPower.NONE , 3 , Attribute.EARTH);
            user.addToCards(card);
        }
        else if(cardName.equals("Yomi Ship")){
            MonsterCard card = new MonsterCard( cardName , "" , "",800 , 1400 ,
                    MonsterPower.YOMI_SHIP , 3 , Attribute.WATER);
            user.addToCards(card);
        }
        else if(cardName.equals("Suijin")){
            MonsterCard card = new MonsterCard( cardName , "" , "",2500 , 2400 ,
                    MonsterPower.SUIJIN , 7 , Attribute.WATER);
            user.addToCards(card);
        }
        else if(cardName.equals("Fireyarou")){
            MonsterCard card = new MonsterCard( cardName , "" , "",1300 , 1000 ,
                    MonsterPower.NONE , 4 , Attribute.FIRE);
            user.addToCards(card);
        }
        else if(cardName.equals("Curtain of Dark Ones")){
            MonsterCard card = new MonsterCard( cardName , "" , "",600 , 500 ,
                    MonsterPower.NONE , 2 , Attribute.DARK);
            user.addToCards(card);
        }
        else if(cardName.equals("Feral Imp")){
            MonsterCard card = new MonsterCard( cardName , "" , "",1400 , 1300 ,
                    MonsterPower.NONE , 4 , Attribute.DARK);
            user.addToCards(card);
        }
        else if(cardName.equals("Dark Magician")){
            MonsterCard card = new MonsterCard( cardName , "" , "",2500 , 2100 ,
                    MonsterPower.NONE , 7 , Attribute.DARK);
            user.addToCards(card);
        }
        else if(cardName.equals("Wattkid")){
            MonsterCard card = new MonsterCard( cardName , "" , "",1000 , 500 ,
                    MonsterPower.NONE , 3 , Attribute.LIGHT);
            user.addToCards(card);
        }
        else if(cardName.equals("Baby Dragon")){
            MonsterCard card = new MonsterCard( cardName , "" , "",1200 , 700 ,
                    MonsterPower.NONE , 3 , Attribute.WIND);
            user.addToCards(card);
        }
        else if(cardName.equals("Hero of the East")){
            MonsterCard card = new MonsterCard( cardName , "" , "",1100 , 1000 ,
                    MonsterPower.NONE , 3 , Attribute.EARTH);
            user.addToCards(card);
        }
        else if(cardName.equals("Battle Warrior")){
            MonsterCard card = new MonsterCard( cardName , "" , "",700 , 1000 ,
                    MonsterPower.NONE , 3 , Attribute.EARTH);
            user.addToCards(card);
        }
        else if(cardName.equals("Crawling dragon")){
            MonsterCard card = new MonsterCard( cardName , "" , "",1600 , 1400 ,
                    MonsterPower.NONE , 5 , Attribute.EARTH);
            user.addToCards(card);
        }
        else if(cardName.equals("Flame Manipulator")){
            MonsterCard card = new MonsterCard( cardName , "" , "",900 , 100 ,
                    MonsterPower.NONE , 3 , Attribute.FIRE);
            user.addToCards(card);
        }
        else if(cardName.equals("Blue-Eyes White Dragon")){
            MonsterCard card = new MonsterCard( cardName , "" , "",3000 , 2500 ,
                    MonsterPower.NONE , 8 , Attribute.LIGHT);
            user.addToCards(card);
        }
        else if(cardName.equals("Crab Turtle")){
            MonsterCard card = new MonsterCard( cardName , "" , "",2550 , 2500 ,
                    MonsterPower.CRAB_TURTLE , 7 , Attribute.WATER);
            user.addToCards(card);
        }
        else if(cardName.equals("Skull Guardian")){
            MonsterCard card = new MonsterCard( cardName , "" , "",2050 , 2500 ,
                    MonsterPower.SKULL_GUARDIAN , 7 , Attribute.LIGHT);
            user.addToCards(card);
        }
        else if(cardName.equals("Slot Machine")){
            MonsterCard card = new MonsterCard( cardName , "" , "",2000 , 2300 ,
                    MonsterPower.NONE , 7 , Attribute.DARK);
            user.addToCards(card);
        }
        else if(cardName.equals("Haniwa")){
            MonsterCard card = new MonsterCard( cardName , "" , "",500 , 500 ,
                    MonsterPower.NONE , 2 , Attribute.EARTH);
            user.addToCards(card);
        }
        else if(cardName.equals("Man-Eater Bug")){
            MonsterCard card = new MonsterCard( cardName , "" , "",450 , 600 ,
                    MonsterPower.MAN_EATER_BUG , 2 , Attribute.EARTH);
            user.addToCards(card);
        }
        else if(cardName.equals("Gate Guardian")){
            MonsterCard card = new MonsterCard( cardName , "" , "",3750 , 3400 ,
                    MonsterPower.GATE_GUARDIAN , 11 , Attribute.DARK);
            user.addToCards(card);
        }
        else if(cardName.equals("Scanner")){
            MonsterCard card = new MonsterCard( cardName , "" , "",0 , 0 ,
                    MonsterPower.SCANNER , 1 , Attribute.LIGHT);
            user.addToCards(card);
        }
        else if(cardName.equals("Bitron")){
            MonsterCard card = new MonsterCard( cardName , "" , "",200 , 2000 ,
                    MonsterPower.NONE , 2 , Attribute.DARK);
            user.addToCards(card);
        }
        else if(cardName.equals("Marshmallon")){
            MonsterCard card = new MonsterCard( cardName , "" , "",300 , 500 ,
                    MonsterPower.MARSHMALLON , 3 , Attribute.LIGHT);
            user.addToCards(card);
        }
        else if(cardName.equals("Beast King Barbaros")){
            MonsterCard card = new MonsterCard( cardName , "" , "",3000 , 1200 ,
                    MonsterPower.BEAST_KING_BARBAROS , 8 , Attribute.EARTH);
            user.addToCards(card);
        }
        else if(cardName.equals("Texchanger")){
            MonsterCard card = new MonsterCard( cardName , "" , "",100 , 100 ,
                    MonsterPower.TEXCHANGER , 1 , Attribute.DARK);
            user.addToCards(card);
        }
        else if(cardName.equals("Leotron")){
            MonsterCard card = new MonsterCard( cardName , "" , "",2000 , 0 ,
                    MonsterPower.NONE , 4 , Attribute.EARTH);
            user.addToCards(card);
        }
    }
}
