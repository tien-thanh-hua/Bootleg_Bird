package com.bootleg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bootleg.game.actors.Bird;
import com.bootleg.game.actors.Land;
import com.bootleg.game.actors.Pipe;

public class GameRenderer {
    private GameWorld _world;
    private OrthographicCamera _cam;
    private ShapeRenderer _shapeRenderer;

    private SpriteBatch _spriteBatch;

    private int _midPointY;
    private int _gameHeight;

    private Bird _bird;
    private ScrollHandler _scroller;
    private Land _land1, _land2;
    private Pipe _pipe1, _pipe2, _pipe3;

    private Texture _bg, _base, _birdMid, _birdDown, _birdUp, _pipe;
    private Animation _birdAnimation;

    // initialize actors
    private void initActors() {
        _bird = _world.getBird();
        _scroller = _world.getScroller();
        _land1 = _scroller.getLand1();
        _land2 = _scroller.getLand2();
        _pipe1 = _scroller.getPipe1();
        _pipe2 = _scroller.getPipe2();
        _pipe3 = _scroller.getPipe3();
    }

    // initialize Assets
    private void initAssets() {
        _bg = AssetLoader.bg;
        _base = AssetLoader.base;
        _birdAnimation = AssetLoader.birdAnimation;
        _birdMid = AssetLoader.birdMid;
        _birdDown = AssetLoader.birdDown;
        _birdUp = AssetLoader.birdUp;
        _pipe = AssetLoader.pipe;
    }

    private void drawLand() {
        // Draw the base (land)
        _spriteBatch.draw(_base, _land1.getX(), _land1.getY(), _land1.getWidth(),
                56,0, 0, _base.getWidth(), _base.getHeight(), false, true);
        _spriteBatch.draw(_base, _land2.getX(), _land2.getY(), _land2.getWidth(),
                56,0, 0, _base.getWidth(), _base.getHeight(), false, true);
    }

    private void drawPipes() {
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.
        _spriteBatch.draw(_pipe, _pipe1.getX(), _pipe1.getY(), _pipe1.getWidth(),
                _pipe1.getHeight(),0, 0, _pipe.getWidth(), _pipe.getHeight(), false, false);
        _spriteBatch.draw(_pipe, _pipe1.getX(), _pipe1.getY() + _pipe1.getHeight() + 45,
                _pipe1.getWidth(), _midPointY + 66 - (_pipe1.getHeight() + 45),0, 0,
                _pipe.getWidth(), _pipe.getHeight(), false, true);

        _spriteBatch.draw(_pipe, _pipe2.getX(), _pipe2.getY(), _pipe2.getWidth(),
                _pipe2.getHeight(),0, 0, _pipe.getWidth(), _pipe.getHeight(), false, false);
        _spriteBatch.draw(_pipe, _pipe2.getX(), _pipe2.getY() + _pipe2.getHeight() + 45,
                _pipe2.getWidth(), _midPointY + 66 - (_pipe2.getHeight() + 45),0, 0,
                _pipe.getWidth(), _pipe.getHeight(), false, true);

        _spriteBatch.draw(_pipe, _pipe3.getX(), _pipe3.getY(), _pipe3.getWidth(),
                _pipe3.getHeight(),0, 0, _pipe.getWidth(), _pipe.getHeight(), false, false);
        _spriteBatch.draw(_pipe, _pipe3.getX(), _pipe3.getY() + _pipe3.getHeight() + 45,
                _pipe3.getWidth(), _midPointY + 66 - (_pipe3.getHeight() + 45),0, 0,
                _pipe.getWidth(), _pipe.getHeight(), false, true);
    }

    private void drawCollisionPipes(Pipe pipe) {
        _shapeRenderer.rect(pipe.getHitRectUp().x, pipe.getHitRectUp().y,
                pipe.getHitRectUp().getWidth(), pipe.getHitRectUp().getHeight());
        _shapeRenderer.rect(pipe.getHitRectDown().x, pipe.getHitRectDown().y,
                pipe.getHitRectDown().getWidth(), pipe.getHitRectDown().getHeight());
    }

    private void drawCollisionBird(Bird bird) {
        _shapeRenderer.circle(_bird.getHitCircle().x, _bird.getHitCircle().y,
                _bird.getHitCircle().radius);
    }

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        _world = world;

        _gameHeight = gameHeight;
        _midPointY = midPointY;

        _cam = new OrthographicCamera();
        _cam.setToOrtho(true, 136, gameHeight);

        _shapeRenderer = new ShapeRenderer();
        _shapeRenderer.setProjectionMatrix(_cam.combined);

        _spriteBatch = new SpriteBatch();
        _spriteBatch.setProjectionMatrix(_cam.combined);

        // initialize actors and assets
        initActors();
        initAssets();
    }

    private void drawCollision() {
        _shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        _shapeRenderer.setColor(0, 0, 255, 0.5f);
        drawCollisionBird(_bird);
        _shapeRenderer.setColor(255, 0, 0, 0.5f);
        drawCollisionPipes(_pipe1);
        drawCollisionPipes(_pipe2);
        drawCollisionPipes(_pipe3);
        _shapeRenderer.end();
    }

    public void render(float runTime) {
        // draw background color
        Gdx.gl.glClearColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _spriteBatch.begin();

        // draw background
        _spriteBatch.draw(_bg, 0, -36, 136, 204, 0, 0, 272, 408, false, true);

        // draw land
        drawLand();
        // draw pipes
        drawPipes();

        // draw bird
        if (_bird.shouldntFlap()) {
            TextureRegion birdStill = new TextureRegion (_birdMid,
                    _bird.getWidth() * 2,
                    _bird.getHeight() * 2);
            _spriteBatch.draw(birdStill, _bird.getX(), _bird.getY(), _bird.getWidth() / 2.0f,
                    _bird.getHeight() / 2.0f, _bird.getWidth(), _bird.getHeight(),  1, -1,
                    _bird.getRotation());
        } else {
            TextureRegion birdFrame =
                    new TextureRegion((Texture) _birdAnimation.getKeyFrame(runTime),
                            _bird.getWidth() * 2, _bird.getHeight() * 2);
            _spriteBatch.draw(birdFrame, _bird.getX(), _bird.getY(), _bird.getWidth() / 2.0f,
                    _bird.getHeight() / 2.0f, _bird.getWidth(), _bird.getHeight(),  1, -1,
                    _bird.getRotation());
        }

        // draw score
        String score = _world.getScore() + "";
        AssetLoader.shadow.draw(_spriteBatch, "" + _world.getScore(),
                (136 / 2.0f) - (3 * score.length() - 1),
                12);
        AssetLoader.font.draw(_spriteBatch, "" + _world.getScore(),
                (136 / 2.0f) - (3 * score.length()),11);

        // draw collision for debugging purposes
        // drawCollision();

        _spriteBatch.end();
    }
}
