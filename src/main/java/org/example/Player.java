package org.example;

import java.util.*;

public abstract class Player implements Comparable<Player> {
    protected String name;
    private List<Card> deck = new LinkedList<>();
    private List<Card> hand = new LinkedList<>();
    private List<Card> discard = new LinkedList<>();
    private List<Card> inPlay = new LinkedList<>();
    private int money;
    private int actions;
    private int buys;
    private int victoryPoints;
    protected Game game;

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    public void ActionPhase(Game game) {
        draw(5);
        actions = 1;
        buys = 1;
        money = 0;
        System.out.println("ACTION PHASE");
        while (true) {
            System.out.println("hand:\n" + hand);
            System.out.println("actions: " + actions);
            if (actions < 1) {
                return;
            }
            System.out.println("what card would you like to play? (press enter if you want to end action phase)");
            List<Card> actionCards = new ArrayList<>();
            hand.forEach(card -> {
                if (card instanceof Action) {
                    actionCards.add(card);
                }
            });
            Action choice = (Action) input(actionCards);
            if (choice == null) {
                return;
            }
            hand.remove(choice);
            inPlay.add((Card) choice);
            actions--;
            choice.play(game, this);
        }
    }

    public void BuyPhase(Game game) {
        System.out.println("\nBUY PHASE");
        while (true) {
            System.out.println("buys: " + buys);
            if (buys < 1) {
                return;
            }
            System.out.println("what card would you like to buy? (press enter if you want end buy phase)");
            List<Card> actionCards = new ArrayList<>();
            hand.forEach(card -> {
                if (card instanceof Treasure) {
                    ((Treasure) card).pay(game, this);
                }
            });
            List<Card> treasureCards = new ArrayList<>();
            for (Queue<Card> list : game.supply.cards.values()) {
                if (!list.isEmpty() && list.peek().cost <= money) {
                    treasureCards.add(list.peek());
                }
            }
            treasureCards.sort(new Card.CardComparator("cost"));
            Card choice = input(treasureCards);
            if (choice == null) {
                return;
            }
            buys--;
            gainToDiscard(choice.name, game.supply);
        }
    }

    public void pickUp() {
        discard.addAll(inPlay);
        inPlay.clear();
    }

    public Card input(List<Card> cards) {
        List<String> strs = new ArrayList<>();
        cards.forEach(card -> strs.add(card.name));
        System.out.println("Your choices:\n" + strs + "\nYou choose: ");
        String result = game.sc.nextLine();
        if (result.isEmpty()) {
            return null;
        }
        while (!strs.contains(result)) {
            System.out.println("Incorrect input, try again: ");
            game.sc.nextLine();
            result = game.sc.nextLine();
        }
        return cards.get(strs.indexOf(result));
    }

    public Card reveal() {
        return deck.getFirst();
    }

    public List<Card> reveal(int num) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            cards.add(deck.get(i));
        }
        return cards;
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

    public void addActions(int num) {
        actions += num;
    }

    public void addBuys(int num) {
        buys += num;
    }

    public void addMoney(int num) {
        money += num;
    }

    public int countVictoryPointsInDeck() {
        int count = 0;
        for (Card card : deck) {
            if (card instanceof Victory victory) {
                count += victory.getPoints(game, this);
            }
        }
        return count;
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

    public void gainToDiscard(String cardName, Supply supply) throws IndexOutOfBoundsException {
        discard.add(supply.cards.get(cardName).remove());
    }

    public void gainToDiscard(int num, String cardName, Supply supply) throws IndexOutOfBoundsException {
        for (int i = 0; i < num; i++) {
            discard.add(supply.cards.get(cardName).remove());
        }
    }

    public void gainToHand(String cardName, Supply supply) throws IndexOutOfBoundsException {
        hand.add(supply.cards.get(cardName).remove());
    }

    public void gainToTopOfDeck(String cardName, Supply supply) throws IndexOutOfBoundsException {
        deck.addFirst(supply.cards.get(cardName).remove());
    }

    public void shuffleDiscardIntoDeck() {
        deck.addAll(discard);
        discard.clear();
        Collections.shuffle(deck);
    }

    @Override
    public int compareTo(Player o) {
        return this.victoryPoints - o.victoryPoints;
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
