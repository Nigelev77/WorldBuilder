package glRenderingObjects;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Vao {
	
	private int vaoID;
	private int vertexCount;
	private Vbo vbo;
	
	private static int FLOAT_SIZE = 4;
	
	public Vao() {
		vaoID = GL30.glGenVertexArrays();
		ObjectHandler.addVao(this);
	}
	
	public void storeData(int[] indices, int[] dimensions, float[]...data) {
		GL30.glBindVertexArray(vaoID);
		vbo = new Vbo(indices, dimensions, data);
		vertexCount = vbo.getVertexCount();
		bufferData(dimensions);
		enableArrays(data.length);
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	public int getVaoID() {
		return vaoID;
	}
	
	private void enableArrays(int dataLength) {
		GL30.glBindVertexArray(vaoID);
		for(int i =0;i<dataLength;i++) {
			GL30.glEnableVertexAttribArray(i);
		}
		GL30.glBindVertexArray(0);
	}
	
	private void bufferData(int[] dimensions) {
		GL30.glBindVertexArray(vaoID);
		GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo.getVboID());
		int offset = 0;
		for(int i = 0;i<dimensions.length;i++) {
			GL20.glVertexAttribPointer(i, dimensions[i], GL11.GL_FLOAT, false, vbo.getSize()*Vao.FLOAT_SIZE, offset*Vao.FLOAT_SIZE);
			offset+=dimensions[i];
		}
		GL30.glBindVertexArray(0);
		GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	/*
	 * Need to update this so that I can store in the masterbuffer whenever the vertexBuffer is updated ONLY
	 */
//	public void storeAttributeList(int index, float[] data) {
//		GL30.glBindVertexArray(vaoID);
//		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo.getVboID());
//		GL30.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, vbo.getSize(), 0);
//	}
}
