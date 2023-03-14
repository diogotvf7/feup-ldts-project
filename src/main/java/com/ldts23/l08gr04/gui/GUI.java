package com.ldts23.l08gr04.gui;

import com.ldts23.l08gr04.model.Position;

import java.io.IOException;

public interface GUI {

    void drawImage(Position pos, String filename);

    void drawPixel(Position pos, String color);

    ACTION getNextAction() throws IOException;

    void fillMenuBackground();

    void drawText(Position position, String text);

    int getWidth();

    int getHeight();

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, DOWN, HIT, STAND, NONE, QUIT, SELECT, DOUBLE, ALLIN}
}