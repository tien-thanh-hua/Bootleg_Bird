package com.bootleg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {
    private Rectangle _rect = new Rectangle(0, 0, 17, 12);

    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        _rect.x++;
        if (_rect.x > 137) {
            _rect.x = 0;
        }
    }

    public Rectangle getRect() {
        return _rect;
    }
}
