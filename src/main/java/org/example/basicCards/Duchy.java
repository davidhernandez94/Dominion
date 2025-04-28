package org.example.basicCards;

import org.example.Card;

public class Duchy extends BasicVictory {
    public Duchy() {
        cost = 5;
        name = "duchy";
        points = 3;
        startingAmount = 12;
    }

    @Override
    public Card copy() {
        return new Duchy();
    }
}
