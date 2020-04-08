package glRenderingObjects;

import org.lwjgl.opengl.GL30;

public abstract class Vao {
	
	protected int vaoID;
	private int vertexCount;
	
	private static int FLOAT_SIZE = 4;

	public Vao() {
		vaoID = GL30.glGenVertexArrays();
		ObjectHandler.addVao(this);
	}

	protected int getVaoID() {
		return vaoID;
	}

	protected int getVertexCount() {
		return vertexCount;
	}

	

	
}
