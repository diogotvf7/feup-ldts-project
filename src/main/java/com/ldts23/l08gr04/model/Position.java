package com.ldts23.l08gr04.model;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public Position offset(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }
}
