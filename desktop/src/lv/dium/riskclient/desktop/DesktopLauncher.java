package lv.dium.riskclient.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import lv.dium.riskclient.riskclient;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "TRisk Game CLient";
		config.width = 857;
		config.height = 600;

		new LwjglApplication(new riskclient(), config);
	}
}
