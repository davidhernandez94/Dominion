package org.example.actionCards;

import org.example.Action;
import org.example.Card;
import org.example.Game;
import org.example.Player;

public class Village extends Card implements Action {
    public Village() {
        cost = 3;
        name = "village";
        startingAmount = 10;
    }

    @Override
    public void play(Game game, Player player) {
        System.out.println(description());
        player.draw(1);
        player.addActions(2);
    }

    @Override
    public Card copy() {
        return new Village();
    }

    @Override
    public String description() {
        return "action:\n+1 card\n+2 actions";
    }
}
