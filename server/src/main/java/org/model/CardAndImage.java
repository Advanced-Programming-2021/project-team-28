package org.model;

import javafx.scene.image.Image;

public class CardAndImage {
    private String cardName;
    private Image image;

    public CardAndImage(Image image, String cardName){
        this.cardName = cardName;
        this.image = image;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
