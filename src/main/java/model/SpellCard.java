package model;

public class SpellCard extends Card{
    private SpellEffect effect;
    private SpellIcon icon;
    public SpellCard (String name , String number , String description , SpellIcon icon , SpellEffect effect){
        super(name, number, description);
        setEffect(effect);
        setIcon(icon);
    }
    public SpellCard(){

    }

    @Override
    public String toString() {
        return "Name : " + this.name + "\n"
                + "Spell" + "\n"
                + "Type : " + this.icon.getName() + "\n"
                + "Description : " + this.description ;
    }

    public SpellEffect getEffect() {
        return effect;
    }

    public void setEffect(SpellEffect effect) {
        this.effect = effect;
    }

    public SpellIcon getIcon() {
        return icon;
    }

    public void setIcon(SpellIcon icon) {
        this.icon = icon;
    }

    @Override
    public Object clone() {
        SpellCard cloneSpellCard = new SpellCard();
        cloneSpellCard.name = this.name;
        cloneSpellCard.description = this.description;
        cloneSpellCard.number = this.number;
        cloneSpellCard.icon = this.icon;
        cloneSpellCard.effect = this.effect;
        return cloneSpellCard;
    }
}
