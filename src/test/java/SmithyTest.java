import org.example.Card;
import org.example.GuestPlayer;
import org.example.Player;
import org.example.basicCards.Copper;
import org.example.kingdomCards.Festival;
import org.example.kingdomCards.Laboratory;
import org.example.kingdomCards.Smithy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SmithyTest {
    @Test
    void testPlay() {
        Player player = new GuestPlayer("a");
        List<Card> deck = new ArrayList<>();
        List<Card> hand = new ArrayList<>();
        deck.add(new Festival());
        deck.add(new Copper());
        deck.add(new Copper());
        player.setHand(hand);
        player.setDeck(deck);
        Smithy smithy = new Smithy();
        smithy.play(null, player);

        Player expected = new GuestPlayer("a");
        List<Card> expectedHand = new ArrayList<>();
        List<Card> expectedDeck = new ArrayList<>();
        expectedHand.add(new Festival());
        expectedHand.add(new Copper());
        expectedHand.add(new Copper());
        expected.setDeck(expectedDeck);
        expected.setHand(expectedHand);
        Assertions.assertEquals(expected, player);
    }
}
