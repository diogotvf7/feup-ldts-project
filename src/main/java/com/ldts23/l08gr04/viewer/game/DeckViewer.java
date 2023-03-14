package com.ldts23.l08gr04.viewer.game;

import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.Position;

public class DeckViewer {

    public void drawDeck(GUI gui) {
        gui.drawImage(new Position(gui.getWidth() / 6, gui.getHeight() / 3), "cards/Deck");
    }
}
