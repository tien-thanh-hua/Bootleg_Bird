package com.bootleg.game.actors;

import com.badlogic.gdx.math.Vector2;

public abstract class Scrollable {
    protected Vector2 _position;
    protected Vector2 _velocity;
    protected int _width;
    protected int _height;
    protected boolean isScrolledLeft;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        _position = new Vector2(x, y);
        _velocity = new Vector2(scrollSpeed, 0);
        _width = width;
        _height = height;
        isScrolledLeft = false;
    }

    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getTailX() {
        return _position.x + _width;
    }

    public float getX() {
        return _position.x;
    }

    public float getY() {
        return _position.y;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public void update(float delta) {
        _position.add(_velocity.cpy().scl(delta));

        // If the Scrollable object is no longer visible:
        if (_position.x + _width < 0) {
            isScrolledLeft = true;
        }
    }

    // Reset: Should Override in subclass for more specific behavior.
    public void reset(float newX) {
        _position.x = newX;
        isScrolledLeft = false;
    }

    public void stop() {
        _velocity.x = 0;
    }

    public abstract boolean collides(Bird bird);
}
