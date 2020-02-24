package io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyboardController{
	
	private GLFWKeyCallback keyboard;
	
	public KeyboardController(){
		keyboard = new GLFWKeyCallback() {
			
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				System.out.println(GLFW.glfwGetKeyName(key, scancode));
				
			}
		};
	}
	
	
	public GLFWKeyCallback getKeyboard() {
		return keyboard;
	}
	
	
}
