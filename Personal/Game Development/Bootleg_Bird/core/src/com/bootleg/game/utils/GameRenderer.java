package com.bootleg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer {
    private GameWorld _world;
    private OrthographicCamera _cam;
    private ShapeRenderer _shapeRenderer;

    public GameRenderer(GameWorld world) {
        _world = world;
        _cam = new OrthographicCamera();
        _shapeRenderer = new ShapeRenderer();
        _cam.setToOrtho(true, 136, 204);
        _shapeRenderer.setProjectionMatrix(_cam.combined);
    }

    public void render() {
        Gdx.app.log("GameRenderer", "render");

        // draw black background
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
