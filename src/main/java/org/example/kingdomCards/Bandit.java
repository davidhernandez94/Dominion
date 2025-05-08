package org.example.kingdomCards;

import org.example.*;
import org.example.basicCards.Copper;

import java.util.List;

public class Bandit extends Card implements Action, Attack {
    public Bandit() {
        cost = 5;
        name = "bandit";
        startingAmount = 10;
    }

    @Override
    public void play(Game game, Player player) {
        player.gainToDiscard("gold", game.getSupply());
        attack(game, player);
    }

    @Override
    public void attack(Game game, Player player) {
        for (Player opponent : game.getPlayers()) {
            List<Card> topTwo = opponent.reveal(2);
            System.out.println("top two cards of " + opponent.getName() + "'s deck: " + topTwo);
            topTwo = topTwo.stream()
                    .filter(card -> (card instanceof Treasure && !(card instanceof Copper)))
                    .toList();
            if (topTwo.isEmpty()) {
                System.out.println("discarded both");
                opponent.getDiscard().add(opponent.getDeck().removeFirst());
                opponent.getDiscard().add(opponent.getDeck().removeFirst());
            } else if (topTwo.size() == 1) {
                System.out.println("trashed " + topTwo.getFirst().getName());
                game.getTrash().add(topTwo.getFirst());
                if (opponent.getDeck().getFirst() instanceof Treasure && !(opponent.getDeck().getFirst() instanceof Copper)) {
                    game.getTrash().add(opponent.getDeck().removeFirst());
                    opponent.getDiscard().add(opponent.getDeck().removeFirst());
                } else {
                    opponent.getDiscard().add(opponent.getDeck().removeFirst());
                    game.getTrash().add(opponent.getDeck().removeFirst());
                }
            } else {
                System.out.println(opponent.getName() + ", which card do you want to trash?");
                Card choice = opponent.inputCards(topTwo);
                if ((opponent.getDeck().getFirst().equals(choice))) {
                    game.getTrash().add(opponent.getDeck().removeFirst());
                    opponent.getDiscard().add(opponent.getDeck().removeFirst());
                } else {
                    opponent.getDiscard().add(opponent.getDeck().removeFirst());
                    game.getTrash().add(opponent.getDeck().removeFirst());
                }
            }
        }
    }

    @Override
    public String description() {
        return "action, attack:\ngain a gold\neach other player reveals the top 2 cards of their deck, trashes a revealed treasure other than copper, and discards the rest\ncost: 5";
    }

    @Override
    public Card copy() {
        return null;
    }
}
