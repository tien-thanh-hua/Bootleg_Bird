package com.bootleg.game.utils;

import com.badlogic.gdx.InputProcessor;
import com.bootleg.game.actors.Bird;

public class InputHandler implements InputProcessor {
    private Bird _bird;
    private GameWorld _world;

    public InputHandler(Bird bird) {
        _bird = bird;
    }

    public InputHandler(GameWorld world) {
        // _bird now represents the gameWorld's bird
        _world = world;
        _bird = world.getBird();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //  touch starts game if it's in READY state
        if (_world.isReady()) {
            _world.start();
        }

        // touch executes bird's onTap function during RUNNING state
        _bird.onTap();

        // touch restarts game if game is over
        if (_world.isGameOver()) {
            _world.restart();
        }
        return true;
    }

    // the rest is unnecessary to implement
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
