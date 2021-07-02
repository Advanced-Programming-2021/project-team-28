package org.view;

import org.controller.ShopController;
import org.model.Card;
import org.model.MonstersDescriptions;
import org.model.SpellsDescription;
import org.model.TrapsDescription;

import java.util.Scanner;

public class ShopView {
    Scanner scanner = ScannerInstance.getInstance().getScanner();
    ShopController controller;

    public ShopView (ShopController controller){
        this.controller = controller;
    }

    public void run() throws Exception {
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

    public void invalidCommand(){
        System.out.println("invalid command");
    }

    public void menuShowCurrent(){
        System.out.println("Shop Menu");
    }

    public void impossibleMenuNavigation() {
        System.out.println("menu navigation is not possible");
    }

    public void showCard(Card card){
        System.out.println(card);
    }

    public void showAllCards(){
        System.out.println("Advanced Ritual Art " + ": " + SpellsDescription.advancedRitualArt);
        System.out.println("Alexandrite Dragon " + ": " + MonstersDescriptions.alexandriteDragon);
        System.out.println("Axe Raider " + ": " + MonstersDescriptions.axeRaider);
        System.out.println("Baby dragon " + ": " + MonstersDescriptions.babyDragon);
        System.out.println("Battle OX " + ": " + MonstersDescriptions.battleOX);
        System.out.println("Battle warrior " + ": " + MonstersDescriptions.battleWarrior);
        System.out.println("Beast King Barbaros " + ": " + MonstersDescriptions.beastKing);
        System.out.println("Bitron " + ": " + MonstersDescriptions.bitron);
        System.out.println("Black Pendant " + ": " + SpellsDescription.blackPendant);
        System.out.println("Blue-Eyes white dragon " + ": " + MonstersDescriptions.blueEyesDragon);
        System.out.println("Call of The Haunted " + ": " + TrapsDescription.callOfTheHaunted);
        System.out.println("Change of Hearts " + ": " + SpellsDescription.changeOfHearts);
        System.out.println("Closed Forest " + ": " + SpellsDescription.closedForest);
        System.out.println("Command Knight " + ": " + MonstersDescriptions.commandKnight);
        System.out.println("Crab Turtle " + ": " + MonstersDescriptions.crabTurtle);
        System.out.println("Crawling dragon " + ": " + MonstersDescriptions.crawlingDragon);
        System.out.println("Curtain of the dark ones " + ": " + MonstersDescriptions.curtainOfTheDarkOnes);
        System.out.println("Dark Blade " + ": " + MonstersDescriptions.darkBlade);
        System.out.println("Dark Hole " + ": " + SpellsDescription.darkHole);
        System.out.println("Dark magician " + ": " + MonstersDescriptions.darkMagician);
        System.out.println("Exploder Dragon " + ": " + MonstersDescriptions.exploderDragon);
        System.out.println("Feral Imp " + ": " + MonstersDescriptions.feralImp);
        System.out.println("Fireyarou " + ": " + MonstersDescriptions.fireYarou);
        System.out.println("Flame manipulator " + ": " + MonstersDescriptions.flameManipulator);
        System.out.println("Forest " + ": " + SpellsDescription.forest);
        System.out.println("Gate Guardian " + ": " + MonstersDescriptions.gateGuardian);
        System.out.println("Haniwa " + ": " + MonstersDescriptions.haniwa);
        System.out.println("Harpie's Feather Duster " + ": " + SpellsDescription.harpies);
        System.out.println("Herald of Creation " + ": " + MonstersDescriptions.heraldOfTheCreation);
        System.out.println("Hero of the east " + ": " + MonstersDescriptions.heroOfTheEast);
        System.out.println("Horn Imp " + ": " + MonstersDescriptions.hornImp);
        System.out.println("Leotron " + ": " + MonstersDescriptions.leotron);
        System.out.println("Magic Cylinder " + ": " + TrapsDescription.magicCylinder);
        System.out.println("Magic jammer " + ": " + TrapsDescription.magicJammer);
        System.out.println("Magnum Shield " + ": " + SpellsDescription.magnumShield);
        System.out.println("Man-Eater Bug " + ": " + MonstersDescriptions.manEaterBug);
        System.out.println("Marshmallon " + ": " + MonstersDescriptions.marshmallon);
        System.out.println("Messenger of Peace " + ": " + SpellsDescription.messengerOfPeace);
        System.out.println("Mind Crush " + ": " + TrapsDescription.mindCrush);
        System.out.println("Mirage Dragon " + ": " + MonstersDescriptions.mirageDragon);
        System.out.println("Mirror Force " + ": " + TrapsDescription.mirrorForce);
        System.out.println("Monster Reborn " + ": " + SpellsDescription.monsterReborn);
        System.out.println("Mystical Space Typhoon " + ": " + SpellsDescription.mysticalSpaceTyphoon);
        System.out.println("Negate Attack " + ": " + TrapsDescription.negateAttack);
        System.out.println("Pot of Greed " + ": " + SpellsDescription.potOfGreed);
        System.out.println("Raigeki " + ": " + SpellsDescription.raigeki);
        System.out.println("Ring of Defence " + ": " + SpellsDescription.ringOfDefence);
        System.out.println("Scanner " + ": " + MonstersDescriptions.scanner);
        System.out.println("Silver Fang " + ": " + MonstersDescriptions.silverFang);
        System.out.println("Skull Guardian " + ": " + MonstersDescriptions.skullGuardian);
        System.out.println("Slot Machine " + ": " + MonstersDescriptions.slotMachine);
        System.out.println("Solemn Warning " + ": " + TrapsDescription.solemnWarning);
        System.out.println("Spell Absorption " + ": " + SpellsDescription.spellAbsorption);
        System.out.println("Spiral Serpent " +  ": " +MonstersDescriptions.spiralSerpent);
        System.out.println("Suijin " + ": " + MonstersDescriptions.suijin);
        System.out.println("Supply Squad " + ": " + SpellsDescription.supplySquad);
        System.out.println("Sword of dark Destruction " + ": " + SpellsDescription.swordOfDestruction);
        System.out.println("Sword of Revealing Light " + ": " + SpellsDescription.swordOfRevealingLight);
        System.out.println("Terraforming " + ": " + SpellsDescription.terrafoming);
        System.out.println("Terratiger, the Empowered Warrior " + ": " + MonstersDescriptions.terraTigerTheEmpoweredWarrior);
        System.out.println("Texchanger " + ": " + MonstersDescriptions.texChanger);
        System.out.println("The Calculator " + ": " + MonstersDescriptions.theCalculator);
        System.out.println("The Tricky " + ": " + MonstersDescriptions.theTricky);
        System.out.println("Time Seal " + ": " + TrapsDescription.timeSeal);
        System.out.println("Torrential Tribute " + ": " + TrapsDescription.torrentialTribute);
        System.out.println("Trap Hole " + ": " + TrapsDescription.trapHole);
        System.out.println("Twin Twister " + ": " + SpellsDescription.twinTwister);
        System.out.println("Umiiruka " + ": " + SpellsDescription.umiiruka);
        System.out.println("United We Stand " + ": " + SpellsDescription.unitedWeStand);
        System.out.println("Vanity's Emptiness " + ": " + TrapsDescription.vanitiesEmptiness);
        System.out.println("Wall of Revealing Light " + ": " + TrapsDescription.wallOfRevealingLight);
        System.out.println("Warrior Dai Grepher " + ": " + MonstersDescriptions.warriorDaiGrepher);
        System.out.println("Wattaildragon " + ": " + MonstersDescriptions.wattailDragon);
        System.out.println("Wattkid " + ": " + MonstersDescriptions.wattkid);
        System.out.println("Yami " + ": " + SpellsDescription.yami);
        System.out.println("Yomi Ship " + ": " + MonstersDescriptions.yomiShip);
    }

    public void cheatActivated() {
        System.out.println("Cheat activated");
    }
}
