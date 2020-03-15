package selectors;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;


public class MouseSelector {
	
	
	private Vector4f direction = new Vector4f(0,0,-1f, 1f);
	private Vector4f currentRay = new Vector4f().zero();
	
	private Matrix4f projectionMatrix;
	

	public void update(Matrix4f viewMatrix) {
		if(projectionMatrix==null) {
			return;
		}
		viewMatrix.invert();
		viewMatrix.transform(direction, currentRay);
		currentRay.normalize();
		System.out.println(currentRay.toString());
		
	}
	
	public void setProjection(Matrix4f project) {
		this.projectionMatrix = new Matrix4f(project).invert();
		direction = projectionMatrix.transform(direction);
		direction.z = -1f;
		direction.w = 0f;
	}
	

}
