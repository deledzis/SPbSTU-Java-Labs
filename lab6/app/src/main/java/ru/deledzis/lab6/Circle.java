package ru.deledzis.lab6;

class Circle {
    private float x;
    private float y;
    private float radius;
    private int color;

    Circle(float x, float y, float radius, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
    }

    float getRadius() {
        return radius;
    }

    int getColor() {
        return color;
    }
}