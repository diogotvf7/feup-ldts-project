package com.ldts23.l08gr04.controller.game;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.model.game.element.Card;
import com.ldts23.l08gr04.model.game.element.Dealer;
import com.ldts23.l08gr04.model.game.element.Deck;
import com.ldts23.l08gr04.model.game.element.Player;
import com.ldts23.l08gr04.state.PlayerState;
import com.ldts23.l08gr04.state.PostGameState;
import com.ldts23.l08gr04.state.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class PlayerControllerTest {

    PlayerController controller;
    Game game;
    Board board;
    Player player;
    Deck deck;

    @BeforeEach
    public void setUp() {
        game = mock(Game.class);
        board = new Board();
        player = new Player();
        board = new Board();
        deck = new Deck();
        deck.fill();
        deck.fill();
        board.setDeck(deck);
        board.setPlayer(new Player());
        player = board.getPlayer();
        deck = board.getDeck();
        controller = new PlayerController(board);
    }

    @Test
    void canDoubleTest1() {
        player.addCard(new Card(10,1));
        int initBet = player.getBet().getMoney();
        try {
            controller.step(game, GUI.ACTION.DOUBLE, 1000);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        int expected = initBet * 2;
        int result = player.getBet().getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void canDoubleTest2() {
        player.addCard(new Card(14,1));
        int initBet = player.getBet().getMoney();
        try{
            controller.step(game, GUI.ACTION.DOUBLE, 1000);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        int expected = initBet * 2;
        int result = player.getBet().getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void canDoubleTest3() {
        player.addCard(new Card(6,1));
        int initBet = player.getBet().getMoney();
        try{
            controller.step(game, GUI.ACTION.DOUBLE, 1000);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        int expected = initBet;
        int result = player.getBet().getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void hitTest() {
        try{
            controller.step(game,GUI.ACTION.HIT,1000);
        }
        catch (Exception e) {throw new RuntimeException(e);}
        int expected = 1;
        int result = player.getHand().size();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void hitTest2() {
        player.addCard(new Card(9,1));
        player.addCard(new Card(10,1));
        player.addCard(new Card(2,1));
        try {
            controller.step(game,GUI.ACTION.HIT,1000);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        int expected = 3;
        int result = player.getHand().size();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void standTest() {
        player.addCard(new Card(12,1));
        player.addCard(new Card(10,1));
        try {
            controller.step(game,GUI.ACTION.STAND,1000);
        }
        catch (Exception e) {throw new RuntimeException(e);}
        int expected = 2;
        int result = player.getHand().size();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void stateTest1() throws IOException, URISyntaxException, FontFormatException {
        player.addCard(new Card(12,1));
        player.addCard(new Card(10,1));
        controller.step(game,GUI.ACTION.STAND,3000);
        // Mockito.verify(game,Mockito.times(1).setState(Mockito.any(PostGameState.class)));
    }

}
