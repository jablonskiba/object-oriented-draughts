package com.jablonskiba;

import org.junit.Test;

import static org.junit.Assert.*;

import com.jablonskiba.models.Position;

public class PositionTest {

    @Test
    public void testAddition() {
        assertEquals(new Position(5, 4).add(new Position(-3, 6)), new Position(2, 10));
    }

    @Test
    public void testSubtraction() {
        assertEquals(new Position(5, 4).subtract(new Position(-3, 6)), new Position(8, -2));
    }

    @Test
    public void testNormalisation() {
        assertEquals(new Position(5, 4).normalise(), new Position(1, 1));
        assertEquals(new Position(-8, 0).normalise(), new Position(-1, 0));
    }

}
