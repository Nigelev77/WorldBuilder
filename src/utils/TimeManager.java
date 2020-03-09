package utils;

import org.lwjgl.glfw.GLFW;

public class TimeManager {
	
	private float currentTime;
	private float lastTime;
	private float delta;
	private long resolution;
	
	public TimeManager() {
		currentTime = (float) GLFW.glfwGetTime();
		lastTime = currentTime;
		delta = 0;
		resolution = (long) GLFW.glfwGetTime();
	}
	
	public void update() {
		currentTime = (float) GLFW.glfwGetTime();
		delta = currentTime-lastTime;
		lastTime = currentTime;
	}
	
	public float getFrameTimeSeconds() {
		return delta;
	}
	
}
