package glRenderingObjects;

import org.lwjgl.opengl.GL30;

public abstract class Vao {
	
	protected int vaoID;
	private int vertexCount;
	
	protected static int FLOAT_SIZE = 4;

	public Vao() {
		vaoID = GL30.glGenVertexArrays();
		ObjectHandler.addVao(this);
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	

	
}
