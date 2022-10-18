package com.bootleg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetLoader {
    public static Texture bg, base, birdMid, birdDown, birdUp, pipe, ready, gameOver;
    public static Animation<Texture> birdAnimation;

    public static Sound hit;
    public static Sound die;
    public static Sound point;
    public static Sound wing;

    public static BitmapFont font, shadow;

    public static void load() {
        bg = new Texture(Gdx.files.internal("sprites\\background-day.png"));
        bg.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        base = new Texture("sprites\\base.png");
        base.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        birdDown = new Texture("sprites\\bluebird-downflap.png");
        birdDown.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        birdMid = new Texture("sprites\\bluebird-midflap.png");
        birdMid.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        birdUp = new Texture("sprites\\bluebird-upflap.png");
        birdUp.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        pipe = new Texture("sprites\\pipe-green.png");
        pipe.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        ready = new Texture("sprites\\message.png");
        ready.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        gameOver = new Texture("sprites\\gameover.png");
        gameOver.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        Texture[] birds = { birdDown, birdMid, birdUp };
        // create new animation from birds[] with frame duration of 0.06f
        birdAnimation = new Animation<Texture>(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        hit = Gdx.audio.newSound(Gdx.files.internal("audio\\hit.ogg"));
        die = Gdx.audio.newSound(Gdx.files.internal("audio\\die.ogg"));
        point = Gdx.audio.newSound(Gdx.files.internal("audio\\point.ogg"));
        wing = Gdx.audio.newSound(Gdx.files.internal("audio\\wing.ogg"));

        font = new BitmapFont(Gdx.files.internal("font\\text.fnt"));
        font.getData().setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("font\\shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);
    }

    // dispose assets when game closes
    public static void dispose() {
        bg.dispose();
        base.dispose();
        birdDown.dispose();
        birdMid.dispose();
        birdUp.dispose();
        pipe.dispose();
        hit.dispose();
        die.dispose();
        point.dispose();
        wing.dispose();
        font.dispose();
        shadow.dispose();
    }
}
