package org.example.basicCards;

import org.example.Card;
import org.example.Game;
import org.example.Player;
import org.example.Treasure;

public abstract class BasicTreasure extends Card implements Treasure {
    protected int money;

    public BasicTreasure() {
    }

    @Override
    public int pay(Game game, Player player) {
        return money;
    }

    @Override
    public String description() {
        return "treasure: " + money + "$";
    }
}
