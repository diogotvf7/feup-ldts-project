package com.ldts23.l08gr04.model.game.element;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private static Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
        player.addMoney(100);
        player.addCard(new Card(7, 4));
        player.addCard(new Card(14, 2));
    }

    @Test
    public void subtractMoney() {
        int expected = player.getMoney().getMoney() - 100;
        player.subtractMoney(100);
        Assertions.assertEquals(expected, player.getMoney().getMoney());
    }

    @Test
    public void addMoney() {
        int expected = player.getMoney().getMoney() + 100;
        player.addMoney(100);
        Assertions.assertEquals(expected, player.getMoney().getMoney());
    }

    @Test
    public void getHandValue1() {
        int[] expected = new int[]{8, 18};
        int[] result = player.getHandValues();
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void getHandValue2() {
        player.clearHand();
        player.addCard(new Card(9, 2));
        player.addCard(new Card(12,1));
        int[] expected = new int[]{19, 19};
        int[] result = player.getHandValues();
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void getHandValue3() {
        player.clearHand();
        player.addCard(new Card(14, 1));
        player.addCard(new Card(14,2));
        int[] expected = {2, 12};
        int[] result = player.getHandValues();
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void bust() {
        player.addCard(new Card(5,4));
        player.addCard(new Card(10,2));
        boolean expected =  true;
        boolean result = player.handIsBust();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void blackjack() {
        player.clearHand();
        player.addCard(new Card(14,1));
        player.addCard(new Card(10,4));
        boolean expected =  true;
        boolean result = player.handIsBlackjack();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void falseBlackjack() {
        player.clearHand();
        player.addCard(new Card(14,1));
        player.addCard(new Card(8,4));
        player.addCard(new Card(2,3));
        boolean expected =  false;
        boolean result = player.handIsBlackjack();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testMessage(){
        player.setMessage("WIN");
        String expected = "WIN";
        String result = player.getMessage();
        Assertions.assertEquals(expected,result);
    }

    @Property
    public void handValuesProperty(@ForAll @IntRange(min = 2, max = 14) int a, @ForAll @IntRange(min = 2, max = 14) int b) {
        Player player1 = new Player();
        player1.addCard(new Card(a, 1));
        player1.addCard(new Card(b, 1));
        Player player2 = new Player();
        player2.addCard(new Card(b, 1));
        player2.addCard(new Card(a, 1));
        assert(player1.getHandValues()[0] == player2.getHandValues()[0] &&
                player1.getHandValues()[1] == player2.getHandValues()[1]);
    }
}
