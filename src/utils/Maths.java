package utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import player.Camera;

public class Maths {
	
	public static Matrix4f setProjectionAndView(Matrix4f viewMatrix, Matrix4f projection, Camera camera) {
		
		Matrix4f.projViewFromRectangle(camera.getPosition(), null, null, null, 700, false, projection, viewMatrix);
		//NEED TO FINISH THIS
		return null;
		
	}
	
	
}
