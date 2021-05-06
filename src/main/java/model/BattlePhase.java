package model;

import enums.Turn;

public class BattlePhase extends Phase{
    public BattlePhase(Player firstPlayer, Player secondPlayer, Turn turn) {
        super(firstPlayer, secondPlayer, turn);
    }

    public int attackDirect(){
        Player player = getPlayerByTurn();
        Player rivalPlayer = getRivalPlayerByTurn();
        int damageOfAttackingCard = ((MonsterCard) player.getSelectedCard()).getAttackPoint();
        if(rivalPlayer.getLifePoint() < damageOfAttackingCard){
            int attackDamage = rivalPlayer.getLifePoint();
            rivalPlayer.setLifePoint(0);
            return attackDamage;
        }
        rivalPlayer.decreaseLifePoint(damageOfAttackingCard);
        ((MonsterCard) player.getSelectedCard()).setHasBattledInBattlePhase(true);
        return damageOfAttackingCard;
    }
}
