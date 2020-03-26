package entities;

import org.joml.Vector3f;

import models.StaticModel;

public class StaticEntity extends Entity{
	

	private StaticModel model;
	private int vertexCount;
	private int vaoID;
	
	public StaticEntity(Vector3f position, Vector3f rotation, float scale, StaticModel model) {
		super(position, rotation, scale);
		this.model = model;
		this.vertexCount = model.getVertexCount();
		this.vaoID = model.getVaoId();
		
	}
	
	public int getVertexCount() {
		return vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}
	public void move(Vector3f translation) {
		position.add(translation);
	}
	
}
