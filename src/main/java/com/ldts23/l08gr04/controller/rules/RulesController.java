package com.ldts23.l08gr04.controller.rules;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.controller.Controller;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.menu.Menu;
import com.ldts23.l08gr04.model.rules.Rules;
import com.ldts23.l08gr04.state.MenuState;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class RulesController extends Controller<Rules> {

    boolean check;

    public RulesController(Rules model) {
        super(model);
        check = true;
    }

    private boolean reachedEnd() {
        return getModel().getFirstIndex() == 22;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

        if (check && action != GUI.ACTION.QUIT) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            getModel().moveDown();

            if (reachedEnd())
                check = false;
            return;
        }

        switch (action) {
            case UP:
                getModel().moveUp();
                break;
            case DOWN:
                getModel().moveDown();
                break;
            case QUIT:
                game.setState(new MenuState(new Menu()));
                break;
        }
    }
}
