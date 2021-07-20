package org.model;

import java.io.Serializable;

public class AdminCardFields implements Serializable {
    private int amount = 1;
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
