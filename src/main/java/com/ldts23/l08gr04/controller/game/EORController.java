package com.ldts23.l08gr04.controller.game;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.controller.Controller;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.model.game.element.Dealer;
import com.ldts23.l08gr04.model.game.element.Money;
import com.ldts23.l08gr04.model.game.element.Player;
import com.ldts23.l08gr04.model.menu.Menu;
import com.ldts23.l08gr04.state.MenuState;
import com.ldts23.l08gr04.state.PreGameState;

import static java.lang.Math.min;

public class EORController extends Controller<Board> {

    private final Player player;
    private final Dealer dealer;

    public EORController(Board board) {
            super(board);
            this.player = board.getPlayer();
            this.dealer = board.getDealer();
    }

    private void clean() {
        player.clearHand();
        dealer.clearHand();
        player.setBet(new Money(min(player.getMoney().getMoney(), player.getBet().getMoney())));
        player.setMessage("");
    }

    private void goToState(Game game) {
        if (player.getMoney().getMoney() == 0)
            game.setState(new MenuState(new Menu()));
        else
            game.setState(new PreGameState(getModel()));
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        switch (action) {
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;
            case SELECT:
                player.setHandIsClosed(false);
                clean();
                goToState(game);
                break;
        }
    }
}
