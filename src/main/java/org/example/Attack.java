package org.example;

import java.util.List;

public interface Attack {

    /**
     * attacks another player according to the card description
     * @param game game where the card is played
     * @param player player who plays the card
     */
    void attack(Game game, Player player);
}
