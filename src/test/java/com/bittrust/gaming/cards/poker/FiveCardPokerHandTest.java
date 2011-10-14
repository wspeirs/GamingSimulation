package com.bittrust.gaming.cards.poker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bittrust.gaming.cards.Card;
import com.bittrust.gaming.cards.Card.Suite;
import com.bittrust.gaming.cards.poker.FiveCardPokerHand;
import com.bittrust.gaming.cards.poker.FiveCardPokerHand.Hand;

public class FiveCardPokerHandTest {

    @Test
    public void testHighCard() {
        Card c1 = new Card(1, Suite.CLUB);
        Card c2 = new Card(2, Suite.HEART);
        Card c3 = new Card(3, Suite.HEART);
        Card c4 = new Card(4, Suite.HEART);
        Card c5 = new Card(7, Suite.HEART);
        
        FiveCardPokerHand pokerHand = new FiveCardPokerHand(new Card[] { c1, c2, c3, c4, c5 });
        
        assertEquals(Hand.HIGH_CARD, pokerHand.getHand());
    }

    @Test
    public void testPair() {
        Card c1 = new Card(1, Suite.CLUB);
        Card c2 = new Card(1, Suite.HEART);
        Card c3 = new Card(2, Suite.HEART);
        Card c4 = new Card(3, Suite.HEART);
        Card c5 = new Card(4, Suite.HEART);
        
        FiveCardPokerHand pokerHand = new FiveCardPokerHand(new Card[] { c1, c2, c3, c4, c5 });
        
        assertEquals(Hand.PAIR, pokerHand.getHand());
    }

    @Test
    public void testTwoPair() {
        Card c1 = new Card(1, Suite.CLUB);
        Card c2 = new Card(1, Suite.HEART);
        Card c3 = new Card(4, Suite.HEART);
        Card c4 = new Card(2, Suite.HEART);
        Card c5 = new Card(2, Suite.HEART);
        
        FiveCardPokerHand pokerHand = new FiveCardPokerHand(new Card[] { c1, c2, c3, c4, c5 });
        
        assertEquals(Hand.TWO_PAIR, pokerHand.getHand());
    }

    @Test
    public void testThreeOfAKind() {
        Card c1 = new Card(1, Suite.CLUB);
        Card c2 = new Card(1, Suite.HEART);
        Card c3 = new Card(1, Suite.HEART);
        Card c4 = new Card(3, Suite.HEART);
        Card c5 = new Card(4, Suite.HEART);
        
        FiveCardPokerHand pokerHand = new FiveCardPokerHand(new Card[] { c1, c2, c3, c4, c5 });
        
        assertEquals(Hand.THREE_OF_A_KIND, pokerHand.getHand());
    }

    @Test
    public void testStraight() {
        Card c1 = new Card(1, Suite.CLUB);
        Card c2 = new Card(2, Suite.HEART);
        Card c3 = new Card(3, Suite.HEART);
        Card c4 = new Card(4, Suite.HEART);
        Card c5 = new Card(5, Suite.HEART);
        
        FiveCardPokerHand pokerHand = new FiveCardPokerHand(new Card[] { c1, c2, c3, c4, c5 });
        
        assertEquals(Hand.STRAIGHT, pokerHand.getHand());
    }

    @Test
    public void testFlush() {
        Card c1 = new Card(1, Suite.HEART);
        Card c2 = new Card(7, Suite.HEART);
        Card c3 = new Card(2, Suite.HEART);
        Card c4 = new Card(3, Suite.HEART);
        Card c5 = new Card(4, Suite.HEART);
        
        FiveCardPokerHand pokerHand = new FiveCardPokerHand(new Card[] { c1, c2, c3, c4, c5 });
        
        assertEquals(Hand.FLUSH, pokerHand.getHand());
    }

    @Test
    public void testFourOfAKind() {
        Card c1 = new Card(1, Suite.CLUB);
        Card c2 = new Card(1, Suite.HEART);
        Card c3 = new Card(1, Suite.HEART);
        Card c4 = new Card(1, Suite.HEART);
        Card c5 = new Card(4, Suite.HEART);
        
        FiveCardPokerHand pokerHand = new FiveCardPokerHand(new Card[] { c1, c2, c3, c4, c5 });
        
        assertEquals(Hand.FOUR_OF_A_KIND, pokerHand.getHand());
    }

    @Test
    public void testStraightFlush() {
        Card c1 = new Card(1, Suite.HEART);
        Card c2 = new Card(2, Suite.HEART);
        Card c3 = new Card(3, Suite.HEART);
        Card c4 = new Card(4, Suite.HEART);
        Card c5 = new Card(5, Suite.HEART);
        
        FiveCardPokerHand pokerHand = new FiveCardPokerHand(new Card[] { c1, c2, c3, c4, c5 });
        
        assertEquals(Hand.STRAIGHT_FLUSH, pokerHand.getHand());
    }

}
