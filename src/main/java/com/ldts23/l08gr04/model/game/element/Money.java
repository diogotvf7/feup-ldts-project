package com.ldts23.l08gr04.model.game.element;

public class Money {

    private int money;

    public Money(int money) {
        this.money = money;
    }

    public int getMoney() { return money; }

    public void setMoney(int money) { this.money = money; }

    public void add(int money) { this.money += money; }

    public void subtract(int money) { this.money -= money; }

    public boolean isLessThan(Money money) {
        return this.money < money.money;
    }

}
