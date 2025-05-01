import org.example.Card;
import org.example.GuestPlayer;
import org.example.Player;
import org.example.kingdomCards.Festival;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FestivalTest {
    @Test
    void testPlay() {
        Player player = new GuestPlayer("a");
        player.setMoney(3);
        player.setActions(2);
        player.setBuys(1);
        Festival card = new Festival();
        card.play(null, player);

        Player expected = new GuestPlayer("a");
        expected.setBuys(2);
        expected.setActions(4);
        expected.setMoney(5);
        Assertions.assertEquals(expected, player);
    }
}
