package glRenderingObjects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

public class TerrainVbo extends Vbo{
	
	private int indexID;
	private int xzID;
	private int yID;
	private int otherID;
	private FloatBuffer xzBuffer;
	private FloatBuffer yBuffer;
	private FloatBuffer otherBuffer;
	private IntBuffer indexBuffer;
	private int vertexCount;
	private int size;
	private int totalFloats;
	
	
	private int[] dimensions;
	private float[][] data;
	
	
	public TerrainVbo(int[] indices, int[] dimensions, float[]... data) {
		super();
		this.dimensions = dimensions;
		this.data = new float[data.length][];
		for(int i = 0; i<data.length;i++) {
			this.data[i] = data[i];
		}
		int xzLength = data[0].length;
		int yLength = data[1].length;
		int otherLength = 0;
		for(int i = 2; i<data.length;i++) {
			otherLength+=data[i].length;
		}
		findSizeOffset(dimensions);
		indexBuffer = BufferUtils.createIntBuffer(vertexCount);
		indexBuffer.put(indices);
		indexBuffer.flip();
		xzBuffer = BufferUtils.createFloatBuffer(xzLength);
		yBuffer = BufferUtils.createFloatBuffer(yLength);
		otherBuffer = BufferUtils.createFloatBuffer(otherLength);
		vertexCount = indices.length;
		storeData();
		ObjectHandler.addVbo(this);
	}
	
	
	private void findSizeOffset(int[] dimensions) {
		for(int i:dimensions) {
			size+=i;
		}
	}

	@Override
	protected void storeData() {
		createIndexBuffer();
		storeXZBuffer();
		storeYBuffer();
		storeOtherBuffer();
	}
	
	private void storeXZBuffer() {
		xzBuffer.put(data[0]);
		xzBuffer.flip();
		xzID = bindAndUpdateBuffer(xzBuffer);
	}
	
	private void storeYBuffer() {
		yBuffer.put(data[1]);
		yBuffer.flip();
		yID = bindAndUpdateBuffer(yBuffer);
	}
	
	private void storeOtherBuffer() {
		int[] elements = new int[data.length-2];
		for(int i = 0; i<elements.length;i++) {
			elements[i] = 0;
		}
		int totalLength = otherBuffer.capacity();
		float[] interleavedData =new float[totalLength];
		int pointer = 0;
		int totalPrimitives = 0;
		for(int i:dimensions) {
			totalPrimitives+=i;
		}
		for(int vertex = 0; vertex<totalLength/totalPrimitives;vertex++) {
			for(int attributeList = 2; attributeList<data.length;attributeList++) {
				for(int k = 0;k<dimensions[attributeList-2];k++) {
					interleavedData[pointer++] = data[attributeList][elements[attributeList-2]++];
				}
			}
		}
		otherBuffer.put(interleavedData);
		otherBuffer.flip();
		otherID = bindAndUpdateBuffer(otherBuffer);
	}
	
	private void createIndexBuffer() {
		indexID = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, indexID);
		GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL30.GL_STATIC_DRAW);
	}
	
	private int bindAndUpdateBuffer(FloatBuffer buffer) {
		int id = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, id);
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, buffer, GL30.GL_STATIC_DRAW);
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
		return id;
	}
	
	protected int getSize() {
		return size;
	}
	protected int getTotalFloat() {
		return totalFloats;
	}
	protected int getVertexCount() {
		return vertexCount;
	}
	protected int[] getVboIDs() {
		return new int[] {
				xzID,
				yID,
				otherID
		};
	}
}
