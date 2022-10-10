package com.bootleg.game.utils;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.bootleg.game.actors.Bird;

public class GameWorld {
    private Bird _bird;
    private ScrollHandler _scroller;
    private Rectangle _land;
    private int _score = 0;

    public GameWorld(int midPointY) {
        _bird = new Bird(33, midPointY - 5, 17, 12);
        _scroller = new ScrollHandler(this, midPointY + 66);
        _land = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public Bird getBird() {
        return _bird;
    }

    public int getScore() {
        return _score;
    }

    public ScrollHandler getScroller() {
        return _scroller;
    }

    public void addScore(int increment) {
        _score += increment;
    }

    public void update(float delta) {

        _bird.update(delta);
        _scroller.update(delta);

        // when bird collides with pipe or ground, stop scrolling
        if ((_bird.isAlive()) && (_scroller.collides(_bird))) {

            _scroller.stop();
            AssetLoader.hit.play();
            AssetLoader.die.play();
            _bird.die();
        }

        // bird stays on ground
        if (Intersector.overlaps(_bird.getHitCircle(),_land)) {
            _scroller.stop();
            _bird.die();
            _bird.decelerate();
        }
    }
}
