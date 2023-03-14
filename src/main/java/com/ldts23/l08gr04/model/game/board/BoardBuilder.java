package com.ldts23.l08gr04.model.game.board;

import com.ldts23.l08gr04.model.game.element.Dealer;
import com.ldts23.l08gr04.model.game.element.Deck;
import com.ldts23.l08gr04.model.game.element.Player;

public class BoardBuilder {

    public Board createBoard() {
        Board board = new Board();
        board.setPlayer(createPlayer());
        board.setDealer(createDealer());
        board.setDeck(createDeck());

        return board;
    }

    protected Player createPlayer() {
        return new Player();
    }

    protected Dealer createDealer() {
        return new Dealer();
    }

    protected Deck createDeck() {
        return new Deck();
    }

}
