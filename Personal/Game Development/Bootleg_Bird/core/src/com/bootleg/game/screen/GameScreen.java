package com.bootleg.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bootleg.game.utils.GameRenderer;
import com.bootleg.game.utils.GameWorld;
import com.bootleg.game.utils.InputHandler;

public class GameScreen implements Screen {
    private GameWorld _world;
    private GameRenderer _renderer;
    private float _runTime = 0;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int) (gameHeight / 2);
        _world = new GameWorld(midPointY);
        _renderer = new GameRenderer(_world, (int) gameHeight, midPointY);
        Gdx.input.setInputProcessor(new InputHandler(_world.getBird()));
    }

    @Override
    public void render(float delta) {
        _runTime += delta;
        _world.update(delta);
        _renderer.render(_runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
