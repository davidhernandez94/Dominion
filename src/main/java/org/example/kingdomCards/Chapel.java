package org.example.kingdomCards;

import org.example.Action;
import org.example.Card;
import org.example.Game;
import org.example.Player;

public class Chapel extends Card implements Action {
    public Chapel() {
        cost = 2;
        name = "chapel";
        startingAmount = 10;
    }

    @Override
    public void play(Game game, Player player) {
        System.out.println(description());
        for (int i = 0; i < 4; i++) {
            System.out.println("enter a card you want to trash: (if none, enter any character) ");
            Card choice = player.inputCards(player.getHand());
            if (choice == null) {
                return;
            }
            player.getHand().remove(choice);
            game.getTrash().add(choice);
        }
    }

    @Override
    public String description() {
        return "action:\ntrash up to 4 cards from your hand";
    }

    @Override
    public Card copy() {
        return new Chapel();
    }
}
