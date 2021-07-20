package org.model;

public class AdminCardFields {
    private int amount = 50;
    private boolean isCardAvailable = true;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isCardAvailable() {
        return isCardAvailable;
    }

    public void setCardAvailable(boolean cardAvailable) {
        isCardAvailable = cardAvailable;
    }
}
