package org.example;

public interface Reaction {

    /**
     * reacts during another player's turn
     * @param game game where the card is played
     * @param player player who plays the card
     */
    void react(Game game, Player player);
}
