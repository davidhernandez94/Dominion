import org.example.AccountPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AccountPlayerTest {
    @Test
    void testGetAccounts() {
        File file = new File("src/main/resources/accounts.csv");
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.append("baba,hello,1,4\n");
            fw.append("abc,password,3,9\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AccountPlayer player = new AccountPlayer("a");
        Map<String, AccountPlayer> players = player.getAccounts();
        Map<String, AccountPlayer> expected = new HashMap<>();
        expected.putIfAbsent("baba", new AccountPlayer(null, null, "baba", "hello", 1, 4));
        expected.putIfAbsent("abc", new AccountPlayer(null, null, "abc", "password", 3, 9));
        Assertions.assertEquals(players, expected);
    }

    @Test
    void testExport() {
        File file = new File("src/main/resources/accounts.csv");
        AccountPlayer player = new AccountPlayer(null, null, "awa", "h", 5, 2);
        player.exportPlayer();
        try (Scanner sc = new Scanner(file)) {
            String lastLine = "";
            while (sc.hasNext()) {
                lastLine = sc.nextLine();
            }
            String[] line = lastLine.split(",");
            String[] expected = {"awa", "h", "5", "2"};
            Assertions.assertEquals(Arrays.toString(line), Arrays.toString(expected));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
