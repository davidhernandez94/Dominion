package org.example;

public interface Treasure {
    /**
     * adds to player's money a certain amount (see card description)
     * @param game game where this is played
     * @param player player who plays the card
     */
    int pay(Game game, Player player);
}
