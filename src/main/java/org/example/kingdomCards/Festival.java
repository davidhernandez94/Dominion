package org.example.kingdomCards;

import org.example.Action;
import org.example.Card;
import org.example.Game;
import org.example.Player;

public class Festival extends Card implements Action {
    public Festival() {
        cost = 5;
        name = "festival";
        startingAmount = 10;
    }

    @Override
    public void play(Game game, Player player) {
        System.out.println(description());
        player.addActions(2);
        player.addBuys(1);
        player.addMoney(2);
    }

    @Override
    public Card copy() {
        return new Festival();
    }

    @Override
    public String description() {
        return "action:\n+2 actions\n+1 buy\n+2$";
    }
}
