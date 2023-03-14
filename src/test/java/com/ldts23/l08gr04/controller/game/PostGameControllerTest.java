package com.ldts23.l08gr04.controller.game;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.model.game.element.*;
import com.ldts23.l08gr04.state.EORState;
import com.ldts23.l08gr04.state.PlayerState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class PostGameControllerTest {
    private static Game game;
    private static PostGameController controller;
    private static Player player;
    private static Dealer dealer;

    @BeforeEach
    public void setUp(){
        Board board = new Board();
        board.setDealer(new Dealer());
        Deck deck = new Deck();
        deck.fill();
        deck.fill();
        board.setDeck(deck);
        board.setPlayer(new Player());
        game = mock(Game.class);
        player = board.getPlayer();
        dealer =  board.getDealer();
        controller = Mockito.spy(new PostGameController(board));
    }
    @Test
    public void bjMessageTest(){
        player.addCard(new Card(14,2));
        player.addCard(new Card(12,1));
        dealer.addCard(new Card(10,2));
        dealer.addCard(new Card(10,1));
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        String expected = "BLACKJACK";
        String result = player.getMessage();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void winMessageTest(){
        player.addCard(new Card(10,2));
        player.addCard(new Card(4,2));
        player.addCard(new Card(6,1));
        dealer.addCard(new Card(10,2));
        dealer.addCard(new Card(9,1));
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        String expected = "WIN";
        String result = player.getMessage();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void winMessageTest2(){
        player.addCard(new Card(14,2));
        player.addCard(new Card(9,2));
        dealer.addCard(new Card(10,2));
        dealer.addCard(new Card(8,1));
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        String expected = "WIN";
        String result = player.getMessage();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void lostMessageTest(){
        player.addCard(new Card(10,2));
        player.addCard(new Card(7,2));
        dealer.addCard(new Card(10,2));
        dealer.addCard(new Card(14,1));
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        String expected = "WHAT A LOSS";
        String result = player.getMessage();
        Assertions.assertEquals(expected,result);
    }
    @Test
    public void pushMessageTest(){
        player.addCard(new Card(10,2));
        player.addCard(new Card(10,4));
        dealer.addCard(new Card(8,2));
        dealer.addCard(new Card(2,1));
        dealer.addCard(new Card(10,3));
        try {
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        String expected = "PUSH";
        String result = player.getMessage();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void bustMessageTest(){
        player.addCard(new Card(10,2));
        player.addCard(new Card(10,4));
        player.addCard(new Card(5,3));
        dealer.addCard(new Card(2,1));
        try {
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        String expected = "BUUUUSTED";
        String result = player.getMessage();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void winMoneyTest(){
        player.addCard(new Card(10,2));
        player.addCard(new Card(4,2));
        player.addCard(new Card(6,1));
        dealer.addCard(new Card(10,2));
        dealer.addCard(new Card(9,1));
        player.addMoney(500);
        player.setBet(new Money(500));
        player.subtractMoney(500);
        int moneyPostBet = player.getMoney().getMoney();
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        int expected = moneyPostBet + player.getBet().getMoney() * 2;
        int result = player.getMoney().getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void loseMoneyTest(){
        player.addCard(new Card(10,2));
        player.addCard(new Card(4,2));
        player.addCard(new Card(3,1));
        dealer.addCard(new Card(10,2));
        dealer.addCard(new Card(9,1));
        player.addMoney(500);
        player.setBet(new Money(500));
        player.subtractMoney(500);
        int moneyPostBet = player.getMoney().getMoney();
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        int expected = moneyPostBet;
        int result = player.getMoney().getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void pushMoneyTest(){
        player.addCard(new Card(10,2));
        player.addCard(new Card(7,2));
        dealer.addCard(new Card(10,2));
        dealer.addCard(new Card(4,1));
        dealer.addCard(new Card(3,4));
        player.addMoney(500);
        player.setBet(new Money(500));
        player.subtractMoney(500);
        int moneyPostBet = player.getMoney().getMoney();
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        int expected = player.getBet().getMoney() + moneyPostBet;
        int result = player.getMoney().getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void pushMoneyTest2(){
        player.addCard(new Card(14,1));
        player.addCard(new Card(12,2));
        dealer.addCard(new Card(14,2));
        dealer.addCard(new Card(13,1));
        player.addMoney(500);
        player.setBet(new Money(500));
        player.subtractMoney(500);
        int moneyPostBet = player.getMoney().getMoney();
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        int expected = player.getBet().getMoney() + moneyPostBet;
        int result = player.getMoney().getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void bustMoneyTest(){
        player.addCard(new Card(10,2));
        player.addCard(new Card(7,2));
        player.addCard(new Card(8,2));
        dealer.addCard(new Card(10,2));
        dealer.addCard(new Card(4,1));
        dealer.addCard(new Card(3,4));
        player.addMoney(500);
        player.setBet(new Money(500));
        player.subtractMoney(500);
        int moneyPostBet = player.getMoney().getMoney();
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        int expected = moneyPostBet;
        int result = player.getMoney().getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void bjMoneyTest(){
        player.addCard(new Card(14,2));
        player.addCard(new Card(10,2));
        dealer.addCard(new Card(10,1));
        dealer.addCard(new Card(10,4));
        player.addMoney(500);
        player.setBet(new Money(500));
        player.subtractMoney(500);
        int moneyPostBet = player.getMoney().getMoney();
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        int expected = moneyPostBet + player.getBet().getMoney() * 5/2;
        int result = player.getMoney().getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void bjMoneyTest2(){
        player.addCard(new Card(14,2));
        player.addCard(new Card(10,2));
        dealer.addCard(new Card(10,1));
        dealer.addCard(new Card(9,4));
        dealer.addCard(new Card(2,2));
        player.addMoney(500);
        player.setBet(new Money(500));
        player.subtractMoney(500);
        int moneyPostBet = player.getMoney().getMoney();
        try{
            controller.step(game, GUI.ACTION.NONE, 0);
        }
        catch (IOException e) {throw new RuntimeException(e);}
        int expected = moneyPostBet + player.getBet().getMoney() * 5/2;
        int result = player.getMoney().getMoney();
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void changeState() {
        Game gameMock = Mockito.mock(Game.class);
        player.getBet().setMoney(10);
        Mockito.when(controller.shouldDeal()).thenReturn(false);
        try {
            controller.step(gameMock, GUI.ACTION.SELECT, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Mockito.verify(gameMock, Mockito.times(1)).setState(Mockito.any(EORState.class));
    }
}
