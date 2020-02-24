package display;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {
	
	private int width, height;
	private String title;
	private long window;
	
	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	public void createWindow() {
		if(!GLFW.glfwInit()) {
			System.err.println("Could not initialiase glfw");
			System.exit(-1);
		}
		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		if(window == 0) {
			System.err.println("Could not create window");
			System.exit(-1);
		}
		
		GLFWVidMode video = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (video.width()-width)/2, (video.height()-height)/2);
		GLFW.glfwShowWindow(window);
		
	}

}
