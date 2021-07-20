package org.controller;

import org.MainClient;
import org.model.*;
import org.view.ShopView;

import java.io.IOException;
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
            buyCard(matcher.group(1));
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


    public void buyCard(String cardName){
        try {
            String result = (String) LoginMenuController.sendAndReceive("shop buy --token " + MainClient.getToken() + " --card " + cardName);
            if(result.startsWith("Success")) {
                user = (User) LoginMenuController.sendAndReceive("get user " + MainClient.getToken());
            } else {
                view.showError(result);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
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
