package com.ldts23.l08gr04.viewer.game;

import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.Position;
import com.ldts23.l08gr04.model.game.element.Player;

public class PlayerViewer {

    private void drawHand(Position tmp, Player player, GUI gui) {
        for (int i = 0; i < player.getHand().size(); i++) {
            Position pos = tmp.offset(16 + i * 18, - 20 );
            gui.drawImage(pos, "cards/" + player.getHand().get(i));
        }
    }

    private void drawHandValues(Position tmp, Player player, GUI gui) {
        if ((player.getHandValues()[0] == player.getHandValues()[1]) || player.getHandIsClosed() )
            gui.drawText(tmp.offset(48, -40), Integer.toString(player.getHandValues()[1]));
        else if ((player.handIsBlackjack()))
            gui.drawText(tmp.offset(48, -40), Integer.toString(player.getHandValues()[1]));
        else
            gui.drawText(tmp.offset(48, -40), player.getHandValues()[0] + "-" + player.getHandValues()[1]);
    }

    private void drawMessage(Player player, GUI gui) {
        if (player.getMessage().length() != 0)
            gui.drawText(new Position (212 - player.getMessage().length() * 9, 92), player.getMessage());
    }

    public void drawPlayer(Player player, GUI gui) {

        Position tmp = new Position(148, 184);
        gui.drawText(tmp, "PLAYER");
        gui.drawText(new Position(283, 107), player.getBet().getMoney() + "$");
        gui.drawText(tmp.offset(0, 16), player.getMoney().getMoney() + "$");
        drawHand(tmp, player, gui);
        drawHandValues(tmp, player, gui);
        drawMessage(player, gui);
    }
}
