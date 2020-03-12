package models;

import glRenderingObjects.Vao;

public class StaticModel {
	
	private Vao vao;
	private String name;
	
	public StaticModel(Vao vao, String name) {
		this.vao = vao;
		this.name = name;
	}
	
	public int getVaoId() {
		return vao.getVaoID();
	}
	
	public int getVertexCount() {
		return vao.getVertexCount();
	}

	public String getName() {
		return name;
	}
	
	
	
}
