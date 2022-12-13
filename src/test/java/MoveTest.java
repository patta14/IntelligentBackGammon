import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    Move test1 = new Move(5, 6, false, false);
    Move test2 = new Move(5, 6, false, false, true);

    @Test
    void getPreviousPosition() {
        assertEquals(5, test1.getPreviousPosition());
    }

    @Test
    void setPreviousPosition() {
        test1.setPreviousPosition(6);
        assertEquals(6, test1.getPreviousPosition());
        test1.setPreviousPosition(5);
        assertEquals(5, test1.getPreviousPosition());
    }

    @Test
    void getNewPosition() {
        assertEquals(6, test1.getNewPosition());
    }

    @Test
    void setNewPosition() {
        test1.setNewPosition(5);
        assertEquals(5, test1.getNewPosition());
        test1.setNewPosition(6);
        assertEquals(6, test1.getNewPosition());
    }

    @Test
    void isCanKick() {
        assertFalse(test1.isCanKick());
    }

    @Test
    void setCanKick() {
        test1.setCanKick(true);
        assertTrue(test1.isCanKick());
        test1.setCanKick(false);
        assertFalse(test1.isCanKick());
    }

    @Test
    void isLegal() {
        assertFalse(test2.isLegal());
    }

    @Test
    void setLegal() {
        test1.setLegal(true);
        assertTrue(test1.isLegal());
        test1.setLegal(false);
        assertFalse(test1.isLegal());
    }

    @Test
    void isOut() {
        assertTrue(test2.isOut());
    }

    @Test
    void setOut() {
        test1.setOut(true);
        assertTrue(test1.isOut());
        test1.setOut(false);
        assertFalse(test1.isOut());
    }
}