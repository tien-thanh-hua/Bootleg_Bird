package com.bootleg.game.utils;

import com.badlogic.gdx.Gdx;
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

    // values for more convenient calculations
    private int _midPointX = 136 / 2;
    private int _midPointY;
    private int _gameHeight;
    private float _alpha = 1;
    private boolean _hasFlashed = false;

    private Bird _bird;
    private ScrollHandler _scroller;
    private Land _land1, _land2;
    private Pipe _pipe1, _pipe2, _pipe3;

    private Texture _bg, _base, _birdMid, _birdDown, _birdUp, _pipe, _ready, _gameOver;
    private Animation _birdAnimation;

    // constructor
    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        _world = world;

        _gameHeight = gameHeight;
        _midPointY = midPointY;

        // gdx is a 3D framework, but an orthographic cam can simulate 2D graphics
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
        _ready = AssetLoader.ready;
        _gameOver = AssetLoader.gameOver;
    }

    // drawing methods
    private void drawLand() {
        // draw the base (land)
        _spriteBatch.draw(_base, _land1.getX(), _land1.getY(), _land1.getWidth(),
                56,0, 0, _base.getWidth(), _base.getHeight(), false, true);
        _spriteBatch.draw(_base, _land2.getX(), _land2.getY(), _land2.getWidth(),
                56,0, 0, _base.getWidth(), _base.getHeight(), false, true);
    }

    private void drawPipes() {
        // I know it works, dunno how though
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

    // takes runTime argument from render(runTime)
    private void drawBird(float runTime) {
        if (_bird.shouldNotFlap()) {
            // stops bird movement animation
            TextureRegion birdStill = new TextureRegion (_birdMid,
                    _bird.getWidth() * 2,
                    _bird.getHeight() * 2);
            _spriteBatch.draw(birdStill, _bird.getX(), _bird.getY(), _bird.getWidth() / 2.0f,
                    _bird.getHeight() / 2.0f, _bird.getWidth(), _bird.getHeight(),  1, -1,
                    _bird.getRotation());
        } else {
            // animate flapping bird
            TextureRegion birdFrame =
                    new TextureRegion((Texture) _birdAnimation.getKeyFrame(runTime),
                            _bird.getWidth() * 2, _bird.getHeight() * 2);
            _spriteBatch.draw(birdFrame, _bird.getX(), _bird.getY(), _bird.getWidth() / 2.0f,
                    _bird.getHeight() / 2.0f, _bird.getWidth(), _bird.getHeight(),  1, -1,
                    _bird.getRotation());
        }
    }

    private void drawScore() {
        String score = _world.getScore() + "";
        AssetLoader.shadow.draw(_spriteBatch, "" + _world.getScore(),
                (136 / 2.0f) - (3 * score.length() - 1),
                12);
        AssetLoader.font.draw(_spriteBatch, "" + _world.getScore(),
                (136 / 2.0f) - (3 * score.length()),11);
    }

    private void drawReady() {
        _spriteBatch.draw(_ready, _ready.getWidth() / 2.0f - _midPointX,
                _ready.getHeight() / 2.0f - _midPointY, 184 / 2, 267 / 2,
                0, 0,
                184,
                267, false, true);
    }

    private void drawGameOver() {
        // game over + restart prompt
        if (_world.isGameOver()) {
            _spriteBatch.draw(_gameOver, _gameOver.getWidth() / 2.2f - _midPointX,
                    _midPointY - _gameOver.getHeight(), 192 / 2.0f,
                    42 / 2.0f, 0, 0,
                    192, 42, false, true);
        }
    }

    private void drawFlash(float delta) {

        if (_alpha > 0) {
            _alpha -= delta;
        }

        // enable transparency for shapes
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // begins shapeRenderer for vector shape rendering
        _shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // set white color
        _shapeRenderer.setColor(255, 255, 255, _alpha);

        // white flash rectangle's size matching the game window size
        _shapeRenderer.rect(0,0,Gdx.graphics.getHeight(),Gdx.graphics.getWidth());

        // end shapeRenderer
        _shapeRenderer.end();

        // disable transparency for future shapes
        Gdx.gl.glDisable(GL20.GL_BLEND);

        if (_alpha == 0) {
            _hasFlashed = true;
        }

    }

    // collision drawing for debugging, too lazy to implement land collision
    private void drawCollisionPipes(Pipe pipe) {
        _shapeRenderer.rect(pipe.getHitRectUp().x, pipe.getHitRectUp().y,
                pipe.getHitRectUp().getWidth(), pipe.getHitRectUp().getHeight());
        _shapeRenderer.rect(pipe.getHitRectDown().x, pipe.getHitRectDown().y,
                pipe.getHitRectDown().getWidth(), pipe.getHitRectDown().getHeight());
    }
    private void drawCollisionBird(Bird bird) {
        _shapeRenderer.circle(_bird.getHurtCircle().x, _bird.getHurtCircle().y,
                _bird.getHurtCircle().radius);
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

    public void render(float delta, float runTime) {
        // draw background color
        Gdx.gl.glClearColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // begin spriteBatch for texture drawing
        _spriteBatch.begin();

        // draw background
        _spriteBatch.draw(_bg, 0, -36, 136, 204, 0, 0, 272, 408, false, true);

        // draw land
        drawLand();

        // draw pipes
        drawPipes();

        // draw bird
        drawBird(runTime);

        // if hit, flashes the screen
        if (_scroller.collides(_bird) && (_world.isRunning() || _world.isGameOver()) && !_hasFlashed) {
            // drawFlash(delta);
        }

        // start game prompt (in READY state)
        if (_world.isReady()) {
            drawReady();
            _alpha = 1;
            _hasFlashed = false;
        } else if (_world.isRunning()) {
            // draw score only while RUNNING
            drawScore();
        } else {
            // draw Game Over
            drawGameOver();
        }

        // draw collision for debugging purposes
        // drawCollisionBird(_bird);
        // drawCollisionPipes(_pipe1);
        // drawCollisionPipes(_pipe2);
        // drawCollisionPipes(_pipe3);
        // drawCollision();

        _spriteBatch.end();
    }
}
