package com.bootleg.game.utils;

import com.badlogic.gdx.Gdx;
import com.bootleg.game.actors.Bird;

public class GameWorld {
    private Bird _bird;
    public GameWorld(int midPointY) {
        _bird = new Bird(33, midPointY - 5, 17, 12);
    }

    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        _bird.update(delta);
    }

    public Bird getBird() {
        return _bird;
    }
}
