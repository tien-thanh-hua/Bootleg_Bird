package com.bootleg.game.actors;

import static com.bootleg.game.utils.Config.pipeGap;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.bootleg.game.screen.GameScreen;
import com.bootleg.game.utils.GameRenderer;

import java.util.Random;

public class Pipe extends Scrollable {

    private Random r;
    private Rectangle _hitRectUp, _hitRectDown;
    public static final int VERTICAL_GAP = 45;
    private float _groundY;
    private boolean _isScored = false;

    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        _hitRectUp = new Rectangle();
        _hitRectDown = new Rectangle();
        r = new Random();
        _groundY = groundY;
    }

    @Override
    public boolean collides(Bird bird) {
        if (_position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getHitCircle(), getHitRectUp()) ||
                    Intersector.overlaps(bird.getHitCircle(), getHitRectDown()));
        }
        return false;
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        // Change the height to a random number
        _height = r.nextInt(90) + 15;
        _isScored = false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        _hitRectUp.set(_position.x, _position.y, _width,_height);
        _hitRectDown.set(_position.x, _position.y + _height + VERTICAL_GAP, _width,
                _groundY - (_position.y + _height + VERTICAL_GAP));
    }

    public Rectangle getHitRectUp() {
        return _hitRectUp;
    }
    public Rectangle getHitRectDown() {
        return _hitRectDown;
    }

    public boolean isScored() {
        return _isScored;
    }

    public void setScored(boolean b) {
        _isScored = b;
    }
}
