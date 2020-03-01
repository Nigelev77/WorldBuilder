package utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import display.WindowManager;
import player.Camera;

public class Maths {
	public static final Vector3f posX = new Vector3f(1,0,0);
	public static final Vector3f posY = new Vector3f(0,1,0);
	public static final Vector3f posZ = new Vector3f(0,0,1);
	public static final float FOV = 70f;
	private static final float NEAR_PLANE = 0.1f;
	
	
	public static void setProjectionAndView(Matrix4f viewMatrix, Matrix4f projection, Camera camera) {
		float x_width = (float) (NEAR_PLANE*Math.tan(Math.toRadians(FOV/2)));
		float y_width = x_width/WindowManager.aspectRatio;
		Vector3f nearPlane = new Vector3f(-x_width, -y_width, -NEAR_PLANE);
		nearPlane.rotateX(camera.getRotations().x);
		nearPlane.rotateY(camera.getRotations().y);
		Vector3f width = new Vector3f(Maths.posX).mul(NEAR_PLANE);
		Vector3f height = new Vector3f(Maths.posY).mul(NEAR_PLANE);
		
		Matrix4f.projViewFromRectangle(camera.getPosition(), nearPlane, width, height, 700, false, projection, viewMatrix);
		

		
	}
	
	
}
