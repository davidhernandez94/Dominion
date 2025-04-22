package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Supply {
    protected Map<String, Queue<Card>> cards;

    public Supply(Map<String, Queue<Card>> cards) {
        this.cards = cards;
    }

    public Supply(List<Card> startingCards) {
        Queue<Card> base = new LinkedList<>();
        base.add(new Copper());
        base.add(new Silver());
        base.add(new Gold());
        base.add(new Estate());
        base.add(new Duchy());
        base.add(new Province());
        startingCards.addAll(base);

        startingCards
                .forEach(card -> {
                    Queue<Card> list = new LinkedList<>();
                    for (int i = 0; i < card.getStartingAmount(); i++) {
                        // list.add();
                    }
                    cards.put(card.name, list);
                });
    }

    @Override
    public String toString() {
        return "Supply{" +
                "cards=" + cards +
                '}';
    }
}
