package com.bootleg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AssetLoader {
    public static Texture bg, base, birdMid, birdDown, birdUp, pipe;
    public static Animation birdAnimation;

    public static void load() {
        bg = new Texture(Gdx.files.internal("flappy-bird-assets\\sprites\\background-day.png"));
        bg.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        base = new Texture("flappy-bird-assets\\sprites\\base.png");
        base.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        birdDown = new Texture("flappy-bird-assets\\sprites\\bluebird-downflap.png");
        birdDown.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        birdMid = new Texture("flappy-bird-assets\\sprites\\bluebird-midflap.png");
        birdMid.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        birdUp = new Texture("flappy-bird-assets\\sprites\\bluebird-upflap.png");
        birdUp.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        Texture[] birds = { birdDown, birdMid, birdUp };
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        pipe = new Texture("flappy-bird-assets\\sprites\\pipe-green.png");
        pipe.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
    }

    public static void dispose() {
        bg.dispose();
        base.dispose();
        birdDown.dispose();
        birdMid.dispose();
        birdUp.dispose();
        pipe.dispose();
    }
}
