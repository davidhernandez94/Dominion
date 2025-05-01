import org.example.Card;
import org.example.basicCards.Copper;
import org.example.basicCards.Gold;
import org.example.basicCards.Silver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BasicTreasureTest {
    @Test
    void testPay() {
        Copper copper = new Copper();
        Silver silver = new Silver();
        Gold gold = new Gold();
        Assertions.assertEquals(copper.pay(null, null), 1);
        Assertions.assertEquals(silver.pay(null, null), 2);
        Assertions.assertEquals(gold.pay(null, null), 3);
    }
}
