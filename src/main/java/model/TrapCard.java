package model;

public class TrapCard extends Card{
    private TrapIcon icon;
    private TrapEffect effect;
    public TrapCard (String name, String number, String description , TrapIcon icon , TrapEffect effect){
        super(name, number, description);
        setIcon(icon);
        setEffect(effect);

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
}
