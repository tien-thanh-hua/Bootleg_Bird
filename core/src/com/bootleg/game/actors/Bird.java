package com.bootleg.game.actors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.bootleg.game.utils.AssetLoader;

public class Bird {
    private Vector2 _position;
    private Vector2 _velocity;
    private Vector2 _acceleration;

    private float _rotation; // for bird rotation
    private int _width;
    private int _height;
    private boolean _isAlive;

    // bird hurtbox
    private Circle _hurtCircle;

    // constructor
    public Bird(float x, float y, int width, int height) {
        _width = width;
        _height = height;
        _position = new Vector2(x, y);
        _velocity = new Vector2(0, 0);
        _acceleration = new Vector2(0, 460);
        _hurtCircle = new Circle();
        _isAlive = true;
    }

    // getters

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
    public Circle getHurtCircle() {
        return _hurtCircle;
    }

    // reset variables upon restart
    public void onRestart(int y) {
        _rotation = 0;
        _position.y = y;
        _velocity.x = 0;
        _velocity.y = 0;
        _acceleration.x = 0;
        _acceleration.y = 460;
        _isAlive = true;
    }

    public void update(float delta) {
        // cap falling speed
        _velocity.add(_acceleration.cpy().scl(delta));
        if (_velocity.y > 200) {
            _velocity.y = 200;
        }

        // ceiling cap
        if (_position.y < 0) {
            _position.y = 0;
            _velocity.y = 0;
        }

        _position.add(_velocity.cpy().scl(delta));

        _hurtCircle.set(_position.x + 9, _position.y + 6, 6.5f);

        // clockwise rotation while falling down
        if (isFalling() || !_isAlive) {
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

    public boolean shouldNotFlap() {
        return _velocity.y > 70 || !_isAlive;
    }

    public void onTap() {
        // on tap, flies up and play flapping sound if alive
        if(_isAlive) {
            _velocity.y = -140;
            AssetLoader.wing.play();
        }
    }

    public void die() {
        // disable flying upon death
        _isAlive = false;
        _velocity.y = 0;
    }

    public void decelerate() {
        _acceleration.y = 0;
    }

    public boolean isAlive() {
        return _isAlive;
    }
}
