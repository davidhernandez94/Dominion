import org.example.Card;
import org.example.GuestPlayer;
import org.example.Player;
import org.example.basicCards.Copper;
import org.example.kingdomCards.Market;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MarketTest {
    @Test
    void testPlay() {
        Player player = new GuestPlayer("a");
        player.setActions(2);
        List<Card> deck = new ArrayList<>();
        List<Card> hand = new ArrayList<>();
        deck.add(new Copper());
        deck.add(new Copper());
        player.setHand(hand);
        player.setDeck(deck);
        player.setMoney(3);
        player.setActions(2);
        player.setBuys(1);
        Market market = new Market();
        market.play(null, player);

        Player expected = new GuestPlayer("a");
        expected.setActions(3);
        expected.setMoney(4);
        expected.setActions(3);
        expected.setBuys(2);
        List<Card> expectedHand = new ArrayList<>();
        List<Card> expectedDeck = new ArrayList<>();
        expectedDeck.add(new Copper());
        expectedHand.add(new Copper());
        expected.setDeck(expectedDeck);
        expected.setHand(expectedHand);
        Assertions.assertEquals(expected, player);
    }
}
