package glRenderingObjects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class Vbo {
	
	private int vboID;
	private int indexID;
	private FloatBuffer masterBuffer;
	private IntBuffer indexBuffer;
	private int vertexCount;
	private int size;
	private int totalFloats;
	private static final int FLOAT = 4;
	
	public Vbo(int[] indices, int[] dimensions, float[]...data) {
		
		int length = 0;
		for(float[] arr:data) {
			length+=arr.length;
		}
		
		
		masterBuffer = BufferUtils.createFloatBuffer(length);
		indexBuffer = BufferUtils.createIntBuffer(indices.length);
		vertexCount = indices.length;
		findTotalFloats(data);
		findSizeOffset(dimensions);
		storeIntData(indices);
		int[] elements = generateAttributePointers(data);
		storeInterleavedData(dimensions, elements, data);
		ObjectHandler.addVbo(this);
	}
	
	private void storeIntData(int[] indices) {
		indexBuffer.put(indices);
		indexBuffer.flip();
		indexID = GL20.glGenBuffers();
		GL20.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexID);
		GL20.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL15.GL_STATIC_DRAW);
	}
	
	private void storeInterleavedData(int[] dimensions, int[] elements, float[]... data) {
		float[] interleavedData = new float[vertexCount];
		int pointer = 0;
		for(int vertex = 0; vertex<vertexCount;vertex++) {
			for(int attributeList = 0; attributeList<data.length;attributeList++) {
				for(int k = 0; k<dimensions[attributeList]; k++) {
					interleavedData[pointer++] = data[attributeList][elements[attributeList]++];
				}
			}
		}
		masterBuffer.put(interleavedData);
		masterBuffer.flip();
		vboID = GL20.glGenBuffers();
		GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		GL20.glBufferData(GL15.GL_ARRAY_BUFFER, masterBuffer, GL15.GL_STATIC_DRAW);
		GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void findSizeOffset(int[] dimensions) {
		for(int i:dimensions) {
			size+=i;
		}
		size*=FLOAT;
	}
	
	private void findTotalFloats(float[]...data) {
		for(float[] arr:data) {
			totalFloats+=arr.length;
		}
	}
	
	private int[] generateAttributePointers(float[]...data) {
		int[] elements = new int[data.length];
		for(int i = 0; i<elements.length; i++) {
			elements[i] = 0;
		}
		return elements;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getTotalFloat() {
		return totalFloats;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	public int getVboID() {
		return vboID;
	}
	
}
