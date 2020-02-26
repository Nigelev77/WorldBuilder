package models;

import glRenderingObjects.Vao;

public class StaticModel {
	
	private Vao vao;
	
	public StaticModel(Vao vao) {
		this.vao = vao;
	}
	
	public int getVaoId() {
		return vao.getVaoID();
	}
	
	public int getVertexCount() {
		return vao.getVertexCount();
	}
	
}
