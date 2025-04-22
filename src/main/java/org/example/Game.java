package org.example;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Game {
    protected List<Player> players = new ArrayList<>();
    protected List<Card> trash = new ArrayList<>();
    protected Supply supply;

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("how many players? (1-6) \n");
        int numOfPlayers = sc.nextInt();
        while (numOfPlayers < 1 || numOfPlayers > 6) {
            System.out.println("incorrect");
            numOfPlayers = sc.nextInt();
        }
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("player " + (i + 1) + "'s name: ");
            String name = sc.next();
            players.add(new GuestPlayer(name));
        }
        while (true) {
            for (Player player : players) {
                player.ActionPhase(this);
                player.BuyPhase(this);
                player.pickUp();
//                for ()
//                if (supply.cards.get("province").isEmpty() || )
            }
        }
    }
}
