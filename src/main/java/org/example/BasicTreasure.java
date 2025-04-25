package org.example;

public abstract class BasicTreasure extends Card implements Treasure {
    protected int money;

    public BasicTreasure() {
    }

    @Override
    public void pay(Game game, Player player) {
        player.setMoney(player.getMoney() + money);
    }

    @Override
    public String description() {
        return "treasure: " + money + "$";
    }
}
