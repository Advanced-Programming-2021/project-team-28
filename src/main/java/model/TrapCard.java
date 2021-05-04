package model;

import enums.SpellOrTrapCardPosition;
import enums.TrapEffect;
import enums.TrapIcon;

public class TrapCard extends Card implements Cloneable{

    private TrapIcon icon;
    private TrapEffect effect;
    private SpellOrTrapCardPosition position;

    public TrapCard (String name, String number, String description , TrapIcon icon , TrapEffect effect){
        super(name, number, description);
        setIcon(icon);
        setEffect(effect);
        setPosition(SpellOrTrapCardPosition.NOT_IN_PLAY_ZONE);
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

    public SpellOrTrapCardPosition getPosition() {
        return position;
    }

    public void setPosition(SpellOrTrapCardPosition position) {
        this.position = position;
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
        cloneTrapCard.position = this.position;
        return cloneTrapCard;
    }
}
