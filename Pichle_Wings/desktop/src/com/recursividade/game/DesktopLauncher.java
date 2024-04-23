//Configurações da janela,
package com.recursividade.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.recursividade.game.PickleWings;
// cor de jundo das imagens : #252627
// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("PickleWings");
		config.setWindowedMode(1280,720); //tamanho da janela
		new Lwjgl3Application(new PickleWings(), config);
	}
}
