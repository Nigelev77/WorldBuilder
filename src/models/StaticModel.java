package models;

import glRenderingObjects.ModelVao;

public class StaticModel {
	
	private ModelVao modelVao;
	private String name;
	
	public StaticModel(ModelVao modelVao, String name) {
		this.modelVao = modelVao;
		this.name = name;
	}
	
	public int getVaoId() {
		return modelVao.getVaoID();
	}
	
	public int getVertexCount() {
		return modelVao.getVertexCount();
	}

	public String getName() {
		return name;
	}
	
	
	
}
