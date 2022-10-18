package com.bootleg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.bootleg.game.screen.GameScreen;
import com.bootleg.game.utils.AssetLoader;

public class BootlegBird extends Game {

	@Override
	public void create () {
		Gdx.app.log("BootlegBird", "created");
		// load game assets
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		// dispose all loaded stuffs
		super.dispose();
		AssetLoader.dispose();
	}
}
