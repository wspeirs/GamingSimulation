/**
 * @author William R. Speirs <bill.speirs@gmail.com>
 */
package com.bittrust.gaming.cards.poker;

import java.util.Arrays;

import com.bittrust.gaming.cards.Card;
import com.bittrust.gaming.cards.Card.Suite;

/**
 * Represents a 5-card poker hand.
 */
public class FiveCardPokerHand {

    private final Card[] cards;
    private Hand hand;
    
    public enum Hand {
        HIGH_CARD,
        PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH
    }
    
    public FiveCardPokerHand(Card[] cards) {
        if(cards.length != 5) {
            throw new IllegalArgumentException("Poker hand must be 5 cards");
        }
        
        this.cards = cards;
        
        // sort the hand
        Arrays.sort(this.cards);
    }
    
    public Hand getHand() {
        if(hand != null) {
            return hand;
        }
        
        // figure out if we have a flush, straight, or straight-flush
        if(isFlush()) {
            hand = Hand.FLUSH;
        }
        
        if(isStraight()) {
            if(hand == Hand.FLUSH) {
                hand = Hand.STRAIGHT_FLUSH;
            } else {
                hand = Hand.STRAIGHT;
            }
        }
        
        // figure out what pair counts we have
        int highCount = 0;
        int lowCount = 0;
        int[] count = countValues();
        
        for(int i=Card.LOW_VALUE; i < count.length; ++i) {
            if(count[i] >= highCount) {
                lowCount = highCount;
                highCount = count[i];
            }
        }
        
        if(highCount == 4) {
            hand = Hand.FOUR_OF_A_KIND;
        } else if(highCount == 3) {
            if(lowCount == 2) {
                hand = Hand.FULL_HOUSE;
            } else {
                hand = Hand.THREE_OF_A_KIND;
            }
        } else if(highCount == 2) {
            if(lowCount == 2) {
                hand = Hand.TWO_PAIR;
            } else {
                hand = Hand.PAIR;
            }
        } else if(hand == null) {
            hand = Hand.HIGH_CARD;
        }
        
        return hand;
    }
    
    private int[] countValues() {
        int count[] = new int[Card.HIGH_VALUE + 1];
        
        for(Card c:cards) {
            count[c.getValue()]++;
        }

        return count;
    }
    
    private boolean isFlush() {
        Card.Suite suite = cards[0].getSuite();
        
        for(int i=1; i < cards.length; ++i) {
            if(cards[i].getSuite() != suite) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isStraight() {
        int value = cards[0].getValue();
        
        for(int i=1; i < cards.length; ++i) {
            if(value + i != cards[i].getValue()) {
                return false;
            }
        }
        
        return true;
    }
}
