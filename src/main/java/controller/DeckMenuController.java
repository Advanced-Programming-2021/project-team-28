package controller;

import model.*;


public class DeckMenuController {
    private User user;

    public DeckMenuController (User user){
        this.user = user;
    }

    public void run() {
        System.out.println("Welcome to the Dick Menu");
    }
}
