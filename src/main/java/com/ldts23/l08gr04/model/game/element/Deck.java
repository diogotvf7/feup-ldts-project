package com.ldts23.l08gr04.model.game.element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
    }

    public List<Card> getDeck() { return deck; }

    public void setDeck(ArrayList<Card> deck) { this.deck = deck; }

    public void fill() {
        for (int suit = 1; suit <= 4; suit++)
            for (int rank = 2; rank <= 14; rank++)
                deck.add(new Card(rank, suit));
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card deal() {
        return deck.remove(0);
    }

    public void empty(){
        deck.clear();
    }
}
