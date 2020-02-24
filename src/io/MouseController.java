package io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseController{
	
	private GLFWMouseButtonCallback mouseButtons;
	private GLFWCursorPosCallback cursorPos;
	
	public MouseController() {
		mouseButtons = new GLFWMouseButtonCallback() {
			
			@Override
			public void invoke(long window, int button, int action, int mods) {
				System.out.println(GLFW.glfwGetMouseButton(window, button));
				
			}
		};
		cursorPos = new GLFWCursorPosCallback() {
			
			@Override
			public void invoke(long window, double xpos, double ypos) {
				System.out.println(xpos+" "+ypos);
				
			}
		};
	}
	
	public GLFWMouseButtonCallback getMousButtons() {
		return mouseButtons;
	}
	
	public GLFWCursorPosCallback getCursorPos() {
		return cursorPos;
	}

}
