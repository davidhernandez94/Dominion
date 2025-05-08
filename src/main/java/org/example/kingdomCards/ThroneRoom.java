package org.example.kingdomCards;

import org.example.Action;
import org.example.Card;
import org.example.Game;
import org.example.Player;

import java.util.ArrayList;
import java.util.List;

public class ThroneRoom extends Card implements Action {
    public ThroneRoom() {
        cost = 4;
        name = "throne room";
        startingAmount = 10;
    }

    @Override
    public void play(Game game, Player player) {
        System.out.println(description());
        List<Card> actionCards = new ArrayList<>();
        System.out.println("(if you don't want to, press any letter and enter)");
        for (Card card : player.getHand()) {
            if (card instanceof Action) {
                actionCards.add(card);
            }
        }
        Action card = (Action) player.inputCards(actionCards);
        card.play(game, player);
        card.play(game, player);
    }

    @Override
    public String description() {
        return "action:\nyou may play an action card from your hand twice\ncost: 4";
    }

    @Override
    public Card copy() {
        return new ThroneRoom();
    }
}
