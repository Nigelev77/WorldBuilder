package glRenderingObjects;

import org.lwjgl.opengl.GL30;

public class Vao {
	
	private int vaoID;
	private int vertexCount;
	private Vbo vbo;
	
	public Vao() {
		vaoID = GL30.glGenVertexArrays();
		ObjectHandler.addVao(this);
	}
	
	public void storeData(int[] indices, int[] dimensions, float[]...data) {
		GL30.glBindVertexArray(vaoID);
		vbo = new Vbo(indices, dimensions, data);
		GL30.glBindVertexArray(0);
		vertexCount = vbo.getVertexCount();
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	public int getVaoID() {
		return vaoID;
	}
}
