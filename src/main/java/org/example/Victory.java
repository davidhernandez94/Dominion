package org.example;

public interface Victory {

    /**
     * adds a certain amount of points to player's points (see card description)
     * @param game game where this card is
     * @param player player who owns this card
     * @return how many points this card gives
     */
    int getPoints(Game game, Player player);
}
