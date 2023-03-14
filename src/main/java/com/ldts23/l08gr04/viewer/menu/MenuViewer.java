package com.ldts23.l08gr04.viewer.menu;

import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.Position;
import com.ldts23.l08gr04.model.menu.Menu;
import com.ldts23.l08gr04.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {

    public MenuViewer(Menu menu) {
        super(menu);
    }

    private void drawEntries(GUI gui) {
        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            String entry = getModel().getEntry(i);
            Position pos = new Position(gui.getWidth() / 2 - 16 - entry.length() * 9, gui.getHeight() / 4 + 60 + i * 18);

            if (getModel().isSelected(i))
                gui.drawImage(pos.offset(-24, 0), "Arrow");

            gui.drawText(pos, entry);
        }
    }

    @Override
    protected void drawElements(GUI gui) {
        gui.fillMenuBackground();
        gui.drawText(new Position(gui.getWidth() / 2 - 128, gui.getHeight() / 4),"BL.EICJACK MENU");
        drawEntries(gui);
    }
}
