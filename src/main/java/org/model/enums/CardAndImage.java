package org.model.enums;

import javafx.scene.image.Image;

public class CardAndImage {
    private String cardName;
    private Image image;

    public CardAndImage(Image image, String cardName){
        this.cardName = cardName;
        this.image = image;
    }
}
