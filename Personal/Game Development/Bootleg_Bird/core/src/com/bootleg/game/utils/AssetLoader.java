package com.bootleg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture bg, base, bird, birdDown, birdUp, pipe;

    public static Animation birdAnimation;

    public static TextureRegion skullUp, skullDown, bar;

    public static void load() {
        bg = new Texture(Gdx.files.internal("assets\\flappy-bird-assets\\sprites\\background-day.png"));
        bg.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        base = new Texture("assets\\flappy-bird-assets\\sprites\\base.png");
        base.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        birdDown = new Texture("assets\\flappy-bird-assets\\sprites\\bluebird-downflap.png");
        birdDown.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bird = new Texture("assets\\flappy-bird-assets\\sprites\\bluebird-midflap.png");
        bird.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        birdUp = new Texture("assets\\flappy-bird-assets\\sprites\\bluebird-upflap.png");
        birdUp.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        Texture[] birds = { birdDown, bird, birdUp };
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        pipe = new Texture("assets\\flappy-bird-assets\\sprites\\pipe-green.png");
        pipe.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
    }

    public static void dispose() {
        bg.dispose();
        base.dispose();
        birdDown.dispose();
        bird.dispose();
        birdUp.dispose();
        pipe.dispose();
    }
}
