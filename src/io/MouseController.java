package io;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import selectors.GUISelector;

public class MouseController{
	
	private GLFWMouseButtonCallback mouseButtons;
	private GLFWCursorPosCallback cursorPos;
	
	private static Vector2d mouse = new Vector2d(0,0);
	private Vector2d deltaPos = new Vector2d(0,0);
	private Vector3f mousef = new Vector3f(0,0,0);
	
	public static boolean mouseFree = false;
	
	public MouseController() {
		mouseButtons = new GLFWMouseButtonCallback() {
			@Override
			public void invoke(long window, int button, int action, int mods) {
				if(button == GLFW.GLFW_MOUSE_BUTTON_RIGHT && action == GLFW.GLFW_PRESS) {
					mouseFree = !mouseFree;
					if(!mouseFree) {
						GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
						GLFW.glfwSetInputMode(window, GLFW.GLFW_RAW_MOUSE_MOTION, GLFW.GLFW_TRUE);
					}else {
						GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
						GLFW.glfwSetInputMode(window, GLFW.GLFW_RAW_MOUSE_MOTION, GLFW.GLFW_FALSE);
					}
				}
				if(button == GLFW.GLFW_MOUSE_BUTTON_LEFT && mouseFree) {
					if(GLFW.glfwGetMouseButton(window, button)==GLFW.GLFW_PRESS) {
						GUISelector.checkGUI((float) mouse.x, (float) mouse.y);
					}else {
						GUISelector.releaseGUI();
					}

				}
				
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
		mousef.x = (float)(deltaPos.y) * (mouseFree ? 0: 1);
		mousef.y = (float)(deltaPos.x) * (mouseFree ? 0: 1);
		deltaPos.zero();
		return mousef;
	}
	
	public static Vector2f queryMousePos() {
		return new Vector2f((float) mouse.x, (float) mouse.y);
	}
	
	public static boolean queryMouseButton(int button) {
		return GLFW.glfwGetMouseButton(PeripheralController.window.getWindow(), button)==GLFW.GLFW_PRESS;
	}

}
