package com.bootleg.game.actors;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Land extends Scrollable {

    private Rectangle _hurtRect;
    // When Grass's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Land(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        _hurtRect = new Rectangle();
    }

    public Rectangle getHurtRect() {
        return _hurtRect;
    }

    @Override
    public boolean collides(Bird bird) {
        if (_position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getHurtCircle(), getHurtRect()));
        }
        return false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        _hurtRect.set(_position.x, _position.y, getWidth(),getHeight());
    }

    @Override
    public void onRestart(float x, float scrollSpeed) {
        _position.x = x;
        _velocity.x = scrollSpeed;
    }
}
