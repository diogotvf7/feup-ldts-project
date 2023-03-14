package com.ldts23.l08gr04.model.game.element;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


public class DeckTest {
    private static Deck deck;

    @BeforeEach
    public void setUp(){
        deck = new Deck();
        deck.fill();
    }

    @Test
    public void dealCard() {
        String expected = deck.getDeck().get(0).toString();
        String result = deck.deal().toString();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void countSuits() {
        Set<Integer> expected = new TreeSet<Integer>();
        Set<Integer> result = new TreeSet<Integer>();
        expected.addAll(Arrays.asList(1,2,3,4));
        for (Card card : deck.getDeck()){
            result.add(card.getSuit());
        }
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void countRanks() {
        Set<Integer> expected = new TreeSet<Integer>();
        Set<Integer> result = new TreeSet<Integer>();
        expected.addAll(Arrays.asList(2,3,4,5,6,7,8,9,10,11,12,13,14));
        for (Card card : deck.getDeck()){
            result.add(card.getRank());
        }
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void sizeTest() {
        int result = deck.getDeck().size();
        int expected = 52;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void cleanTest() {
        deck.empty();
        int expected = 0;
        int result = deck.getDeck().size();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void shuffleTest(){
        String card = deck.getDeck().get(0).toString();
        deck.shuffle();
        String card2 = deck.deal().toString();
        boolean expected = false;
        boolean result = card == card2;
        Assertions.assertEquals(expected,result);
    }
}