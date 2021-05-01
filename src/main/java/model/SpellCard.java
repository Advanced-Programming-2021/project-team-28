package model;

public class SpellCard extends Card{
    private SpellEffect effect;
    private SpellIcon icon;
    public SpellCard (String name , String number , String description , SpellIcon icon , SpellEffect effect){
        super(name, number, description);
        setEffect(effect);
        setIcon(icon);
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
}
