package com.bootleg.game.utils;

import com.bootleg.game.actors.Bird;
import com.bootleg.game.actors.Land;
import com.bootleg.game.actors.Pipe;
import com.bootleg.game.screen.GameScreen;

public class GameWorld {
    private Bird _bird;
    private ScrollHandler _scroller;

    public GameWorld(int midPointY) {
        _bird = new Bird(33, midPointY - 5, 17, 12);
        _scroller = new ScrollHandler(midPointY + 66);
    }

    public Bird getBird() {
        return _bird;
    }

    public ScrollHandler getScroller() {
        return _scroller;
    }

    public void update(float delta) {
        // Gdx.app.log("GameWorld", "update");
        _bird.update(delta);
        _scroller.update(delta);

        if (_scroller.collides(_bird)) {
            // Clean up on game over
            _scroller.stop();
        }
    }


}
