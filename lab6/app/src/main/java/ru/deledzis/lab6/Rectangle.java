package ru.deledzis.lab6;

import android.graphics.Rect;

class Rectangle {
    private Rect rect;
    private int color;

    Rectangle(Rect rect, int color) {
        this.rect = rect;
        this.color = color;
    }

    Rect getRect() {
        return rect;
    }

    int getColor() {
        return color;
    }
}
