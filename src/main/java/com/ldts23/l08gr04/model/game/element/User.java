package com.ldts23.l08gr04.model.game.element;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private final List<Card> hand;

    public User() {
        this.hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public int[] getHandValues() {
        int[] values = {0, 0};
        boolean ace = false;
        for (Card c : hand) {
            if (c.getRank() < 10) {
                values[0] += c.getRank();
                values[1] += c.getRank();
            }
            else if (10 <= c.getRank() && c.getRank() <= 13) {
                values[0] += 10;
                values[1] += 10;
            }
            else if (c.getRank() == 14) {
                values[0] += 1;
                if (!ace) {
                    ace = true;
                    values[1] += 11;
                } else {
                    values[1] += 1;
                }
            }
        }
        if (values[1] > 21)
            values[1] = values[0];
        return values;
    }

    public boolean handIsBust() {
        return getHandValues()[0] > 21;
    }

    public boolean handIsBlackjack() {
        return getHand().size() == 2 && getHandValues()[1] == 21;
    }

    public abstract boolean canDeal();
}
