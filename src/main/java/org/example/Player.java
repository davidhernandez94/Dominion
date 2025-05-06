package org.example;

import org.example.basicCards.Curse;

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

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    /**
     * player can play only one action card from their hand (unless they gain actions during their turn)
     * @param game current game
     */
    public void ActionPhase(Game game) {
        System.out.println("ACTION PHASE");
        while (true) {
            System.out.println("hand:\n" + hand);
            System.out.println("actions: " + actions);
            if (actions < 1) {
                return;
            }
            System.out.println("what card would you like to play? (press any letter and enter if you want to end action phase)\nIf you want to see the description of a card, enter the card name with '?' at the end");
            List<Card> actionCards = new ArrayList<>();
            hand.forEach(card -> {
                if (card instanceof Action) {
                    actionCards.add(card);
                }
            });
            Action choice = (Action) inputCards(actionCards);
            if (choice == null) {
                return;
            }
            hand.remove(choice);
            inPlay.add((Card) choice);
            actions--;
            choice.play(game, this);
        }
    }

    /**
     * player can buy only one card from the supply (unless they gain buys during their turn)
     * @param game current game
     */
    public void BuyPhase(Game game) {
        System.out.println("\nBUY PHASE");
        hand.forEach(card -> {
            if (card instanceof Treasure treasure) {
                money += treasure.pay(game, this);
            }
        });
        while (true) {
            System.out.println("hand: " + hand);
            System.out.println("money: " + money);
            System.out.println("buys: " + buys);
            if (buys < 1) {
                return;
            }
            System.out.println("what card would you like to buy? (press any letter and enter if you want end buy phase)");
            List<Card> actionCards = new ArrayList<>();
            List<Card> treasureCards = new ArrayList<>();
            for (Queue<Card> list : game.supply.cards.values()) {
                if (!list.isEmpty() && list.peek().cost <= money) {
                    treasureCards.add(list.peek());
                }
            }
            treasureCards.sort(new Card.CardComparator("cost"));
            Card choice = inputCards(treasureCards);
            if (choice == null) {
                return;
            }
            money -= choice.cost;
            buys--;
            gainToDiscard(choice.name, game.supply);
        }
    }

    /**
     * all cards from player's hand and inPlay go in their discard they draw a new hand
     * their settings for actions, buys, and money reset for their next turn
     */
    public void pickUp() {
        discard.addAll(inPlay);
        discard.addAll(hand);
        inPlay.clear();
        hand.clear();
        draw(5);
        actions = 1;
        buys = 1;
        money = 0;
    }

    /**
     * allows player to choose a card from a list of cards through the terminal
     * @param cards list of cards to choose from
     * @return chosen card
     */
    public Card inputCards(List<Card> cards) {
        List<String> strs = new ArrayList<>();
        cards.forEach(card -> strs.add(card.name));
        cards.forEach(card -> strs.add(card.name + "?"));
        System.out.println("Your choices: " + strs + "\nYou choose: ");
        String result = game.sc.nextLine();
        while (!strs.contains(result)) {
            if (result.length() == 1) {
                return null;
            }
            System.out.println("Incorrect input, try again: ");
            result = game.sc.nextLine();
        }
        if (result.contains("?")) {
            System.out.println(cards.get(strs.indexOf(result) - cards.size()).description());
            return inputCards(cards);
        }
        return cards.get(strs.indexOf(result));
    }

    /**
     * reveals the top card of player's deck
     * @return revealed card
     */
    public Card reveal() {
        if (deck.isEmpty()) {
            shuffleDiscardIntoDeck();
        }
        return deck.getFirst();
    }

    /**
     * reveals the top num cards of a player's deck
     * @param num number of cards to be revealed
     * @return list of revealed cards
     */
    public List<Card> reveal(int num) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            if (deck.size() <= i) {
                shuffleDiscardIntoDeck();
            }
            cards.add(deck.get(i));
        }
        return cards;
    }

    /**
     * draws cards from top of deck into hand
     * @param num amount of cards to be drawn
     */
    public void draw(int num) {
        for (int i = 0; i < num; i++) {
            if (deck.isEmpty()) {
                shuffleDiscardIntoDeck();
            }
            hand.add(deck.removeFirst());
        }
    }

    /**
     * increases player's actions
     * @param num how many to be increased by
     */
    public void addActions(int num) {
        actions += num;
    }

    /**
     * increases player's buys
     * @param num how many to be increased by
     */
    public void addBuys(int num) {
        buys += num;
    }

    /**
     * increases player's money
     * @param num how many to be increased by
     */
    public void addMoney(int num) {
        money += num;
    }

    /**
     * counts how many victory points player has in deck
     * based on victory cards and curses
     */
    public void countVictoryPointsInDeck() {
        int count = 0;
        for (Card card : deck) {
            if (card instanceof Victory victory) {
                count += victory.getPoints(game, this);
            } else if (card instanceof Curse) {
                count--;
            }
        }
        victoryPoints = count;
    }

    /**
     * counts how many action cards are in player's hand
     * @return how many there are
     */
    public int countActionCardsInHand() {
        int count = 0;
        for (Card card : hand) {
            if (card instanceof Action) {
                count++;
            }
        }
        return count;
    }

    /**
     * counts how many treasure cards are in player's hand
     * @return how many there are
     */
    public int countTreasuresInHand() {
        int count = 0;
        for (Card card : hand) {
            if (card instanceof Treasure) {
                count++;
            }
        }
        return count;
    }

    /**
     * counts how many victory cards are in player's hand
     * @return how many there are
     */
    public int countVictoryCardsInHand() {
        int count = 0;
        for (Card card : hand) {
            if (card instanceof Victory) {
                count++;
            }
        }
        return count;
    }

    /**
     * gains card from supply to player's discard
     * @param cardName name of added card
     * @param supply supply of the game
     * @return whether the card was added or not
     */
    public boolean gainToDiscard(String cardName, Supply supply) {
        if (supply.cards.get(cardName).isEmpty()) {
            return false;
        }
        discard.add(supply.cards.get(cardName).remove());
        return true;
    }

    /**
     * gains multiple cards from supply to discard
     * @param num number of cards to be added
     * @param cardName name of card to be added
     * @param supply supply of game
     * @return whether the operation was successful
     */
    public boolean gainToDiscard(int num, String cardName, Supply supply) {
        if (supply.cards.get(cardName).size() < num) {
            return false;
        }
        for (int i = 0; i < num; i++) {
            discard.add(supply.cards.get(cardName).remove());
        }
        return true;
    }

    /**
     * gains multiple cards from supply to hand
     * @param cardName name of card to be added
     * @param supply supply of game
     * @return whether the operation was successful
     */
    public boolean gainToHand(String cardName, Supply supply) {
        if (supply.cards.get(cardName).isEmpty()) {
            return false;
        }
        hand.add(supply.cards.get(cardName).remove());
        return true;
    }

    /**
     * gains multiple cards from supply to hand
     * @param num number of cards to be added
     * @param cardName name of card to be added
     * @param supply supply of game
     * @return whether the operation was successful
     */
    public boolean gainToHand(String cardName, Supply supply, int num) {
        if (supply.cards.get(cardName).size() < num) {
            return false;
        }
        for (int i = 0; i < num; i++) {
            gainToHand(cardName, supply);
        }
        return true;
    }

    /**
     * gains card to top of deck
     * @param cardName name of card
     * @param supply supply of game
     * @return whether the operation was successful
     */
    public boolean gainToTopOfDeck(String cardName, Supply supply) {
        if (supply.cards.get(cardName).isEmpty()) {
            return false;
        }
        deck.addFirst(supply.cards.get(cardName).remove());
        return true;
    }

    /**
     * when deck is empty, randomly mixes discard and puts them in deck
     */
    public void shuffleDiscardIntoDeck() {
        Collections.shuffle(discard);
        deck.addAll(discard);
        discard.clear();
        System.out.println("(your discard has been shuffled into your deck)");
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
        return Objects.hash(name, deck, hand, discard, inPlay, (Object) money, (Object) actions, (Object) buys, (Object) victoryPoints);
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
