package org.example.basicCards;

import org.example.Card;

public class Curse extends Card {
    public Curse() {
        cost = 0;
        name = "curse";
        startingAmount = 30;
    }

    @Override
    public String description() {
        return "curse: -1 point\ncost: 0";
    }

    @Override
    public Card copy() {
        return new Curse();
    }
}
