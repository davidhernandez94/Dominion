package org.example.basicCards;

import org.example.Card;

public class Silver extends BasicTreasure {
    public Silver() {
        cost = 3;
        name = "silver";
        money = 2;
        startingAmount = 40;
    }

    @Override
    public Card copy() {
        return new Silver();
    }
}
