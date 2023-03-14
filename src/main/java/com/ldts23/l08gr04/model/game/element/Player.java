package com.ldts23.l08gr04.model.game.element;

public class Player extends User {

    private final Money money;
    private Money bet;
    private String message;
    private boolean handIsClosed;

    public Player() {
        super();
        this.money = new Money(1000);
        this.bet = new Money(0);
        this.message = "";
        this.handIsClosed = false;
    }

    public Money getMoney() {
        return money;
    }

    public Money getBet() {
        return bet;
    }

    public void setBet(Money bet) {
        this.bet = bet;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setHandIsClosed(boolean value) {
        handIsClosed = value;
    }

    public boolean getHandIsClosed() {
        return handIsClosed;
    }

    public void addMoney(int amount) {
        this.money.add(amount);
    }

    public void subtractMoney(int amount) {
        this.money.subtract(amount);
    }

    @Override
    public boolean canDeal() {
        return getHandValues()[0] < 21 && getHandValues()[1] != 21;
    }
}
