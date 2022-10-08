package com.bootleg.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bootleg.game.utils.GameRenderer;
import com.bootleg.game.utils.GameWorld;

public class GameScreen implements Screen {
    private GameWorld _world;
    private GameRenderer _renderer;
    public GameScreen() {
        _world = new GameWorld();
        _renderer = new GameRenderer(_world);
        Gdx.app.log("GameScreen", "Attached");
    }

    @Override
    public void render(float delta) {
        _world.update(delta);
        _renderer.render();
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
