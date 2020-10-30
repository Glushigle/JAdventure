package com.jadventure.game.navigation;

import org.junit.Test;

import com.jadventure.game.monsters.Goblin;

import static org.junit.Assert.*;

public class MyTestCases {

    @Test
    public void testEqual() {
    	Goblin goblin = new Goblin(5);
    	Coordinate a = new Coordinate("1,2,3");
        Coordinate b = new Coordinate("1,2,3");
        Coordinate c = new Coordinate("-1,2,3");
        Coordinate d = new Coordinate("1,-2,3");
        Coordinate e = new Coordinate("1,-2,3");
        assertFalse(a.equals(null));
        assertFalse(a.equals(goblin));
        assertTrue(a.equals(a));
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
        assertFalse(a.equals(d));
        assertFalse(a.equals(e));
    }
}
