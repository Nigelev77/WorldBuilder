package Main;

import org.lwjgl.glfw.GLFW;

import game.GameEngine;

public class Main {
	
	private static GameEngine game;
	
	
	public static void main(String[] args) {
		game = new GameEngine();
		game.start();
		GLFW.glfwTerminate();
		System.out.println(GameEngine.isRunning);
	}
}
