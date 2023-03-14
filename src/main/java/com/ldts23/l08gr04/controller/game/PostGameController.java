package com.ldts23.l08gr04.controller.game;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.controller.Controller;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.model.game.element.Dealer;
import com.ldts23.l08gr04.model.game.element.Deck;
import com.ldts23.l08gr04.model.game.element.Player;
import com.ldts23.l08gr04.state.EORState;

import java.io.IOException;

public class PostGameController extends Controller<Board> {

    private final Player player;
    private final Dealer dealer;
    private final Deck deck;

    public PostGameController(Board board) {
        super(board);

        this.player = board.getPlayer();
        this.dealer = board.getDealer();
        this.deck = board.getDeck();
    }

    public boolean bj() {
        return player.handIsBlackjack() && !dealer.handIsBlackjack();
    }

    private boolean win() {
        return  !bust() &&
                (dealer.handIsBust() || player.getHandValues()[1] > dealer.getHandValues()[1]);
    }

    public boolean push() {
        return !bust() &&
                player.getHandValues()[1] == dealer.getHandValues()[1] &&
                player.handIsBlackjack() == dealer.handIsBlackjack();
    }

    public boolean bust() {
        return player.handIsBust();
    }

    public int distributeMoney()  {
        if (bj())
            return 5 * player.getBet().getMoney() / 2;
        else if (win())
            return player.getBet().getMoney() * 2;
        else if (push())
            return player.getBet().getMoney();
        return 0;
    }

    private void setMessage() {
        if (bj())           player.setMessage("BLACKJACK");
        else if (win())     player.setMessage("WIN");
        else if (push())    player.setMessage("PUSH");
        else if (bust())    player.setMessage("BUUUUSTED");
        else                player.setMessage("WHAT A LOSS");
    }

    public boolean shouldDeal() {
        return (player.handIsBlackjack() && dealer.getHand().size() < 2) || (dealer.canDeal() && !bust() && !player.handIsBlackjack());
    }

    public void deal() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dealer.addCard(deck.deal());
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (shouldDeal())
            deal();
        else {
            player.addMoney(distributeMoney());
            setMessage();
            game.setState(new EORState(getModel()));
        }
    }
}
