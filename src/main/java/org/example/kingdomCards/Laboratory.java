package org.example.kingdomCards;

import org.example.Action;
import org.example.Card;
import org.example.Game;
import org.example.Player;

public class Laboratory extends Card implements Action {
    public Laboratory() {
        cost = 5;
        name = "laboratory";
        startingAmount = 10;
    }

    @Override
    public void play(Game game, Player player) {
        System.out.println(description());
        player.draw(2);
        player.addActions(1);
    }

    @Override
    public Card copy() {
        return new Laboratory();
    }

    @Override
    public String description() {
        return "action:\n+2 cards\n+1 action\ncost: 5";
    }
}
