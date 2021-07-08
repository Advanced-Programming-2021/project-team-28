package org.model;

import org.controller.CreateNewCardController;
import org.model.enums.Attribute;
import org.model.enums.MonsterPower;
import org.model.enums.MonsterType;

public class CreateNewCard {
    private CreateNewCardController controller;

    public CreateNewCard(CreateNewCardController controller) {
        this.controller = controller;
    }

    public void MonsterCreate(String cardName, MonsterType type, int level, int atk, int def, MonsterPower power, Attribute attribute) {

    }
}
