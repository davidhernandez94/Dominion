package org.example;

import org.example.kingdomCards.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Game {
    protected List<Player> players = new ArrayList<>();
    protected List<Card> trash = new ArrayList<>();
    protected Supply supply;
    protected Scanner sc = new Scanner(System.in);
    private static Map<String, AccountPlayer> accountPlayers = AccountPlayer.getAccounts();

    /**
     * game initialisation:
     * through the terminal, chooses the amount of players, their names
     * logs in the players with accounts
     * gives each player their starting deck
     */
    public void start() {
        List<Card> kingdomCards = new ArrayList<>();
        /*
        usually, there are 10 types of kingdom cards per game,
        but these are all the ones I coded so far.
        in the original game, there are 26 different types
         */
        kingdomCards.add(new Festival());
        kingdomCards.add(new Laboratory());
        kingdomCards.add(new Market());
        kingdomCards.add(new Smithy());
        kingdomCards.add(new Village());
        kingdomCards.add(new Bandit());
        kingdomCards.add(new ThroneRoom());
        kingdomCards.add(new Witch());
        supply = new Supply(kingdomCards);
        System.out.println("how many players? (1-6)");
        String choice = sc.nextLine();
        while (!choice.matches("^[2-6]$")) {
            System.out.println("invalid, try again: ");
            choice = sc.nextLine();
        }
        int numOfPlayers = Integer.parseInt(choice);
        while (numOfPlayers < 1 || numOfPlayers > 6) {
            System.out.println("incorrect");
            numOfPlayers = sc.nextInt();
            sc.nextLine();
        }
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("player " + (i + 1) + ", do you want to sign in, create an account, or play as a guest? (a/b/c)");
            String hasAccount = sc.nextLine();
            while (!hasAccount.matches("^[abc]$")) {
                System.out.println("invalid, you must type a, b, or c");
                hasAccount = sc.nextLine();
            }
            System.out.println("player " + (i + 1) + "'s name: ");
            String name = sc.nextLine();
            Player player;
            if (hasAccount.equals("a")) {
                player = AccountPlayer.login();
                player.setName(name);
                player.setGame(this);
            } else if (hasAccount.equals("b")) {
                player = new AccountPlayer(name, this, AccountPlayer.makeUsername(), AccountPlayer.makePassword(), 0, 0);
                ((AccountPlayer) player).exportPlayer();
            } else {
                player = new GuestPlayer(name, this);
            }
            players.add(player);
            players.get(i).gainToDiscard(7, "copper", supply);
            players.get(i).gainToDiscard(3, "estate", supply);
            players.get(i).draw(5);
            players.get(i).setActions(1);
            players.get(i).setBuys(1);
            players.get(i).setMoney(0);
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
                    break;
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
        File file = new File("src/main/resources/accounts.csv");

        // empty the file
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // update stats of accountPlayer's
        for (Player player : players) {
            if (player instanceof AccountPlayer accountPlayer) {
                if (players.getFirst() == accountPlayer) {
                    accountPlayer.setGamesWon(accountPlayer.getGamesWon() + 1);
                } else {
                    accountPlayer.setGamesLost(accountPlayer.getGamesLost() + 1);
                }
                accountPlayers.put(accountPlayer.getUserName(), accountPlayer);
                accountPlayer.exportPlayer();

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

    public static Map<String, AccountPlayer> getAccountPlayers() {
        return accountPlayers;
    }

    public static void setAccountPlayers(Map<String, AccountPlayer> accountPlayers) {
        Game.accountPlayers = accountPlayers;
    }
}
