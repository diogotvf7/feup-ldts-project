package com.ldts23.l08gr04.controller.game;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.controller.Controller;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.model.game.element.Deck;
import com.ldts23.l08gr04.model.game.element.Player;
import com.ldts23.l08gr04.model.menu.Menu;
import com.ldts23.l08gr04.state.MenuState;
import com.ldts23.l08gr04.state.PostGameState;

import java.io.IOException;

public class PlayerController extends Controller<Board> {

    private final Player player;
    private final Deck deck;

    public PlayerController(Board board) {
        super(board);
        this.player = board.getPlayer();
        this.deck = board.getDeck();
    }

    private boolean canDouble() {
        return player.getBet().getMoney() * 2 <= player.getMoney().getMoney() &&
                (player.getHandValues()[0] == 9 ||
                        player.getHandValues()[0] == 10 ||
                        player.getHandValues()[0] == 11) && player.getHand().size() == 2;
    }

    public void deal() {
        player.addCard(deck.deal());
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

        if (!player.canDeal()) {
            // player.setHandIsClosed(true);
            game.setState(new PostGameState(getModel()));
            return;
        }

        switch (action) {
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;
            case DOUBLE:
                if (!canDouble()) break;
                player.getMoney().subtract(player.getBet().getMoney());
                player.getBet().setMoney(player.getBet().getMoney() * 2);
                player.addCard(deck.deal());
                game.setState(new PostGameState(getModel()));
                break;
            case HIT:
                deal();
                break;
            case STAND:
                player.setHandIsClosed(true);
                game.setState(new PostGameState(getModel()));
                break;
            }
    }
}

