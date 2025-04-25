package org.example;

public class Curse extends Card {
    public Curse() {
        cost = 0;
        name = "curse";
        startingAmount = 30;
    }

    @Override
    public String description() {
        return "curse: -1 point";
    }

    @Override
    public Card copy() {
        return new Curse();
    }
}
