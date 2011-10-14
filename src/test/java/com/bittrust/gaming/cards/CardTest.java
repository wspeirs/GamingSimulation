package com.bittrust.gaming.cards;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bittrust.gaming.cards.Card.Suite;

public class CardTest {

    @Test
    public void testCompareToSameCards() {
        Card c = new Card(1, Suite.HEART);
        
        assertEquals(0, c.compareTo(c));
    }
    
    @Test
    public void testCompareToValues() {
        Card c1 = new Card(1, Suite.HEART);
        Card c2 = new Card(12, Suite.HEART);
        
        // check both ways
        assertEquals(-1, c1.compareTo(c2));
        assertEquals(1, c2.compareTo(c1));
    }
    
    @Test
    public void testCompareToSuites() {
        Card h = new Card(10, Suite.HEART);
        Card d = new Card(10, Suite.DIAMOND);
        Card c = new Card(10, Suite.CLUB);
        Card s = new Card(10, Suite.SPADE);
        
        // compare hearts
        assertEquals(0, h.compareTo(h));
        assertEquals(-1, h.compareTo(d));
        assertEquals(-1, h.compareTo(c));
        assertEquals(-1, h.compareTo(s));
        
        // compare diamonds
        assertEquals(1, d.compareTo(h));
        assertEquals(0, d.compareTo(d));
        assertEquals(-1, d.compareTo(c));
        assertEquals(-1, d.compareTo(s));
        
        // compare clubs
        assertEquals(1, c.compareTo(h));
        assertEquals(1, c.compareTo(d));
        assertEquals(0, c.compareTo(c));
        assertEquals(-1, c.compareTo(s));
        
        // compare spades
        assertEquals(1, s.compareTo(h));
        assertEquals(1, s.compareTo(d));
        assertEquals(1, s.compareTo(c));
        assertEquals(0, s.compareTo(s));
    }

}
