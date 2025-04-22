package org.example;

import java.util.*;

public abstract class Player {
    protected String name;
    private List<Card> deck = new LinkedList<>();
    private List<Card> hand = new LinkedList<>();
    private List<Card> discard = new LinkedList<>();
    private List<Card> inPlay = new LinkedList<>();
    private int money;
    private int actions;
    private int buys;
    private int victoryPoints;

    public Player(String name) {
        this.name = name;
    }

    public void ActionPhase(Game game) {
        draw(5);
        System.out.println(hand);
        Scanner sc = new Scanner(System.in);
        System.out.println("ACTION PHASE");
        while (actions > 0) {

        }
    }

    public void BuyPhase(Game game) {
        // TODO
    }

    public void pickUp() {
        discard.addAll(inPlay);
        inPlay.clear();
    }

    public int countVictoryPoints() {
        return 0; // TODO
    }

    public String input(List<Card> cards) {
        return null; // TODO
    }

    public void draw(int num) {
        for (int i = 0; i < num; i++) {
            if (!deck.isEmpty()) {
                hand.add(deck.removeFirst());
            } else {
                shuffleDiscardIntoDeck();
                draw(1);
            }
        }
    }

    public int countActionCardsInHand() {
        int count = 0;
        for (Card card : hand) {
            if (card instanceof Action) {
                count++;
            }
        }
        return count;
    }

    public int countTreasuresInHand() {
        int count = 0;
        for (Card card : hand) {
            if (card instanceof Treasure) {
                count++;
            }
        }
        return count;
    }

    public int countVictoryCardsInHand() {
        int count = 0;
        for (Card card : hand) {
            if (card instanceof Victory) {
                count++;
            }
        }
        return count;
    }

    public void gain(String cardName, Supply supply) {

    }

    public void shuffleDiscardIntoDeck() {
        deck.addAll(discard);
        discard.clear();
        Collections.shuffle(deck);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return money == player.money && actions == player.actions && buys == player.buys && victoryPoints == player.victoryPoints && Objects.equals(name, player.name) && Objects.equals(deck, player.deck) && Objects.equals(hand, player.hand) && Objects.equals(discard, player.discard) && Objects.equals(inPlay, player.inPlay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, deck, hand, discard, inPlay, money, actions, buys, victoryPoints);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public List<Card> getDiscard() {
        return discard;
    }

    public void setDiscard(List<Card> discard) {
        this.discard = discard;
    }

    public List<Card> getInPlay() {
        return inPlay;
    }

    public void setInPlay(List<Card> inPlay) {
        this.inPlay = inPlay;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public int getBuys() {
        return buys;
    }

    public void setBuys(int buys) {
        this.buys = buys;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }
}
