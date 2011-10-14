/**
 * @author William R. Speirs <bill.speirs@gmail.com>
 */
package com.bittrust.gaming.cards;

import java.util.ArrayList;

/**
 * Represents a deck of 52 cards.
 */
public class Deck {
    public static final int DECK_SIZE = 52;
    private ArrayList<Card> cards;
    
    /**
     * Creates a deck of cards in sorted order.
     */
    public Deck() {
        cards = new ArrayList<Card>(DECK_SIZE);
        
        // generate the hearts
        for(int i=1; i < 14; ++i) {
            cards.add(new Card(i, Card.Suite.HEART));
        }
        
        // generate the diamonds
        for(int i=1; i < 14; ++i) {
            cards.add(new Card(i, Card.Suite.DIAMOND));
        }
        
        // generate the clubs
        for(int i=1; i < 14; ++i) {
            cards.add(new Card(i, Card.Suite.CLUB));
        }
        
        // generate the spades
        for(int i=1; i < 14; ++i) {
            cards.add(new Card(i, Card.Suite.SPADE));
        }
    }
    
    public void shuffle() {
        
    }
}
