package io;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class PeripheralController {
	
	private KeyboardController keyboard;
	private MouseController mouse;
	
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
	
}
