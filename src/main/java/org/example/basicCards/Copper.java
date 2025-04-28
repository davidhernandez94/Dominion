package org.example.basicCards;

import org.example.Card;

public class Copper extends BasicTreasure {
    public Copper() {
        cost = 0;
        name = "copper";
        money = 1;
        startingAmount = 60;
    }

    @Override
    public Card copy() {
        return new Copper();
    }
}
