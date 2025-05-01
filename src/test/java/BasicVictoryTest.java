import org.example.basicCards.Duchy;
import org.example.basicCards.Estate;
import org.example.basicCards.Province;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasicVictoryTest {
    @Test
    void testGetPoints() {
        Estate estate = new Estate();
        Duchy duchy = new Duchy();
        Province province = new Province();
        Assertions.assertEquals(1, estate.getPoints(null, null));
        Assertions.assertEquals(3, duchy.getPoints(null, null));
        Assertions.assertEquals(6, province.getPoints(null, null));
    }
}
