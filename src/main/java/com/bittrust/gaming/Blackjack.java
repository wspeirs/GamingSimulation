package com.bittrust.gaming;

import java.text.MessageFormat;

import com.bittrust.gaming.blackjack.BasicStrategy;
import com.bittrust.gaming.blackjack.DealerStrategy;
import com.bittrust.gaming.cards.Card;
import com.bittrust.gaming.cards.Deck;

public class Blackjack {

    private Deck deck = new Deck();
    private double houseMoney;
    private double[] playerMoney;
    private BasicStrategy[] players;

    public Blackjack() {
        houseMoney = 200;
        playerMoney = new double[5];
        players = new BasicStrategy[5];

        for(int i=0; i < 5; ++i) {
            playerMoney[i] = 10;
        }

        deck.shuffle();
    }

    public void run() {
        for(int r=0; r < 100; ++r) {
            deck.shuffle();
            Card upCard = deck.dealCard();

            for(int i=0; i < 5; ++i) {
                players[i] = new BasicStrategy(deck, upCard, deck.dealCard(), deck.dealCard());
            }

            for(int i=0; i < 5; ++i) {
                players[i].play();
            }

            DealerStrategy ds = new DealerStrategy(deck, upCard);

            ds.play();
            int dealerValue = ds.getValue();

            for(int i=0; i < 5; ++i) {
                int playerValue = players[i].getValue();

                if(playerValue > 21) {
                    playerMoney[i]--;
                    houseMoney++;
                } else if(dealerValue > 21) {
                    playerMoney[i]++;
                    houseMoney--;
                } else if(playerValue < dealerValue) {
                    playerMoney[i]--;
                    houseMoney++;
                } else if(playerValue > dealerValue){
                    playerMoney[i]++;
                    houseMoney--;
                }
            }

            System.out.print(MessageFormat.format("{0,number}: {1,number} ({2,number})\t", r, houseMoney, ds.getValue()));

            for(int i=0; i < 5; ++i) {
                System.out.print(MessageFormat.format("{0,number} ({1,number}) ", playerMoney[i], players[i].getValue()));
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        new Blackjack().run();
    }

}
