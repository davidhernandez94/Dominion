package org.example;

public interface Action {

    /**
     * plays the card according to its description
     * @param game game that the card is being played in
     * @param player player who is playing the card
     */
    void play(Game game, Player player);
}
