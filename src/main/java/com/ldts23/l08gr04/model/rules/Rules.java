package com.ldts23.l08gr04.model.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rules {

    private final List<String> lines;
    private int firstIndex;


    public Rules() {
        this.lines = Arrays.asList(
                "", "", "", "", "", "", "", "", "",
                "You lose if your hand",
                "values more than 21",
                "points. You lose if",
                "the dealer has a",
                "better hand. Even if ",
                "you win you will end",
                "up losing money.",
                "",
                "Commands:",
                "up    : increase bet",
                "down  : decrease bet",
                "right : Hit",
                "left  : Stand",
                "d     : double",
                "a     : all in",
                "q     : quit",
                "",
                "Have fun",
                "Game made by:",
                "Daniel Rebelo",
                "Diogo Fernandes",
                "Pedro Marcelino",
                ""
        );
        firstIndex = 0;
    }


    public int getFirstIndex() {
        return firstIndex;
    }

    public String getLine(int i) {
        return lines.get(i);
    }

    public List<String> getDisplayLines() {
        ArrayList<String> displayLines = new ArrayList<>();
        for (int i = firstIndex; i < firstIndex + 10; i++) {
            displayLines.add(getLine(i));
        }
        return displayLines;
    }

    public void moveDown() {
        if (firstIndex + 10 < getNumberLines())
            firstIndex++;
    }

    public void moveUp() {
        if (firstIndex > 9)
            firstIndex--;
    }

    public int getNumberLines() {
        return this.lines.size();
    }
}
