package org.example;

public class Estate extends BasicVictory {
    public Estate() {
        cost = 2;
        name = "estate";
        points = 1;
        startingAmount = 24;
    }

    @Override
    public Card copy() {
        return new Estate();
    }
}
