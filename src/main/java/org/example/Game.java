package org.example;

import java.util.*;

public class Game {
    protected List<Player> players = new ArrayList<>();
    protected List<Card> trash = new ArrayList<>();
    protected Supply supply = new Supply();
    protected Scanner sc = new Scanner(System.in);
    private static List<AccountPlayer> accountPlayers = new ArrayList<>();

    /**
     * game initialisation:
     * through the terminal, chooses the amount of players, their names
     * logs in the players with accounts
     * gives each player their starting deck
     */
    public void start() {
        System.out.println("how many players? (1-6)");
        int numOfPlayers = sc.nextInt();
        sc.nextLine();
        while (numOfPlayers < 1 || numOfPlayers > 6) {
            System.out.println("incorrect");
            numOfPlayers = sc.nextInt();
            sc.nextLine();
        }

        //TODO log in account players

        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("player " + (i + 1) + "'s name: ");
            String name = sc.nextLine();
            players.add(new GuestPlayer(name, this));
            players.get(i).gainToDiscard(7, "copper", supply);
            players.get(i).gainToDiscard(3, "estate", supply);
            players.get(i).draw(5);
            players.get(i).setActions(1);
            players.get(i).setBuys(1);
            players.get(i).setMoney(1);
        }
        run();
    }

    /**
     * game loop:
     * each player rotates through their action phase, buy phase, and pick up
     * until one of two conditions is met: either three supply piles are empty
     * or the province pile is empty
     */
    public void run() {
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
                    System.out.println("GAME IS DONE");
                    end();
                }
            }
        }
    }

    /**
     * end of the game: victory points are counted and winner is determined.
     * Players with accounts are updated in the accounts.csv file
     */
    public void end() {
        for (Player player : players) {
            player.getDeck().addAll(player.getDiscard());
            player.getDiscard().clear();
            player.getDeck().addAll(player.getHand());
            player.getHand().clear();
            player.getDeck().addAll(player.getInPlay());
            player.getInPlay().clear();
            player.countVictoryPointsInDeck();
        }
        Collections.sort(players);
        System.out.println("Winner: " + players.getFirst());
        for (Player player : players) {
            if (player instanceof AccountPlayer accountPlayer) {
                if (players.getFirst() == accountPlayer) {
                    accountPlayer.setGamesWon(accountPlayer.getGamesWon() + 1);
                } else {
                    accountPlayer.setGamesLost(accountPlayer.getGamesLost() + 1);
                }
                // TODO: make it update the csv
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getTrash() {
        return trash;
    }

    public void setTrash(List<Card> trash) {
        this.trash = trash;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public static List<AccountPlayer> getAccountPlayers() {
        return accountPlayers;
    }

    public static void setAccountPlayers(List<AccountPlayer> accountPlayers) {
        Game.accountPlayers = accountPlayers;
    }
}
