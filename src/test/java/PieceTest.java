import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    Piece piece1 = new Piece(true);
    Piece piece2 = new Piece(false);

    @Test
    void isColour() {
        assertTrue(piece1.isColour());
        assertFalse(piece2.isColour());
    }


    @Test
    void setColour() {
        piece1.setColour(false);
        piece2.setColour(true);
        assertTrue(piece2.isColour());
        assertFalse(piece1.isColour());
        piece2.setColour(false);
        piece1.setColour(true);
        assertTrue(piece1.isColour());
        assertFalse(piece2.isColour());
    }
}