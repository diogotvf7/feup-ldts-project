package com.ldts23.l08gr04.model.game.element;

public class Dealer extends User {

    public Dealer() {
        super();
    }

    @Override
    public boolean canDeal() {
        return getHandValues()[1] < 17;
    }
}

