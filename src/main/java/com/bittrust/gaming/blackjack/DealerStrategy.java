package com.bittrust.gaming.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.bittrust.gaming.cards.Card;
import com.bittrust.gaming.cards.Deck;

public class DealerStrategy {

    private final Deck deck;
    private final List<Card> cards;

    public DealerStrategy(Deck deck, Card upCard) {
        this.deck = deck;
        cards = new ArrayList<Card>(6);

        cards.add(upCard);
    }

    public List<Card> play() {
        while(getValue() <= 17) {
            cards.add(deck.dealCard());
        }

        return cards;
    }

    public int getValue() {
        int ret = 0;

        for(Card card:cards) {
            ret += card.getValue() > 10 ? 10 : card.getValue();
        }

        return ret;
    }
}
