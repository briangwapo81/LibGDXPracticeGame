package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.Null;
import com.mygdx.game.TestingGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public int xsize;
	public int ysize;
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode()); Fullscreen
		config.setForegroundFPS(60);
		config.setTitle("Hallo Warldd");
		config.setWindowedMode(800,480);
		new Lwjgl3Application(new Drop(), config);
	}
}
