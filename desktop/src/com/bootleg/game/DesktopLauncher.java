package com.bootleg.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(120);
		config.setIdleFPS(8);
		config.setTitle("Bootleg Bird");
		config.setWindowedMode(600, 900);
		new Lwjgl3Application(new BootlegBird(), config);
	}
}
