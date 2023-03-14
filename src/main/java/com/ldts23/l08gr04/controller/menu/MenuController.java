package com.ldts23.l08gr04.controller.menu;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.controller.Controller;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.game.board.BoardBuilder;
import com.ldts23.l08gr04.model.menu.Menu;
import com.ldts23.l08gr04.model.rules.Rules;
import com.ldts23.l08gr04.state.PreGameState;
import com.ldts23.l08gr04.state.RulesState;

import java.io.IOException;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(null);
                else if (getModel().isSelectedRules()) game.setState(new RulesState(new Rules()));
                else if (getModel().isSelectedStart())
                    game.setState(new PreGameState(new BoardBuilder().createBoard()));
                break;
        }
    }
}
