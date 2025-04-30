import org.example.Card;
import org.example.GuestPlayer;
import org.example.Player;
import org.example.Supply;
import org.example.basicCards.*;
import org.example.kingdomCards.Festival;
import org.example.kingdomCards.Laboratory;
import org.example.kingdomCards.Smithy;
import org.example.kingdomCards.Village;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {
    @Test
    void testReveal1() {
        Player player = new GuestPlayer("a");
        List<Card> deck = new ArrayList<>();
        deck.add(new Copper());
        deck.add(new Silver());
        player.setDeck(deck);
        Card actual = player.reveal();
        Card expected = new Copper();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testReveal2() {
        Player player = new GuestPlayer("a");
        List<Card> deck = new ArrayList<>();
        player.setDeck(deck);
        List<Card> discard = new ArrayList<>();
        discard.add(new Estate());
        player.setDiscard(discard);
        Card actual = player.reveal();
        Card expected = new Estate();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testReveal3() {
        Player player = new GuestPlayer("a");
        List<Card> deck = new ArrayList<>();
        deck.add(new Estate());
        deck.add(new Duchy());
        deck.add(new Gold());
        player.setDeck(deck);
        List<Card> actual = player.reveal(2);
        List<Card> expected = new ArrayList<>();
        expected.add(new Estate());
        expected.add(new Duchy());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testReveal4() {
        Player player = new GuestPlayer("a");
        List<Card> deck = new ArrayList<>();
        List<Card> discard = new ArrayList<>();
        deck.add(new Estate());
        discard.add(new Duchy());
        player.setDeck(deck);
        player.setDiscard(discard);
        List<Card> actual = player.reveal(2);
        List<Card> expected = new ArrayList<>();
        expected.add(new Estate());
        expected.add(new Duchy());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDraw1() {
        Player player = new GuestPlayer("a");
        List<Card> deck = new ArrayList<>();
        List<Card> hand = new ArrayList<>();
        deck.add(new Estate());
        deck.add(new Gold());
        hand.add(new Duchy());
        player.setDeck(deck);
        player.setHand(hand);
        player.draw(2);

        List<Card> expectedHand = new ArrayList<>();
        List<Card> expectedDeck = new ArrayList<>();
        expectedHand.add(new Estate());
        expectedHand.add(new Gold());
        expectedHand.add(new Duchy());
        Player expected = new GuestPlayer("a");
        expected.setHand(hand);
        expected.setDeck(deck);
        Assertions.assertEquals(player, expected);
    }

    @Test
    void testDraw2() {
        Player player = new GuestPlayer("a");
        List<Card> deck = new ArrayList<>();
        List<Card> hand = new ArrayList<>();
        List<Card> discard = new ArrayList<>();
        deck.add(new Estate());
        deck.add(new Gold());
        discard.add(new Duchy());
        discard.add(new Duchy());
        player.setDeck(deck);
        player.setHand(hand);
        player.setDiscard(discard);
        player.draw(3);

        List<Card> expectedHand = new ArrayList<>();
        List<Card> expectedDeck = new ArrayList<>();
        List<Card> expectedDiscard = new ArrayList<>();
        expectedHand.add(new Estate());
        expectedHand.add(new Gold());
        expectedHand.add(new Duchy());
        Player expected = new GuestPlayer("a");
        expected.setHand(hand);
        expected.setDeck(deck);
        Assertions.assertEquals(player, expected);
    }

    @Test
    void testAddActions() {
        Player actual = new GuestPlayer("a");
        actual.setActions(3);
        actual.addActions(2);
        Player expected = new GuestPlayer("a");
        expected.setActions(5);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddMoney() {
        Player actual = new GuestPlayer("a");
        actual.setMoney(4);
        actual.addMoney(1);
        Player expected = new GuestPlayer("a");
        expected.setMoney(5);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddBuys() {
        Player actual = new GuestPlayer("a");
        actual.setBuys(1);
        actual.addBuys(3);
        Player expected = new GuestPlayer("a");
        expected.setBuys(4);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCountVictoryPointsInDeck() {
        Player actual = new GuestPlayer("a");
        List<Card> deck = new ArrayList<>();
        deck.add(new Estate());
        deck.add(new Estate());
        deck.add(new Estate());
        deck.add(new Duchy());
        deck.add(new Province());
        deck.add(new Curse());
        deck.add(new Curse());
        actual.setDeck(deck);
        actual.countVictoryPointsInDeck();

        Player expected = new GuestPlayer("a");
        expected.setVictoryPoints(10);

        Assertions.assertEquals(actual.getVictoryPoints(), expected.getVictoryPoints());
    }

    @Test
    void testCountActionCardsInHand() {
        Player player = new GuestPlayer("");
        List<Card> hand = new ArrayList<>();
        hand.add(new Festival());
        hand.add(new Curse());
        hand.add(new Copper());
        hand.add(new Duchy());
        hand.add(new Laboratory());
        hand.add(new Smithy());
        hand.add(new Gold());
        hand.add(new Village());
        player.setHand(hand);
        Assertions.assertEquals(4, player.countActionCardsInHand());
    }

    @Test
    void testCountTreasuresInHand() {
        Player player = new GuestPlayer("");
        List<Card> hand = new ArrayList<>();
        hand.add(new Festival());
        hand.add(new Curse());
        hand.add(new Copper());
        hand.add(new Duchy());
        hand.add(new Laboratory());
        hand.add(new Smithy());
        hand.add(new Gold());
        hand.add(new Village());
        player.setHand(hand);
        Assertions.assertEquals(2, player.countTreasuresInHand());
    }

    @Test
    void testCountVictoryCardsInHand() {
        Player player = new GuestPlayer("");
        List<Card> hand = new ArrayList<>();
        hand.add(new Festival());
        hand.add(new Curse());
        hand.add(new Copper());
        hand.add(new Duchy());
        hand.add(new Laboratory());
        hand.add(new Smithy());
        hand.add(new Gold());
        hand.add(new Village());
        player.setHand(hand);
        Assertions.assertEquals(1, player.countVictoryCardsInHand());
    }

    @Test
    void testGainToDiscard1() {
        Player actual = new GuestPlayer("a");
        Supply supply = new Supply();
        List<Card> discard = new ArrayList<>();
        actual.setDiscard(discard);
        actual.gainToDiscard(2, "copper", supply);

        List<Card> expectedDiscard = new ArrayList<>();
        expectedDiscard.add(new Copper());
        expectedDiscard.add(new Copper());
        Assertions.assertEquals(expectedDiscard, actual.getDiscard());
    }

    @Test
    void testGainToDiscard2() {
        Player actual = new GuestPlayer("a");
        Supply supply = new Supply();
        supply.getCards().get("copper").clear();
        supply.getCards().get("copper").add(new Copper());
        List<Card> discard = new ArrayList<>();
        actual.setDiscard(discard);

        List<Card> expected = new ArrayList<>();

        Assertions.assertFalse(actual.gainToDiscard(2, "copper", supply));
        Assertions.assertEquals(expected, actual.getDiscard());
    }

    @Test
    void testGainToDiscard3() {
        Player actual = new GuestPlayer("a");
        Supply supply = new Supply();
        List<Card> discard = new ArrayList<>();
        actual.setDiscard(discard);
        actual.gainToDiscard("copper", supply);

        List<Card> expectedDiscard = new ArrayList<>();
        expectedDiscard.add(new Copper());
        Assertions.assertEquals(expectedDiscard, actual.getDiscard());
    }

    @Test
    void testGainToHand1() {
        Player actual = new GuestPlayer("a");
        Supply supply = new Supply();
        List<Card> hand = new ArrayList<>();
        actual.setDiscard(hand);
        actual.gainToHand( "copper", supply, 2);

        List<Card> expected = new ArrayList<>();
        expected.add(new Copper());
        expected.add(new Copper());
        Assertions.assertEquals(expected, actual.getHand());
    }

    @Test
    void testGainToHand2() {
        Player actual = new GuestPlayer("a");
        Supply supply = new Supply();
        supply.getCards().get("copper").clear();
        supply.getCards().get("copper").add(new Copper());
        List<Card> hand = new ArrayList<>();
        actual.setHand(hand);

        List<Card> expected = new ArrayList<>();

        Assertions.assertFalse(actual.gainToHand( "copper", supply, 2));
        Assertions.assertEquals(expected, actual.getHand());
    }

    @Test
    void testGainToHand3() {
        Player actual = new GuestPlayer("a");
        Supply supply = new Supply();
        List<Card> hand = new ArrayList<>();
        actual.setHand(hand);
        actual.gainToHand("copper", supply);

        List<Card> expected = new ArrayList<>();
        expected.add(new Copper());
        Assertions.assertEquals(expected, actual.getHand());
    }

    @Test
    void testGainToTopOfDeck1() {
        Player actual = new GuestPlayer("a");
        Supply supply = new Supply();
        List<Card> deck = new ArrayList<>();
        actual.setDiscard(deck);
        actual.gainToTopOfDeck( "copper", supply);

        List<Card> expected = new ArrayList<>();
        expected.add(new Copper());
        Assertions.assertEquals(expected, actual.getDeck());
    }

    @Test
    void testGainTotopOfDeck2() {
        Player actual = new GuestPlayer("a");
        Supply supply = new Supply();
        supply.getCards().get("copper").clear();
        List<Card> deck = new ArrayList<>();
        actual.setHand(deck);

        List<Card> expected = new ArrayList<>();

        Assertions.assertFalse(actual.gainToTopOfDeck( "copper", supply));
        Assertions.assertEquals(expected, actual.getDeck());
    }

    @Test
    void testGainToTopOfDeck3() {
        Player actual = new GuestPlayer("a");
        Supply supply = new Supply();
        List<Card> deck = new ArrayList<>();
        actual.setHand(deck);
        actual.gainToTopOfDeck("copper", supply);

        List<Card> expected = new ArrayList<>();
        expected.add(new Copper());
        Assertions.assertEquals(expected, actual.getDeck());
    }
}
