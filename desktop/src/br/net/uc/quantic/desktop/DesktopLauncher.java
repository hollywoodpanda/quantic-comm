package br.net.uc.quantic.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.net.uc.quantic.QuanticComm;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.resizable = true;
		config.title = "Are we there yet?";
		config.width = 1200;
		config.height = 960;

		new LwjglApplication(new QuanticComm(), config);
	}
}
