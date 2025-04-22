package org.example;

public abstract class BasicVictory extends Card implements Victory {
    protected int points;

    public BasicVictory() {
    }

    @Override
    public int getPoints(Game game, Player player) {
        return points;
    }
}
