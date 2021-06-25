package controller;

import model.*;
import view.DeckMenuView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DeckMenuController {
    private User user;
    DeckMenuView view = new DeckMenuView(this);

    public DeckMenuController(User user) {
        this.user = user;
    }

    public void run() {
        view.run();
    }

    public MenuEnum processCommand (String command){
        String cardName = "cardName";
        String deckName = "deckName";
        Matcher[] commandMatchers = getCommandMatchers(command);
        if (commandMatchers[0].find()) {
            controlCreateCommand(commandMatchers[0].group(1));
        } else if (commandMatchers[1].find()) {
            controlDeleteCommand(commandMatchers[1].group(1));
        } else if (commandMatchers[2].find()) {
            controlSetActivateCommand(commandMatchers[2].group(1));
        } else if (commandMatchers[3].find() || commandMatchers[4].find()) {
            view.print(user.decksArrayListToString());
        } else if (commandMatchers[5].find() || commandMatchers[6].find()) {
            view.print(Card.cardsArrayListToString(user.getAllCards()));
        }  else if (commandMatchers[7].find()) {
            return MenuEnum.BACK;
        } else if (commandMatchers[8].find()) {
            view.showCurrentMenu();
        } else {
            for(int i=9; i<21; i++){
                if(commandMatchers[i].find()){
                    controlAddCardCommand(commandMatchers[i].group(cardName), commandMatchers[i].group(deckName), true);
                    return MenuEnum.CONTINUE;
                }
            }
            for (int i=21; i<25; i++){
                if(commandMatchers[i].find()){
                    controlAddCardCommand(commandMatchers[i].group(cardName), commandMatchers[i].group(deckName), false);
                    return MenuEnum.CONTINUE;
                }
            }
            for (int i=25; i<37; i++){
                if(commandMatchers[i].find()){
                    controlRemoveCardCommand(commandMatchers[i].group(cardName), commandMatchers[i].group(deckName), true);
                    return MenuEnum.CONTINUE;
                }
            }
            for (int i=37; i<41; i++){
                if(commandMatchers[i].find()){
                    controlRemoveCardCommand(commandMatchers[i].group(cardName), commandMatchers[i].group(deckName), false);
                    return MenuEnum.CONTINUE;
                }
            }
            for (int i=41; i<45; i++){
                if(commandMatchers[i].find()){
                    controlShowOneDeckCommand(commandMatchers[i].group(deckName), true);
                    return MenuEnum.CONTINUE;
                }
            }
            for (int i=45; i<47; i++){
                if(commandMatchers[i].find()){
                    controlShowOneDeckCommand(commandMatchers[i].group(deckName), false);
                    return MenuEnum.CONTINUE;
                }
            }
            if(commandMatchers[47].find()){
                view.impossibleMenuNavigation();
            } else {
                view.invalidCommand();
            }
        }
        return MenuEnum.CONTINUE;
    }

    private void controlShowOneDeckCommand(String deckName, boolean isSideDeck) {
        if (!user.doesUserHaveThisDeck(deckName)) {
            view.deckDoesNotExist(deckName);
        } else {
            view.print(user.getDeckByDeckName(deckName).showDeck(isSideDeck));
        }
    }

    private void controlCreateCommand(String deckName) {
        if (user.doesUserHaveThisDeck(deckName)) {
            view.thisDeckAlreadyExists(deckName);
        } else {
            new Deck(deckName, user.getUsername());
            view.deckCreated();
        }
    }

    private void controlDeleteCommand(String deckName) {
        if (!user.doesUserHaveThisDeck(deckName)) {
            view.deckDoesNotExist(deckName);
        } else {
            if(user.getDeckByDeckName(deckName).equals(user.getActiveDeck())){
                user.setActiveDeck(null);
            }
            user.getDecks().remove(user.getDeckByDeckName(deckName));
            view.deckDeleted();
        }
    }

    private void controlSetActivateCommand(String deckName) {
        if (!user.doesUserHaveThisDeck(deckName)) {
            view.deckDoesNotExist(deckName);
        } else {
            user.setActiveDeck(user.getDeckByDeckName(deckName));
            view.deckActivated();
        }
    }

    private void controlAddCardCommand(String cardName, String deckName, boolean isToSideDeck) {
        if (user.numOfCardsWithThisName(cardName) == 0) {
            view.cardDoesNotExist(cardName);
            return;
        } else if (!user.doesUserHaveThisDeck(deckName)) {
            view.deckDoesNotExist(deckName);
            return;
        }
        Deck deck = user.getDeckByDeckName(deckName);
        if (!user.isAmountOfThisCardEnough(deck, cardName)) {
            view.cardDoesNotExist(cardName);
        } else if (!isToSideDeck && deck.isMainDeckFull()) {
            view.mainOrSideDeckIsFull(false);
        } else if (isToSideDeck && deck.isSideDeckFull()) {
            view.mainOrSideDeckIsFull(true);
        } else if (deck.doesDeckHaveThreeCardsFromThisTypeOfCard(cardName)) {
            view.threeSimilarCardInADeck(cardName, deckName);
        } else {
            deck.addCardToDeck(cardName, isToSideDeck);
            view.cardAddedToDeck();
        }
    }

    private void controlRemoveCardCommand(String cardName, String deckName, boolean isFromSideDeck) {
        if (!user.doesUserHaveThisDeck(deckName)) {
            view.deckDoesNotExist(deckName);
            return;
        }
        Deck deck = user.getDeckByDeckName(deckName);
        if (isFromSideDeck && !deck.isThisCardInSideOrMainDeck(cardName, true)) {
            view.cardDoesNotExistInSideOrMainDeck(cardName, true);
        } else if (!isFromSideDeck && !deck.isThisCardInSideOrMainDeck(cardName, false)) {
            view.cardDoesNotExistInSideOrMainDeck(cardName, false);
        } else {
            deck.removeCardFromDeck(cardName, isFromSideDeck);
            view.cardRemovedFromDeck();
        }
    }

    private Matcher[] getCommandMatchers(String command) {
        Pattern patternForDeckCreate = Pattern.compile("^deck create (.+)$");
        Pattern patternForDeckDelete = Pattern.compile("^deck delete (.+)$");
        Pattern patternForSetActivateDeck = Pattern.compile("^deck set-activate (.+)$");
        Pattern patternForDeckShowAll = Pattern.compile("^deck show --all$");
        Pattern patternForDeckShowAll2 = Pattern.compile("^deck show -a$");
        Pattern patternForDeckShowCards = Pattern.compile("^deck show --cards$");
        Pattern patternForDeckShowCards2 = Pattern.compile("^deck show -c$");
        Pattern patternForMenuExit = Pattern.compile("^menu exit$");
        Pattern patternForShowCurrentMenu = Pattern.compile("^menu show-current$");
        Pattern patternForAddCardToSideDeck1 = Pattern.compile("^deck add-card --card (?<cardName>.+?) --deck (?<deckName>.+?) --side$");
        Pattern patternForAddCardToSideDeck2 = Pattern.compile("^deck add-card --deck (?<deckName>.+?) --card (?<cardName>.+?) --side$");
        Pattern patternForAddCardToSideDeck3 = Pattern.compile("^deck add-card --card (?<cardName>.+?) --side --deck (?<deckName>.+?)$");
        Pattern patternForAddCardToSideDeck4 = Pattern.compile("^deck add-card --deck (?<deckName>.+?) --side --card (?<cardName>.+?)$");
        Pattern patternForAddCardToSideDeck5 = Pattern.compile("^deck add-card --side --card (?<cardName>.+?) --deck (?<deckName>.+?)$");
        Pattern patternForAddCardToSideDeck6 = Pattern.compile("^deck add-card --side --deck (?<deckName>.+?) --card (?<cardName>.+?)$");
        Pattern patternForAddCardToSideDeck7 = Pattern.compile("^deck add-card -c (?<cardName>.+?) -d (?<deckName>.+?) -s$");
        Pattern patternForAddCardToSideDeck8 = Pattern.compile("^deck add-card -d (?<deckName>.+?) -c (?<cardName>.+?) -s$");
        Pattern patternForAddCardToSideDeck9 = Pattern.compile("^deck add-card -c (?<cardName>.+?) -s -d (?<deckName>.+?)$");
        Pattern patternForAddCardToSideDeck10 = Pattern.compile("^deck add-card -d (?<deckName>.+?) -s -c (?<cardName>.+?)$");
        Pattern patternForAddCardToSideDeck11 = Pattern.compile("^deck add-card -s -c (?<cardName>.+?) -d (?<deckName>.+?)$");
        Pattern patternForAddCardToSideDeck12 = Pattern.compile("^deck add-card -s -d (?<deckName>.+?) -c (?<cardName>.+?)$");
        Pattern patternForAddCardToMainDeck1 = Pattern.compile("^deck add-card --card (?<cardName>.+?) --deck (?<deckName>.+?)$");
        Pattern patternForAddCardToMainDeck2 = Pattern.compile("^deck add-card --deck (?<deckName>.+?) --card (?<cardName>.+?)$");
        Pattern patternForAddCardToMainDeck3 = Pattern.compile("^deck add-card -d (?<deckName>.+?) -c (?<cardName>.+?)$");
        Pattern patternForAddCardToMainDeck4 = Pattern.compile("^deck add-card -c (?<cardName>.+?) -d (?<deckName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck1 = Pattern.compile("^deck rm-card --card (?<cardName>.+?) --deck (?<deckName>.+?) --side$");
        Pattern patternForRemoveCardFromSideDeck2 = Pattern.compile("^deck rm-card --deck (?<deckName>.+?) --card (?<cardName>.+?) --side$");
        Pattern patternForRemoveCardFromSideDeck3 = Pattern.compile("^deck rm-card --card (?<cardName>.+?) --side --deck (?<deckName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck4 = Pattern.compile("^deck rm-card --deck (?<deckName>.+?) --side --card (?<cardName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck5 = Pattern.compile("^deck rm-card --side --card (?<cardName>.+?) --deck (?<deckName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck6 = Pattern.compile("^deck rm-card --side --deck (?<deckName>.+?) --card (?<cardName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck7 = Pattern.compile("^deck rm-card -c (?<cardName>.+?) -d (?<deckName>.+?) -s$");
        Pattern patternForRemoveCardFromSideDeck8 = Pattern.compile("^deck rm-card -d (?<deckName>.+?) -c (?<cardName>.+?) -s$");
        Pattern patternForRemoveCardFromSideDeck9 = Pattern.compile("^deck rm-card -c (?<cardName>.+?) -s -d (?<deckName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck10 = Pattern.compile("^deck rm-card -d (?<deckName>.+?) -s -c (?<cardName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck11 = Pattern.compile("^deck rm-card -s -c (?<cardName>.+?) -d (?<deckName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck12 = Pattern.compile("^deck rm-card -s -d (?<deckName>.+?) -c (?<cardName>.+?)$");
        Pattern patternForRemoveCardFromMainDeck1 = Pattern.compile("^deck rm-card --card (?<cardName>.+?) --deck (?<deckName>.+?)$");
        Pattern patternForRemoveCardFromMainDeck2 = Pattern.compile("^deck rm-card --deck (?<deckName>.+?) --card (?<cardName>.+?)$");
        Pattern patternForRemoveCardFromMainDeck3 = Pattern.compile("^deck rm-card -c (?<cardName>.+?) -d (?<deckName>.+?)$");
        Pattern patternForRemoveCardFromMainDeck4 = Pattern.compile("^deck rm-card -d (?<deckName>.+?) -c (?<cardName>.+?)$");
        Pattern patternForShowSideDeck1 = Pattern.compile("^deck show --deck-name (?<deckName>.+?) --side$");
        Pattern patternForShowSideDeck2 = Pattern.compile("^deck show --side --deck-name (?<deckName>.+?)$");
        Pattern patternForShowSideDeck3 = Pattern.compile("^deck show -d-n (?<deckName>.+?) -s$");
        Pattern patternForShowSideDeck4 = Pattern.compile("^deck show -s -d-n (?<deckName>.+?)$");
        Pattern patternForShowMainDeck1 = Pattern.compile("^deck show --deck-name (?<deckName>.+?)$");
        Pattern patternForShowMainDeck2 = Pattern.compile("^deck show -d-n (?<deckName>.+?)$");
        Pattern patternForImpossibleMenuNavigation = Pattern.compile("^menu enter (Duel|Scoreboard|Import/Export|Shop|Profile)$");
        Matcher[] commandMatchers = new Matcher[48];
        commandMatchers[0] = patternForDeckCreate.matcher(command);
        commandMatchers[1] = patternForDeckDelete.matcher(command);
        commandMatchers[2] = patternForSetActivateDeck.matcher(command);
        commandMatchers[3] = patternForDeckShowAll.matcher(command);
        commandMatchers[4] = patternForDeckShowAll2.matcher(command);
        commandMatchers[5] = patternForDeckShowCards.matcher(command);
        commandMatchers[6] = patternForDeckShowCards2.matcher(command);
        commandMatchers[7] = patternForMenuExit.matcher(command);
        commandMatchers[8] = patternForShowCurrentMenu.matcher(command);
        commandMatchers[9] = patternForAddCardToSideDeck1.matcher(command);
        commandMatchers[10] = patternForAddCardToSideDeck2.matcher(command);
        commandMatchers[11] = patternForAddCardToSideDeck3.matcher(command);
        commandMatchers[12] = patternForAddCardToSideDeck4.matcher(command);
        commandMatchers[13] = patternForAddCardToSideDeck5.matcher(command);
        commandMatchers[14] = patternForAddCardToSideDeck6.matcher(command);
        commandMatchers[15] = patternForAddCardToSideDeck7.matcher(command);
        commandMatchers[16] = patternForAddCardToSideDeck8.matcher(command);
        commandMatchers[17] = patternForAddCardToSideDeck9.matcher(command);
        commandMatchers[18] = patternForAddCardToSideDeck10.matcher(command);
        commandMatchers[19] = patternForAddCardToSideDeck11.matcher(command);
        commandMatchers[20] = patternForAddCardToSideDeck12.matcher(command);
        commandMatchers[21] = patternForAddCardToMainDeck1.matcher(command);
        commandMatchers[22] = patternForAddCardToMainDeck2.matcher(command);
        commandMatchers[23] = patternForAddCardToMainDeck3.matcher(command);
        commandMatchers[24] = patternForAddCardToMainDeck4.matcher(command);
        commandMatchers[25] = patternForRemoveCardFromSideDeck1.matcher(command);
        commandMatchers[26] = patternForRemoveCardFromSideDeck2.matcher(command);
        commandMatchers[27] = patternForRemoveCardFromSideDeck3.matcher(command);
        commandMatchers[28] = patternForRemoveCardFromSideDeck4.matcher(command);
        commandMatchers[29] = patternForRemoveCardFromSideDeck5.matcher(command);
        commandMatchers[30] = patternForRemoveCardFromSideDeck6.matcher(command);
        commandMatchers[31] = patternForRemoveCardFromSideDeck7.matcher(command);
        commandMatchers[32] = patternForRemoveCardFromSideDeck8.matcher(command);
        commandMatchers[33] = patternForRemoveCardFromSideDeck9.matcher(command);
        commandMatchers[34] = patternForRemoveCardFromSideDeck10.matcher(command);
        commandMatchers[35] = patternForRemoveCardFromSideDeck11.matcher(command);
        commandMatchers[36] = patternForRemoveCardFromSideDeck12.matcher(command);
        commandMatchers[37] = patternForRemoveCardFromMainDeck1.matcher(command);
        commandMatchers[38] = patternForRemoveCardFromMainDeck2.matcher(command);
        commandMatchers[39] = patternForRemoveCardFromMainDeck3.matcher(command);
        commandMatchers[40] = patternForRemoveCardFromMainDeck4.matcher(command);
        commandMatchers[41] = patternForShowSideDeck1.matcher(command);
        commandMatchers[42] = patternForShowSideDeck2.matcher(command);
        commandMatchers[43] = patternForShowSideDeck3.matcher(command);
        commandMatchers[44] = patternForShowSideDeck4.matcher(command);
        commandMatchers[45] = patternForShowMainDeck1.matcher(command);
        commandMatchers[46] = patternForShowMainDeck2.matcher(command);
        commandMatchers[47] = patternForImpossibleMenuNavigation.matcher(command);
        return commandMatchers;
    }
}
