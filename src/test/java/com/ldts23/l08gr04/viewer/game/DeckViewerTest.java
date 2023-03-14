package com.ldts23.l08gr04.viewer.game;

import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.Position;
import com.ldts23.l08gr04.model.game.element.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DeckViewerTest {
    private static Deck deck;
    private static DeckViewer viewer;
    private static GUI gui;

    @BeforeEach
    void setUp() {
        deck = new Deck();
        viewer = new DeckViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawDeckTest() {
        viewer.drawDeck(gui);
        Mockito.verify(gui, Mockito.times(1)).drawImage(new Position(gui.getWidth() / 6, gui.getHeight() / 3), "cards/Deck");
    }
}
