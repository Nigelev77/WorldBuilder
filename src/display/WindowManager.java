package display;

import utils.TimeManager;

public class WindowManager {
	
	public static int WIDTH = 1600, HEIGHT = 900;
	public static float aspectRatio = ( float)WIDTH/HEIGHT;
	public static boolean shouldClose = false;
	public static TimeManager time;
	
	public static boolean windowCreated = false;
	
	private Window window;
	
	public WindowManager() {
		window = new Window(WIDTH, HEIGHT, "GAME");
		time = new TimeManager();
	}
	
	public void createWindow() {
		window.createWindow();
	}
	
	public void createContext() {
		window.createContext();
	}
	
	public void update() {
		window.update();
	}
	
	public Window getWindow() {
		return window;
	}
	
	public long getWindowNum() {
		return window.getWindow();
	}
	
	public void destroy() {
		window.destroy();
	}
	
	public static void toClose() {
		shouldClose = true;
	}
	
	public static float getFrameTimer() {
		return time.getFrameTimeSeconds();
	}
	
	
}
