package display;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import engine.RenderEngine;
import game.GameEngine;
import io.PeripheralController;

public class Window {
	
	private int width, height;
	private String title;
	private long window;
	private boolean vsync = true;
	
	private PeripheralController peripherals;
	
	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		peripherals = new PeripheralController();
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
		setupInputCallbacks();
	}
	
	public void createContext() {
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
		if(vsync) {
			GLFW.glfwSwapInterval(1);
		}
	}
	
	public void update() {
		RenderEngine.render();
		synchronized(GameEngine.glfwLock) {
			if(GameEngine.isRunning) {
				swapBuffers();
			}
		}

	}
	public void destroy() {
		peripherals.destroy();
		System.out.println(Thread.currentThread().getName());
		GLFW.glfwDestroyWindow(window);
	}
	
	public void swapBuffers() {
		GLFW.glfwSwapBuffers(window);
	}
	
	
	public long getWindow() {
		return window;
	}
	
	private void setupInputCallbacks() {
		GLFW.glfwSetKeyCallback(window, peripherals.getKeyboard());
		GLFW.glfwSetMouseButtonCallback(window, peripherals.getMouseButtons());
		GLFW.glfwSetCursorPosCallback(window, peripherals.getCursorPos());
	}
}
