package view;

import controller.ShopController;
import model.MonstersDescriptions;

import java.util.Scanner;

public class ShopView {
    Scanner scanner = new Scanner(System.in);
    ShopController controller;

    public ShopView (ShopController controller){
        this.controller = controller;
    }

    public void run(){
        String command;
        while (true){

            command = scanner.nextLine().trim();
            if(command.equals("menu exit")) break;
            controller.processCommand(command);
        }
    }

    public void notEnoughMoney(){
        System.out.println("not enough money");
    }

    public void cardNotFound(){
        System.out.println("there is no card with this name");
    }

    public void showAllCards(){
        System.out.println("Command Knight " + ": " + MonstersDescriptions.commandKnight);
        System.out.println("Battle OX " + ": " + MonstersDescriptions.battleOX);
        System.out.println("Axe Raider " + ": " + MonstersDescriptions.axeRaider);
        System.out.println("Yomi Ship " + ": " + MonstersDescriptions.yomiShip);
        System.out.println("Horn Imp " + ": " + MonstersDescriptions.hornImp);
        System.out.println("Silver Fang " + ": " + MonstersDescriptions.silverFang);
        System.out.println("Suijin " + ": " + MonstersDescriptions.suijin);
        System.out.println("Fireyarou " + ": " + MonstersDescriptions.fireYarou);
        System.out.println("Curtain of the dark ones " + ": " + MonstersDescriptions.curtainOfTheDarkOnes);
        System.out.println("Feral Imp " + ": " + MonstersDescriptions.feralImp);
        System.out.println("Dark magician " + ": " + MonstersDescriptions.darkMagician);
        System.out.println("Wattkid " + ": " + MonstersDescriptions.wattkid);
        System.out.println("Baby dragon " + ": " + MonstersDescriptions.babyDragon);
        System.out.println("Hero of the east " + ": " + MonstersDescriptions.heroOfTheEast);
        System.out.println("Battle warrior " + ": " + MonstersDescriptions.battleWarrior);
        System.out.println("Crawling dragon " + ": " + MonstersDescriptions.crawlingDragon);
        System.out.println("Flame manipulator " + ": " + MonstersDescriptions.flameManipulator);
        System.out.println("Blue-Eyes white dragon " + ": " + MonstersDescriptions.blueEyesDragon);
        System.out.println("Crab Turtle " + ": " + MonstersDescriptions.crabTurtle);
        System.out.println("Skull Guardian " + ": " + MonstersDescriptions.skullGuardian);
        System.out.println("Slot Machine " + ": " + MonstersDescriptions.slotMachine);
        System.out.println("Haniwa " + ": " + MonstersDescriptions.haniwa);
        System.out.println("Man-Eater Bug " + ": " + MonstersDescriptions.manEaterBug);
        System.out.println("Gate Guardian " + ": " + MonstersDescriptions.gateGuardian);
        System.out.println("Scanner " + ": " + MonstersDescriptions.scanner);
        System.out.println("Bitron " + ": " + MonstersDescriptions.bitron);
        System.out.println("Marshmallon " + ": " + MonstersDescriptions.marshmallon);
        System.out.println("Beast King Barbaros " + ": " + MonstersDescriptions.beastKing);
        System.out.println("Texchanger " + ": " + MonstersDescriptions.texChanger);
        System.out.println("Leotron " + ": " + MonstersDescriptions.leotron);
        System.out.println("The Calculator " + ": " + MonstersDescriptions.theCalculator);
        System.out.println("Alexandrite Dragon " + ": " + MonstersDescriptions.alexandriteDragon);
        System.out.println("Mirage Dragon " + ": " + MonstersDescriptions.mirageDragon);
        System.out.println("Herald of Creation " + ": " + MonstersDescriptions.heraldOfTheCreation);
        System.out.println("Exploder Dragon " + ": " + MonstersDescriptions.exploderDragon);
        System.out.println("Warrior Dai Grepher " + ": " + MonstersDescriptions.warriorDaiGrepher);
        System.out.println("Dark Blade " + ": " + MonstersDescriptions.darkBlade);
        System.out.println("Wattaildragon " + ": " + MonstersDescriptions.wattailDragon);
        System.out.println("Terratiger, the Empowered Warrior " + ": " + MonstersDescriptions.terraTigerTheEmpoweredWarrior);
        System.out.println("The Tricky " + ": " + MonstersDescriptions.theTricky);
        System.out.println("Spiral Serpent " +  ": " +MonstersDescriptions.spiralSerpent);
    }

}
