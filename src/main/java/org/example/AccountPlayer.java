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

    public void login() {
        Scanner sc = new Scanner(System.in);
        File file = new File("src/main/resources/accounts.csv");
        Map<String, String> accounts = getAccounts();
        System.out.println("enter username: ");
        String username = sc.nextLine();
        while (!accounts.containsKey(username)) {
            System.out.println("username does not exist, try again: ");
            username = sc.nextLine();
        }
        System.out.println("enter your password:");
        String password = sc.nextLine();
        while (!password.equals(accounts.get(username))) {
            System.out.println("incorrect password, try again: ");
            password = sc.nextLine();
        }
    }

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

    public void exportPlayer() {
        File file = new File("src/main/resources/accounts.csv");
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.append(this.userName + "," + this.password + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getAccounts() {
        Map<String, String> result = new HashMap<>();
        File file = new File("src/main/resources/accounts.csv");
        Scanner sysScanner = new Scanner(System.in);
        try (Scanner csvScanner = new Scanner(file)) {
            while (csvScanner.hasNext()) {
                String[] line = csvScanner.nextLine().split(",");
                result.putIfAbsent(line[0], line[1]);
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
        return Objects.hash(super.hashCode(), userName, password, gamesWon, gamesLost);
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
