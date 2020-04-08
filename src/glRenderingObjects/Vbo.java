package glRenderingObjects;

import org.lwjgl.opengl.GL20;

public abstract class Vbo {
	
	protected int vboID;
	protected int vertexCount;
	protected int size;
	protected int totalFloats;
	
	protected Vbo() {
		this.vboID = GL20.glGenBuffers();
	}
	
	protected abstract void storeData();
	
	public int getVboID() {
		return vboID;
	}
}
