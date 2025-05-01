package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AccountPlayer extends Player {
    private String userName;
    private String password;
    private int gamesWon;
    private int gamesLost;

    public AccountPlayer(String name) {
        super(name);
    }

    public AccountPlayer(String name, Game game) {
        super(name, game);
        userName = makeUsername();
        password = makePassword();
        gamesWon = 0;
        gamesLost = 0;
        exportPlayer();
    }

    public AccountPlayer(String name, Game game, String userName, String password, int gamesWon, int gamesLost) {
        super(name, game);
        this.userName = userName;
        this.password = password;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
    }

    /**
     * allows account player to log in to their account through the terminal by reading the accounts.csv file.
     */
    public void login() {
        Scanner sc = new Scanner(System.in);
        File file = new File("src/main/resources/accounts.csv");
        Map<String, AccountPlayer> accounts = getAccounts();
        System.out.println("enter username: ");
        String username = sc.nextLine();
        while (!accounts.containsKey(username)) {
            System.out.println("username does not exist, try again: ");
            username = sc.nextLine();
        }
        System.out.println("enter your password:");
        String password = sc.nextLine();
        while (!password.equals(accounts.get(username).getPassword())) {
            System.out.println("incorrect password, try again: ");
            password = sc.nextLine();
        }
    }

    /**
     * creates new username through the terminal
     * username must contain only letters and numbers and must be at least 8 characters long
     * @return new username
     */
    public String makeUsername() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your new username: (must contain only letters and numbers, no spaces, at least 8 characters) ");
        String username = sc.nextLine();
        while (username.length() < 8 || !username.matches("^[a-zA-Z0-9]+$") || getAccounts().containsKey(username)) {
            if (username.length() < 8 || !username.matches("^[a-zA-Z0-9]+$")) {
                System.out.println("invalid, try again:");
            } else {
                System.out.println("username already exists, try again:");
            }
            username = sc.nextLine();
        }
        return username;
    }

    /**
     * creates new password through the terminal
     * password must contain only letters and numbers and must be at least 8 characters long
     * @return new password
     */
    public String makePassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your new password: (must contain only letters and numbers, no spaces, at least 8 characters) ");
        String password = sc.nextLine();
        while (password.length() < 8 || !password.matches("^[a-zA-Z0-9]+$")) {
            System.out.println("invalid, try again:");
            password = sc.nextLine();
        }
        return password;
    }

    /**
     * writes account player information to the accounts.csv file
     * format: username,password,games won,games lost
     */
    public void exportPlayer() {
        File file = new File("src/main/resources/accounts.csv");
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.append(this.userName + "," + this.password + "," + this.gamesWon + "," + this.gamesLost + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * gets all accounts in the csv file and puts them in a map
     * @return a map where the key is a string username and the value is a string password
     */
    public Map<String, AccountPlayer> getAccounts() {
        Map<String, AccountPlayer> result = new HashMap<>();
        File file = new File("src/main/resources/accounts.csv");
        Scanner sysScanner = new Scanner(System.in);
        try (Scanner csvScanner = new Scanner(file)) {
            while (csvScanner.hasNext()) {
                String[] line = csvScanner.nextLine().split(",");
                String name = null;
                Game game = null;
                String username = line[0];
                String password = line[1];
                int gamesWon = Integer.parseInt(line[2]);
                int gamesLost = Integer.parseInt(line[3]);
                AccountPlayer accountPlayer = new AccountPlayer(name, game, username, password, gamesWon, gamesLost);
                result.putIfAbsent(username, accountPlayer);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountPlayer that = (AccountPlayer) o;
        return gamesWon == that.gamesWon && gamesLost == that.gamesLost && Objects.equals(userName, that.userName) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash((Object) super.hashCode(), userName, password, (Object) gamesWon, (Object) gamesLost);
    }

    private String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }
}
