package glRenderingObjects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Vbo {
	
	private int vboID;
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
	}
	
	private void storeIntData(int[] indices) {
		indexBuffer.put(indices);
		indexBuffer.flip();
	}
	
	private void storeInterleavedData(int[] dimensions, float[]... data) {
		float[] interleavedData = new float[vertexCount];
		int pointer = 0;
		for(int i = 0; i<vertexCount;i++) {
			for(float[] attribute:data) {
				//NEED TO IMPLEMENT THIS
			}
		}
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
	
	
}
