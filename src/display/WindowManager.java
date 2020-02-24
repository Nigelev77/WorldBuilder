package display;

public class WindowManager {
	
	public static int WIDTH = 1600, HEIGHT = 900;
	
	
	private Window window;
	
	public WindowManager() {
		window = new Window(WIDTH, HEIGHT, "GAME");
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
	
}
