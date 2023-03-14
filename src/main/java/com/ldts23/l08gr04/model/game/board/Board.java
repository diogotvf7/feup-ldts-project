package com.ldts23.l08gr04.model.game.board;

import com.ldts23.l08gr04.model.game.element.Dealer;
import com.ldts23.l08gr04.model.game.element.Deck;
import com.ldts23.l08gr04.model.game.element.Player;

public class Board {

    private Deck deck;
    private Player player;
    private Dealer dealer;

    public Board() {}

    public Player getPlayer() {
        return this.player;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

}
