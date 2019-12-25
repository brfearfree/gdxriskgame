package lv.dium.riskclient.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import lv.dium.riskclient.riskclient;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Risk Game Client";
		config.width = 1158;
		config.useGL30 = true;
		config.height = 780;
		config.samples=3;

		/*
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxWidth = 857;
		settings.maxHeight = 600;

		TexturePacker.process(settings, "core/assets/images", "core/assets/packed", "game");
		*/

		new LwjglApplication(new riskclient(), config);
	}
}
