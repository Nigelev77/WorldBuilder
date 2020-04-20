package glRenderingObjects;

import org.lwjgl.opengl.GL30;

public class TerrainVao extends Vao{
	
	private int vertexCount;
	private TerrainVbo vbo;
	private int[] vboIDs;
	
	public TerrainVao(int[] indices, int[] dimensions, float[] data) {
		super();
		GL30.glBindVertexArray(vaoID);
		vbo = new TerrainVbo(indices, dimensions, data);
		vertexCount = vbo.getVertexCount();
		vboIDs = vbo.getVboIDs();
		bufferData(dimensions);
		enableArrays(data.length);
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
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboIDs[0]);
		GL30.glVertexAttribPointer(0, 2, GL30.GL_FLOAT, false, 0, 0);
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboIDs[1]);
		GL30.glVertexAttribPointer(1, 1, GL30.GL_FLOAT, false, 0, 0);
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboIDs[2]);
		int offset = 0;
		for(int i = 0;i<dimensions.length;i++) {
			GL30.glVertexAttribPointer(i+2, dimensions[i], GL30.GL_FLOAT, false, vbo.getSize()*FLOAT_SIZE, offset*FLOAT_SIZE);
			offset+=dimensions[i];
		}
		GL30.glBindVertexArray(0);
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
	}
	
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	public int getVaoID() {
		return vaoID;
	}
}
