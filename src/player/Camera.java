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
		peripherals = window.getPeripherals();
	}
	
	public Vector3f getRotations() {
		return rotations;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public void move() {
		rotations.add(peripherals.mouse.getMousePos());
		rotations.x = rotations.x%360;
		rotations.y = rotations.y%360;
		//System.out.println(rotations.x+" "+rotations.y);
		position.add(peripherals.keyboard.updatePos(rotations.x, rotations.y));
		System.out.println(position.x+" "+position.y+" "+position.z);
	}

}
