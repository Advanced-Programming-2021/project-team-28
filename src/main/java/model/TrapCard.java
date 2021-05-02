package model;

import Enums.TrapEffect;
import Enums.TrapIcon;

public class TrapCard extends Card{
    private TrapIcon icon;
    private TrapEffect effect;
    public TrapCard (String name, String number, String description , TrapIcon icon , TrapEffect effect){
        super(name, number, description);
        setIcon(icon);
        setEffect(effect);

    }

    public TrapCard(){

    }

    @Override
    public String toString() {
        return "Name : " + this.name + "\n"
                + "Trap" + "\n"
                + "Type : " + this.icon.getName() + "\n"
                + "Description : " + this.description ;
    }


    public TrapIcon getIcon() {
        return icon;
    }

    public void setIcon(TrapIcon icon) {
        this.icon = icon;
    }

    public TrapEffect getEffect() {
        return effect;
    }

    public void setEffect(TrapEffect effect) {
        this.effect = effect;
    }

    @Override
    public Object clone() {
        TrapCard cloneTrapCard = new TrapCard();
        cloneTrapCard.number = this.number;
        cloneTrapCard.description = this.description;
        cloneTrapCard.name = this.name;
        cloneTrapCard.icon = this.icon;
        cloneTrapCard.effect = this.effect;
        return cloneTrapCard;
    }
}
