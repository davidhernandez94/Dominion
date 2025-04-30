package org.example.kingdomCards;

import org.example.Action;
import org.example.Card;
import org.example.Game;
import org.example.Player;

public class Smithy extends Card implements Action {
    public Smithy() {
        cost = 4;
        name = "smithy";
        startingAmount = 10;
    }

    @Override
    public Card copy() {
        return new Smithy();
    }

    @Override
    public String description() {
        return "action:\n+3 cards\ncost: 4";
    }

    @Override
    public void play(Game game, Player player) {
        System.out.println(description());
    }
}
