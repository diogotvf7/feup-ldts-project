package com.ldts23.l08gr04.controller;

import com.ldts23.l08gr04.Game;
import com.ldts23.l08gr04.gui.GUI;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) { this.model = model; }

    public T getModel() { return model; }

    public abstract void step(Game game, GUI.ACTION action, long time) throws IOException;
}
