package org.example;

public interface Attackable {

    /**
     * attacks another player according to the card description
     * @param game game where the card is played
     * @param player player who plays the card
     */
    void attack(Game game, Player player);
}
