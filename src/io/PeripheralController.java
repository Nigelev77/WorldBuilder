package io;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import display.Window;

public class PeripheralController {
	
	public KeyboardController keyboard;
	public MouseController mouse;
	
	protected static Window window;
	
	public PeripheralController(Window window) {
		keyboard = new KeyboardController();
		mouse = new MouseController();
		this.window=window;
	}
	
	public GLFWKeyCallback getKeyboard() {
		return keyboard.getKeyboard();
	}
	
	public GLFWMouseButtonCallback getMouseButtons() {
		return mouse.getMousButtons();
	}
	public GLFWCursorPosCallback getCursorPos() {
		return mouse.getCursorPos();
	}
	
	public void destroy() {
		keyboard.destroy();
		mouse.destroy();
	}
	
}
