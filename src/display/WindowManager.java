package display;

public class WindowManager {
	
	public static int WIDTH = 1600, HEIGHT = 900;
	public static int aspectRatio = WIDTH/HEIGHT;
	
	
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
	
	public void destroy() {
		window.destroy();
	}
	
	
	
}
