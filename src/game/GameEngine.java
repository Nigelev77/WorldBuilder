package game;

import org.lwjgl.glfw.GLFW;

import display.WindowManager;
import engine.RenderEngine;

public class GameEngine implements Runnable{
	
	//With inspiration from Kai Burjack from repo https://github.com/LWJGL/lwjgl3-demos/blob/master/src/org/lwjgl/demo/opengl/glfw/Multithreaded.java for the synchronized object idea!
	
	private final Thread gameLoopThread;
	
	private WindowManager windows;
	
	public static boolean isRunning = true;
	
	public static Object glfwLock = new Object();
	
	public GameEngine() {
		
		windows = new WindowManager();
		gameLoopThread = new Thread(this, "GAME");
	}
	
	public void start() {
		init();
	}
	
	private void init() {
		windows.createWindow();
		startGameLoop();
		while(!GLFW.glfwWindowShouldClose(windows.getWindowNum())) {
			GLFW.glfwWaitEvents();
		}
		synchronized(GameEngine.glfwLock) {
			isRunning = false;
			windows.destroy();
		}
	}
	private void startGameLoop() {
		gameLoopThread.start();
	}

	
	
	@Override
	public void run() {
		windows.createContext();
		RenderEngine.init();
		while(GameEngine.isRunning) {
			windows.update();
		}

		
	}
	
	
	
}
