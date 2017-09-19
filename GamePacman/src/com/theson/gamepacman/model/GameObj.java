package com.theson.gamepacman.model;


import java.awt.*;

public abstract class GameObj {

    public int x;
    public int y;

    public abstract void draw(Graphics2D graphics2D);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
