package com.ldts23.l08gr04.viewer.game;

import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.Position;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.model.game.element.Dealer;
import com.ldts23.l08gr04.model.game.element.Deck;
import com.ldts23.l08gr04.model.game.element.Player;
import com.ldts23.l08gr04.viewer.Viewer;

public class BoardViewer extends Viewer<Board> {

    public BoardViewer(Board model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) {
        gui.drawImage(new Position(0, 0), "ClassicBoard");
        drawPlayer(gui, getModel().getPlayer(), new PlayerViewer());
        drawDealer(gui, getModel().getDealer(), new DealerViewer());
        drawDeck(gui, getModel().getDeck(), new DeckViewer());
        gui.drawImage(new Position(30, 100), "dealer");
    }

    public void drawPlayer(GUI gui, Player player, PlayerViewer viewer) {
        viewer.drawPlayer(player, gui);
    }

    public void drawDealer(GUI gui, Dealer dealer, DealerViewer viewer) {
        viewer.drawDealer(dealer, gui);
    }

    public void drawDeck(GUI gui, Deck deck, DeckViewer viewer) {
        viewer.drawDeck(gui);
    }
}
