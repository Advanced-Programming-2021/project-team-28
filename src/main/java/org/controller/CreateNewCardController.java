package org.controller;

import org.model.CreateNewCard;
import org.model.User;
import org.view.CreateNewCardView;

public class CreateNewCardController {
    private CreateNewCardView view;
    private CreateNewCard model;
    private User user;
    public CreateNewCardController(User user){
        view = new CreateNewCardView(this);
        model = new CreateNewCard(this);
        this.user = user;
    }
    public void run(){
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
