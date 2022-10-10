package com.bootleg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bootleg.game.screen.GameScreen;
import com.bootleg.game.utils.AssetLoader;

public class BootlegBird extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		Gdx.app.log("BootlegBird", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
