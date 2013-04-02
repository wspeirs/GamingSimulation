package com.bittrust.gaming.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.bittrust.gaming.cards.Card;
import com.bittrust.gaming.cards.Deck;

public class BasicStrategy {

    private final Deck deck;
    private final Card upCard;
    private final List<Card> cards;

    public BasicStrategy(Deck deck, Card upCard, Card card1, Card card2) {
        cards = new ArrayList<Card>(6);

        cards.add(card1);
        cards.add(card2);

        this.upCard = upCard;
        this.deck = deck;
    }

    public List<Card> play() {
        if(getValue() > 12 && upCard.getValue() < 7) {
            return cards;
        }

        while(getValue() < 17) {
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
