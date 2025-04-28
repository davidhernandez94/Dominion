package org.example.basicCards;

import org.example.Card;
import org.example.Game;
import org.example.Player;
import org.example.Victory;

public abstract class BasicVictory extends Card implements Victory {
    protected int points;

    public BasicVictory() {
    }

    @Override
    public int getPoints(Game game, Player player) {
        return points;
    }

    @Override
    public String description() {
        return "victory: " + points + "points";
    }
}
