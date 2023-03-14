package com.ldts23.l08gr04.controller.game;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.model.game.element.Dealer;
import com.ldts23.l08gr04.model.game.element.Deck;
import com.ldts23.l08gr04.model.game.element.Player;
import com.ldts23.l08gr04.state.PlayerState;
import com.ldts23.l08gr04.state.State;
import com.ldts23.l08gr04.viewer.game.BoardViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PreGameControllerTest {
    private static Game game;
    private static PreGameController controller;
    private static Player player;
    private static Dealer dealer;
    private static Deck deck;

    @BeforeEach
    public void setUp() {
        Board board = new Board();
        game = Mockito.mock(Game.class);
        player = new Player();
        dealer = new Dealer();
        deck = new Deck();
        board.setPlayer(player);
        board.setDealer(dealer);
        board.setDeck(deck);
        controller = Mockito.spy(new PreGameController(board));
    }

    @Test
    public void setUpDeckTest() {
        try {
            controller.step(game, GUI.ACTION.NONE, 1000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(104, deck.getDeck().size());
    }

    @Test
    public void dealCardsPlayerTest() {
        try {
            controller.step(game, GUI.ACTION.UP, 0);
            controller.step(game, GUI.ACTION.SELECT, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int expected = 2;
        int result = player.getHand().size();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void dealCardsDealerTest() {
        try {
            controller.step(game, GUI.ACTION.UP, 0);
            controller.step(game, GUI.ACTION.SELECT, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int expected = 1;
        int result = dealer.getHand().size();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void allInTest() {
        try {
            controller.step(game, GUI.ACTION.ALLIN, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int expected = player.getMoney().getMoney();
        int result = player.getBet().getMoney();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void isValidBetTest() { // public for tests. Need solution
        try {
            controller.step(game, GUI.ACTION.SELECT, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean expected = false;
        boolean result = controller.isValidBet();
        Assertions.assertEquals(expected, result);
    }


    @Test
    public void lowerBetTest() {
        player.getBet().setMoney(10);
        try {
            controller.step(game, GUI.ACTION.DOWN, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int expected = 0;
        int result = player.getBet().getMoney();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void raiseBetTest() {
        player.getBet().setMoney(10);
        try {
            controller.step(game, GUI.ACTION.UP, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int expected = 20;
        int result = player.getBet().getMoney();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void cantRaiseBetTest() {
        player.getBet().setMoney(player.getMoney().getMoney());
        try {
            controller.step(game, GUI.ACTION.UP, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int expected = player.getMoney().getMoney();
        int result = player.getBet().getMoney();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void cantLowerBetTest() {
        player.getBet().setMoney(0);
        try {
            controller.step(game, GUI.ACTION.DOWN, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int expected = 0;
        int result = player.getBet().getMoney();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void changeState() {
        Game gameMock = Mockito.mock(Game.class);
        Player playerMock = Mockito.mock(Player.class);
        player.getBet().setMoney(10);
        Mockito.when(controller.isValidBet()).thenReturn(true);
        Mockito.when(playerMock.handIsBlackjack()).thenReturn(false);
        try {
            controller.step(gameMock, GUI.ACTION.SELECT, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Mockito.verify(gameMock, Mockito.times(1)).setState(Mockito.any(PlayerState.class));
    }
}