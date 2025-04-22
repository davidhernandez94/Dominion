package org.example;

import java.util.Objects;

public abstract class Card {
    protected String name;
    protected int cost;
    protected int startingAmount;

    public Card() {
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cost == card.cost && startingAmount == card.startingAmount && Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, startingAmount);
    }

    public String getName() {
        return name;
    }

    public int getStartingAmount() {
        return startingAmount;
    }

    public int getCost() {
        return cost;
    }
}
