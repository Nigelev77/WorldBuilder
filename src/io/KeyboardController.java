package io;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import display.WindowManager;

public class KeyboardController{
	
	private GLFWKeyCallback keyboard;
	
	private Vector2f pos = new Vector2f();
	private float elevation = 0;
	
	public KeyboardController(){
		keyboard = new GLFWKeyCallback() {
			
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				switch(key) {
				case GLFW.GLFW_KEY_W:
					pos.y -=10f;
					break;
				case GLFW.GLFW_KEY_S:
					pos.y +=10f;
					break;
				case GLFW.GLFW_KEY_A:
					pos.x -= 10f;
					break;
				case GLFW.GLFW_KEY_D:
					pos.x +=10f;
					break;
				case GLFW.GLFW_KEY_SPACE:
					elevation+=10f;
					break;
				case GLFW.GLFW_KEY_Q:
					elevation-=10f;
					break;
				case GLFW.GLFW_KEY_ESCAPE:
					WindowManager.shouldClose=true;
					break;
				}
				
			}
		};
	}
	
	
	public GLFWKeyCallback getKeyboard() {
		return keyboard;
	}
	
	public void destroy() {
		keyboard.free();
	}
	
	public Vector3f updatePos(float rx, float ry) {
		Vector3f newPos = new Vector3f();
		/*
		newPos.x = (float) Math.sin(Math.toRadians(90-ry))*pos.x;
		newPos.z = (float) Math.cos(Math.toRadians(90-ry))*pos.y;
		newPos.y = (float) Math.cos(Math.toRadians(90-rx))*elevation;
		*/
		
		newPos.x += pos.x;
		newPos.y += elevation;
		newPos.z += pos.y;
		pos.zero();
		elevation = 0;
		return newPos;
	}
	
}
