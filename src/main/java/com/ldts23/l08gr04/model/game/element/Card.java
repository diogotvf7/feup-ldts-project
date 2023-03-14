package com.ldts23.l08gr04.model.game.element;

public class Card {

    private final int rank;
    private final int suit;

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    @Override
    public String toString() {

        String[] ranks = {null, null, "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {null, "Clubs", "Diamonds", "Hearts", "Spades"};

        return ranks[this.rank] + "_" + suits[this.suit];
    }
}