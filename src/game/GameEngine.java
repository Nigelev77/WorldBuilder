package game;

import org.lwjgl.glfw.GLFW;

import display.WindowManager;

public class GameEngine implements Runnable{
	
	private final Thread gameLoopThread;
	
	private WindowManager windows;
	
	public GameEngine() {
		
		windows = new WindowManager();
		gameLoopThread = new Thread(this, "GAME");
	}
	
	public void start() {
		init();
	}
	
	private void init() {
		gameLoopThread.start();
	}
	
	
	@Override
	public void run() {
		windows.createWindow();
		windows.createContext();
		while(!GLFW.glfwWindowShouldClose(windows.getWindowNum())) {
			windows.update();
		}
		
	}
	
	
	
}
