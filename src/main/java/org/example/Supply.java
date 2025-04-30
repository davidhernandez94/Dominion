package org.example;

import org.example.basicCards.*;

import java.util.*;

public class Supply {
    protected Map<String, Queue<Card>> cards = new HashMap<>();

    public Supply() {
        this(new LinkedList<>());
    }

    public Supply(Map<String, Queue<Card>> cards) {
        this.cards = cards;
    }

    public Supply(List<Card> startingCards) {
        cards = makeSupply(startingCards);
    }

    /**
     * makes supply: adds startingAmount of each card to supply
     * @param startingCards original starting cards (cards that are in kingdomCards package)
     * @return map where key is name of card and value is queue of cards with that name
     */
    public Map<String, Queue<Card>> makeSupply(List<Card> startingCards) {
        Map<String, Queue<Card>> supply = new HashMap<>();
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
                        list.add(card.copy());
                    }
                    supply.putIfAbsent(card.name, list);
                });
        return supply;
    }

    @Override
    public String toString() {
        return "Supply{" +
                "cards=" + cards +
                '}';
    }

    public Map<String, Queue<Card>> getCards() {
        return cards;
    }

    public void setCards(Map<String, Queue<Card>> cards) {
        this.cards = cards;
    }
}
