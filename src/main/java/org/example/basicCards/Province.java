package org.example.basicCards;

import org.example.Card;

public class Province extends BasicVictory {
    public Province() {
        cost = 8;
        name = "province";
        points = 6;
        startingAmount = 12;
    }

    @Override
    public Card copy() {
        return new Province();
    }
}
