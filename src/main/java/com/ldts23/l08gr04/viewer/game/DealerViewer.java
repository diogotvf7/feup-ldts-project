package com.ldts23.l08gr04.viewer.game;

import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.Position;
import com.ldts23.l08gr04.model.game.element.Dealer;

public class DealerViewer {

    public void drawDealer(Dealer dealer, GUI gui) {
        Position tmp = new Position(gui.getWidth() / 2 - 64, 16);
        gui.drawText(tmp, "DEALER");
        drawHand(tmp, dealer, gui);
        drawHandValues(tmp,dealer,gui);
    }

    private void drawHand(Position tmp, Dealer dealer, GUI gui) {
        for (int i = 0; i < dealer.getHand().size(); i++) {
            Position pos = tmp.offset(96 - i * 18,18);
            gui.drawImage(pos, "cards/" + dealer.getHand().get(i).toString());
        }
    }

    private void drawHandValues(Position tmp, Dealer dealer, GUI gui) {
        if ((dealer.getHandValues()[0] == dealer.getHandValues()[1]) || dealer.getHandValues()[1] >= 17)
            gui.drawText(tmp.offset(32, 32), Integer.toString(dealer.getHandValues()[1]));
        else
            gui.drawText(tmp.offset(0, 32), dealer.getHandValues()[0] + "-" + dealer.getHandValues()[1]);
    }
}
