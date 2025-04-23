package org.example;

import java.util.Comparator;
import java.util.Objects;

public abstract class Card {
    protected String name;
    protected int cost;
    protected int startingAmount;

    public Card() {
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract Card copy();

    public static class CardComparator implements Comparator<Card> {
        private String type;

        public CardComparator(String type) {
            this.type = type;
        }

        @Override
        public int compare(Card o1, Card o2) {
            return switch (type) {
                case "cost" -> o2.cost - o1.cost;
                case null, default -> o1.name.compareTo(o2.name);
            };
        }
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
