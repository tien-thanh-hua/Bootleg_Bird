package com.bootleg.game.actors;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Pipe extends Scrollable {

    private Random r;
    private Rectangle _hurtRectUp, _hurtRectDown;
    public static final int VERTICAL_GAP = 45;
    private float _groundY;
    private boolean _isScored = false;

    // constructor
    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        _hurtRectUp = new Rectangle();
        _hurtRectDown = new Rectangle();
        r = new Random();
        _groundY = groundY;
    }

    // getters
    public Rectangle getHitRectUp() {
        return _hurtRectUp;
    }
    public Rectangle getHitRectDown() {
        return _hurtRectDown;
    }
    public boolean isScored() {
        return _isScored;
    }

    // setters
    public void setScored(boolean b) {
        _isScored = b;
    }

    @Override
    public boolean collides(Bird bird) {
        if (_position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getHurtCircle(), getHitRectUp()) ||
                    Intersector.overlaps(bird.getHurtCircle(), getHitRectDown()));
        }
        return false;
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        // change the height to a random number
        _height = r.nextInt(90) + 15;
        // reset scoring "sensor" for instance pipe
        _isScored = false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        // set up new hurtbox for upper pipe
        _hurtRectUp.set(_position.x, _position.y, _width,_height);

        // set up new hurtbox for lower pipe
        _hurtRectDown.set(_position.x, _position.y + _height + VERTICAL_GAP, _width,
                _groundY - (_position.y + _height + VERTICAL_GAP));
    }

    public void onRestart(float x, float scrollSpeed) {
        // synchronize pipe scroll speed with game scroll speed
        _velocity.x = scrollSpeed;
        reset(x);
    }
}
