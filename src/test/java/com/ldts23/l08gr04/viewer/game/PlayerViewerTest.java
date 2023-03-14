package com.ldts23.l08gr04.viewer.game;

import com.ldts23.l08gr04.gui.GUI;

import com.ldts23.l08gr04.model.Position;
import com.ldts23.l08gr04.model.game.element.Card;
import com.ldts23.l08gr04.model.game.element.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.matches;

public class PlayerViewerTest {

    private static Player player;
    private static PlayerViewer viewer;
    private static GUI gui;

    @BeforeEach
    void setUp() {
        player = new Player();
        player.clearHand();
        viewer = new PlayerViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawNameTest() {
        viewer.drawPlayer(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(Mockito.any(Position.class), eq("PLAYER"));
    }

    @Test
    void drawHandTest() {
        player.addCard(new Card(10, 1));
        player.addCard(new Card(11, 1));
        player.addCard(new Card(2, 3));
        viewer.drawPlayer(player,gui);
        Mockito.verify(gui, Mockito.times(3)).drawImage(Mockito.any(Position.class), matches("cards/*"));
    }

    @Test
    void drawHandValuesBothTest() {
        player.addCard(new Card(14, 1));
        viewer.drawPlayer(player,gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(Mockito.any(Position.class), eq(player.getHandValues()[0] + "-" + player.getHandValues()[1]));
    }

    @Test
    void drawHandValuesSingleTest() {
        player.addCard(new Card(10, 1));
        player.addCard(new Card(9, 2));
        viewer.drawPlayer(player,gui);
        Mockito.verify(gui,Mockito.times(1)).drawText(Mockito.any(Position.class), eq(Integer.toString(player.getHandValues()[1])));
    }
}

