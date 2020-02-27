package player;

import org.joml.Vector3f;

import display.Window;
import game.GameEngine;
import io.PeripheralController;

public class Camera {
	
	private Vector3f rotations;
	private Vector3f position;
	private PeripheralController peripherals;
	
	public Camera() {
		this.rotations = new Vector3f(0,0,0);
		this.position = new Vector3f(0,0,0);
		Window window = GameEngine.windows.getWindow();
	}
	
	public Vector3f getRotations() {
		return rotations;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public void move() {
		rotations.add(peripherals.mouse.getMousePos());
		
	}

}
