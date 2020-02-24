package display;

public class WindowManager {
	
	public static int WIDTH = 1600, HEIGHT = 900;
	
	
	private Window window;
	
	public WindowManager() {
		window = new Window(WIDTH, HEIGHT, "GAME");
	}
	
}
