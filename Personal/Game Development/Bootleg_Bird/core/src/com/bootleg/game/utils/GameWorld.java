package com.bootleg.game.utils;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.bootleg.game.actors.Bird;

public class GameWorld {

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }

    private Bird _bird;
    private ScrollHandler _scroller;
    private Rectangle _land;
    private GameState _currentState;
    private int _score = 0;
    private int _midPointY;


    public GameWorld(int midPointY) {
        _bird = new Bird(33, midPointY - 5, 17, 12);
        _scroller = new ScrollHandler(this, midPointY + 66);
        _land = new Rectangle(0, midPointY + 66, 136, 11);
        _currentState = GameState.READY;
        _midPointY = midPointY;
    }

    // getters
    public Bird getBird() {
        return _bird;
    }
    public int getScore() {
        return _score;
    }
    public ScrollHandler getScroller() {
        return _scroller;
    }
    public boolean isReady() {
        return _currentState == GameState.READY;
    }
    public boolean isRunning() {
        return _currentState == GameState.RUNNING;
    }
    public boolean isGameOver() {
        return _currentState == GameState.GAMEOVER;
    }

    public void addScore(int increment) {
        _score += increment;
    }

    // RUNNING when first tapped (started)
    public void start() {
        _currentState = GameState.RUNNING;
    }

    // READY on game boot and after restart
    public void restart() {
        _currentState = GameState.READY;
        _score = 0;
        _bird.onRestart(_midPointY - 5);
        _scroller.onRestart();
        _currentState = GameState.READY;
    }

    // update game objects every frame (does not rely on FPS)
    public void update(float delta) {
        switch (_currentState) {
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            case GAMEOVER:
        }
    }

    public void updateReady(float delta) {
        _scroller.updateLand(delta);
    }

    public void updateRunning(float delta) {

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
        if (Intersector.overlaps(_bird.getHurtCircle(),_land)) {
            _scroller.stop();
            _bird.die();
            _bird.decelerate();
            _currentState = GameState.GAMEOVER;
        }
    }

}
