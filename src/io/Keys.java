package io;

import org.lwjgl.glfw.GLFW;

import display.Window;

public enum Keys {
	
	X(GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_A), Y(GLFW.GLFW_KEY_SPACE, GLFW.GLFW_KEY_Q), Z(GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_W);
	
	int[] keys;
	
	
	Keys(int...values) {
		this.keys = values;
	}
	
	public static float keysAffectingAxis(Keys key, Window window) {
		float result = 0f;
		if(window.isKeyDown(key.keys[0])) {
			result++;
		}
		if(window.isKeyDown(key.keys[1])) {
			result--;
		}
		return result;
	}
	
}
