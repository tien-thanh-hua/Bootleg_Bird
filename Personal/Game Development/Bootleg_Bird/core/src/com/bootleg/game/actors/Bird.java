package com.bootleg.game.actors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    private Vector2 _position;
    private Vector2 _velocity;
    private Vector2 _acceleration;

    private float _rotation; // for bird rotation
    private int _width;
    private int _height;

    private Circle _hitCircle;

    public Bird(float x, float y, int width, int height) {
        _width = width;
        _height = height;
        _position = new Vector2(x, y);
        _velocity = new Vector2(0, 0);
        _acceleration = new Vector2(0, 460);
        _hitCircle = new Circle();
    }

    public void update(float delta) {
        _velocity.add(_acceleration.cpy().scl(delta));
        if (_velocity.y > 200) {
            _velocity.y = 200;
        }
        _position.add(_velocity.cpy().scl(delta));

        _hitCircle.set(_position.x + 9, _position.y + 6, 6.5f);

        // clockwise rotation while falling down
        if (isFalling()) {
            _rotation += 480 * delta;
            if (_rotation > 90) {
                _rotation = 90;
            }
        }

        // rotate counterclockwise when flying up
        if (_velocity.y < 0) {
            _rotation -= 600 * delta;

            if (_rotation < -20) {
                _rotation = -20;
            }
        }
    }

    public boolean isFalling() {
        return _velocity.y > 110;
    }

    public boolean shouldntFlap() {
        return _velocity.y > 70;
    }

    public void onTap() {
        _velocity.y = -140;
    }

    public float getX() {
        return _position.x;
    }

    public float getY() {
        return _position.y;
    }

    public float getRotation() {
        return _rotation;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public Circle getHitCircle() {
        return _hitCircle;
    }
}
