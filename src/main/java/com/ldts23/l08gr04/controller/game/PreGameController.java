package com.ldts23.l08gr04.controller.game;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.controller.Controller;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.model.game.element.Dealer;
import com.ldts23.l08gr04.model.game.element.Deck;
import com.ldts23.l08gr04.model.game.element.Player;
import com.ldts23.l08gr04.model.menu.Menu;
import com.ldts23.l08gr04.state.MenuState;
import com.ldts23.l08gr04.state.PlayerState;
import com.ldts23.l08gr04.state.PostGameState;

import java.io.IOException;

public class PreGameController extends Controller<Board> {

    private final Player player;
    private final Dealer dealer;
    private final Deck deck;

    public PreGameController(Board board) {
        super(board);

        this.player = board.getPlayer();
        this.dealer = board.getDealer();
        this.deck = board.getDeck();

        if (deck.getDeck().size() < 30)
            setupDeck();
    }

    public void setupDeck() {
        deck.empty();
        deck.fill();
        deck.fill();
        deck.shuffle();
    }

    public boolean isValidBet() {
        return player.getBet().getMoney() > 0 &&
                player.getBet().getMoney() <= player.getMoney().getMoney();
    }

    public void deal() {
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        player.addCard(deck.deal());
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;
            case UP:
                if (player.getBet().isLessThan(player.getMoney()))
                    player.getBet().setMoney(player.getBet().getMoney() + 10);
                break;
            case DOWN:
                if (player.getBet().getMoney() > 0)
                    player.getBet().setMoney(player.getBet().getMoney() - 10);
                break;
            case ALLIN:
                player.getBet().setMoney(player.getMoney().getMoney());
            break;
            case SELECT: 
                if (isValidBet()) {
                    player.subtractMoney(player.getBet().getMoney());
                    deal();
                    if(player.handIsBlackjack()) {
                        player.setHandIsClosed(true);
                        game.setState(new PostGameState(getModel()));
                    }
                    else
                        game.setState(new PlayerState(getModel()));
                }
                break;
        }
    }
}

