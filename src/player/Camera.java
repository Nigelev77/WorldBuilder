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
		rotations.x = Math.max(360, rotations.x);
		rotations.x = Math.min(0, rotations.x);
		rotations.y = Math.min(0, rotations.y);
		rotations.y = Math.max(360, rotations.y);
		//System.out.println(rotations.toString());
		position.add(peripherals.keyboard.updatePos(rotations.x, rotations.y));
		System.out.println(position.toString());
	}

}
