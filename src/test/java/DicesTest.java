import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DicesTest {

    Dices dices = new Dices();

    @Test
    void roll() {
        dices.roll();
        assertTrue(dices.getFace1() < 7 && dices.getFace1() > 0);
        assertTrue(dices.getFace2() < 7 && dices.getFace2() > 0);
    }

    @Test
    void returnSum() {
        assertEquals(dices.returnSum(), dices.getFace1() + dices.getFace2());
    }


}