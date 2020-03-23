package utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import display.WindowManager;
import player.Camera;

public class Maths {
	public static final Vector3f posX = new Vector3f(1,0,0);
	public static final Vector3f posY = new Vector3f(0,1,0);
	public static final Vector3f posZ = new Vector3f(0,0,1);
	public static final float FOV = 60f;
	public static final float NEAR_PLANE = 0.5f;
	public static final float FAR_PLANE = 1500f;
	
	
	public static void setProjectionAndView(Matrix4f viewMatrix, Matrix4f projection, Camera camera) {
		float x_width = (float) (NEAR_PLANE*Math.tan(Math.toRadians(FOV/2)));
		float y_width = x_width/WindowManager.aspectRatio;
		Vector3f nearPlane = new Vector3f(-x_width+camera.getPosition().x, -y_width+camera.getPosition().y, -NEAR_PLANE+camera.getPosition().z);
		nearPlane.rotateX((float) Math.toRadians(camera.getRotations().x));
		nearPlane.rotateY((float) Math.toRadians(camera.getRotations().y));
		Vector3f width = new Vector3f(x_width*2, 0,0);
		Vector3f height = new Vector3f(0,y_width*2, 0);
		
		Matrix4f.projViewFromRectangle(camera.getPosition(), nearPlane, width, height, 700, false, projection, viewMatrix);
		
	}
	
	public static void setProjectionAndViewTwo(Matrix4f viewMatrix, Matrix4f projection, Camera camera) {
		float aspectRatio = WindowManager.aspectRatio;
		projection = new Matrix4f().setPerspective((float) Math.toRadians(FOV), aspectRatio, NEAR_PLANE, FAR_PLANE);

		
		Vector3f cameraPos = camera.getPosition();
		Vector3f rotation = camera.getRotations();
		viewMatrix.identity();
		viewMatrix.rotate((float) Math.toRadians(rotation.x), new Vector3f(1,0,0))
			.rotate((float) Math.toRadians(rotation.y), new Vector3f(0,1,0))
			.rotate((float) Math.toRadians(rotation.z), new Vector3f(0,0,1));
		viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
	}
	
	public static void setProjectionMatrix(Matrix4f projection) {
		float aspectRatio = WindowManager.aspectRatio;
		projection.setPerspective((float) Math.toRadians(FOV), aspectRatio, NEAR_PLANE, FAR_PLANE);
	}
	
	public static Matrix4f setViewMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		Vector3f cameraPos = camera.getPosition();
		Vector3f rotation = camera.getRotations();
		viewMatrix.identity();
		viewMatrix.rotate((float) Math.toRadians(rotation.x), new Vector3f(1,0,0))
			.rotate((float) Math.toRadians(rotation.y), new Vector3f(0,1,0))
			.rotate((float) Math.toRadians(rotation.z), new Vector3f(0,0,1));
		viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		
		return viewMatrix;
		
	}
	
	public static Matrix4f createTransformMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f().identity();
		matrix.translate(translation);
		matrix.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0));
		matrix.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0));
		matrix.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1));
		matrix.scale(scale);
		return matrix;
	}
	
	
	
}
