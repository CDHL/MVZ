package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MVZ_20020804;

public class DesktopLauncher {
	
	public static final int STAGE_WIDTH = 1024;
	public static final int STAGE_HEIGHT = 680;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = STAGE_WIDTH;
		config.height = STAGE_HEIGHT;
		config.title = "Monsters vs. Zombies";
		config.resizable = false;
		new LwjglApplication(new MVZ_20020804(), config);
	}
}
