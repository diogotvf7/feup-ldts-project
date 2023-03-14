package com.ldts23.l08gr04.model.game.element;

import com.ldts23.l08gr04.model.game.element.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CardTest {
    private static Card card;

    @BeforeAll
    static void setUp(){
        card = new Card(6, 3);
    }

    @Test
    public void toStringTest1() {
        Card newCard = new Card(14, 1);
        String expected = "A_Clubs";
        String result = newCard.toString();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void toStringTest2() {
        Card newCard = new Card(7,4);
        String expected = "7_Spades";
        String result = newCard.toString();

        Assertions.assertEquals(expected, result);
    }
}
