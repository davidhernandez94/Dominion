package org.example.basicCards;

import org.example.Card;

public class Gold extends BasicTreasure {
    public Gold() {
        cost = 6;
        name = "gold";
        money = 3;
        startingAmount = 30;
    }

    @Override
    public Card copy() {
        return new Gold();
    }
}
