package org.example.kingdomCards;

import org.example.Action;
import org.example.Card;
import org.example.Game;
import org.example.Player;

public class Market extends Card implements Action {
    public Market() {
        cost = 5;
        name = "market";
        startingAmount = 10;
    }

    @Override
    public void play(Game game, Player player) {
        System.out.println(description());
        player.draw(1);
        player.addActions(1);
        player.addBuys(1);
        player.addMoney(1);
    }

    @Override
    public Card copy() {
        return new Market();
    }

    @Override
    public String description() {
        return "action:\n+1 card\n+1 action\n+1$\n+1 buy\ncost: 5";
    }
}
