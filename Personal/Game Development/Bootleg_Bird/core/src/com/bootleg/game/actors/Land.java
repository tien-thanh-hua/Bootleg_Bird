package com.bootleg.game.actors;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import sun.util.resources.cldr.ext.CurrencyNames_fr_HT;

public class Land extends Scrollable {

    private Rectangle _hitRect;
    // When Grass's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Land(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        _hitRect = new Rectangle();
    }

    @Override
    public boolean collides(Bird bird) {
        if (_position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getHitCircle(), getHitRect()));
        }
        return false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        _hitRect.set(_position.x, _position.y, getWidth(),getHeight());
    }

    public Rectangle getHitRect() {
        return _hitRect;
    }
}
