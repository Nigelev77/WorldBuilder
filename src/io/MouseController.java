package io;

import org.joml.Vector2d;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseController{
	
	private GLFWMouseButtonCallback mouseButtons;
	private GLFWCursorPosCallback cursorPos;
	
	private Vector2d mouse = new Vector2d(0,0);
	private Vector2d deltaPos = new Vector2d(0,0);
	private Vector3f mousef = new Vector3f(0,0,0);
	
	public MouseController() {
		mouseButtons = new GLFWMouseButtonCallback() {
			@Override
			public void invoke(long window, int button, int action, int mods) {
				//System.out.println(GLFW.glfwGetMouseButton(window, button));
				
			}
		};
		cursorPos = new GLFWCursorPosCallback() {
			
			@Override
			public void invoke(long window, double xpos, double ypos) {
				deltaPos.x = xpos - mouse.x;
				deltaPos.y = ypos - mouse.y;
				
				mouse.x=xpos;
				mouse.y=ypos;
				
			}
		};
	}
	
	public GLFWMouseButtonCallback getMousButtons() {
		return mouseButtons;
	}
	
	public GLFWCursorPosCallback getCursorPos() {
		return cursorPos;
	}
	
	public void destroy() {
		mouseButtons.free();
		cursorPos.free();
	}
	public Vector3f getMousePos() {
		mousef.x = (float)(deltaPos.y);
		mousef.y = (float)(deltaPos.x);
		deltaPos.zero();
		return mousef;
	}
	

}
