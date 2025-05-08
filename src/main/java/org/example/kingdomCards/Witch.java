package org.example.kingdomCards;

import org.example.*;

public class Witch extends Card implements Action, Attack {
    public Witch() {
        cost = 5;
        name = "witch";
        startingAmount = 10;
    }

    @Override
    public void play(Game game, Player player) {
        System.out.println(description());
        player.draw(2);
        attack(game, player);
    }

    @Override
    public void attack(Game game, Player player) {
        for (Player opponent : game.getPlayers()) {
            if (!opponent.equals(player)) {
                opponent.gainToDiscard("curse", game.getSupply());
            }
        }
    }

    @Override
    public String description() {
        return "Action, Attack:\n+2 cards\neach other player gains a curse\ncost: 5";
    }

    @Override
    public Card copy() {
        return new Witch();
    }
}
