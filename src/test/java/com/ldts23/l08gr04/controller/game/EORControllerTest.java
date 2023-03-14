package com.ldts23.l08gr04.controller.game;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.game.board.Board;
import com.ldts23.l08gr04.model.game.element.Dealer;
import com.ldts23.l08gr04.model.game.element.Player;
import com.ldts23.l08gr04.state.MenuState;
import com.ldts23.l08gr04.state.PlayerState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class EORControllerTest {

    private static Game game;
    private static Board board;
    private static Player player;
    private static Dealer dealer;
    private static EORController controller;

    @BeforeAll
    public static void setUp(){

        game = Mockito.mock(Game.class);
        board = Mockito.mock(Board.class);
        player = Mockito.mock(Player.class);
        dealer = Mockito.mock(Dealer.class);
        controller = Mockito.spy(new EORController(board));
    }

    @Test
    public void stepTest1() {
        controller.step(game, GUI.ACTION.QUIT, 0);
        Mockito.verify(game, Mockito.times(1)).setState(any(MenuState.class));
    }
}
