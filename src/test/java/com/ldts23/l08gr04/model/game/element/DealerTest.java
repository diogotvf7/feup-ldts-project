package com.ldts23.l08gr04.model.game.element;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class DealerTest {
        private static Dealer dealer;

        @BeforeEach
        public void setUp() {
            dealer = new Dealer();
        }

        @Test
        public void getHandValue1() {
            dealer.addCard(new Card(6, 3));
            dealer.addCard(new Card(14, 2));
            int[] expected = {7, 17};
            int[] result = dealer.getHandValues();
            Assertions.assertArrayEquals(expected, result);
        }

        @Test
        public void getHandValue2() {
            dealer.addCard(new Card(9, 1));
            dealer.addCard(new Card(7,2));
            int[] expected = {16, 16};
            int[] result = dealer.getHandValues();
            Assertions.assertArrayEquals(expected, result);
        }
        @Test
        public void getHandValue3() {
            dealer.addCard(new Card(14, 1));
            dealer.addCard(new Card(14,2));
            int[] expected = {2, 12};
            int[] result = dealer.getHandValues();
            Assertions.assertArrayEquals(expected, result);
        }
        @Test
        public void isBust1() {
            dealer.addCard(new Card(9, 1));
            dealer.addCard(new Card(7,2));
            boolean expected = false;
            boolean result = dealer.handIsBust();
            Assertions.assertEquals(expected, result);
        }

        @Test
        public void isBust2() {
            dealer.addCard(new Card(12, 2));
            dealer.addCard(new Card(8, 2));
            dealer.addCard(new Card(5, 2));
            boolean expected = true;
            boolean result = dealer.handIsBust();
            Assertions.assertEquals(expected, result);
        }

        @Test
        public void isBlackjack1(){
            dealer.addCard(new Card(14,3));
            dealer.addCard(new Card(10,1));
            boolean expected = true;
            boolean result = dealer.handIsBlackjack();
            Assertions.assertEquals(expected,result);
        }

        @Test
        public void isBlackjack2(){
        dealer.addCard(new Card(2,3));
        dealer.addCard(new Card(10,1));
        dealer.addCard(new Card(9,2));
        boolean expected = false;
        boolean result = dealer.handIsBlackjack();
        Assertions.assertEquals(expected,result);
        }


}
