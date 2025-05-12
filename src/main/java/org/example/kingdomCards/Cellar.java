package org.example.kingdomCards;

import org.example.Action;
import org.example.Card;
import org.example.Game;
import org.example.Player;

public class Cellar extends Card implements Action {
    public Cellar() {
        cost = 2;
        name = "cellar";
        startingAmount = 10;
    }

    @Override
    public void play(Game game, Player player) {
        System.out.println(description());
        player.addActions(1);
        int cardsDiscarded = 0;
        while (true) {
            System.out.println("which card do you want to discard? (if none, enter any character)");
            Card choice = player.inputCards(player.getHand());
            if (choice == null) {
                break;
            }
            cardsDiscarded++;
            player.getDiscard().add(choice);
            player.getHand().remove(choice);
        }
        System.out.println("draws " + cardsDiscarded + " cards");
        player.draw(cardsDiscarded);
    }

    @Override
    public String description() {
        return "action:\n+1 action\ndiscard any number of cards\n+1 card per card discarded";
    }

    @Override
    public Card copy() {
        return new Cellar();
    }
}
