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

    public MenuEnum processCommand(String command) {
        String cardName = "cardName";
        String deckName = "deckName";
        Matcher[] commandMatchers = getCommandMatchers(command);
        if (commandMatchers[0].find()) {
            controlCreateCommand(commandMatchers[0].group(1));
        } else if (commandMatchers[1].find()) {
            controlDeleteCommand(commandMatchers[1].group(1));
        } else if (commandMatchers[2].find()) {
            controlSetActivateCommand(commandMatchers[2].group(1));
        } else if (commandMatchers[3].find()) {
            view.print(user.decksArrayListToString());
        } else if (commandMatchers[4].find()) {
            view.print(Card.cardsArrayListToString(user.getAllCards()));
        } else if (commandMatchers[5].find()) {
            return MenuEnum.BACK;
        } else if (commandMatchers[6].find()) {
            view.showCurrentMenu();
        } else if (commandMatchers[9].find()) {
            controlAddCardCommand(commandMatchers[9].group(cardName), commandMatchers[9].group(deckName), true);
        } else if (commandMatchers[10].find()) {
            controlAddCardCommand(commandMatchers[10].group(cardName), commandMatchers[10].group(deckName), true);
        } else if (commandMatchers[11].find()) {
            controlAddCardCommand(commandMatchers[11].group(cardName), commandMatchers[11].group(deckName), true);
        } else if (commandMatchers[12].find()) {
            controlAddCardCommand(commandMatchers[12].group(cardName), commandMatchers[12].group(deckName), true);
        } else if (commandMatchers[13].find()) {
            controlAddCardCommand(commandMatchers[13].group(cardName), commandMatchers[13].group(deckName), true);
        } else if (commandMatchers[14].find()) {
            controlAddCardCommand(commandMatchers[14].group(cardName), commandMatchers[14].group(deckName), true);
        } else if (commandMatchers[7].find()) {
            controlAddCardCommand(commandMatchers[7].group(cardName), commandMatchers[7].group(deckName), false);
        } else if (commandMatchers[8].find()) {
            controlAddCardCommand(commandMatchers[8].group(cardName), commandMatchers[8].group(deckName), false);
        } else if (commandMatchers[17].find()) {
            controlRemoveCardCommand(commandMatchers[17].group(cardName), commandMatchers[17].group(deckName), true);
        } else if (commandMatchers[18].find()) {
            controlRemoveCardCommand(commandMatchers[18].group(cardName), commandMatchers[18].group(deckName), true);
        } else if (commandMatchers[19].find()) {
            controlRemoveCardCommand(commandMatchers[19].group(cardName), commandMatchers[19].group(deckName), true);
        } else if (commandMatchers[20].find()) {
            controlRemoveCardCommand(commandMatchers[20].group(cardName), commandMatchers[20].group(deckName), true);
        } else if (commandMatchers[21].find()) {
            controlRemoveCardCommand(commandMatchers[21].group(cardName), commandMatchers[21].group(deckName), true);
        } else if (commandMatchers[22].find()) {
            controlRemoveCardCommand(commandMatchers[22].group(cardName), commandMatchers[22].group(deckName), true);
        } else if (commandMatchers[15].find()) {
            controlRemoveCardCommand(commandMatchers[15].group(cardName), commandMatchers[15].group(deckName), false);
        } else if (commandMatchers[16].find()) {
            controlRemoveCardCommand(commandMatchers[16].group(cardName), commandMatchers[16].group(deckName), false);
        } else if (commandMatchers[24].find()) {
            controlShowOneDeckCommand(commandMatchers[24].group(deckName), true);
        } else if (commandMatchers[25].find()) {
            controlShowOneDeckCommand(commandMatchers[25].group(deckName), true);
        } else if (commandMatchers[23].find()) {
            controlShowOneDeckCommand(commandMatchers[23].group(deckName), false);
        } else if (commandMatchers[26].find()) {
            view.impossibleMenuNavigation();
        } else {
            view.invalidCommand();
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
            new Deck(user, deckName);
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
        Pattern patternForDeckShowCards = Pattern.compile("^deck show --cards$");
        Pattern patternForMenuExit = Pattern.compile("^menu exit$");
        Pattern patternForShowCurrentMenu = Pattern.compile("^menu show-current$");
        Pattern patternForAddCardToSideDeck1 = Pattern.compile("^deck add-card --card (?<cardName>.+?) --deck (?<deckName>.+?) --side$");
        Pattern patternForAddCardToSideDeck2 = Pattern.compile("^deck add-card --deck (?<deckName>.+?) --card (?<cardName>.+?) --side$");
        Pattern patternForAddCardToSideDeck3 = Pattern.compile("^deck add-card --card (?<cardName>.+?) --side --deck (?<deckName>.+?)$");
        Pattern patternForAddCardToSideDeck4 = Pattern.compile("^deck add-card --deck (?<deckName>.+?) --side --card (?<cardName>.+?)$");
        Pattern patternForAddCardToSideDeck5 = Pattern.compile("^deck add-card --side --card (?<cardName>.+?) --deck (?<deckName>.+?)$");
        Pattern patternForAddCardToSideDeck6 = Pattern.compile("^deck add-card --side --deck (?<deckName>.+?) --card (?<cardName>.+?)$");
        Pattern patternForAddCardToMainDeck1 = Pattern.compile("^deck add-card --card (?<cardName>.+?) --deck (?<deckName>.+?)$");
        Pattern patternForAddCardToMainDeck2 = Pattern.compile("^deck add-card --deck (?<deckName>.+?) --card (?<cardName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck1 = Pattern.compile("^deck rm-card --card (?<cardName>.+?) --deck (?<deckName>.+?) --side$");
        Pattern patternForRemoveCardFromSideDeck2 = Pattern.compile("^deck rm-card --deck (?<deckName>.+?) --card (?<cardName>.+?) --side$");
        Pattern patternForRemoveCardFromSideDeck3 = Pattern.compile("^deck rm-card --card (?<cardName>.+?) --side --deck (?<deckName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck4 = Pattern.compile("^deck rm-card --deck (?<deckName>.+?) --side --card (?<cardName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck5 = Pattern.compile("^deck rm-card --side --card (?<cardName>.+?) --deck (?<deckName>.+?)$");
        Pattern patternForRemoveCardFromSideDeck6 = Pattern.compile("^deck rm-card --side --deck (?<deckName>.+?) --card (?<cardName>.+?)$");
        Pattern patternForRemoveCardFromMainDeck1 = Pattern.compile("^deck rm-card --card (?<cardName>.+?) --deck (?<deckName>.+?)$");
        Pattern patternForRemoveCardFromMainDeck2 = Pattern.compile("^deck rm-card --deck (?<deckName>.+?) --card (?<cardName>.+?)$");
        Pattern patternForShowSideDeck1 = Pattern.compile("^deck show --deck-name (?<deckName>.+?) --side$");
        Pattern patternForShowSideDeck2 = Pattern.compile("^deck show --side --deck-name (?<deckName>.+?)$");
        Pattern patternForShowMainDeck = Pattern.compile("^deck show --deck-name (?<deckName>.+?)$");
        Pattern patternForImpossibleMenuNavigation = Pattern.compile("^menu enter (Duel|Scoreboard|Import/Export|Shop|Profile)$");
        Matcher[] commandMatchers = new Matcher[27];
        commandMatchers[0] = patternForDeckCreate.matcher(command);
        commandMatchers[1] = patternForDeckDelete.matcher(command);
        commandMatchers[2] = patternForSetActivateDeck.matcher(command);
        commandMatchers[3] = patternForDeckShowAll.matcher(command);
        commandMatchers[4] = patternForDeckShowCards.matcher(command);
        commandMatchers[5] = patternForMenuExit.matcher(command);
        commandMatchers[6] = patternForShowCurrentMenu.matcher(command);
        commandMatchers[7] = patternForAddCardToMainDeck1.matcher(command);
        commandMatchers[8] = patternForAddCardToMainDeck2.matcher(command);
        commandMatchers[9] = patternForAddCardToSideDeck1.matcher(command);
        commandMatchers[10] = patternForAddCardToSideDeck2.matcher(command);
        commandMatchers[11] = patternForAddCardToSideDeck3.matcher(command);
        commandMatchers[12] = patternForAddCardToSideDeck4.matcher(command);
        commandMatchers[13] = patternForAddCardToSideDeck5.matcher(command);
        commandMatchers[14] = patternForAddCardToSideDeck6.matcher(command);
        commandMatchers[15] = patternForRemoveCardFromMainDeck1.matcher(command);
        commandMatchers[16] = patternForRemoveCardFromMainDeck2.matcher(command);
        commandMatchers[17] = patternForRemoveCardFromSideDeck1.matcher(command);
        commandMatchers[18] = patternForRemoveCardFromSideDeck2.matcher(command);
        commandMatchers[19] = patternForRemoveCardFromSideDeck3.matcher(command);
        commandMatchers[20] = patternForRemoveCardFromSideDeck4.matcher(command);
        commandMatchers[21] = patternForRemoveCardFromSideDeck5.matcher(command);
        commandMatchers[22] = patternForRemoveCardFromSideDeck6.matcher(command);
        commandMatchers[23] = patternForShowMainDeck.matcher(command);
        commandMatchers[24] = patternForShowSideDeck1.matcher(command);
        commandMatchers[25] = patternForShowSideDeck2.matcher(command);
        commandMatchers[26] = patternForImpossibleMenuNavigation.matcher(command);
        return commandMatchers;
    }
}
