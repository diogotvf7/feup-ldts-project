package com.ldts23.l08gr04.viewer.game;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.Position;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.model.game.element.Dealer;
import com.ldts23.l08gr04.model.game.element.Deck;
import com.ldts23.l08gr04.model.game.element.Money;
import com.ldts23.l08gr04.model.game.element.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.*;

public class BoardViewerTest {

    private static BoardViewer viewer;
    private static GUI gui;

    @BeforeEach
    void setUp() {
        int[] values = {0, 0};
        Board board = new Board();
        Player player = Mockito.mock(Player.class);
        Dealer dealer = Mockito.mock(Dealer.class);
        Deck deck = Mockito.mock(Deck.class);
        board.setPlayer(player);
        board.setDealer(dealer);
        board.setDeck(deck);
        viewer = Mockito.spy(new BoardViewer(board));
        gui = Mockito.mock(GUI.class);
        Mockito.when(player.getBet()).thenReturn(Mockito.mock(Money.class));
        Mockito.when(player.getMoney()).thenReturn(Mockito.mock(Money.class));
        Mockito.when(player.getHandValues()).thenReturn(values);
        Mockito.when(player.getMessage()).thenReturn("Mock message");
        Mockito.when(dealer.getHandValues()).thenReturn(values);
    }

    @Test
    void drawBackGroundTest() {
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawImage(Mockito.any(Position.class), eq("ClassicBoard"));
        Mockito.verify(gui, Mockito.times(1)).drawImage(Mockito.any(Position.class), eq("dealer"));
    }

    @Test
    void drawPlayerTest() {
        viewer.drawElements(gui);
        Mockito.verify(viewer, Mockito.times(1)).drawPlayer(Mockito.any(GUI.class), Mockito.any(Player.class), Mockito.any(PlayerViewer.class));
    }

    @Test
    void drawDealerTest() {
        viewer.drawElements(gui);
        Mockito.verify(viewer, Mockito.times(1)).drawDealer(Mockito.any(GUI.class), Mockito.any(Dealer.class), Mockito.any(DealerViewer.class));
    }

    @Test
    void drawDeckTest() {
        viewer.drawElements(gui);
        Mockito.verify(viewer, Mockito.times(1)).drawDeck(Mockito.any(GUI.class), Mockito.any(Deck.class), Mockito.any(DeckViewer.class));
    }

}