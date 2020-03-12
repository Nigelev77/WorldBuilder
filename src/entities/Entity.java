package entities;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import utils.Maths;

public abstract class Entity {
	
	
	public Entity(Vector3f position, Vector3f rotation, float scale) {
		this.position=position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	protected Vector3f position;
	protected Vector3f rotation;
	protected float scale;
	
	
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public Vector3f getRotation() {
		return rotation;
	}
	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public Matrix4f getTransformMatrix() {
		return Maths.createTransformMatrix(position, rotation.x, rotation.y, rotation.z, scale);
	}
	
	
	
}
