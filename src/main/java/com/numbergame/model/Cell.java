package com.numbergame.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell
{
    private int x,y;
    private int width,height;
    private Rectangle rectangle;

    public Cell(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean colorEquals() {
        return rectangle.getFill() == Color.LIGHTYELLOW;
    }

    public void reset()
    {
        rectangle.setFill(Color.LIGHTYELLOW);
        rectangle.setStroke(Color.BLACK);
    }
}
