package server.controller;

import org.model.*;
import org.view.CreateNewCardView;

public class CreateNewCardController {

    private CreateNewCardView view;
    private CreateNewCard model;
    private User user;

    public CreateNewCardController(User user) {
        view = new CreateNewCardView(this);
        model = new CreateNewCard(this);
        this.user = user;
    }

    public void creationCommission(int price) {

        user.changeBalance(-1 * (price / 10));
    }


    public static void addNewMonster(MonsterCard card) {
        CreateNewCard.newMonsters.add(card);
        addNewCard(card);


    }

    public static void addNewSpell(SpellCard card) {
        CreateNewCard.newSpells.add(card);
        addNewCard(card);
    }

    public static void addNewTrap(TrapCard card) {
        CreateNewCard.newTraps.add(card);
        addNewCard(card);
    }

    public static void addNewCard(Card card){
        CreateNewCard.newCards.add(card);
    }

    public void run() {
        view.run();
    }

    public CreateNewCardView getView() {
        return view;
    }

    public CreateNewCard getModel() {
        return model;
    }

    public User getUser() {
        return user;
    }
}
