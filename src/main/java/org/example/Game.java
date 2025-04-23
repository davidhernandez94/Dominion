package org.example;

import java.util.*;

public class Game {
    protected List<Player> players = new ArrayList<>();
    protected List<Card> trash = new ArrayList<>();
    protected Supply supply = new Supply();
    protected Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("how many players? (1-6)");
        int numOfPlayers = sc.nextInt();
        sc.nextLine();
        while (numOfPlayers < 1 || numOfPlayers > 6) {
            System.out.println("incorrect");
            numOfPlayers = sc.nextInt();
            sc.nextLine();
        }
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("player " + (i + 1) + "'s name: ");
            String name = sc.nextLine();
            players.add(new GuestPlayer(name, this));
            players.get(i).gainToDiscard(7, "copper", supply);
            players.get(i).gainToDiscard(3, "estate", supply);
        }
        while (true) {
            for (Player player : players) {
                System.out.println("\n" + player.name.toUpperCase() + "'S TURN");
                player.ActionPhase(this);
                player.BuyPhase(this);
                player.pickUp();
                int emptyPiles = 0;
                for (Queue<Card> list : supply.cards.values()) {
                    if (list.isEmpty()) {
                        emptyPiles++;
                    }
                }
                if (supply.cards.get("province").isEmpty() || emptyPiles >= 3) {
                    break;
                }
            }
        }
    }
}
