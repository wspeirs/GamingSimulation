/**
 * @author William R. Speirs <bill.speirs@gmail.com>
 */
package com.bittrust.gaming.cards;

/**
 * Represents a single card in a deck.
 */
public class Card implements Comparable<Card> {
    public enum Suite {
        HEART,
        DIAMOND,
        CLUB,
        SPADE
    }
    
    public final static int LOW_VALUE = 1;
    public final static int HIGH_VALUE = 13;
    
    private final Suite suite;
    
    /**
     * 1 = Ace
     * 2 - 10 = regular value
     * 11 = Jack
     * 12 = Queen
     * 13 = King
     */
    private final int value;
    
    public Card(int value, Suite suite) {
        if(value < LOW_VALUE || value > HIGH_VALUE) {
            throw new IllegalArgumentException("Value is out of the range [1, 13]");
        }
        
        this.value = value;
        this.suite = suite;
    }

    public final Suite getSuite() {
        return suite;
    }

    public final int getValue() {
        return value;
    }

    public int compareTo(Card card) {
        if(this.value == card.value) {
            if(this.suite == card.suite) {
                return 0;
            } else if(this.suite == Suite.HEART) {
                return -1;
            } else if(this.suite == Suite.DIAMOND) {
                if(card.suite == Suite.HEART) {
                    return 1;
                } else {
                    return -1;
                }
            } else if(this.suite == Suite.CLUB) {
                if(card.suite == Suite.HEART || card.suite == Suite.DIAMOND) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return 1;
            }
        } else if(this.value < card.value) {
           return -1;
        } else {
            return 1;
        }
    }
}
