package com.ldts23.l08gr04.viewer.rules;

import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.Position;
import com.ldts23.l08gr04.model.rules.Rules;
import com.ldts23.l08gr04.viewer.Viewer;

public class RulesViewer extends Viewer<Rules> {

    public RulesViewer(Rules model) {
        super(model);
    }

    private void drawLines(GUI gui) {
        int height = 32;
        for (String line : getModel().getDisplayLines()) {
            Position pos = new Position(16, height);
            gui.drawText(pos,line);
            height+=16;
        }
    }

    @Override
    protected void drawElements(GUI gui) {
        gui.fillMenuBackground();
        drawLines(gui);
    }
}
