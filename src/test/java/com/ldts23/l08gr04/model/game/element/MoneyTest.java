package com.ldts23.l08gr04.model.game.element;

import net.jqwik.time.api.constraints.YearMonthRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    private static Money money;

    @BeforeEach
    void setUp() {
        money = new Money(1000);
    }

    @Test
    public void setMoneyTest(){
        money.setMoney(2000);
        int expected = 2000;
        int result = money.getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void addToBalance() {
        money.add(250);
        int expected = 1250;
        int result = money.getMoney();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void subtractToBalance() {
        money.subtract(250);
        int expected = 750;
        int result = money.getMoney();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void isLessthan1() {
        Money money1 = new Money(1250);
        Assertions.assertTrue(money.isLessThan(money1));
    }

    @Test
    public void isLessthan2() {
        Money money1 = new Money(1000);
        Assertions.assertFalse(money.isLessThan(money1));
    }

    @Test
    public void isLessthan3() {
        Money money1 = new Money(999);
        Assertions.assertFalse(money.isLessThan(money1));
    }
}
