package io;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import display.WindowManager;
import gui.GUIMasterRenderer;

public class KeyboardController{
	
	private GLFWKeyCallback keyboard;
	
	private Vector2f pos = new Vector2f();
	
	public KeyboardController(){
		
		keyboard = new GLFWKeyCallback() {
			
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if(key==GLFW.GLFW_KEY_ESCAPE) {
					WindowManager.shouldClose = true;
				}else if(key==GLFW.GLFW_KEY_M && GLFW.glfwGetKey(window, key)==GLFW.GLFW_RELEASE) {
					GUIMasterRenderer.showMenu= !GUIMasterRenderer.showMenu;
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
		
		newPos.y = Keys.keysAffectingAxis(Keys.Y, PeripheralController.window);
		
		float distanceForwards = Keys.keysAffectingAxis(Keys.Z, PeripheralController.window);
		float distanceSideways = Keys.keysAffectingAxis(Keys.X, PeripheralController.window);
		
		newPos.x = (float) (distanceForwards * Math.sin(-Math.toRadians(ry)));
		newPos.z = (float) (distanceForwards* Math.cos(-Math.toRadians(ry)));
		newPos.x += distanceSideways*Math.sin(Math.toRadians(ry+90f));
		newPos.z -= distanceSideways*Math.cos(Math.toRadians(ry+90f));
		
		float delta = WindowManager.getFrameTimer();
		newPos.z *= delta*10;
		newPos.x *= delta*10;
		newPos.y *= delta*10;
		

		pos.zero();
		return newPos;
	}
	
}
