package com.ldts23.l08gr04.controller.menu;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.gui.GUI;
import com.ldts23.l08gr04.model.menu.Menu;
import com.ldts23.l08gr04.model.rules.Rules;
import com.ldts23.l08gr04.state.MenuState;
import com.ldts23.l08gr04.state.PreGameState;
import com.ldts23.l08gr04.state.RulesState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MenuTest {

    static Game game;
    static MenuController controller;

    @BeforeAll
    public static void setup() {
        game = Mockito.mock(Game.class);
        controller = Mockito.spy(new MenuController(Mockito.mock(Menu.class)));
    }

    @Test
    public void upStepTest() {
        try {
            controller.step(game, GUI.ACTION.UP, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Mockito.verify(controller.getModel(), Mockito.times(1)).previousEntry();
    }

    @Test
    public void downStepTest() {
        try {
            controller.step(game, GUI.ACTION.DOWN, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Mockito.verify(controller.getModel(), Mockito.times(1)).nextEntry();
    }

    @Test
    public void exitStepTest() {
        when(controller.getModel().isSelectedExit()).thenReturn(true);
        try {
            controller.step(game, GUI.ACTION.SELECT, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Mockito.verify(game, Mockito.times(1)).setState(null);
    }

    @Test
    public void rulesStepTest() {
        when(controller.getModel().isSelectedExit()).thenReturn(false);
        when(controller.getModel().isSelectedRules()).thenReturn(true);
        try {
            controller.step(game, GUI.ACTION.SELECT, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(RulesState.class));
    }

    @Test
    public void startStepTest() {
        when(controller.getModel().isSelectedExit()).thenReturn(false);
        when(controller.getModel().isSelectedRules()).thenReturn(false);
        when(controller.getModel().isSelectedStart()).thenReturn(true);
        try {
            controller.step(game, GUI.ACTION.SELECT, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(PreGameState.class));
    }
}
