package io;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class PeripheralController {
	
	public KeyboardController keyboard;
	public MouseController mouse;
	
	public PeripheralController() {
		keyboard = new KeyboardController();
		mouse = new MouseController();
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
